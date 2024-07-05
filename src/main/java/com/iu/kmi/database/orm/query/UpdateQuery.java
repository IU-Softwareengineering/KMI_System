package com.iu.kmi.database.orm.query;

import com.iu.kmi.database.DatabaseConnection;
import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UpdateQuery<T> extends BaseQuery<T> {
    private final Object entity;
    private final Map<String, Object> compositeId;

    public UpdateQuery(Class<T> type, Object entity) {
        super(type);
        this.entity = entity;
        this.compositeId = null;
    }

    public UpdateQuery(Class<T> type, Object entity, Map<String, Object> compositeId) {
        super(type);
        this.entity = entity;
        this.compositeId = compositeId;
    }

    @Override
    protected String buildSql() {
        String tableName = getTableName();
        Field[] fields = type.getDeclaredFields();
        StringBuilder setClause = new StringBuilder();
        StringBuilder whereClause = new StringBuilder(" WHERE ");

        for (Field field : fields) {
            field.setAccessible(true);
                try {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Id.class)){
                            if(compositeId == null){
                                whereClause.append(getColumnName(field)).append(" = ? AND ");
                            }
                        }else if (field.isAnnotationPresent(Column.class)) {
                            setClause.append(getColumnName(field)).append(" = ?,");
                        } else if (field.isAnnotationPresent(JoinColumn.class)) {
                            JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                            Object relatedEntity = field.get(entity);
                            if (relatedEntity != null) {
                                UpdateQuery<?> updateQuery = new UpdateQuery<>(field.getType(), relatedEntity);
                                updateQuery.save();
                                Field idField = getIdField(relatedEntity.getClass());
                                idField.setAccessible(true);
                                Object joinValue = idField.get(relatedEntity);

                                setClause.append(joinColumn.name()).append(" = ?,");
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        }

        setClause.setLength(setClause.length() - 1);
        if (compositeId != null) {
            for (Map.Entry<String, Object> entry : compositeId.entrySet()) {
                whereClause.append(entry.getKey()).append(" = ? AND ");
            }
            whereClause.setLength(whereClause.length() - 5);
        } else {
            whereClause.setLength(whereClause.length() - 5);
        }

        return "UPDATE " + tableName + " SET " + setClause.toString() + whereClause.toString();
    }

    private Field getIdField(Class<?> relatedClass) {
        for (Field field : relatedClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new RuntimeException("No ID field found in related class: " + relatedClass.getName());
    }

    @Override
    public int save() throws SQLException {
        String sql = buildSql();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Field[] fields = type.getDeclaredFields();
            int index = 1;
            Object idValue = null;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            pstmt.setObject(index++, field.get(entity));
                        } else if (field.isAnnotationPresent(JoinColumn.class)) {
                            JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                            Field relatedIdField = getIdField(field.get(entity).getClass());
                            relatedIdField.setAccessible(true);
                            Object joinValue = relatedIdField.get(field.get(entity));
                            pstmt.setObject(index++, joinValue);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (compositeId != null) {
                for (Object value : compositeId.values()) {
                    pstmt.setObject(index++, value);
                }
            } else {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Id.class)) {
                        idValue = field.get(entity);
                        break;
                    }
                }
                if (idValue == null) {
                    throw new IllegalStateException("No ID value found");
                }
                pstmt.setObject(index, idValue);
            }
            return pstmt.executeUpdate();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

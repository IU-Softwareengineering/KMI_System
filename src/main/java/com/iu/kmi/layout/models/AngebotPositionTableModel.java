package com.iu.kmi.layout.models;

import com.iu.kmi.entities.*;
import com.iu.kmi.repositories.MaterialRepository;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AngebotPositionTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nummer", "ArtikelNr", "Name", "Einzelpreis", "Menge", "Gesamtpreis"};
    // initial positions
    private List<PositionTableRow> positions;

    private MaterialRepository materialRepository;

    public AngebotPositionTableModel(MaterialRepository materialRepository) {
        this.positions = new ArrayList<>();
        this.materialRepository = materialRepository;
    }

    @Override
    public int getRowCount() {
        return positions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PositionTableRow row = positions.get(rowIndex);
        AngebotsPosition position = row.getPosition();
        Material material = position.getArtikelNr();

        return switch (columnIndex) {
            case 0 -> position.getAngebotspositionNr();
            case 1 -> (material == null) ? "" : material.getArtikelNr();
            case 2 -> (material == null) ? "" :  material.getName();
            case 3 -> (material == null || material.getArtikelNr() == null) ? "" : material.getVerkaufsPreis();
            case 4 -> position.getMenge();
            case 5 ->{
                if (position.getArtikelNr() == null) {
                    yield "";
                }
                yield (new BigDecimal(position.getMenge()).multiply(position.getArtikelNr().getVerkaufsPreis())).toString();

            }
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PositionTableRow row = positions.get(rowIndex);
        AngebotsPosition position = row.getPosition();
        if (columnIndex == 3 || columnIndex == 5) {
            return;
        }

        switch (columnIndex) {
            case 0:
                position.setAngebotspositionNr((String) aValue);
                break;
            case 1:
                try {
                    fillColumnWithMaterial(rowIndex, (String) aValue);
                    this.fireTableDataChanged();
                } catch (ReflectiveOperationException | SQLException e) {

                    throw new RuntimeException(e);
                }
                break;
            case 4:
                position.setMenge(Integer.parseInt((String) aValue));
                // update gesamtpreis column
                setValueAt(0, rowIndex, 5);
        }

        ModelState state = row.getState();
        if (state != ModelState.Created || state != ModelState.Modified) {
            row.setState(ModelState.Modified);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return switch(columnIndex) {
            case 2, 3, 5 -> false;
            default -> true;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public void deletePositionByRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < positions.size()) {
            this.positions.get(rowIndex).setState(ModelState.Deleted);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    public void addPosition(AngebotsPosition position) {
        this.positions.add(new PositionTableRow(position, ModelState.Unchanged));
        fireTableRowsInserted(positions.size() - 1, positions.size() - 1);
    }

    public void addPosition() {
        AngebotsPosition newPosition = new AngebotsPosition();
        this.positions.add(new PositionTableRow(newPosition, ModelState.Created));
        fireTableRowsInserted(positions.size() - 1, positions.size() - 1);
    }

    public void clearPositions() {
       this.positions.clear();
    }

    public void addFromAnfragePosition(AnfragePosition anfragePosition) {
        AngebotsPosition position = new AngebotsPosition();
        position.setArtikelNr(anfragePosition.getArtikelNr());
        position.setMenge(anfragePosition.getMenge());

        this.positions.add(new PositionTableRow(position, ModelState.Created));
        fireTableRowsInserted(positions.size() - 1, positions.size() - 1);
    }

    public void fillColumnWithMaterial(Integer row, String artikelNr) throws ReflectiveOperationException, SQLException {
       Material material = this.materialRepository.findById(artikelNr).findOne();
       if (material != null) {
           this.positions.get(row).getPosition().setArtikelNr(material);

           // display row anpassen
           setValueAt(material.getName(), row, 2);
           setValueAt(material.getVerkaufsPreis(), row, 3);
       }
    }

    public List<PositionTableRow> getPositions() {
        return this.positions;
    }
}

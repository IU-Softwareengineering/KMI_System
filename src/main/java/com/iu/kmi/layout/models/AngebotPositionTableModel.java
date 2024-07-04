package com.iu.kmi.layout.models;

import com.iu.kmi.entities.*;
import com.iu.kmi.repositories.KonditionRepository;
import com.iu.kmi.repositories.MaterialRepository;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AngebotPositionTableModel extends AbstractTableModel {
//    private final String[] columnNames = {"Nummer", "ArtikelNr", "Name", "Einzelpreis", "Menge", "Gesamtpreis"};
    private final String[] columnNames = {"Nummer", "ArtikelNr", "Name", "Einzelpreis", "Menge", "Gesamtpreis", "Rabatt %", "Rabatt"};
    private List<PositionTableRow> positions;

    private MaterialRepository materialRepository;
    private Kondition kondition;

    private BigDecimal konditionRabatt;

    public AngebotPositionTableModel(MaterialRepository materialRepository) {
        this.positions = new ArrayList<>();
        this.materialRepository = materialRepository;
        this.konditionRabatt = new BigDecimal(0);
    }

    public void setKondition(Kondition kondition) {
        if (kondition == null) {
            return;
        }
        this.kondition = kondition;
        this.konditionRabatt = kondition.getRabatt();
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

        if (row.getState() == ModelState.Deleted) {
            return "<D>";
        }

        return switch (columnIndex) {
            case 0 -> position.getAngebotspositionNr();
            case 1 -> (material == null) ? "" : material.getArtikelNr();
            case 2 -> (material == null) ? "" :  material.getName();
            case 3 -> (material == null || material.getArtikelNr() == null) ? "" : material.getVerkaufsPreis();
            case 4 -> {
                if (kondition != null && position.getArtikelNr() != null) {
                    BigDecimal rabattValue = calculateRabatt(position);
                    setValueAt(rabattValue.toString(), rowIndex, 7);
                    setValueAt(konditionRabatt.toString(), rowIndex, 6);
                }

                yield position.getMenge();
            }
            case 5 -> {
                if (position.getArtikelNr() == null) {
                    yield "";
                }
                yield (new BigDecimal(position.getMenge()).multiply(position.getArtikelNr().getVerkaufsPreis())).toString();

            }
            case 6 -> konditionRabatt.toString() + "%";
            case 7 ->  {
                if (position.getArtikelNr() == null || kondition == null) {
                    yield "";
                }
                yield calculateRabatt(position);
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
        if (state != ModelState.Created) {
            row.setState(ModelState.Modified);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        ModelState state = positions.get(rowIndex).getState();
        if (state == ModelState.Deleted) {
            return false;
        }
        return switch(columnIndex) {
            case 0 -> {
                yield state == ModelState.Created;
            }
            case 2, 3, 5, 6, 7 -> false;
            default -> true;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public void deletePositionByRow(int rowIndex) {
        System.out.println("deleting row at " + rowIndex);
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

    public BigDecimal calculateRabatt(AngebotsPosition position) {
        BigDecimal rabatt = kondition.getRabatt().divide(new BigDecimal(100));
        BigDecimal gesamtPreis = new BigDecimal(position.getMenge()).multiply(position.getArtikelNr().getVerkaufsPreis());
        return rabatt.multiply(gesamtPreis);
    }

    public List<PositionTableRow> getPositions() {
        return this.positions;
    }
}

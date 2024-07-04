package com.iu.kmi.layout.models;

import com.iu.kmi.entities.*;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class AngebotPositionTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nummer", "ArtikelNr", "Name", "Einzelpreis", "Menge", "Gesamtpreis"};
    // initial positions
    private List<PositionTableRow> positions;

    public AngebotPositionTableModel() {
        this.positions = new ArrayList<>();
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
        Material material = new Material(); // TODO: load this from MaterialRepository
        if (columnIndex == 3 || columnIndex == 5) {
            return;
        }
        switch (columnIndex) {
            case 0:
                //person.setId((Integer) aValue);
                position.setAngebotspositionNr((String) aValue);
                break;
            case 1:
                // artikel nr
//                Material material = new Material(); // TODO: load this from MaterialRepository
//                position.setArtikelNr(material);
//                position.setEinzelpreis(material.getVerkaufsPreis().doubleValue());
                break;
            case 2:
//                Material material = new Material(); // TODO: load this from MaterialRepository
//                position.getArtikelNr().setName((String) aValue);
                break;
            case 4:
                position.setMenge(Integer.parseInt((String) aValue));
                // update gesamtpreis column
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
            case 3, 5 -> false;
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
        position.setEinzelpreis(anfragePosition.getArtikelNr().getVerkaufsPreis().doubleValue());
        position.setMenge(anfragePosition.getMenge());

        this.positions.add(new PositionTableRow(position, ModelState.Created));
        fireTableRowsInserted(positions.size() - 1, positions.size() - 1);
    }

    public List<PositionTableRow> getPositions() {
        return this.positions;
    }

    /*
    public static AngebotPositionTableModel fromAnfragePositionen(List<AnfragePosition> anfragePositions) {
        AngebotPositionTableModel model = new AngebotPositionTableModel();
        anfragePositions.forEach(model::addFromAnfragePosition);
        return model;
    }
     */
}

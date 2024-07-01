package com.iu.kmi.layout.models;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;
import com.iu.kmi.entities.AnfragePosition;
import com.iu.kmi.entities.Angebot;
import com.iu.kmi.entities.AngebotsPosition;
import com.iu.kmi.entities.Material;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PositionTableModel {
    public int nummer;
    public String name;
    public BigDecimal einzelpreis;
    public Integer menge;
    public Double gesamtpreis;

    public PositionTableModel(){
        this.nummer = 123;
        this.name = "Test1";
        this.menge = 5;
        this.gesamtpreis = 4.0;
    }

    public static Object[] toArray(PositionTableModel positionModel) {
        return new Object[] {positionModel.nummer, positionModel.name, positionModel.einzelpreis, positionModel.menge, positionModel.gesamtpreis};
    }

    public static PositionTableModel convert(AngebotsPosition[] angebotsPosition){
        AngebotsPosition angebotsPosition1 = angebotsPosition[0];
        PositionTableModel positionTableModel = new PositionTableModel();
        positionTableModel.name = angebotsPosition1.getArtikelNr().getName();
        positionTableModel.einzelpreis = angebotsPosition1.getArtikelNr().getVerkaufsPreis();
        positionTableModel.menge = angebotsPosition.length;
        positionTableModel.gesamtpreis = angebotsPosition1.getEinzelpreis() * angebotsPosition.length;
        return positionTableModel;
    }

    public static PositionTableModel convert(List<AnfragePosition> anfragePosition){
        AnfragePosition anfragePosition1 = anfragePosition.get(0);
        PositionTableModel positionTableModel = new PositionTableModel();
        positionTableModel.name = anfragePosition1.getArtikelNr().getName();
        positionTableModel.einzelpreis = anfragePosition1.getArtikelNr().getVerkaufsPreis();
        positionTableModel.menge = anfragePosition.size();
        positionTableModel.gesamtpreis = anfragePosition1.getArtikelNr().getVerkaufsPreis().doubleValue() * anfragePosition.size();
        return positionTableModel;
    }
}




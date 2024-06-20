package com.iu.kmi.layout.models;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;
import com.iu.kmi.entities.Angebot;
import com.iu.kmi.entities.AngebotsPosition;
import com.iu.kmi.entities.Material;

import java.util.Arrays;

public class PositionTableModel {
    public int nummer;
    public String name;
    public Double einzelpreis;
    public Integer menge;
    public Double gesamtpreis;

    public PositionTableModel(){
        this.nummer = 123;
        this.name = "Test1";
        this.menge = 5;
        this.einzelpreis = 1.2;
        this.gesamtpreis = 4.0;
    }

    public static Object[] toArray(PositionTableModel positionModel) {
        return new Object[] {positionModel.nummer, positionModel.name, positionModel.einzelpreis, positionModel.menge, positionModel.gesamtpreis};
    }

    public static PositionTableModel convert(AngebotsPosition[] angebotsPosition){
        AngebotsPosition angebotsPosition1 = angebotsPosition[0];
        PositionTableModel positionTableModel = new PositionTableModel();
        positionTableModel.name = angebotsPosition1.getArtikelNr().getName();
        positionTableModel.einzelpreis = angebotsPosition1.getEinzelpreis();
        positionTableModel.menge = angebotsPosition.length;
        positionTableModel.gesamtpreis = angebotsPosition1.getEinzelpreis() * angebotsPosition.length;
        return positionTableModel;
    }
}




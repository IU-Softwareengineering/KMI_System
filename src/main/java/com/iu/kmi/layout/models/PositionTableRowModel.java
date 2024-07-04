package com.iu.kmi.layout.models;

import com.iu.kmi.entities.AnfragePosition;
import com.iu.kmi.entities.AngebotsPosition;
import com.iu.kmi.entities.Material;

import java.math.BigDecimal;

public class PositionTableRowModel {
    public String nummer;
    public String artikelNr;
    public String name;
    public BigDecimal einzelpreis;
    public Integer menge;
    public BigDecimal gesamtpreis;

    private ModelState state;

    public ModelState getState() {
        return state;
    }

    public void setState(ModelState state) {
        this.state = state;
    }

    public PositionTableRowModel() {}

    public PositionTableRowModel(String nummer, String artikelNr, String name, BigDecimal einzelpreis, Integer menge, BigDecimal gesamtpreis) {
        this.nummer = nummer;
        this.artikelNr = artikelNr;
        this.name = name;
        this.einzelpreis = einzelpreis;
        this.menge = menge;
        this.gesamtpreis = gesamtpreis;
    }

    public static PositionTableRowModel fromAnfragePosition(AnfragePosition anfragePosition) {
        return new PositionTableRowModel(
                "",
                anfragePosition.getArtikelNr().getArtikelNr(),
                "",
                anfragePosition.getArtikelNr().getVerkaufsPreis(),
                anfragePosition.getMenge(),
                new BigDecimal(anfragePosition.getMenge()).multiply(anfragePosition.getArtikelNr().getVerkaufsPreis())
        );
    }

    public static AngebotsPosition toAngebotsPosition(PositionTableRowModel model, Material material) {
        return new AngebotsPosition(
                model.nummer,
                null, //angebotnr
                material, //<artikel_nr>",
                0.0, // (double) material.getVerkaufsPreis(), //einzelpreis
                model.menge
        );
    }
}




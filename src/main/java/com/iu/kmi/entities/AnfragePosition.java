package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

@Entity(tableName = "anfrageposition")
public class AnfragePosition {
    @Id(name = "anfrageposition_nr")
    private String anfragepositionNr;

    @JoinColumn(name = "kundenanfrage_nr", referencedColumnName = "kundenanfrage_nr")
    private Kundenanfrage kundenanfrageNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    public String getAnfragepositionNr() {
        return anfragepositionNr;
    }

    public void setAnfragepositionNr(String anfragepositionNr) {
        this.anfragepositionNr = anfragepositionNr;
    }

    public Kundenanfrage getKundenanfrageNr() {
        return kundenanfrageNr;
    }

    public void setKundenanfrageNr(Kundenanfrage kundeNr) {
        this.kundenanfrageNr = kundeNr;
    }

    public Material getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Material artikelNr) {
        this.artikelNr = artikelNr;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }

    public void setEinzelpreis(double einzelpreis) {
        this.einzelpreis = einzelpreis;
    }

}
package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "anfrageposition")
public class AnfragePosition {

    @Column(name = "anfrageposition_nr")
    private String anfragepositionNr;

    @Column(name = "kundenanfrage_nr")
    private String anfrageNr;

    @Column(name = "artikel_nr")
    private String artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    // Getter und Setter
    public String getAnfragepositionNr() {
        return anfragepositionNr;
    }

    public void setAnfragepositionNr(String anfragepositionNr) {
        this.anfragepositionNr = anfragepositionNr;
    }

    public String getAnfrageNr() {
        return anfrageNr;
    }

    public void setAnfrageNr(String anfrageNr) {
        this.anfrageNr = anfrageNr;
    }

    public String getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(String artikelNr) {
        this.artikelNr = artikelNr;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }

    public void setEinzelpreis(double einzelpreis) {
        this.einzelpreis = einzelpreis;
    }
}
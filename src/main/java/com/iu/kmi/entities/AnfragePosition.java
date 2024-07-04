package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "anfrageposition")
public class AnfragePosition {

    @Id(name = "anfrageposition_nr")
    private String anfragepositionNr;

    @Column(name = "kundenanfrage_nr")
    private String anfrageNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    @Column(name = "menge")
    private int menge;

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
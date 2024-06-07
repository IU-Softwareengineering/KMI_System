package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "auftragsposition")
public class AuftragsPosition {

    @Column(name = "auftragsposition_nr")
    private String auftragspositionNr;

    @Column(name = "auftrags_nr")
    private String auftragsNr;

    @Column(name = "artikel_nr")
    private String artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    // Getter und Setter
    public String getAuftragspositionNr() {
        return auftragspositionNr;
    }

    public void setAuftragspositionNr(String auftragspositionNr) {
        this.auftragspositionNr = auftragspositionNr;
    }

    public String getAuftragsNr() {
        return auftragsNr;
    }

    public void setAuftragsNr(String auftragsNr) {
        this.auftragsNr = auftragsNr;
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

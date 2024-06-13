package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "rechnungsposition")
public class RechnungsPosition {

    @Column(name = "rechnungsposition_nr")
    private String rechnungspositionNr;

    @Column(name = "rechnung_nr")
    private String rechnungNr;

    @Column(name = "artikel_nr")
    private String artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    // Getter und Setter
    public String getRechnungspositionNr() {
        return rechnungspositionNr;
    }

    public void setRechnungspositionNr(String rechnungspositionNr) {
        this.rechnungspositionNr = rechnungspositionNr;
    }

    public String getRechnungNr() {
        return rechnungNr;
    }

    public void setRechnungNr(String rechnungNr) {
        this.rechnungNr = rechnungNr;
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
package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "rechnungsposition")
public class RechnungsPosition {

    @Id(name = "rechnungsposition_nr")
    private String rechnungspositionNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Artikel artikel;

    @JoinColumn(name = "rechnung_nr", referencedColumnName = "rechnung_nr")
    private Rechnung rechnung;

    public RechnungsPosition() {

    }

    public RechnungsPosition(String rechnungspositionNr, Artikel artikel, Rechnung rechnung) {
        this.rechnungspositionNr = rechnungspositionNr;
        this.rechnung = rechnung;
        this.artikel = artikel;
    }

    // Getter und Setter
    public String getRechnungspositionNr() {
        return rechnungspositionNr;
    }

    public void setRechnungspositionNr(String rechnungspositionNr) {
        this.rechnungspositionNr = rechnungspositionNr;
    }

    public Rechnung getRechnung() {
        return rechnung;
    }

    public void setRechnung(Rechnung rechnung) {
        this.rechnung = rechnung;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }
}
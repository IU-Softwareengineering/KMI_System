package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;


@Entity(tableName = "rechnungsposition")
public class RechnungsPosition {

    @Id(name = "rechnungsposition_nr")
    private String rechnungspositionNr;

    @JoinColumn(name = "rechnung_nr", referencedColumnName = "rechnung_nr")
    private Rechnung rechnungNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "menge")
    private int menge;
    
    public RechnungsPosition() {}

    // Getter und Setter
    public String getRechnungspositionNr() {
        return rechnungspositionNr;
    }

    public void setRechnungspositionNr(String rechnungspositionNr) {
        this.rechnungspositionNr = rechnungspositionNr;
    }

    public Rechnung getRechnungNr() {
        return rechnungNr;
    }

    public void setRechnungNr(Rechnung rechnungNr) {
        this.rechnungNr = rechnungNr;
    }

    public Material getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Material artikelNr) {
        this.artikelNr = artikelNr;
    }
    
    
    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

}
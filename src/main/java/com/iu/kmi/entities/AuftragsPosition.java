package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

/**
 * @author Anthony, Florian
 */
@Entity(tableName = "auftragsposition")
public class AuftragsPosition {

    @Id(name = "auftragsposition_nr")
    private String auftragspositionNr;

    @JoinColumn(name = "auftrags_nr", referencedColumnName = "autrags_nr")
    private Auftrag auftragsNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    // Getter und Setter
    public String getAuftragspositionNr() {
        return auftragspositionNr;
    }

    public void setAuftragspositionNr(String auftragspositionNr) {
        this.auftragspositionNr = auftragspositionNr;
    }

    public Auftrag getAuftragsNr() {
        return auftragsNr;
    }

    public void setAuftragsNr(Auftrag auftragsNr) {
        this.auftragsNr = auftragsNr;
    }

    public Material getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Material artikelNr) {
        this.artikelNr = artikelNr;
    }

}
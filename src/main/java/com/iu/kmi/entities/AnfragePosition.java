package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

import java.math.BigDecimal;

@Entity(tableName = "anfrageposition")
public class AnfragePosition {

    @Id(name = "anfrageposition_nr")
    private String anfragepositionNr;

    @Column(name = "kundenanfrage_nr")
    private Kundenanfrage anfrageNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "einzelpreis")
    private BigDecimal einzelpreis;

    @Column(name = "menge")
    private int menge;

    // Getter und Setter
    public String getAnfragepositionNr() {
        return anfragepositionNr;
    }

    public void setAnfragepositionNr(String anfragepositionNr) {
        this.anfragepositionNr = anfragepositionNr;
    }

    public Kundenanfrage getAnfrageNr() {
        return anfrageNr;
    }

    public void setAnfrageNr(Kundenanfrage anfrageNr) {
        this.anfrageNr = anfrageNr;
    }

    public Material getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Material artikelNr) {
        this.artikelNr = artikelNr;
    }

    public BigDecimal getEinzelpreis() {
        return einzelpreis;
    }

    public void setEinzelpreis(BigDecimal einzelpreis) {
        this.einzelpreis = einzelpreis;
    }

    public int getMenge() { return menge; }

    public void setMenge(int menge) { this.menge = menge; }
}
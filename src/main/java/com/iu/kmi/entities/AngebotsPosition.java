package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "angebotsposition")
public class AngebotsPosition {
    @Id(name = "angebotsposition_nr")
    private String angebotspositionNr;

    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebotNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    @Column(name = "menge")
    private int menge;

    public AngebotsPosition() {
    }

    public AngebotsPosition(String angebotspositionNr, Angebot angebotNr, Material artikelNr, double einzelpreis, int menge) {
        this.angebotspositionNr = angebotspositionNr;
        this.angebotNr = angebotNr;
        this.artikelNr = artikelNr;
        this.einzelpreis = einzelpreis;
        this.menge = menge;
    }
    public String getAngebotspositionNr() {
        return angebotspositionNr;
    }

    public void setAngebotspositionNr(String angebotspositionNr) {
        this.angebotspositionNr = angebotspositionNr;
    }

    public Angebot getAngebotNr() {
        return angebotNr;
    }

    public void setAngebotNr(Angebot angebotNr) {
        this.angebotNr = angebotNr;
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

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    @Override
    public String toString() {
        return "AngebotsPosition{" +
                "angebotspositionNr='" + angebotspositionNr + '\'' +
                ", angebotNr=" + angebotNr +
                ", artikelNr=" + artikelNr +
                ", einzelpreis=" + einzelpreis +
                ", menge=" + menge +
                '}';
    }
}
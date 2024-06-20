package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "angebotsposition")
public class AngebotsPosition {

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

    @Id(name = "angebotsposition_nr")
    private String angebotspositionNr;

    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebotNr;

    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

}
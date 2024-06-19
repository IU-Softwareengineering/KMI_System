package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "angebotsposition")
public class AngebotsPosition {

    @Id(name = "angebotsposition_nr")
    private String angebotspositionNr;

    @Column(name = "angebot_nr")
    private String angebotNr;

    @Column(name = "artikel_nr")
    private String artikelNr;

    @Column(name = "einzelpreis")
    private double einzelpreis;

    // Getter und Setter
    public String getAngebotspositionNr() {
        return angebotspositionNr;
    }

    public void setAngebotspositionNr(String angebotspositionNr) {
        this.angebotspositionNr = angebotspositionNr;
    }

    public String getAngebotNr() {
        return angebotNr;
    }

    public void setAngebotNr(String angebotNr) {
        this.angebotNr = angebotNr;
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
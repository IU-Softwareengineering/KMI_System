package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "artikel")
public class Artikel {
    @Id(name = "artikel_nr")
    private String artikelNr;

    @Column(name = "name")
    private String name;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Column(name = "lieferant")
    private String lieferant;

    @Column(name = "einkaufspreis")
    private float einkaufsPreis;

    @Column(name = "verkaufspreis")
    private float verkaufsPreis;

    public Artikel() {

    }

    public Artikel(String artikelNr, String name, String beschreibung, String lieferant, float einkaufsPreis, float verkaufsPreis) {
        this.artikelNr = artikelNr;
        this.name = name;
        this.beschreibung = beschreibung;
        this.lieferant = lieferant;
        this.einkaufsPreis = einkaufsPreis;
        this.verkaufsPreis = verkaufsPreis;
    }

    // Getter und Setter
    public String getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(String artikelNr) {
        this.artikelNr = artikelNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getLieferant() {
        return lieferant;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public float getEinkaufsPreis() {
        return einkaufsPreis;
    }

    public void setEinkaufsPreis(float einkaufsPreis) {
        this.einkaufsPreis = einkaufsPreis;
    }

    public float getVerkaufsPreis() {
        return verkaufsPreis;
    }

    public void setVerkaufsPreis(float verkaufsPreis) {
        this.verkaufsPreis = verkaufsPreis;
    }
}
package com.iu.kmi.entities;

/**
 * @author Bludwell, Anthony
 */
public class Material {
    // Attribute
    private String artikelNr;
    private String name;
    private String beschreibung;
    private String lieferant;
    private float einkaufsPreis;
    private float verkaufsPreis;

    // Konstruktor
    public Material(String artikelNr, String name, String beschreibung, String lieferant, float einkaufsPreis, float verkaufsPreis) {
        this.artikelNr = artikelNr;
        this.name = name;
        this.beschreibung = beschreibung;
        this.lieferant = lieferant;
        this.einkaufsPreis = einkaufsPreis;
        this.verkaufsPreis = verkaufsPreis;
    }

    // Getter-Methoden
    public String getArtikelNr() {
        return artikelNr;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getLieferant() {
        return lieferant;
    }

    public float getEinkaufsPreis() {
        return einkaufsPreis;
    }

    public float getVerkaufsPreis() {
        return verkaufsPreis;
    }

    // Setter-Methoden
    public void setArtikelNr(String artikelNr) {
        this.artikelNr = artikelNr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public void setEinkaufsPreis(float einkaufsPreis) {
        this.einkaufsPreis = einkaufsPreis;
    }

    public void setVerkaufsPreis(float verkaufsPreis) {
        this.verkaufsPreis = verkaufsPreis;
    }

    // toString Method
    @Override
    public String toString() { //
        return "Material{" +
                "artikelNummer=" + artikelNr +
                ", name='" + name + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", lieferant='" + lieferant + '\'' +
                ", einkaufsPreis=" + einkaufsPreis +
                ", verkaufsPreis=" + verkaufsPreis +
                '}';
    }
}




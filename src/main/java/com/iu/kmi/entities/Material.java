package com.iu.kmi.entities;
public class Material {
    // Attribute
    private int artikelNummer;
    private String name;
    private String beschreibung;
    private String lieferant;
    private float einkaufsPreis;
    private float verkaufsPreis;

    // Konstruktor
    public Material(int artikelNummer, String name, String beschreibung, String lieferant, float einkaufsPreis, float verkaufsPreis) {
        this.artikelNummer = artikelNummer;
        this.name = name;
        this.beschreibung = beschreibung;
        this.lieferant = lieferant;
        this.einkaufsPreis = einkaufsPreis;
        this.verkaufsPreis = verkaufsPreis;
    }

    // Getter-Methoden
    public int getArtikelNummer() {
        return artikelNummer;
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
    public void setArtikelNummer(int artikelNummer) {
        this.artikelNummer = artikelNummer;
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
                "artikelNummer=" + artikelNummer +
                ", name='" + name + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", lieferant='" + lieferant + '\'' +
                ", einkaufsPreis=" + einkaufsPreis +
                ", verkaufsPreis=" + verkaufsPreis +
                '}';
    }
}




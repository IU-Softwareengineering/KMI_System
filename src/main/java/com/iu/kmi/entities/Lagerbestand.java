package com.iu.kmi.entities;

public class Lagerbestand {

    // Attribute
    private String artikelNummer;
    private String lagerNummer;
    private int menge;

    // Konstruktor
    public Lagerbestand(String artikelNummer, String lagerNummer, int menge) {
        this.artikelNummer = artikelNummer;
        this.lagerNummer = lagerNummer;
        this.menge = menge;
    }

    // Getter-Methoden
    public String getArtikelNummer() {
        return artikelNummer;
    }

    public String getLagerNummer() {
        return lagerNummer;
    }

    public int getMenge() {
        return menge;
    }

    // Setter-Methoden
    public void setArtikelNummer(String artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public void setLagerNummer(String lagerNummer) {
        this.lagerNummer = lagerNummer;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    @Override
    public String toString() {
        return "Lagerbestand{" +
                "artikelNummer='" + artikelNummer + '\'' +
                ", lagerNummer='" + lagerNummer + '\'' +
                ", menge=" + menge +
                '}';
    }
}
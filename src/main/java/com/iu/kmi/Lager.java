package com.iu.kmi;

public class Lager {

    // Attribute
    private int lagerNummer;
    private String name;
    private int adresse;

    // Konstruktor
    public Lager(int lagerNummer, String name, int adresse) {
        this.lagerNummer = lagerNummer;
        this.name = name;
        this.adresse = adresse;
    }


    // Getter-Methoden
    public int getLagerNummer() {
        return lagerNummer;
    }

    public String getName() {
        return name;
    }

    public int getAdresse() {
        return adresse;
    }


    // Setter-Methoden
    public void setLagerNummer(int lagerNummer) {
        this.lagerNummer = lagerNummer;
    }

    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Lager{" +
                "lagerNummer=" + lagerNummer +
                ", name='" + name + '\'' +
                ", adresse=" + adresse +
                '}';
    }

}
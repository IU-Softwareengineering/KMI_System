package com.iu.kmi.entities;

public class Lager {

    // Attribute
    private String lagerNummer;
    private String name;
    private int adresse;

    // Konstruktor
    public Lager(String lagerNummer, String name, int adresse) {
        this.lagerNummer = lagerNummer;
        this.name = name;
        this.adresse = adresse;
    }


    // Getter-Methoden
    public String getLagerNummer() {
        return lagerNummer;
    }

    public String getName() {
        return name;
    }

    public int getAdresse() {
        return adresse;
    }


    // Setter-Methoden
    public void setLagerNummer(String lagerNummer) {
        this.lagerNummer = lagerNummer;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(int adresse) {
        this.adresse = adresse;
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
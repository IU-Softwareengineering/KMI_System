package com.iu.kmi.entities;

public class Kundenanfrage {
    private String Id;
    private String Kunde;
    private String anfrageDatum;
    private String anfrageBeschreibung;
    private String prioritaet;
    private String status;

    // Konstruktor
    public Kundenanfrage(String Id, String Kunde, String anfrageDatum, String anfrageBeschreibung, String prioritaet, String status) {
        this.Id = Id;
        this.Kunde = Kunde;
        this.anfrageDatum = anfrageDatum;
        this.anfrageBeschreibung = anfrageBeschreibung;
        this.prioritaet = prioritaet;
        this.status = status;
    }

    // Getter- und Setter-Methoden
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getKunde() {
        return Kunde;
    }

    public void setKunde(String Kunde) {
        this.Kunde = Kunde;
    }

    public String getAnfrageDatum() {
        return anfrageDatum;
    }

    public void setAnfrageDatum(String anfrageDatum) {
        this.anfrageDatum = anfrageDatum;
    }

    public String getAnfrageBeschreibung() {
        return anfrageBeschreibung;
    }

    public void setAnfrageBeschreibung(String anfrageBeschreibung) {
        this.anfrageBeschreibung = anfrageBeschreibung;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Kunderequest{" +
                "Id=" + Id +
                ", Kunde=" + Kunde +
                ", anfrageDatum='" + anfrageDatum + '\'' +
                ", anfrageBeschreibung='" + anfrageBeschreibung + '\'' +
                ", prioritaet='" + prioritaet + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
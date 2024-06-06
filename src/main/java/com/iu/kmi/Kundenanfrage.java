package com.iu.kmi;

public class Kundenanfrage {
    private String id;
    private String kunde;
    private String anfragedatum;
    private String anfragebeschreibung;
    private String prioritaet;
    private String status;

    // Konstruktor
    public Kundenanfrage(String id, String kunde, String anfragedatum, String anfragebeschreibung, String prioritaet, String status) {
        this.id = id;
        this.kunde = kunde;
        this.anfragedatum = anfragedatum;
        this.anfragebeschreibung = anfragebeschreibung;
        this.prioritaet = prioritaet;
        this.status = status;
    }

    // Getter- und Setter-Methoden
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKunde() {
        return kunde;
    }

    public void setKunde(String kunde) {
        this.kunde = kunde;
    }

    public String getAnfragedatum() {
        return anfragedatum;
    }

    public void setAnfragedatum(String anfragedatum) {
        this.anfragedatum = anfragedatum;
    }

    public String getAnfragebeschreibung() {
        return anfragebeschreibung;
    }

    public void setAnfragebeschreibung(String anfragebeschreibung) {
        this.anfragebeschreibung = anfragebeschreibung;
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
        return "Kundenanfrage{" +
                "id=" + id +
                ", kunde=" + kunde +
                ", anfragedatum='" + anfragedatum + '\'' +
                ", anfragebeschreibung='" + anfragebeschreibung + '\'' +
                ", prioritaet='" + prioritaet + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
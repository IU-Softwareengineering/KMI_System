package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

public class Kundenanfrage {
    @Id(name = "kundenanfrage_nr")
    private String kundenanfrageNr;

    @JoinColumn(name = "kunde_nr", referencedColumnName = "kunde_nr")
    private Kunde kunde;

    @Column(name = "anfragedatum")
    private String anfrageDatum;

    @Column(name = "anfragebeschreibung")
    private String anfrageBeschreibung;

    @Column(name = "prioritaet")
    private String prioritaet;

    @Column(name = "status")
    private String status;

    // Konstruktor
    public Kundenanfrage(String kundenanfrageNr, Kunde kunde, String anfrageDatum, String anfrageBeschreibung, String prioritaet, String status) {
        this.kundenanfrageNr = kundenanfrageNr;
        this.kunde = kunde;
        this.anfrageDatum = anfrageDatum;
        this.anfrageBeschreibung = anfrageBeschreibung;
        this.prioritaet = prioritaet;
        this.status = status;
    }

    // Getter- und Setter-Methoden
    public String getKundenanfrageNr() {
        return kundenanfrageNr;
    }

    public void setKundenanfrageNr(String kundenanfrageNr) {
        this.kundenanfrageNr = kundenanfrageNr;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
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
}
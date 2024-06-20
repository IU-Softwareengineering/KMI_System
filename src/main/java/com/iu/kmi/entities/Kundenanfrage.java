package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "kundenanfrage")
public class Kundenanfrage {

    @Id(name = "kundenanfrage_nr")
    private String kundenanfrage_nr;
    @JoinColumn(name = "kunde_nr", referencedColumnName = "kunde_nr")
    private Kunde kunde_nr; 
    @Column(name = "anfrageDatum")
    private String anfrageDatum;
    @Column(name = "anfragebeschreibung")
    private String anfrageBeschreibung;
    @Column(name = "prioritaet")
    private String prioritaet;
    @Column(name = "status")
    private String status;

    // Konstruktor
    public Kundenanfrage(String kundenanfrage_nr, Kunde kunde_nr, String anfrageDatum, String anfrageBeschreibung, String prioritaet, String status) {
        this.kundenanfrage_nr = kundenanfrage_nr;
        this.kunde_nr = kunde_nr;
        this.anfrageDatum = anfrageDatum;
        this.anfrageBeschreibung = anfrageBeschreibung;
        this.prioritaet = prioritaet;
        this.status = status;
    }
    // No-Args Konstruktor
    public Kundenanfrage() {
        this.kundenanfrage_nr = "";
        this.kunde_nr = new Kunde();
        this.anfrageDatum = "";
        this.anfrageBeschreibung = "";
        this.prioritaet = "";
        this.status = "";
    }

    // Getter- und Setter-Methoden
    public String getkundenanfrage_nr() {
        return kundenanfrage_nr;
    }

    public void setkundenanfrage_nr(String kundenanfrage_nr) {
        this.kundenanfrage_nr = kundenanfrage_nr;
    }

    public Kunde getkunde_nr() {
        return kunde_nr;
    }

    public void setKundeNr(Kunde kunde_nr) {
        this.kunde_nr = kunde_nr;
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
                "kundenanfrage_nr=" + kundenanfrage_nr +
                ", kunde_nr=" + kunde_nr +
                ", anfrageDatum='" + anfrageDatum + '\'' +
                ", anfrageBeschreibung='" + anfrageBeschreibung + '\'' +
                ", prioritaet='" + prioritaet + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
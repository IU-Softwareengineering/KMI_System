package com.iu.kmi.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;
/**
 * @author Julian Treichel
 * @since 20.06.2024
 */
/**
 * Repräsentiert eine Rechnung einschließlich Debitor, Rechnungstag, Fälligkeitsdatum und Status.
 */
@Entity(tableName = "rechnung")
public class Rechnung {
    @Id(name = "rechnung_nr")
    private String rechnungNr;

    @JoinColumn(name = "debitor_nr", referencedColumnName = "debitor_nr")
    private Debitor debitor;

    @Column(name = "rechnungstag")
    private LocalDateTime rechnungstag;

    @Column(name = "faelligkeitsdatum")
    private LocalDateTime faelligkeitsdatum;

    @Column(name = "status")
    private String status;

    /**
     * Standardkonstruktor für eine Rechnung.
     */
    public Rechnung() {
    }

    /**
     * Konstruktor für eine neue Rechnung mit den angegebenen Details.
     *
     * @param rechnungNr die Rechnungsnummer
     * @param debitor der Debitor, der mit der Rechnung verbunden ist
     * @param rechnungstag das Datum der Rechnung
     * @param faelligkeitsdatum das Fälligkeitsdatum der Rechnung
     * @param status der Status der Rechnung
     */
    public Rechnung(String rechnungNr, Debitor debitor, LocalDateTime rechnungstag, LocalDateTime faelligkeitsdatum, String status) {
        this.rechnungNr = rechnungNr;
        this.debitor = debitor;
        this.rechnungstag = rechnungstag;
        this.faelligkeitsdatum = faelligkeitsdatum;
        this.status = status;
    }

    /**
     * Gibt die Rechnungsnummer zurück.
     *
     * @return die Rechnungsnummer
     */
    public String getRechnungNr() {
        return rechnungNr;
    }

    /**
     * Setzt die Rechnungsnummer.
     *
     * @param rechnungNr die neue Rechnungsnummer
     */
    public void setRechnungNr(String rechnungNr) {
        this.rechnungNr = rechnungNr;
    }

    /**
     * Gibt den Debitor zurück, der mit der Rechnung verbunden ist.
     *
     * @return der Debitor
     */
    public Debitor getDebitor() {
        return debitor;
    }

    /**
     * Setzt den Debitor, der mit der Rechnung verbunden ist.
     *
     * @param debitor der neue Debitor
     */
    public void setDebitor(Debitor debitor) {
        this.debitor = debitor;
    }

    /**
     * Gibt das Datum der Rechnung zurück.
     *
     * @return das Datum der Rechnung
     */
    public LocalDateTime getRechnungstag() {
        return rechnungstag;
    }

    /**
     * Setzt das Datum der Rechnung.
     *
     * @param rechnungstag das neue Datum der Rechnung
     */
    public void setRechnungstag(LocalDateTime rechnungstag) {
        this.rechnungstag = rechnungstag;
    }

    /**
     * Gibt das Fälligkeitsdatum der Rechnung zurück.
     *
     * @return das Fälligkeitsdatum der Rechnung
     */
    public LocalDateTime getFaelligkeitsdatum() {
        return faelligkeitsdatum;
    }

    /**
     * Setzt das Fälligkeitsdatum der Rechnung.
     *
     * @param faelligkeitsdatum das neue Fälligkeitsdatum der Rechnung
     */
    public void setFaelligkeitsdatum(LocalDateTime faelligkeitsdatum) {
        this.faelligkeitsdatum = faelligkeitsdatum;
    }

    /**
     * Gibt den Status der Rechnung zurück.
     *
     * @return der Status der Rechnung
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setzt den Status der Rechnung.
     *
     * @param status der neue Status der Rechnung
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gibt eine String-Repräsentation des Objekts zurück.
     *
     * @return eine String-Repräsentation des Objekts
     */
    @Override
    public String toString() {
        return "Rechnung{" +
                "rechnungNr='" + rechnungNr + '\'' +
                ", debitor=" + debitor +
                ", rechnungstag=" + rechnungstag +
                ", faelligkeitsdatum=" + faelligkeitsdatum +
                ", status='" + status + '\'' +
                '}';
    }
}
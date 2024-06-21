package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;
/**
 * @author Julian Treichel
 * @since 20.06.2024
 */

/**
 * Repräsentiert eine Lieferung einschließlich Auftragsnummer und Rechnungsnummer.
 */
@Entity(tableName = "lieferung")
public class Lieferung {
    @Id(name = "lieferung_nr")
    private String lieferungNr;

    @JoinColumn(name = "auftrag_nr", referencedColumnName = "auftrag_nr")
    private Auftrag auftrag;

    @JoinColumn(name = "rechnung_nr", referencedColumnName = "rechnung_nr")
    private Rechnung rechnung;

    /**
     * Standardkonstruktor für eine Lieferung.
     */
    public Lieferung() {
    }

    /**
     * Konstruktor für eine neue Lieferung mit den angegebenen Details.
     *
     * @param lieferungNr die Lieferungsnummer
     * @param auftrag der Auftrag, der mit der Lieferung verbunden ist
     * @param rechnung die Rechnung, die mit der Lieferung verbunden ist
     */
    public Lieferung(String lieferungNr, Auftrag auftrag, Rechnung rechnung) {
        this.lieferungNr = lieferungNr;
        this.auftrag = auftrag;
        this.rechnung = rechnung;
    }

    /**
     * Gibt die Lieferungsnummer zurück.
     *
     * @return die Lieferungsnummer
     */
    public String getLieferungNr() {
        return lieferungNr;
    }

    /**
     * Setzt die Lieferungsnummer.
     *
     * @param lieferungNr die neue Lieferungsnummer
     */
    public void setLieferungNr(String lieferungNr) {
        this.lieferungNr = lieferungNr;
    }

    /**
     * Gibt den Auftrag zurück, der mit der Lieferung verbunden ist.
     *
     * @return der Auftrag
     */
    public Auftrag getAuftrag() {
        return auftrag;
    }

    /**
     * Setzt den Auftrag, der mit der Lieferung verbunden ist.
     *
     * @param auftrag der neue Auftrag
     */
    public void setAuftrag(Auftrag auftrag) {
        this.auftrag = auftrag;
    }

    /**
     * Gibt die Rechnung zurück, die mit der Lieferung verbunden ist.
     *
     * @return die Rechnung
     */
    public Rechnung getRechnung() {
        return rechnung;
    }

    /**
     * Setzt die Rechnung, die mit der Lieferung verbunden ist.
     *
     * @param rechnung die neue Rechnung
     */
    public void setRechnung(Rechnung rechnung) {
        this.rechnung = rechnung;
    }

    /**
     * Gibt eine String-Repräsentation des Objekts zurück.
     *
     * @return eine String-Repräsentation des Objekts
     */
    @Override
    public String toString() {
        return "Lieferung{" +
                "lieferungNr='" + lieferungNr + '\'' +
                ", auftrag=" + auftrag +
                ", rechnung=" + rechnung +
                '}';
    }
}
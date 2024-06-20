package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

/**
 * @author Julian Treichel
 * @since 07.06.2024
 */

/**
 * Repräsentiert eine Kondition einschließlich Zahlungsmethode, Lieferbedingungen und Rabatt.
 */
@Entity(tableName = "kondition")
public class Kondition {
    @Id(name = "kondition_nr")
    private String konditionNr;
    @Column(name = "name")
    private String name;
    @Column(name = "zahlungsbedingungen")
    private String zahlungsbedingungen;
    @Column(name = "lieferbedingungen")
    private String lieferbedingungen;
    @Column(name = "rabatt")
    private double rabatt;
    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebot;

    /**
     * Konstruktor für eine neue Kondition ohne Argumente
     */
    public Kondition(){

    }

    /**
     * Konstruktor für eine neue Kondition mit dem angegebenen Namen, Zahlungsmethode, Lieferbedingungen und Rabatt.
     *
     * @param name der Name der Kondition
     * @param zahlungsbedingungen die Zahlungsmethode, die mit der Kondition verbunden ist
     * @param lieferbedingungen die Lieferbedingungen, die mit der Kondition verbunden sind
     * @param rabatt der Rabatt, der mit der Kondition verbunden ist
     */
    public Kondition(String name, String zahlungsbedingungen, String lieferbedingungen, double rabatt, Angebot angebot) {
        this.name = name;
        this.zahlungsbedingungen = zahlungsbedingungen;
        this.lieferbedingungen = lieferbedingungen;
        this.rabatt = rabatt;
        this.angebot = angebot;
    }

    /**
     * Gibt die ID der Kondition zurück.
     *
     * @return die ID der Kondition
     */
    public String getKonditionNr() {
        return konditionNr;
    }

    /**
     * Setzt die ID der Kondition.
     *
     * @param konditionNr die neue ID der Kondition
     */
    public void setKonditionNr(String konditionNr) {
        this.konditionNr = konditionNr;
    }

    /**
     * Gibt den Namen der Kondition zurück.
     *
     * @return der Name der Kondition
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Kondition.
     *
     * @param name der neue Name der Kondition
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Zahlungsmethode zurück, die mit der Kondition verbunden ist.
     *
     * @return die Zahlungsmethode, die mit der Kondition verbunden ist
     */
    public String getZahlungsbedingungen() {
        return zahlungsbedingungen;
    }

    /**
     * Setzt die Zahlungsmethode, die mit der Kondition verbunden ist.
     *
     * @param zahlungsbedingungen die neue Zahlungsmethode, die mit der Kondition verbunden ist
     */
    public void setZahlungsbedingungen(String zahlungsbedingungen) {
        this.zahlungsbedingungen = zahlungsbedingungen;
    }

    /**
     * Gibt die Lieferbedingungen zurück, die mit der Kondition verbunden sind.
     *
     * @return die Lieferbedingungen, die mit der Kondition verbunden sind
     */
    public String getLieferbedingungen() {
        return lieferbedingungen;
    }

    /**
     * Setzt die Lieferbedingungen, die mit der Kondition verbunden sind.
     *
     * @param lieferbedingungen die neuen Lieferbedingungen, die mit der Kondition verbunden sind
     */
    public void setLieferbedingungen(String lieferbedingungen) {
        this.lieferbedingungen = lieferbedingungen;
    }

    /**
     * Gibt den Rabatt zurück, der mit der Kondition verbunden ist.
     *
     * @return der Rabatt, der mit der Kondition verbunden ist
     */
    public double getRabatt() {
        return rabatt;
    }

    /**
     * Setzt den Rabatt, der mit der Kondition verbunden ist.
     *
     * @param rabatt der neue Rabatt, der mit der Kondition verbunden ist
     */
    public void setRabatt(double rabatt) {
        this.rabatt = rabatt;
    }

    /**
     * @return the angebot
     */
    public Angebot getAngebot(){
        return angebot;
    }

    /**
     * Setzt das Angebot, das mit der Kondition verbunden ist.
     *
     * @param angebot das neue Angebot, das mit der Kondition verbunden ist
     */
    public void setAngebot(final Angebot angebot){
        this.angebot = angebot;
    }

    /**
     * Gibt eine String-Repräsentation des Objekts zurück.
     *
     * @return eine String-Repräsentation des Objekts
     */
    @Override
    public String toString() {
        return "Kondition{" +
                "konditionenNr=" + konditionNr +
                ", name='" + name + '\'' +
                ", zahlungsmethode='" + zahlungsbedingungen + '\'' +
                ", lieferbedingungen='" + lieferbedingungen + '\'' +
                ", rabatt=" + rabatt +
                '}';
    }
}

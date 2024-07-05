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
 * Repräsentiert einen Debitor einschließlich Name, Vorname, Firma, Kontonummer und Adressnummer.
 */
@Entity(tableName = "debitor")
public class Debitor {
    @Id(name = "debitor_nr")
    private String debitorNr;
    @Column(name = "name")
    private String name;
    @Column(name = "vorname")
    private String vorname;
    @Column(name = "firma")
    private String firma;
    @Column(name = "kontonr")
    private String kontoNr;
    @JoinColumn(name = "adresse_nr", referencedColumnName = "adresse_nr")
    private Adresse adresseNr;

    public Debitor(){

    }

    /**
     * Konstruktor für einen neuen Debitor mit den angegebenen Attributen.
     *
     * @param debitorNr die ID des Debitors
     * @param name der Name des Debitors
     * @param vorname der Vorname des Debitors
     * @param firma die Firma des Debitors
     * @param kontoNr die Kontonummer des Debitors
     * @param adresseNr die Adressnummer des Debitors
     */
    public Debitor(String debitorNr, String name, String vorname, String firma, String kontoNr, Adresse adresseNr) {
        this.debitorNr = debitorNr;
        this.name = name;
        this.vorname = vorname;
        this.firma = firma;
        this.kontoNr = kontoNr;
        this.adresseNr = adresseNr;
    }

    /**
     * Gibt die ID des Debitors zurück.
     *
     * @return die ID des Debitors
     */
    public String getDebitorNr() {
        return debitorNr;
    }

    /**
     * Setzt die ID des Debitors.
     *
     * @param debitorNr die neue ID des Debitors
     */
    public void setDebitorNr(String debitorNr) {
        this.debitorNr = debitorNr;
    }

    /**
     * Gibt den Namen des Debitors zurück.
     *
     * @return der Name des Debitors
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Debitors.
     *
     * @param name der neue Name des Debitors
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Vornamen des Debitors zurück.
     *
     * @return der Vorname des Debitors
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des Debitors.
     *
     * @param vorname der neue Vorname des Debitors
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt die Firma des Debitors zurück.
     *
     * @return die Firma des Debitors
     */
    public String getFirma() {
        return firma;
    }

    /**
     * Setzt die Firma des Debitors.
     *
     * @param firma die neue Firma des Debitors
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }

    /**
     * Gibt die Kontonummer des Debitors zurück.
     *
     * @return die Kontonummer des Debitors
     */
    public String getKontoNr() {
        return kontoNr;
    }

    /**
     * Setzt die Kontonummer des Debitors.
     *
     * @param kontoNr die neue Kontonummer des Debitors
     */
    public void setKontoNr(String kontoNr) {
        this.kontoNr = kontoNr;
    }

    /**
     * Gibt die Adressnummer des Debitors zurück.
     *
     * @return die Adressnummer des Debitors
     */
    public Adresse getAdresseNr() {
        return adresseNr;
    }

    /**
     * Setzt die Adressnummer des Debitors.
     *
     * @param adresse die neue Adresse des Debitors
     */
    public void setAdresse(Adresse adresse) {
        this.adresseNr = adresse;
    }

    /**
     * Gibt eine String-Repräsentation des Objekts zurück.
     *
     * @return eine String-Repräsentation des Objekts
     */
    @Override
    public String toString() {
        return "Debitor{" +
                "debitorNr='" + debitorNr + '\'' +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", firma='" + firma + '\'' +
                ", kontoNr='" + kontoNr + '\'' +
                ", adresseNr='" + adresseNr + '\'' +
                '}';
    }
}

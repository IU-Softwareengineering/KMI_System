package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

/**
 * Repräsentiert eine Lager-Entität.
 * Diese Klasse wird auf die "lager" Tabelle in der Datenbank abgebildet.
 */
@Entity(tableName = "lager")
public class Lager {

    // Attribute
    /**
     * Die eindeutige Kennung für das Lager.
     */
    @Id(name = "lager_nr")
    private String lager_nr;

    /**
     * Der Name des Lagers.
     */
    @Column(name = "name")
    private String name;

    /**
     * Die Adresskennung für das Lager.
     */
    @JoinColumn(name = "adresse_nr", referencedColumnName = "adresse_nr")
    private Adresse adresse_nr;

    // No-Args Konstruktor
    /**
     * Erstellt eine neue Lagerbestand-Instanz ohne Details.
     */
    public Lager() {
        // Leerer Konstruktor
    }

    // Konstruktor
    /**
     * Erstellt eine neue Lager-Instanz mit den angegebenen Details.
     *
     * @param lager_nr die eindeutige Kennung für das Lager
     * @param name der Name des Lagers
     * @param adresse_nr die Adresskennung für das Lager
     */
    public Lager(String lager_nr, String name, Adresse adresse_nr) {
        this.lager_nr = lager_nr;
        this.name = name;
        this.adresse_nr = adresse_nr;
    }

    // Getter-Methoden
    /**
     * Gibt die eindeutige Kennung des Lagers zurück.
     *
     * @return die eindeutige Kennung des Lagers
     */
    public String getLagerNummer() {
        return lager_nr;
    }

    /**
     * Gibt den Namen des Lagers zurück.
     *
     * @return der Name des Lagers
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Adresskennung des Lagers zurück.
     *
     * @return die Adresskennung des Lagers
     */
    public Adresse getAdresse() {
        return adresse_nr;
    }

    // Setter-Methoden
    /**
     * Setzt die eindeutige Kennung für das Lager.
     *
     * @param lager_nr die eindeutige Kennung für das Lager
     */
    public void setLagerNummer(String lager_nr) {
        this.lager_nr = lager_nr;
    }

    /**
     * Setzt den Namen des Lagers.
     *
     * @param name der Name des Lagers
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt die Adresskennung für das Lager.
     *
     * @param adresse_nr die Adresskennung für das Lager
     */
    public void setAdresse(Adresse adresse_nr) {
        this.adresse_nr = adresse_nr;
    }

    /**
     * Gibt eine Zeichenkettendarstellung des Lagers zurück.
     *
     * @return eine Zeichenkettendarstellung des Lagers
     */
    @Override
    public String toString() {
        return "Lager{" +
                "lager_nr=" + lager_nr +
                ", name='" + name + '\'' +
                ", adresse_nr=" + adresse_nr +
                '}';
    }
}
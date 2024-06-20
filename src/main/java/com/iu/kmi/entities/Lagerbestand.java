package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.CompositeKey;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;

/**
 * Repräsentiert eine Lagerbestand-Entität.
 * Diese Klasse wird auf die "lagerbestand" Tabelle in der Datenbank abgebildet.
 */
@Entity(tableName = "lagerbestand")
@CompositeKey(keyColumns = {"lager_id", "artikel_nr"})
public class Lagerbestand {

    // Attribute
    /**
     * Die eindeutige Kennung für den Artikel.
     */
    @Id(name = "artikel_nr")
    private Material artikel_nr;

    /**
     * Die eindeutige Kennung für das Lager.
     */
    @Id(name = "lager_nr")
    private Lager lager_nr;

    /**
     * Die Menge des Artikels im Lager.
     */
    @Column(name = "menge")
    private int menge;

    // No-Args Konstruktor
    /**
     * Erstellt eine neue Lagerbestand-Instanz ohne Details.
     */
    public Lagerbestand() {
        // Leerer Konstruktor
    }

    // Konstruktor
    /**
     * Erstellt eine neue Lagerbestand-Instanz mit den angegebenen Details.
     *
     * @param artikel_nr die eindeutige Kennung für den Artikel
     * @param lager_nr die eindeutige Kennung für das Lager
     * @param menge die Menge des Artikels im Lager
     */
    public Lagerbestand(Material artikel_nr, Lager lager_nr, int menge) {
        this.artikel_nr = artikel_nr;
        this.lager_nr = lager_nr;
        this.menge = menge;
    }

    // Getter-Methoden
    /**
     * Gibt die eindeutige Kennung des Artikels zurück.
     *
     * @return die eindeutige Kennung des Artikels
     */
    public Material getArtikelNummer() {
        return artikel_nr;
    }

    /**
     * Gibt die eindeutige Kennung des Lagers zurück.
     *
     * @return die eindeutige Kennung des Lagers
     */
    public Lager getLagerNummer() {
        return lager_nr;
    }

    /**
     * Gibt die Menge des Artikels im Lager zurück.
     *
     * @return die Menge des Artikels im Lager
     */
    public int getMenge() {
        return menge;
    }

    // Setter-Methoden
    /**
     * Setzt die eindeutige Kennung für den Artikel.
     *
     * @param artikel_nr die eindeutige Kennung für den Artikel
     */
    public void setArtikelNummer(Material artikel_nr) {
        this.artikel_nr = artikel_nr;
    }

    /**
     * Setzt die eindeutige Kennung für das Lager.
     *
     * @param lager_nr die eindeutige Kennung für das Lager
     */
    public void setLagerNummer(Lager lager_nr) {
        this.lager_nr = lager_nr;
    }

    /**
     * Setzt die Menge des Artikels im Lager.
     *
     * @param menge die Menge des Artikels im Lager
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Gibt eine Zeichenkettendarstellung des Lagerbestands zurück.
     *
     * @return eine Zeichenkettendarstellung des Lagerbestands
     */
    @Override
    public String toString() {
        return "Lagerbestand{" +
                "artikel_nr='" + artikel_nr + '\'' +
                ", lager_nr='" + lager_nr + '\'' +
                ", menge=" + menge +
                '}';
    }
}

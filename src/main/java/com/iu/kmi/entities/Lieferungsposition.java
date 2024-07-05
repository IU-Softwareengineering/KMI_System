package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

/**
 * Repräsentiert eine Position innerhalb einer Lieferung, die ein bestimmtes Material und die Menge dieses Materials in der Lieferung umfasst.
 */
@Entity(tableName = "lieferungsmaterial")
public class Lieferungsposition{

    // Attribute
    /**
     * Die eindeutige Nummer der Lieferungsposition.
     */
    @Id(name = "lieferungsposition_nr")
    private String lieferungsposition_nr;

    /**
     * Die Lieferung, zu der diese Position gehört.
     */
    @JoinColumn(name = "lieferung_nr", referencedColumnName = "lieferung_nr")
    private Lieferung lieferung_nr;

    /**
     * Das Material, das in dieser Position geliefert wird.
     */
    @JoinColumn(name = "artikel_nr", referencedColumnName = "artikel_nr")
    private Material artikel_nr;

    /**
     * Die Menge des Materials, das in dieser Position geliefert wird.
     */
    @Column(name = "menge")
    private int menge;

    // No-Args Konstruktor
    /**
     * Standard-Konstruktor für die Klasse Lieferungsposition.
     */
    public Lieferungsposition() {
        // Leerer Konstruktor
    }

    // Konstruktor
    /**
     * Konstruktor für die Klasse Lieferungsposition.
     *
     * @param lieferungsposition_nr Die eindeutige Nummer der Lieferungsposition.
     * @param lieferung_nr Die Lieferung, zu der diese Position gehört.
     * @param artikel_nr Das Material, das in dieser Position geliefert wird.
     * @param menge Die Menge des Materials, das in dieser Position geliefert wird.
     */
    public Lieferungsposition(String lieferungsposition_nr, Lieferung lieferung_nr, Material artikel_nr, int menge) {
        this.lieferungsposition_nr = lieferungsposition_nr;
        this.lieferung_nr = lieferung_nr;
        this.artikel_nr = artikel_nr;
        this.menge = menge;
    }

    // Getter-Methoden
    /**
     * Gibt die Nummer der Lieferungsposition zurück.
     *
     * @return Die Nummer der Lieferungsposition.
     */
    public String getLieferungsposition_nr() {
        return lieferungsposition_nr;
    }

    /**
     * Gibt die Lieferung zurück, zu der diese Position gehört.
     *
     * @return Die Lieferung.
     */
    public Lieferung getLieferung_nr() {
        return lieferung_nr;
    }

    /**
     * Gibt das Material zurück, das in dieser Position geliefert wird.
     *
     * @return Das Material.
     */
    public Material getArtikel_nr() {
        return artikel_nr;
    }

    /**
     * Gibt die Menge des Materials zurück, das in dieser Position geliefert wird.
     *
     * @return Die Menge des Materials.
     */
    public int getMenge() {
        return menge;
    }

    // Setter-Methoden
    /**
     * Setzt die Nummer der Lieferungsposition.
     *
     * @param lieferungsposition_nr Die neue Nummer der Lieferungsposition.
     */
    public void setLieferungsposition_nr(String lieferungsposition_nr) {
        this.lieferungsposition_nr = lieferungsposition_nr;
    }

    /**
     * Setzt die Lieferung, zu der diese Position gehört.
     *
     * @param lieferung_nr Die neue Lieferung.
     */
    public void setLieferung_nr(Lieferung lieferung_nr) {
        this.lieferung_nr = lieferung_nr;
    }

    /**
     * Setzt das Material, das in dieser Position geliefert wird.
     *
     * @param artikel_nr Das neue Material.
     */
    public void setArtikel_nr(Material artikel_nr) {
        this.artikel_nr = artikel_nr;
    }

    /**
     * Setzt die Menge des Materials, das in dieser Position geliefert wird.
     *
     * @param menge Die neue Menge des Materials.
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Gibt eine String-Repräsentation des Objekts zurück.
     *
     * @return Eine String-Repräsentation des Objekts.
     */
    @Override
    public String toString() {
        return "Lieferungsposition{" +
                "lieferungsposition_nr='" + lieferungsposition_nr + '\'' +
                ", lieferung_nr=" + lieferung_nr +
                ", artikel_nr=" + artikel_nr +
                ", menge=" + menge +
                '}';
    }
}
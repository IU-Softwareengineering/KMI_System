package com.iu.kmi.entities;

/**
 * Repräsentiert eine Menge von Konditionen einschließlich Zahlungsmethode, Lieferbedingungen und Rabatt.
 */
public class Kondition {
    private int id;
    private String name;
    private String zahlungsmethode;
    private String lieferbedingungen;
    private double rabatt;

    /**
     * Konstruktor für eine neue Kondition mit dem angegebenen Namen, Zahlungsmethode, Lieferbedingungen und Rabatt.
     *
     * @param name der Name der Kondition
     * @param zahlungsmethode die Zahlungsmethode, die mit der Kondition verbunden ist
     * @param lieferbedingungen die Lieferbedingungen, die mit der Kondition verbunden sind
     * @param rabatt der Rabatt, der mit der Kondition verbunden ist
     */
    public Kondition(String name, String zahlungsmethode, String lieferbedingungen, double rabatt) {
        this.name = name;
        this.zahlungsmethode = zahlungsmethode;
        this.lieferbedingungen = lieferbedingungen;
        this.rabatt = rabatt;
    }

    /**
     * Gibt die ID der Kondition zurück.
     *
     * @return die ID der Kondition
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt die ID der Kondition.
     *
     * @param id die neue ID der Kondition
     */
    public void setId(int id) {
        this.id = id;
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
    public String getZahlungsmethode() {
        return zahlungsmethode;
    }

    /**
     * Setzt die Zahlungsmethode, die mit der Kondition verbunden ist.
     *
     * @param zahlungsmethode die neue Zahlungsmethode, die mit der Kondition verbunden ist
     */
    public void setZahlungsmethode(String zahlungsmethode) {
        this.zahlungsmethode = zahlungsmethode;
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
     * Gibt eine String-Repräsentation des Objekts zurück.
     *
     * @return eine String-Repräsentation des Objekts
     */
    @Override
    public String toString() {
        return "KonditionEntitaet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zahlungsmethode='" + zahlungsmethode + '\'' +
                ", lieferbedingungen='" + lieferbedingungen + '\'' +
                ", rabatt=" + rabatt +
                '}';
    }
}

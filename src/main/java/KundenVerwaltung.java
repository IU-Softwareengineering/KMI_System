package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse verwaltet eine Liste von Kunden und bietet Methoden zum Hinzufügen
 * und Abrufen aller Kunden.
 */
class KundenVerwaltung {

    private List<Kunde> kundenListe;

    /**
     * Konstruktor zur Initialisierung der Kundenliste.
     */
    public KundenVerwaltung() {
        this.kundenListe = new ArrayList<>();
    }

    /**
     * Fügt einen neuen Kunden zur Liste hinzu.
     *
     * @param kunde Das hinzuzufügende Kundenobjekt
     */
    public void addKunde(Kunde kunde) {
        kundenListe.add(kunde);
    }

    /**
     * Gibt die Liste aller Kunden zurück.
     *
     * @return Eine Liste von Kunden
     */
    public List<Kunde> getAlleKunden() {
        return kundenListe;
    }
}

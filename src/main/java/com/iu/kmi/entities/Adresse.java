package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;

/**
 * Represents an address with street, house number, city, postal code, and
 * country. Provides getter and setter methods for each field.
 *
 * @autor OT
 */
@Entity(tableName = "adresse")
public class Adresse {

    @Id(name = "adresse_nr")
    private String id;

    @Column(name = "strasse")
    private String strasse;    // Street name

    @Column(name = "hausnummer")
    private String hausnummer; // House number

    @Column(name = "postleitzahl")
    private String postleitzahl; // Postal code

    @Column(name = "stadt")
    private String stadt;      // City name

    @Column(name = "land")
    private String land;       // Country

    // No-Args Constructor
    public Adresse() {
    }

    /**
     * Constructor to initialize the address fields.
     *
     * @param strasse the street name
     * @param hausnummer the house number
     * @param postleitzahl the postal code
     * @param stadt the city name
     * @param land the country
     */
    public Adresse(String strasse, String hausnummer, String postleitzahl, String stadt, String land) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.postleitzahl = postleitzahl;
        this.stadt = stadt;
        this.land = land;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}

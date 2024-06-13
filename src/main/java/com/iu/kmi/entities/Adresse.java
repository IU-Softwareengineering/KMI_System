package com.iu.kmi.entities;

/**
 * Represents an address with street, city, postal code, and country. Provides
 * getter and setter methods for each field.
 *
 * @author OT
 */
public class Adresse {

    private String strasse;    // Street name and number
    private String stadt;      // City name
    private String postleitzahl; // Postal code
    private String land;       // Country

    /**
     * Constructor to initialize the address fields.
     *
     * @param strasse the street name and number
     * @param stadt the city name
     * @param postleitzahl the postal code
     * @param land the country
     */
    public Adresse(String strasse, String stadt, String postleitzahl, String land) {
        this.strasse = strasse;
        this.stadt = stadt;
        this.postleitzahl = postleitzahl;
        this.land = land;
    }

 
    // Getter and Setter methods
    /**
     * Gets the street name and number.
     *
     * @return the street name and number
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Sets the street name and number.
     *
     * @param strasse the street name and number
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Gets the city name.
     *
     * @return the city name
     */
    public String getStadt() {
        return stadt;
    }

    /**
     * Sets the city name.
     *
     * @param stadt the city name
     */
    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    /**
     * Gets the postal code.
     *
     * @return the postal code
     */
    public String getPostleitzahl() {
        return postleitzahl;
    }

    /**
     * Sets the postal code.
     *
     * @param postleitzahl the postal code
     */
    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getLand() {
        return land;
    }

    /**
     * Sets the country.
     *
     * @param land the country
     */
    public void setLand(String land) {
        this.land = land;
    }
}

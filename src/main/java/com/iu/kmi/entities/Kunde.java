package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.*;

/**
 * Represents a customer with personal information, contact details, and login
 * credentials. Provides getter and setter methods for each field.
 *
 * @author OT
 */
public class Kunde {

    // Unique identifier for the customer
    @Id(name = "kunde_nr")
    private String kundeNr;

    // Personal information of the customer
    @Column(name = "name")
    private String name;
    @Column(name = "vorname")
    private String vorname;

    // Contact details of the customer
    @Column(name = "email")
    private String email;
    @Column(name = "telefon")
    private String telefon;
    @JoinColumn(name = "adresse_nr", referencedColumnName = "adresse_nr")
    private Adresse adresse;

    public Kunde() {

    }

    public Kunde(String kundeNr, String name, String vorname, String email, String telefon, Adresse adresse) {
        this.kundeNr = kundeNr;
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    // Getter and Setter methods
    /**
     * Gets the unique identifier for the customer.
     *
     * @return the unique identifier for the customer
     */
    public String getKundennummer() {
        return kundeNr;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param kundeNr the unique identifier for the customer
     */
    public void setKundennummer(String kundeNr) {
        this.kundeNr = kundeNr;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return the first name of the customer
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param vorname the first name of the customer
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return the last name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param name the last name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email the email address of the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param telefon the phone number of the customer
     */
    public void setTelefonnummer(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Gets the address of the customer.
     *
     * @return the address of the customer
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Sets the address of the customer.
     *
     * @param adresse the address of the customer
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
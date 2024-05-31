package Classes;

import Classes.Adresse;

/**
 * Represents a customer with personal information, contact details, and login
 * credentials. Provides getter and setter methods for each field.
 *
 * @author OT
 */
public class Kunde {

    // Unique identifier for the customer
    private String kundennummer;

    // Personal information of the customer
    private String vorname;
    private String nachname;
    private String geburtsdatum;

    // Contact details of the customer
    private String emailAdresse;
    private String telefonnummer;
    private Adresse adresse;

    // Login details of the customer
    private String benutzername;
    private String passwort;

    /**
     * Constructor to initialize all fields of the customer.
     *
     * @param kundennummer unique identifier for the customer
     * @param vorname first name of the customer
     * @param nachname last name of the customer
     * @param geburtsdatum date of birth of the customer
     * @param emailAdresse email address of the customer
     * @param telefonnummer phone number of the customer
     * @param adresse address of the customer
     * @param benutzername username for login
     * @param passwort password for login
     */
    public Kunde(String kundennummer, String vorname, String nachname, String geburtsdatum,
            String emailAdresse, String telefonnummer, Adresse adresse,
            String benutzername, String passwort) {
        this.kundennummer = kundennummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.emailAdresse = emailAdresse;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    // Getter and Setter methods
    /**
     * Gets the unique identifier for the customer.
     *
     * @return the unique identifier for the customer
     */
    public String getKundennummer() {
        return kundennummer;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param kundennummer the unique identifier for the customer
     */
    public void setKundennummer(String kundennummer) {
        this.kundennummer = kundennummer;
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
    public String getNachname() {
        return nachname;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param nachname the last name of the customer
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gets the date of birth of the customer.
     *
     * @return the date of birth of the customer
     */
    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Sets the date of birth of the customer.
     *
     * @param geburtsdatum the date of birth of the customer
     */
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmailAdresse() {
        return emailAdresse;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param emailAdresse the email address of the customer
     */
    public void setEmailAdresse(String emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getTelefonnummer() {
        return telefonnummer;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param telefonnummer the phone number of the customer
     */
    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
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

    /**
     * Gets the username for login.
     *
     * @return the username for login
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Sets the username for login.
     *
     * @param benutzername the username for login
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Gets the password for login.
     *
     * @return the password for login
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Sets the password for login.
     *
     * @param passwort the password for login
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}

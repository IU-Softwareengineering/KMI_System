package Classes;

import Classes.Adresse;

public class Kunde {

    // Eindeutige Identifikationsnummer für den Kunden
    private String kundennummer;

    // Persönliche Informationen des Kunden
    private String vorname;
    private String nachname;
    private String geburtsdatum;

    // Kontaktdaten des Kunden
    private String emailAdresse;
    private String telefonnummer;
    private Adresse adresse;

    // Login-Daten des Kunden
    private String benutzername;
    private String passwort;

    // Konstruktor
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

    // Getter und Setter Methoden
    public String getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(String kundennummer) {
        this.kundennummer = kundennummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getEmailAdresse() {
        return emailAdresse;
    }

    public void setEmailAdresse(String emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}

package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

/**
 * Represents a customer with personal information, contact details, and login
 * credentials. Provides getter and setter methods for each field.
 *
 * @author OT
 */
@Entity(tableName = "kunde")
public class Kunde {

    @Id(name = "kunde_nr")
    private String kundennummer;

    @Column(name = "name")
    private String name;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon")
    private String telefon;

    @JoinColumn(name = "adresse_nr", referencedColumnName = "adresse_nr")
    private Adresse adresse;

    // No-Args-Konstruktor (wird von einigen JPA-Implementierungen benötigt)
    public Kunde() {
    }

    // Constructor mit allen Feldern (optional)
    public Kunde(String kundennummer, String name, String vorname, String email, String telefon, Adresse adresse) {
        this.kundennummer = kundennummer;
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    // Getter und Setter
    public String getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(String kundennummer) {
        this.kundennummer = kundennummer;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
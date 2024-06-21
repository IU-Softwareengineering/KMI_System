package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;

import java.math.BigDecimal;

/**
 * @author Bludwell, Anthony, Anastacia
 */
@Entity(tableName = "material")
public class Material {
    // Attribute
    @Id(name = "artikel_nr")
    private String artikelNr;
    @Column(name = "name")
    private String name;
    @Column(name = "beschreibung")
    private String beschreibung;
    @Column(name = "lieferant")
    private String lieferant;
    @Column(name = "einkaufspreis")
    private BigDecimal einkaufsPreis;
    @Column(name = "verkaufspreis")
    private BigDecimal verkaufsPreis;

    // No-Args Konstruktor
    public Material() {
    }

    // Konstruktor
    public Material(String artikelNr, String name, String beschreibung, String lieferant, BigDecimal einkaufsPreis, BigDecimal verkaufsPreis) {
        this.artikelNr = artikelNr;
        this.name = name;
        this.beschreibung = beschreibung;
        this.lieferant = lieferant;
        this.einkaufsPreis = einkaufsPreis;
        this.verkaufsPreis = verkaufsPreis;
    }

    // Getter-Methoden
    public String getArtikelNr() {
        return artikelNr;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getLieferant() {
        return lieferant;
    }

    public BigDecimal getEinkaufsPreis() {
        return einkaufsPreis;
    }

    public BigDecimal getVerkaufsPreis() {
        return verkaufsPreis;
    }

    // Setter-Methoden
    public void setArtikelNr(String artikelNr) {
        this.artikelNr = artikelNr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public void setEinkaufsPreis(BigDecimal einkaufsPreis) {
        this.einkaufsPreis = einkaufsPreis;
    }

    public void setVerkaufsPreis(BigDecimal verkaufsPreis) {
        this.verkaufsPreis = verkaufsPreis;
    }

    // toString Method
    @Override
    public String toString() { //
        return "Material{" +
                "artikelNummer=" + artikelNr +
                ", name='" + name + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", lieferant='" + lieferant + '\'' +
                ", einkaufsPreis=" + einkaufsPreis +
                ", verkaufsPreis=" + verkaufsPreis +
                '}';
    }
}
package com.iu.kmi.entities;

import java.util.Date;
import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

@Entity(tableName = "angebot")
public class Angebot {

    @Id
    @Column(name = "angebot_nr")
    private int angebotNr;

    @Column(name = "angebotsdatum")
    private Date angebotsdatum;

    @JoinColumn(name = "kunden_nr", referencedColumnName = "kunde_nr")
    private String kundenNr;

    @Column(name = "gueltig_bis")
    private Date gueltigBis;

    @Column(name = "waehrung")
    private String waehrung;

    @Column(name = "status")
    private String status;

    @JoinColumn(name = "kondition_nr", referencedColumnName = "kondition_nr")  // Ordnet die Spalte "kondition_nr" zu, die auf die Spalte "kondition_nr" in der Tabelle "kondition" verweist.
    private String konditionNr;

    // Konstruktor
    public Angebot(String kundenNr, Date angebotsdatum, Date gueltigBis, String waehrung, String status, String konditionNr, int angebotNr) {
        this.kundenNr = kundenNr;
        this.angebotsdatum = angebotsdatum;
        this.gueltigBis = gueltigBis;
        this.waehrung = waehrung;
        this.status = status;
        this.konditionNr = konditionNr;
        this.angebotNr = angebotNr;
    }

    // Getter & Setter

    /**
     * @return kundenNr
     * Getter für die Kunden-Nummer.
     */
    public String getKundenNr() {
        return this.kundenNr;
    }

    /**
     * Set kundenNr
     *
     * @param kundenNr new kundenNr
     * Setter für die Kunden-Nummer.
     */
    public void setKundenNr(final String kundenNr) {
        this.kundenNr = kundenNr;
    }

    /**
     * @return angebotsdatum
     * Getter für das Angebotsdatum.
     */
    public Date getAngebotsdatum() {
        return this.angebotsdatum;
    }

    /**
     * Set angebotsdatum
     *
     * @param angebotsdatum new angebotsdatum
     * Setter für das Angebotsdatum.
     */
    public void setAngebotsdatum(final Date angebotsdatum) {
        this.angebotsdatum = angebotsdatum;
    }

    /**
     * @return gueltigBis
     * Getter für das Gültig-bis-Datum.
     */
    public Date getGueltigBis() {
        return this.gueltigBis;
    }

    /**
     * Set gueltigBis
     *
     * @param gueltigBis new gueltigBis
     * Setter für das Gültig-bis-Datum.
     */
    public void setGueltigBis(final Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    /**
     * @return waehrung
     * Getter für die Währung.
     */
    public String getWaehrung() {
        return this.waehrung;
    }

    /**
     * Set waehrung
     *
     * @param waehrung new waehrung
     * Setter für die Währung.
     */
    public void setWaehrung(final String waehrung) {
        this.waehrung = waehrung;
    }

    /**
     * @return status
     * Getter für den Status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Set status
     *
     * @param status new status
     * Setter für den Status.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @return konditionNr
     * Getter für die Konditionsnummer.
     */
    public String getKonditionNr() {
        return this.konditionNr;
    }

    /**
     * Set konditionNr
     *
     * @param konditionNr new konditionNr
     * Setter für die Konditionsnummer.
     */
    public void setKonditionNr(final String konditionNr) {
        this.konditionNr = konditionNr;
    }

    /**
     * @return angebotNr
     * Getter für die Angebotsnummer.
     */
    public int getAngebotNr() {
        return this.angebotNr;
    }

    /**
     * Set angebotNr
     *
     * @param angebotNr new angebotNr
     * Setter für die Angebotsnummer.
     */
    public void setAngebotNr(final int angebotNr) {
        this.angebotNr = angebotNr;
    }
}

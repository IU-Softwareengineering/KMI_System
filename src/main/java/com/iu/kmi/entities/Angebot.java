package com.iu.kmi.entities;

import java.time.LocalDate;
import java.util.Date;


import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

/**
 * @author Anthony
 */

@Entity(tableName = "Angebot")
public class Angebot {
    @Id(name = "angebot_nr")
    private String angebotNr;

    @JoinColumn(name = "kunde_nr", referencedColumnName = "kunde_nr")
    private Kunde kundeNr;

    @Column(name = "angebotsdatum")
    private LocalDate angebotsdatum;

    @Column(name = "gueltig_bis")
    private LocalDate gueltigBis;

    @Column(name = "waehrung")
    private String waehrung;

    @Column(name = "status")
    private String status;

    @JoinColumn(name = "kondition_nr", referencedColumnName = "kondition_nr")
    private Kondition konditionNr;

    // Constructor
    public Angebot(Kunde kundeNr, LocalDate angebotsdatum, LocalDate gueltigBis, String waehrung, String status, Kondition konditionNr) {
        this.kundeNr = kundeNr;
        this.angebotsdatum = angebotsdatum;
        this.gueltigBis = gueltigBis;
        this.waehrung = waehrung;
        this.status = status;
        this.konditionNr = konditionNr;
    }

    public Angebot() {
    }

    // Getter & Setter

    public String getAngebotNr() {
        return angebotNr;
    }

    public void setAngebotNr(String angebotNr) {
        this.angebotNr = angebotNr;
    }

    public Kunde getKundeNr() {
        return kundeNr;
    }

    public void setKundeNr(Kunde kundeNr) {
        this.kundeNr = kundeNr;
    }

    public LocalDate getAngebotsdatum() {
        return angebotsdatum;
    }

    public void setAngebotsdatum(LocalDate angebotsdatum) {
        this.angebotsdatum = angebotsdatum;
    }

    public LocalDate getGueltigBis() {
        return gueltigBis;
    }

    public void setGueltigBis(LocalDate gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Kondition getKonditionNr() {
        return konditionNr;
    }

    public void setKonditionNr(Kondition konditionNr) {
        this.konditionNr = konditionNr;
    }
}
package com.iu.kmi.entities;

import java.util.Date;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;

@Entity(tableName = "Angebot")
public class Angebot {
    @Id
    @Column(name = "angebot_nr")
    private int angebotNr;

    @Column(name = "kunde_nr")
    private String kundeNr;

    @Column(name = "angebotsdatum")
    private Date angebotsdatum;

    @Column(name = "gueltig_bis")
    private Date gueltigBis;

    @Column(name = "waehrung")
    private String waehrung;

    @Column(name = "status")
    private String status;

    @Column(name = "kondition_nr")
    private String konditionNr;

    @Column(name = "kundenanfrage_nr")
    private String kundenanfrageNr;

    // Constructor
    public Angebot(String kundeNr, Date angebotsdatum, Date gueltigBis, String waehrung, String status, String konditionNr, String kundenanfrageNr) {
        this.kundeNr = kundeNr;
        this.angebotsdatum = angebotsdatum;
        this.gueltigBis = gueltigBis;
        this.waehrung = waehrung;
        this.status = status;
        this.konditionNr = konditionNr;
        this.kundenanfrageNr = kundenanfrageNr;
    }

    // Getter & Setter
    /**
     * @return kundenanfrageNr
     */
    public String getKundenanfrageNr(){
        return this.kundenanfrageNr;
    }

    /**
     * Set kundenanfrageNr
     *
     * @param kundenanfrageNr new kundenanfrageNr
     */
    public void setKundenanfrageNr(final String kundenanfrageNr){
        this.kundenanfrageNr = kundenanfrageNr;
    }

    public int getAngebotNr() {
        return angebotNr;
    }

    public void setAngebotNr(int angebotNr) {
        this.angebotNr = angebotNr;
    }

    public String getKundeNr() {
        return kundeNr;
    }

    public void setKundeNr(String kundeNr) {
        this.kundeNr = kundeNr;
    }

    public Date getAngebotsdatum() {
        return angebotsdatum;
    }

    public void setAngebotsdatum(Date angebotsdatum) {
        this.angebotsdatum = angebotsdatum;
    }

    public Date getGueltigBis() {
        return gueltigBis;
    }

    public void setGueltigBis(Date gueltigBis) {
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

    public String getKonditionNr() {
        return konditionNr;
    }

    public void setKonditionNr(String konditionNr) {
        this.konditionNr = konditionNr;
    }
}

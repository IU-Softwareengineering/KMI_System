package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Anthony, Florian
 */
@Entity(tableName = "terminauftrag")
public class Auftrag {

    @Id(name = "terminauftrag_nr")
    private String auftragNr;

    @JoinColumn(name = "kunde_nr", referencedColumnName ="kunde_nr")
    private Kunde kundeNr;

    @Column(name = "auftragsdatum")
    private LocalDateTime auftragsdatum;

    @Column(name = "lieferdatum")
    private LocalDateTime lieferdatum;

    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebotNr;

    @Column(name = "status")
    private String status;

    public Auftrag() {}

    public Auftrag(String auftragNr, Kunde kundeNr, LocalDateTime auftragsdatum, LocalDateTime lieferdatum, Angebot angebotNr, String status) {
        this.auftragNr = auftragNr;
        this.kundeNr = kundeNr;
        this.auftragsdatum = auftragsdatum;
        this.lieferdatum = lieferdatum;
        this.angebotNr = angebotNr;
        this.status = status;
    }

    // Getter und Setter
    public String getAuftragNr() {
        return auftragNr;
    }

    public void setAuftragNr(String auftragNr) {
        this.auftragNr = auftragNr;
    }

    public Kunde getKundeNr() {
        return kundeNr;
    }

    public void setKundeNr(Kunde kundeNr) {
        this.kundeNr = kundeNr;
    }

    public LocalDateTime getAuftragsdatum() {
        return auftragsdatum;
    }

    public void setAuftragsdatum(LocalDateTime auftragsdatum) {
        this.auftragsdatum = auftragsdatum;
    }

    public LocalDateTime getLieferdatum() {
        return lieferdatum;
    }

    public void setLieferdatum(LocalDateTime lieferdatum) {
        this.lieferdatum = lieferdatum;
    }

    public Angebot getAngebotNr() {
        return angebotNr;
    }

    public void setAngebotNr(Angebot angebotNr) {
        this.angebotNr = angebotNr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
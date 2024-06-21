package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

import java.time.LocalDate;
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
    private LocalDate auftragsdatum;

    @Column(name = "lieferdatum")
    private LocalDate lieferdatum;

    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebotNr;

    @Column(name = "status")
    private String status;

    public Auftrag() {}

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

    public LocalDate getAuftragsdatum() {
        return auftragsdatum;
    }

    public void setAuftragsdatum(LocalDate auftragsdatum) {
        this.auftragsdatum = auftragsdatum;
    }

    public LocalDate getLieferdatum() {
        return lieferdatum;
    }

    public void setLieferdatum(LocalDate lieferdatum) {
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
package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

import java.util.Date;

@Entity(tableName = "terminauftrag")
public class Auftrag {

    @Id(name = "terminauftrag_nr")
    private String auftragNr;

    @JoinColumn(name = "kunde_nr", referencedColumnName ="kunde_nr")
    private Kunde kundeNr;

    @Column(name = "auftragsdatum")
    private Date auftragsdatum;

    @Column(name = "lieferdatum")
    private Date lieferdatum;

    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebotNr;

    @Column(name = "status")
    private String status;

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

    public Date getAuftragsdatum() {
        return auftragsdatum;
    }

    public void setAuftragsdatum(Date auftragsdatum) {
        this.auftragsdatum = auftragsdatum;
    }

    public Date getLieferdatum() {
        return lieferdatum;
    }

    public void setLieferdatum(Date lieferdatum) {
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

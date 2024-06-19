package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

import java.util.Date;

@Entity(tableName = "terminauftrag")
public class Auftrag {

    @Id(name = "terminauftrag_nr")
    private String auftragNr;

    @Column(name = "kunde_nr")
    private String kundeNr;

    @Column(name = "auftragsdatum")
    private Date auftragsdatum;

    @Column(name = "lieferdatum")
    private Date lieferdatum;
  
    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private String angebotNr;

    @Column(name = "status")
    private String status;

    // Getter und Setter
    public String getAuftragNr() {
        return auftragNr;
    }

    public void setAuftragNr(String auftragNr) {
        this.auftragNr = auftragNr;
    }

    public String getKundeNr() {
        return kundeNr;
    }

    public void setKundeNr(String kundeNr) {
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

    public String getAngebotNr() {
        return angebotNr;
    }

    public void setAngebotNr(String angebotNr) {
        this.angebotNr = angebotNr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Auftrag() {
        
    }
    
}

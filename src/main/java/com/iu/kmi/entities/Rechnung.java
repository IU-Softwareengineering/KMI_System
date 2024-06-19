package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "rechnung")
public class Rechnung {
    @Id(name = "rechnung_nr")
    private String rechnungNr;

    @JoinColumn(name = "debitor_nr", referencedColumnName = "debitor_nr")
    private Debitor debitorNr;

    @Column(name = "rechnungstag")
    private String rechnungsTag;

    @Column(name = "faelligkeitsdatum")
    private String faelligkeitsDatum;

    @Column(name = "status")
    private String status;

    public Rechnung() {

    }

    public Rechnung(String rechnungNr, Debitor debitorNr, String rechnungsTag, String faelligkeitsDatum, String status) {
        this.rechnungNr = rechnungNr;
        this.debitorNr = debitorNr;
        this.rechnungsTag = rechnungsTag;
        this.faelligkeitsDatum = faelligkeitsDatum;
        this.status = status;
    }

    // Getter und Setter

    public String getRechnungNr() {
        return rechnungNr;
    }

    public void setRechnungNr(String rechnungNr) {
        this.rechnungNr = rechnungNr;
    }

    public Debitor getDebitorNr() {
        return debitorNr;
    }

    public void setDebitorNr(Debitor debitorNr) {
        this.debitorNr = debitorNr;
    }

    public String getRechnungsTag() {
        return rechnungsTag;
    }

    public void setRechnungsTag(String rechnungsTag) {
        this.rechnungsTag = rechnungsTag;
    }

    public String getFaelligkeitsDatum() {
        return faelligkeitsDatum;
    }

    public void setFaelligkeitsDatum(String faelligkeitsDatum) {
        this.faelligkeitsDatum = faelligkeitsDatum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
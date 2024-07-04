package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

@Entity(tableName = "offener_posten")
public class OffenerPosten {
    @Id(name = "offener_posten_nr")
    private String offenerPostenNr;

    @JoinColumn(name = "rechnung_nr", referencedColumnName = "rechnung_nr")
    private Rechnung rechnungNr;

    @Column(name = "betrag")
    private double betrag;

    @Column(name = "zahlungsziel")
    private String zahlungsziel;

    @Column(name = "zahlungsdatum")
    private String zahlungsdatum;

    public OffenerPosten() {
    }

    public String getOffenerPostenNr() {
        return offenerPostenNr;
    }

    public void setOffenerPostenNr(String offenerPostenNr) {
        this.offenerPostenNr = offenerPostenNr;
    }

    public Rechnung getRechnungNr() {
        return rechnungNr;
    }

    public void setRechnungNr(Rechnung rechnungNr) {
        this.rechnungNr = rechnungNr;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public String getZahlungsziel() {
        return zahlungsziel;
    }

    public void setZahlungsziel(String zahlungsziel) {
        this.zahlungsziel = zahlungsziel;
    }

    public String getZahlungsdatum() {
        return zahlungsdatum;
    }

    public void setZahlungsdatum(String zahlungsdatum) {
        this.zahlungsdatum = zahlungsdatum;
    }
}

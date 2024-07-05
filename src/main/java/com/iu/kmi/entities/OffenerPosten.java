package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(tableName = "offener_posten")
public class OffenerPosten {
    @Id(name = "offener_posten_nr")
    private String offenerPostenNr;

    @JoinColumn(name = "rechnung_nr", referencedColumnName = "rechnung_nr")
    private Rechnung rechnungNr;

    @JoinColumn(name = "debitor_nr", referencedColumnName = "debitor_nr")
    private Debitor debitorNr;

    @Column(name = "betrag")
    private BigDecimal betrag;

    @Column(name = "status")
    private String status;

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

    public Debitor getDebitorNr() {
        return debitorNr;
    }

    public void setDebitorNr(Debitor debitorNr) {
        this.debitorNr = debitorNr;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

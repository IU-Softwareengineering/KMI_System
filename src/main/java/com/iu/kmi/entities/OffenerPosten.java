package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

import java.math.BigDecimal;

@Entity(tableName = "offener_posten")
public class OffenerPosten {
    @Id(name = "offener_posten_nr")
    private String offener_posten_nr;
    
    @Column(name = "rechnung_nr")
    private String rechnung_nr;
    
    @JoinColumn(name = "rechnung_nr", referencedColumnName = "rechnung_nr")
    private Rechnung rechnung;
    
    @JoinColumn(name = "debitor_nr", referencedColumnName = "debitor_nr")
    private Debitor debitor;
    
    @Column(name = "debitor_nr")
    private String debitor_nr;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "gesamtbetrag")
    private BigDecimal gesamtbetrag;

    public OffenerPosten() {}

    // Getter and Setter methods

    public String getOffener_posten_nr() {
        return offener_posten_nr;
    }

    public void setOffener_posten_nr(String offener_posten_nr) {
        this.offener_posten_nr = offener_posten_nr;
    }

    public String getRechnung_nr() {
        return rechnung_nr;
    }

    public void setRechnung_nr(String rechnung_nr) {
        this.rechnung_nr = rechnung_nr;
    }

    public Rechnung getRechnung() {
        return rechnung;
    }

    public void setRechnung(Rechnung rechnung) {
        this.rechnung = rechnung;
        if (rechnung != null) {
            this.debitor = rechnung.getDebitor();
            if (debitor != null) {
                this.debitor_nr = debitor.getDebitor_nr();
            }
        }
    }

    public Debitor getDebitor() {
        return debitor;
    }

    public void setDebitor(Debitor debitor) {
        this.debitor = debitor;
        if (debitor != null) {
            this.debitor_nr = debitor.getDebitor_nr();
        }
    }

    public String getDebitor_nr() {
        return debitor_nr;
    }

    public void setDebitor_nr(String debitor_nr) {
        this.debitor_nr = debitor_nr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getGesamtbetrag() {
        return gesamtbetrag;
    }

    public void setGesamtbetrag(BigDecimal gesamtbetrag) {
        this.gesamtbetrag = gesamtbetrag;
    }
}

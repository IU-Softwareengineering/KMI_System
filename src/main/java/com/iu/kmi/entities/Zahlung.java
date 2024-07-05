package com.iu.kmi.entities;

import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(tableName = "zahlung")
public class Zahlung {
    @Id(name = "zahlung_nr")
    private String zahlung_nr;
    
    @Column(name = "offener_posten_nr")
    private String offener_posten_nr;
    
    @JoinColumn(name = "debitor_nr", referencedColumnName = "debitor_nr")
    private Debitor debitor;
    
    @Column(name = "debitor_nr")
    private String debitor_nr;
    
    @Column(name = "betrag")
    private BigDecimal betrag;
    
    @Column(name = "zahlungsdatum")
    private LocalDateTime zahlungsdatum;

    public Zahlung() {}

    // Getter and Setter methods

    public String getZahlung_nr() {
        return zahlung_nr;
    }

    public void setZahlung_nr(String zahlung_nr) {
        this.zahlung_nr = zahlung_nr;
    }

    public String getOffener_posten_nr() {
        return offener_posten_nr;
    }

    public void setOffener_posten_nr(String offener_posten_nr) {
        this.offener_posten_nr = offener_posten_nr;
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

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public LocalDateTime getZahlungsdatum() {
        return zahlungsdatum;
    }

    public void setZahlungsdatum(LocalDateTime zahlungsdatum) {
        this.zahlungsdatum = zahlungsdatum;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;
import java.util.Objects;

/**
 *
 * @author Julian
 */
@Entity(tableName = "kundenanfrage")
public class Kundenauftrag {
    @Id(name = "kundenanfrage_nr")
    private String kundenauftragNummer;
    @JoinColumn(name = "kunde_nr", referencedColumnName = "kunde_nr")
    private String kundenNummer;
    @Column(name = "anfragedatum")
    private String anfragedatum;
    @Column(name = "anfragebeschreibung")
    private String anfragebeschreibung;
    @Column(name = "prioritaet")
    private String prioritaet;
    @Column(name = "status")
    private String status;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kundenauftrag other = (Kundenauftrag) obj;
        if (!Objects.equals(this.kundenauftragNummer, other.kundenauftragNummer)) {
            return false;
        }
        if (!Objects.equals(this.kundenNummer, other.kundenNummer)) {
            return false;
        }
        if (!Objects.equals(this.anfragedatum, other.anfragedatum)) {
            return false;
        }
        if (!Objects.equals(this.anfragebeschreibung, other.anfragebeschreibung)) {
            return false;
        }
        if (!Objects.equals(this.prioritaet, other.prioritaet)) {
            return false;
        }
        return Objects.equals(this.status, other.status);
    }

    public Kundenauftrag(String kundenauftragNummer, String kundenNummer, String anfragedatum, String anfragebeschreibung, String prioritaet, String status) {
        this.kundenauftragNummer = kundenauftragNummer;
        this.kundenNummer = kundenNummer;
        this.anfragedatum = anfragedatum;
        this.anfragebeschreibung = anfragebeschreibung;
        this.prioritaet = prioritaet;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Kundenauftrag{" + "kundenauftragNummer=" + kundenauftragNummer + ", kundenNummer=" + kundenNummer + ", anfragedatum=" + anfragedatum + ", anfragebeschreibung=" + anfragebeschreibung + ", prioritaet=" + prioritaet + ", status=" + status + '}';
    }

    public void setKundenauftragNummer(String kundenauftragNummer) {
        this.kundenauftragNummer = kundenauftragNummer;
    }

    public void setKundenNummer(String kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public void setAnfragedatum(String anfragedatum) {
        this.anfragedatum = anfragedatum;
    }

    public void setAnfragebeschreibung(String anfragebeschreibung) {
        this.anfragebeschreibung = anfragebeschreibung;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKundenauftragNummer() {
        return kundenauftragNummer;
    }

    public String getKundenNummer() {
        return kundenNummer;
    }

    public String getAnfragedatum() {
        return anfragedatum;
    }

    public String getAnfragebeschreibung() {
        return anfragebeschreibung;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public String getStatus() {
        return status;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iu.kmi.entities;
import com.iu.kmi.database.annotations.Column;
import com.iu.kmi.database.annotations.Entity;
import com.iu.kmi.database.annotations.Id;
import com.iu.kmi.database.annotations.JoinColumn;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Julian
 */
@Entity(tableName = "terminauftrag")
public class Kundenauftrag {
    @Id(name = "terminauftrag_nr")
    private String kundenauftragNummer;
    @JoinColumn(name = "kunde_nr", referencedColumnName = "kunde_nr")
    private Kunde kundenNummer;
    @Column(name = "auftragsdatum")
    private LocalDateTime auftragsdatum;
    @Column(name = "lieferdatum")
    private LocalDateTime lieferdatum;
    @JoinColumn(name = "angebot_nr", referencedColumnName = "angebot_nr")
    private Angebot angebot_nr;
    @Column(name = "status")
    private String status;

    public Kundenauftrag() throws IllegalArgumentException{
        throw new IllegalArgumentException();
    }

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
        if (!Objects.equals(this.angebot_nr, other.angebot_nr)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.auftragsdatum, other.auftragsdatum)) {
            return false;
        }
        return Objects.equals(this.lieferdatum, other.lieferdatum);
    }

    @Override
    public String toString() {
        return "Kundenauftrag{" + "kundenauftragNummer=" + kundenauftragNummer + ", kundenNummer=" + kundenNummer + ", auftragsdatum=" + auftragsdatum + ", lieferdatum=" + lieferdatum + ", angebot_nr=" + angebot_nr + ", status=" + status + '}';
    }

    public Kundenauftrag(String kundenauftragNummer, Kunde kundenNummer, LocalDateTime auftragsdatum, LocalDateTime lieferdatum, Angebot angebot_nr, String status) {
        this.kundenauftragNummer = kundenauftragNummer;
        this.kundenNummer = kundenNummer;
        this.auftragsdatum = auftragsdatum;
        this.lieferdatum = lieferdatum;
        this.angebot_nr = angebot_nr;
        this.status = status;
    }

    public void setKundenauftragNummer(String kundenauftragNummer) {
        this.kundenauftragNummer = kundenauftragNummer;
    }

    public void setKundenNummer(Kunde kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public void setAuftragsdatum(LocalDateTime auftragsdatum) {
        this.auftragsdatum = auftragsdatum;
    }

    public void setLieferdatum(LocalDateTime lieferdatum) {
        this.lieferdatum = lieferdatum;
    }

    public void setAngebot_nr(Angebot angebot_nr) {
        this.angebot_nr = angebot_nr;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKundenauftragNummer() {
        return kundenauftragNummer;
    }

    public Kunde getKundenNummer() {
        return kundenNummer;
    }

    public LocalDateTime getAuftragsdatum() {
        return auftragsdatum;
    }

    public LocalDateTime getLieferdatum() {
        return lieferdatum;
    }

    public Angebot getAngebot_nr() {
        return angebot_nr;
    }

    public String getStatus() {
        return status;
    }
}

   
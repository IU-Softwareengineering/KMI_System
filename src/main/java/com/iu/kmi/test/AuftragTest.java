package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class AuftragTest {

    @Test
    public void testAuftragEntity() {

        Auftrag auftrag = new Auftrag();


        String auftragNr = "A-12345";
        Adresse adresse = new Adresse("Strasse", "1", "123456", "Berlin", "Deutschland");
        Kunde kundeNr = new Kunde("K123", "Mustermann", "Max", "max@gmail.com", "0123456789", adresse);
        LocalDate auftragsdatum = LocalDate.now();
        LocalDate lieferdatum = LocalDate.now().plusDays(7);
        Kondition kondition = new Kondition("KondiName", "Bar", "innerhalb Stadt", BigDecimal.valueOf(0));
        Angebot angebotNr = new Angebot(kundeNr, LocalDate.now(), LocalDate.now().plusDays(7), "EUR", "Offen", kondition);
        String status = "In Bearbeitung";


        auftrag.setAuftragNr(auftragNr);
        auftrag.setKundeNr(kundeNr);
        auftrag.setAuftragsdatum(auftragsdatum);
        auftrag.setLieferdatum(lieferdatum);
        auftrag.setAngebotNr(angebotNr);
        auftrag.setStatus(status);

        assertEquals(auftragNr, auftrag.getAuftragNr());
        assertEquals(kundeNr, auftrag.getKundeNr());
        assertEquals(auftragsdatum, auftrag.getAuftragsdatum());
        assertEquals(lieferdatum, auftrag.getLieferdatum());
        assertEquals(angebotNr, auftrag.getAngebotNr());
        assertEquals(status, auftrag.getStatus());
    }
}

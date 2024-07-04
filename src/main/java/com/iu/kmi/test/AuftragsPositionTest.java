package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AuftragsPositionTest {

    @Test
    public void testEntity() {
        Adresse testAdresse = new Adresse("Strasse","1","123456","Berlin","Deutschland");
        Kunde testKunde = new Kunde("K12345","Mustermann","Max","max@gmail.com","0123456789", testAdresse);
        Angebot testAngebot = new Angebot(testKunde, LocalDate.now(), LocalDate.now().plusDays(7), "EUR", "Offen", new Kondition("KondiName","Bar","innerhalb Stadt", BigDecimal.valueOf(0)));
        Auftrag testAuftrag = new Auftrag("A123", testKunde, LocalDate.now(), LocalDate.now().plusDays(7), testAngebot, "Offen");
        Material testArtikel = new Material("ART123", "TestName", "TestBeschreibung", "TestLieferant", BigDecimal.valueOf(15), BigDecimal.valueOf(20));

        AuftragsPosition auftragsPosition = new AuftragsPosition();
        auftragsPosition.setAuftragspositionNr("AP123");
        auftragsPosition.setAuftragsNr(testAuftrag);
        auftragsPosition.setArtikelNr(testArtikel);

        assertEquals("AP123", auftragsPosition.getAuftragspositionNr());
        assertEquals(testAuftrag, auftragsPosition.getAuftragsNr());
        assertEquals(testArtikel, auftragsPosition.getArtikelNr());

        auftragsPosition.setAuftragspositionNr("AP456");
        assertEquals("AP456", auftragsPosition.getAuftragspositionNr());

        Auftrag testAuftrag2 = new Auftrag("A456", testKunde, LocalDate.now(), LocalDate.now().plusDays(7), testAngebot, "Offen");
        auftragsPosition.setAuftragsNr(testAuftrag2);
        assertEquals(testAuftrag2, auftragsPosition.getAuftragsNr());

        Material testArtikel2 = new Material("ART456", "TestName", "TestBeschreibung", "TestLieferant", BigDecimal.valueOf(15), BigDecimal.valueOf(20));
        auftragsPosition.setArtikelNr(testArtikel2);
        assertEquals(testArtikel2, auftragsPosition.getArtikelNr());
    }

}

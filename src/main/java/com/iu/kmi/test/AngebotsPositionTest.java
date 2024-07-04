package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AngebotsPositionTest {

    @Test
    public void testEntity() {

        Adresse adresse = new Adresse("Strasse", "1", "123456", "Berlin", "Deutschland");
        Kunde kunde = new Kunde("K123", "Mustermann", "Max", "max@gmail.com", "0123456789", adresse);
        Kondition kondition = new Kondition("KondiName", "Bar", "innerhalb Stadt", BigDecimal.valueOf(0));
        Angebot angebot = new Angebot(kunde, LocalDate.now(), LocalDate.now().plusDays(7), "EUR", "Offen", kondition);
        Material artikel = new Material("ART101", "TestName", "TestBeschreibung", "TestLieferant", BigDecimal.valueOf(15), BigDecimal.valueOf(20));

        AngebotsPosition angebotsPosition = new AngebotsPosition();
        angebotsPosition.setAngebotspositionNr("ANG101");
        angebotsPosition.setAngebotNr(angebot);
        angebotsPosition.setArtikelNr(artikel);
        angebotsPosition.setEinzelpreis(130.60);

        assertEquals("ANG101", angebotsPosition.getAngebotspositionNr());
        assertEquals(angebot, angebotsPosition.getAngebotNr());
        assertEquals(artikel, angebotsPosition.getArtikelNr());
        assertEquals(130.60, angebotsPosition.getEinzelpreis(), 0.001);

        angebotsPosition.setAngebotspositionNr("ANG102");
        assertEquals("ANG102", angebotsPosition.getAngebotspositionNr());

        Angebot angebot2 = new Angebot(kunde, LocalDate.now().plusDays(1), LocalDate.now().plusDays(8), "EUR", "Offen", kondition);
        angebotsPosition.setAngebotNr(angebot2);
        assertEquals(angebot2, angebotsPosition.getAngebotNr());

        Material artikel2 = new Material("ART102", "TestName", "TestBeschreibung", "TestLieferant", BigDecimal.valueOf(15), BigDecimal.valueOf(20));
        angebotsPosition.setArtikelNr(artikel2);
        assertEquals(artikel2, angebotsPosition.getArtikelNr());

        angebotsPosition.setEinzelpreis(160.85);
        assertEquals(160.85, angebotsPosition.getEinzelpreis(), 0.001);
    }

}
package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.Kundenanfrage;
import com.iu.kmi.entities.Material;
import org.junit.Test;

import com.iu.kmi.entities.AnfragePosition;

import java.math.BigDecimal;

public class AnfragePositionTest {

    @Test
    public void testEntity() {

        Kundenanfrage kundenanfrage = new Kundenanfrage();
        Material material = new Material();

        AnfragePosition anfragePosition = new AnfragePosition();
        anfragePosition.setAnfragepositionNr("ANF001");
        anfragePosition.setAnfrageNr(kundenanfrage);
        anfragePosition.setArtikelNr(material);
        anfragePosition.setEinzelpreis(BigDecimal.valueOf(120.50));

        assertEquals("ANF001", anfragePosition.getAnfragepositionNr());
        assertEquals(kundenanfrage, anfragePosition.getAnfrageNr());
        assertEquals(material, anfragePosition.getArtikelNr());
        assertEquals(BigDecimal.valueOf(120.50), anfragePosition.getEinzelpreis());

        anfragePosition.setAnfragepositionNr("ANF002");
        assertEquals("ANF002", anfragePosition.getAnfragepositionNr());

        Kundenanfrage kundenanfrage2 = new Kundenanfrage();
        anfragePosition.setAnfrageNr(kundenanfrage2);
        assertEquals(kundenanfrage2, anfragePosition.getAnfrageNr());

        Material material2 = new Material();
        anfragePosition.setArtikelNr(material2);
        assertEquals(material2, anfragePosition.getArtikelNr());

        anfragePosition.setEinzelpreis(BigDecimal.valueOf(150.75));
        assertEquals(BigDecimal.valueOf(150.75), anfragePosition.getEinzelpreis());
    }

}
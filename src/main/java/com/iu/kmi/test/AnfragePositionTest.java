package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.iu.kmi.entities.AnfragePosition;

public class AnfragePositionTest {

    @Test
    public void testEntity() {

        AnfragePosition anfragePosition = new AnfragePosition();
        anfragePosition.setAnfragepositionNr("ANF001");
        anfragePosition.setAnfrageNr("ANQ001");
        anfragePosition.setArtikelNr("ART001");
        anfragePosition.setEinzelpreis(120.50);

        assertEquals("ANF001", anfragePosition.getAnfragepositionNr());
        assertEquals("ANQ001", anfragePosition.getAnfrageNr());
        assertEquals("ART001", anfragePosition.getArtikelNr());
        assertEquals(120.50, anfragePosition.getEinzelpreis(), 0.001);

        anfragePosition.setAnfragepositionNr("ANF002");
        assertEquals("ANF002", anfragePosition.getAnfragepositionNr());

        anfragePosition.setAnfrageNr("ANQ002");
        assertEquals("ANQ002", anfragePosition.getAnfrageNr());

        anfragePosition.setArtikelNr("ART002");
        assertEquals("ART002", anfragePosition.getArtikelNr());

        anfragePosition.setEinzelpreis(150.75);
        assertEquals(150.75, anfragePosition.getEinzelpreis(), 0.001);
    }

}
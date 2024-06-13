package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.iu.kmi.entities.AngebotsPosition;

public class AngebotsPositionTest {

    @Test
    public void testEntity() {

        AngebotsPosition angebotsPosition = new AngebotsPosition();
        angebotsPosition.setAngebotspositionNr("ANG101");
        angebotsPosition.setAngebotNr("ANGQ101");
        angebotsPosition.setArtikelNr("ART101");
        angebotsPosition.setEinzelpreis(130.60);

        assertEquals("ANG101", angebotsPosition.getAngebotspositionNr());
        assertEquals("ANGQ101", angebotsPosition.getAngebotNr());
        assertEquals("ART101", angebotsPosition.getArtikelNr());
        assertEquals(130.60, angebotsPosition.getEinzelpreis(), 0.001);

        angebotsPosition.setAngebotspositionNr("ANG102");
        assertEquals("ANG102", angebotsPosition.getAngebotspositionNr());

        angebotsPosition.setAngebotNr("ANGQ102");
        assertEquals("ANGQ102", angebotsPosition.getAngebotNr());

        angebotsPosition.setArtikelNr("ART102");
        assertEquals("ART102", angebotsPosition.getArtikelNr());

        angebotsPosition.setEinzelpreis(160.85);
        assertEquals(160.85, angebotsPosition.getEinzelpreis(), 0.001);
    }

}
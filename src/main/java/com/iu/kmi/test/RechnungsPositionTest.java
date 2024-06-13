package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.iu.kmi.entities.RechnungsPosition;

public class RechnungsPositionTest {

    @Test
    public void testEntity() {

        RechnungsPosition rechnungsPosition = new RechnungsPosition();
        rechnungsPosition.setRechnungspositionNr("REC201");
        rechnungsPosition.setRechnungNr("RECQ201");
        rechnungsPosition.setArtikelNr("ART201");
        rechnungsPosition.setEinzelpreis(149.90);

        assertEquals("REC201", rechnungsPosition.getRechnungspositionNr());
        assertEquals("RECQ201", rechnungsPosition.getRechnungNr());
        assertEquals("ART201", rechnungsPosition.getArtikelNr());
        assertEquals(149.90, rechnungsPosition.getEinzelpreis(), 0.001);

        rechnungsPosition.setRechnungspositionNr("REC202");
        assertEquals("REC202", rechnungsPosition.getRechnungspositionNr());

        rechnungsPosition.setRechnungNr("RECQ202");
        assertEquals("RECQ202", rechnungsPosition.getRechnungNr());

        rechnungsPosition.setArtikelNr("ART202");
        assertEquals("ART202", rechnungsPosition.getArtikelNr());

        rechnungsPosition.setEinzelpreis(179.99);
        assertEquals(179.99, rechnungsPosition.getEinzelpreis(), 0.001);
    }

}
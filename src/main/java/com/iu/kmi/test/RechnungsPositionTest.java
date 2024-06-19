package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.Artikel;
import com.iu.kmi.entities.Rechnung;
import org.junit.Test;

import com.iu.kmi.entities.RechnungsPosition;

public class RechnungsPositionTest {

    @Test
    public void testEntity() {
        RechnungsPosition rechnungsPosition = new RechnungsPosition();
        rechnungsPosition.setRechnungspositionNr("REC201");
        rechnungsPosition.setRechnung(new Rechnung("RECQ201", null, null, null, null));
        rechnungsPosition.setArtikel(new Artikel());

        assertEquals("REC201", rechnungsPosition.getRechnungspositionNr());
        assertEquals("RECQ201", rechnungsPosition.getRechnung().getRechnungNr());
        assertEquals("ART201", rechnungsPosition.getArtikel().getArtikelNr());

        rechnungsPosition.setRechnungspositionNr("REC202");
        assertEquals("REC202", rechnungsPosition.getRechnungspositionNr());

        rechnungsPosition.getRechnung().setRechnungNr("RECQ202");
        assertEquals("RECQ202", rechnungsPosition.getRechnung().getRechnungNr());

        rechnungsPosition.setArtikel(new Artikel("ART202", null, null, null, 0, 0));
        assertEquals("ART202", rechnungsPosition.getArtikel().getArtikelNr());
    }

}
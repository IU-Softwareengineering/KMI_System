package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.Artikel;
import com.iu.kmi.entities.Material;
import com.iu.kmi.entities.Rechnung;
import org.junit.Test;

import com.iu.kmi.entities.RechnungsPosition;

import java.math.BigDecimal;

public class RechnungsPositionTest {

    @Test
    public void testEntity() {
        RechnungsPosition rechnungsPosition = new RechnungsPosition();
        rechnungsPosition.setRechnungspositionNr("REC201");
        rechnungsPosition.setRechnungNr(new Rechnung("RECQ201", null, null, null, null));
        rechnungsPosition.setArtikelNr(new Material());

        assertEquals("REC201", rechnungsPosition.getRechnungspositionNr());
        assertEquals("RECQ201", rechnungsPosition.getRechnungNr().getRechnungNr());
        assertEquals("ART201", rechnungsPosition.getArtikelNr().getArtikelNr());

        rechnungsPosition.setRechnungspositionNr("REC202");
        assertEquals("REC202", rechnungsPosition.getRechnungspositionNr());

        rechnungsPosition.getRechnungNr().setRechnungNr("RECQ202");
        assertEquals("RECQ202", rechnungsPosition.getRechnungNr().getRechnungNr());

        rechnungsPosition.setArtikelNr(new Material("ART202", null, null, null, BigDecimal.valueOf(0), BigDecimal.valueOf(0)));
        assertEquals("ART202", rechnungsPosition.getArtikelNr().getArtikelNr());
    }

}
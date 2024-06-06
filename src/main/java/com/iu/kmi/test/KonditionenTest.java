package com.iu.kmi.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.iu.kmi.entities.Konditionen;

public class KonditionenTest {

    @Test
    public void testConstructorAndGetters() {
        Konditionen konditionen = new Konditionen("Standard", "Kreditkarte", "Lieferung in 5 Tagen", 10.0);
        assertEquals("Standard", konditionen.getName());
        assertEquals("Kreditkarte", konditionen.getZahlungsmethode());
        assertEquals("Lieferung in 5 Tagen", konditionen.getLieferbedingungen());
        assertEquals(10.0, konditionen.getRabatt());
    }

    @Test
    public void testSetters() {
        Konditionen konditionen = new Konditionen("Standard", "Kreditkarte", "Lieferung in 5 Tagen", 10.0);
        konditionen.setId(1);
        konditionen.setName("Premium");
        konditionen.setZahlungsmethode("PayPal");
        konditionen.setLieferbedingungen("Lieferung in 2 Tagen");
        konditionen.setRabatt(15.0);

        assertEquals(1, konditionen.getId());
        assertEquals("Premium", konditionen.getName());
        assertEquals("PayPal", konditionen.getZahlungsmethode());
        assertEquals("Lieferung in 2 Tagen", konditionen.getLieferbedingungen());
        assertEquals(15.0, konditionen.getRabatt());
    }

    @Test
    public void testToString() {
        Konditionen konditionen = new Konditionen("Standard", "Kreditkarte", "Lieferung in 5 Tagen", 10.0);
        konditionen.setId(1);
        String expected = "BedingungEntitaet{id=1, name='Standard', zahlungsmethode='Kreditkarte', lieferbedingungen='Lieferung in 5 Tagen', rabatt=10.0}";
        assertEquals(expected, konditionen.toString());
    }
}

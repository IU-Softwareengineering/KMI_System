package com.iu.kmi.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.iu.kmi.entities.Kondition;

public class KonditionTest {

    @Test
    public void testConstructorAndGetters() {
        Kondition kondition = new Kondition("Standard", "Kreditkarte", "Lieferung in 5 Tagen", 10.0);
        assertEquals("Standard", kondition.getName());
        assertEquals("Kreditkarte", kondition.getZahlungsmethode());
        assertEquals("Lieferung in 5 Tagen", kondition.getLieferbedingungen());
        assertEquals(10.0, kondition.getRabatt());
    }

    @Test
    public void testSetters() {
        Kondition kondition = new Kondition("Standard", "Kreditkarte", "Lieferung in 5 Tagen", 10.0);
        kondition.setId(1);
        kondition.setName("Premium");
        kondition.setZahlungsmethode("PayPal");
        kondition.setLieferbedingungen("Lieferung in 2 Tagen");
        kondition.setRabatt(15.0);

        assertEquals(1, kondition.getId());
        assertEquals("Premium", kondition.getName());
        assertEquals("PayPal", kondition.getZahlungsmethode());
        assertEquals("Lieferung in 2 Tagen", kondition.getLieferbedingungen());
        assertEquals(15.0, kondition.getRabatt());
    }

    @Test
    public void testToString() {
        Kondition kondition = new Kondition("Standard", "Kreditkarte", "Lieferung in 5 Tagen", 10.0);
        kondition.setId(1);
        String expected = "BedingungEntitaet{id=1, name='Standard', zahlungsmethode='Kreditkarte', lieferbedingungen='Lieferung in 5 Tagen', rabatt=10.0}";
        assertEquals(expected, kondition.toString());
    }
}

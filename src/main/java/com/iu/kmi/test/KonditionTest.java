package com.iu.kmi.test;


import org.junit.Test;

import com.iu.kmi.entities.Kondition;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Julian Treichel
 * @since 07.06.2024
 */
public class KonditionTest {

    /**
     * Testet den Konstruktor und die Getter-Methoden der Kondition-Klasse.
     */
    @Test
    public void testConstructorAndGetters() {
        // Erstellt eine neue Kondition mit den angegebenen Parametern
        Kondition kondition = new Kondition("Standard", "Kreditkarte", "Lieferung in 5 Tagen", BigDecimal.valueOf(10.0));

        // Überprüft, ob die Getter-Methoden die erwarteten Werte zurückgeben
        assertEquals("Standard", kondition.getName());
        assertEquals("Kreditkarte", kondition.getZahlungsbedingungen());
        assertEquals("Lieferung in 5 Tagen", kondition.getLieferbedingungen());
        assertEquals(BigDecimal.valueOf(10.0), kondition.getRabatt());
    }

    /**
     * Testet die Setter-Methoden der Kondition-Klasse.
     */
    @Test
    public void testSetters() {
        // Erstellt eine neue Kondition mit den angegebenen Parametern
        Kondition kondition = new Kondition("Standard", "Kreditkarte", "Lieferung in 5 Tagen", BigDecimal.valueOf(10.0));

        // Setzt neue Werte für die Eigenschaften der Kondition
        kondition.setKonditionNr("1");
        kondition.setName("Premium");
        kondition.setZahlungsbedingungen("PayPal");
        kondition.setLieferbedingungen("Lieferung in 2 Tagen");
        kondition.setRabatt(BigDecimal.valueOf(15.0));

        // Überprüft, ob die Setter-Methoden die neuen Werte korrekt setzen
        assertEquals("1", kondition.getKonditionNr());
        assertEquals("Premium", kondition.getName());
        assertEquals("PayPal", kondition.getZahlungsbedingungen());
        assertEquals("Lieferung in 2 Tagen", kondition.getLieferbedingungen());
        assertEquals(BigDecimal.valueOf(15.0), kondition.getRabatt());
    }

    /**
     * Testet die toString-Methode der Kondition-Klasse.
     */
    @Test
    public void testToString() {
        // Erstellt eine neue Kondition mit den angegebenen Parametern
        Kondition kondition = new Kondition("Standard", "Kreditkarte", "Lieferung in 5 Tagen", BigDecimal.valueOf(10.0));

        // Setzt die KonditionenNr der Kondition
        kondition.setKonditionNr("1");

        // Erwartete String-Repräsentation der Kondition
        String expected = "Kondition{konditionenNr=1, name='Standard', zahlungsmethode='Kreditkarte', lieferbedingungen='Lieferung in 5 Tagen', rabatt=10.0}";

        // Überprüft, ob die toString-Methode die erwartete String-Repräsentation zurückgibt
        assertEquals(expected, kondition.toString());
    }
}

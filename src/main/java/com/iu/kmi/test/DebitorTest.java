package com.iu.kmi.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.iu.kmi.entities.Debitor;

/**
 * @author Julian Treichel
 * @since 07.06.2024
 */
public class DebitorTest {

    /**
     * Testet den Konstruktor und die Getter-Methoden der Debitor-Klasse.
     */
    @Test
    public void testConstructorAndGetters() {
        // Erstellt einen neuen Debitor mit den angegebenen Parametern
        Debitor debitor = new Debitor("1", "Meier", "Hans", "Firma GmbH", "123456789", "987654321");

        // Überprüft, ob die Getter-Methoden die erwarteten Werte zurückgeben
        assertEquals("1", debitor.getDebitorNr());
        assertEquals("Meier", debitor.getName());
        assertEquals("Hans", debitor.getVorname());
        assertEquals("Firma GmbH", debitor.getFirma());
        assertEquals("123456789", debitor.getKontoNr());
        assertEquals("987654321", debitor.getAdresseNr());
    }

    /**
     * Testet die Setter-Methoden der Debitor-Klasse.
     */
    @Test
    public void testSetters() {
        // Erstellt einen neuen Debitor mit den angegebenen Parametern
        Debitor debitor = new Debitor("1", "Meier", "Hans", "Firma GmbH", "123456789", "987654321");

        // Setzt neue Werte für die Eigenschaften des Debitors
        debitor.setDebitorNr("2");
        debitor.setName("Schmidt");
        debitor.setVorname("Peter");
        debitor.setFirma("NeueFirma AG");
        debitor.setKontoNr("987654321");
        debitor.setAdresseNr("123456789");

        // Überprüft, ob die Setter-Methoden die neuen Werte korrekt setzen
        assertEquals("2", debitor.getDebitorNr());
        assertEquals("Schmidt", debitor.getName());
        assertEquals("Peter", debitor.getVorname());
        assertEquals("NeueFirma AG", debitor.getFirma());
        assertEquals("987654321", debitor.getKontoNr());
        assertEquals("123456789", debitor.getAdresseNr());
    }

    /**
     * Testet die toString-Methode der Debitor-Klasse.
     */
    @Test
    public void testToString() {
        // Erstellt einen neuen Debitor mit den angegebenen Parametern
        Debitor debitor = new Debitor("1", "Meier", "Hans", "Firma GmbH", "123456789", "987654321");

        // Erwartete String-Repräsentation des Debitors
        String expected = "Debitor{debitorNr='1', name='Meier', vorname='Hans', firma='Firma GmbH', kontoNr='123456789', adresseNr='987654321'}";

        // Überprüft, ob die toString-Methode die erwartete String-Repräsentation zurückgibt
        assertEquals(expected, debitor.toString());
    }
}

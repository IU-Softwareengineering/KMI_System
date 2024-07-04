package com.iu.kmi.test;


import com.iu.kmi.entities.Adresse;
import org.junit.Test;

import com.iu.kmi.entities.Debitor;

import static org.junit.Assert.assertEquals;

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
        Adresse testAdresse = new Adresse("Musterstraße", "1", "12345", "Musterstadt", "Musterland");
        Debitor debitor = new Debitor("1", "Meier", "Hans", "Firma GmbH", "123456789", testAdresse);

        // Überprüft, ob die Getter-Methoden die erwarteten Werte zurückgeben
        assertEquals("1", debitor.getDebitorNr());
        assertEquals("Meier", debitor.getName());
        assertEquals("Hans", debitor.getVorname());
        assertEquals("Firma GmbH", debitor.getFirma());
        assertEquals("123456789", debitor.getKontoNr());
        assertEquals(testAdresse, debitor.getAdresseNr());
    }

    /**
     * Testet die Setter-Methoden der Debitor-Klasse.
     */
    @Test
    public void testSetters() {
        // Erstellt einen neuen Debitor mit den angegebenen Parametern
        Adresse testAdresse2 = new Adresse("Mustergasse", "2", "56789", "Stadt", "Land");
        Debitor debitor = new Debitor("2", "Mustermann", "Max", "Unternehmen UG", "987654321", testAdresse2);

        // Erstellt eine neue Adresse
        Adresse testAdresse3 = new Adresse("NeueStraße", "3", "98765", "NeueStadt", "NeuesLand");

        // Setzt neue Werte für die Eigenschaften des Debitors
        debitor.setDebitorNr("2");
        debitor.setName("Schmidt");
        debitor.setVorname("Peter");
        debitor.setFirma("NeueFirma AG");
        debitor.setKontoNr("192837465");
        debitor.setAdresse(testAdresse3);

        // Überprüft, ob die Setter-Methoden die neuen Werte korrekt setzen
        assertEquals("2", debitor.getDebitorNr());
        assertEquals("Schmidt", debitor.getName());
        assertEquals("Peter", debitor.getVorname());
        assertEquals("NeueFirma AG", debitor.getFirma());
        assertEquals("987654321", debitor.getKontoNr());
        assertEquals(testAdresse3, debitor.getAdresseNr());
    }
}

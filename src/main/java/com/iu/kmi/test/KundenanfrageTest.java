package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;

import com.iu.kmi.entities.Adresse;
import com.iu.kmi.entities.Kunde;
import org.junit.Before;
import org.junit.Test;
import com.iu.kmi.entities.Kundenanfrage;

public class KundenanfrageTest {

    private Kundenanfrage Kundenanfrage;

    @Before
    public void setUp() {
        Kundenanfrage = new Kundenanfrage("1", new Kunde("KD-123", "Mustermann", "Max", "max@gmx.de", "0123456789", new Adresse("Straße", "1", "12345", "Berlin", "Deutschland")), "01.06.2024", "Produkt xy menge xy", "Hoch", "Offen");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("1", Kundenanfrage.getKundenanfrageNr());
        assertEquals("Max Mustermann", Kundenanfrage.getKunde());
        assertEquals("01.06.2024", Kundenanfrage.getAnfrageDatum());
        assertEquals("Produkt xy menge xy", Kundenanfrage.getAnfrageBeschreibung());
        assertEquals("Hoch", Kundenanfrage.getPrioritaet());
        assertEquals("Offen", Kundenanfrage.getStatus());
    }

    @Test
    public void testSetters() {
        Kundenanfrage.setKundenanfrageNr("2");
        Kundenanfrage.setKunde(new Kunde("KD-123", "Mustermann", "Maximilian", "max@gmx.de", "0123456789", new Adresse("Straße", "1", "12345", "Berlin", "Deutschland")));
        Kundenanfrage.setAnfrageDatum("02.06.2024");
        Kundenanfrage.setAnfrageBeschreibung("Nachfrage");
        Kundenanfrage.setPrioritaet("Mittel");
        Kundenanfrage.setStatus("Abgeschlossen");

        assertEquals("2", Kundenanfrage.getKundenanfrageNr());
        assertEquals("Maximilian", Kundenanfrage.getKunde().getVorname());
        assertEquals("02.06.2024", Kundenanfrage.getAnfrageDatum());
        assertEquals("Nachfrage", Kundenanfrage.getAnfrageBeschreibung());
        assertEquals("Mittel", Kundenanfrage.getPrioritaet());
        assertEquals("Abgeschlossen", Kundenanfrage.getStatus());
    }

    @Test
    public void testToString() {
        String expected = "Kundenanfrage{id=1, Customer=Max Mustermann, anfragedatum='01.06.2024', anfragebeschreibung='Produkt xy menge xy', prioritaet='Hoch', status='Offen'}";
        assertEquals(expected, Kundenanfrage.toString());
    }
}
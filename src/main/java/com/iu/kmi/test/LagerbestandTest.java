package com.iu.kmi.test;

import com.iu.kmi.entities.Lagerbestand;
import com.iu.kmi.entities.Material;
import com.iu.kmi.entities.Lager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LagerbestandTest {

    private Material material;
    private Lager lager;

    @Before
    public void setUp() {
        material = new Material(); // Angenommen, die Material-Klasse hat einen no-args Konstruktor
        lager = new Lager(); // Angenommen, die Lager-Klasse hat einen no-args Konstruktor
    }

    @Test
    public void testLagerbestandConstructorAndGetters() {
        // Arrange
        int menge = 100;

        // Act
        Lagerbestand lagerbestand = new Lagerbestand(material, lager, menge);

        // Assert
        assertEquals(material, lagerbestand.getArtikelNummer());
        assertEquals(lager, lagerbestand.getLagerNummer());
        assertEquals(menge, lagerbestand.getMenge());
    }

    @Test
    public void testNoArgsConstructor() {
        // Act
        Lagerbestand lagerbestand = new Lagerbestand();

        // Assert
        assertNull(lagerbestand.getArtikelNummer());
        assertNull(lagerbestand.getLagerNummer());
        assertEquals(0, lagerbestand.getMenge());
    }

    @Test
    public void testSetArtikelNummer() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand(material, lager, 100);
        Material neuerMaterial = new Material();

        // Act
        lagerbestand.setArtikelNummer(neuerMaterial);

        // Assert
        assertEquals(neuerMaterial, lagerbestand.getArtikelNummer());
    }

    @Test
    public void testSetLagerNummer() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand(material, lager, 100);
        Lager neuerLager = new Lager();

        // Act
        lagerbestand.setLagerNummer(neuerLager);

        // Assert
        assertEquals(neuerLager, lagerbestand.getLagerNummer());
    }

    @Test
    public void testSetMenge() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand(material, lager, 100);
        int neueMenge = 200;

        // Act
        lagerbestand.setMenge(neueMenge);

        // Assert
        assertEquals(neueMenge, lagerbestand.getMenge());
    }

    @Test
    public void testToString() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand(material, lager, 100);
        String expectedString = "Lagerbestand{artikel_nr='" + material + "', lager_nr='" + lager + "', menge=100}";

        // Act
        String actualString = lagerbestand.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }
}

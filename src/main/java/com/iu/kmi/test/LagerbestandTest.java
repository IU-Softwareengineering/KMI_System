package com.iu.kmi.test;

import com.iu.kmi.entities.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LagerbestandTest {

    @Test
    public void testLagerbestandConstructorAndGetters() {
        // Arrange
        Adresse testAdresse = new Adresse("Strasse", "1", "12345", "Berlin", "Deutschland");
        Material artikelNummer = new Material("A123", "Testartikel", "Ein Testartikel", "Testlieferant", BigDecimal.valueOf(10), BigDecimal.valueOf(20));
        Lager lagerNummer = new Lager("L123", "Testlager", testAdresse);
        int menge = 100;

        // Act
        Lagerbestand lagerbestand = new Lagerbestand(artikelNummer, lagerNummer, menge);

        // Assert
        assertEquals(artikelNummer, lagerbestand.getArtikelNummer());
        assertEquals(lagerNummer, lagerbestand.getLagerNummer());
        assertEquals(menge, lagerbestand.getMenge());
    }

    @Test
    public void testSetArtikelNummer() {
        // Arrange
        Material testArtikel = new Material("A456", "Artikel", "Ein Artikel", "Lieferant", BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        Lager testLager = new Lager("L456", "Lager", new Adresse("Allee", "2", "54321", "Wien", "Österreich"));
        Lagerbestand lagerbestand = new Lagerbestand(testArtikel, testLager, 20);
        Material neuerArtikel = new Material("A789", "Neuer Artikel", "Ein neuer Artikel", "Neuer Lieferant", BigDecimal.valueOf(25), BigDecimal.valueOf(35));

        // Act
        lagerbestand.setArtikelNummer(neuerArtikel);

        // Assert
        assertEquals(neuerArtikel, lagerbestand.getArtikelNummer());
    }

    @Test
    public void testSetLagerNummer() {
        // Arrange
        Material testArtikel = new Material("A456", "Artikel", "Ein Artikel", "Lieferant", BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        Lager testLager = new Lager("L456", "Lager", new Adresse("Allee", "2", "54321", "Wien", "Österreich"));
        Lagerbestand lagerbestand = new Lagerbestand(testArtikel, testLager, 100);
        Lager neuesLager = new Lager("L789", "Neues Lager", new Adresse("Straße", "3", "98765", "Berlin", "Deutschland"));

        // Act
        lagerbestand.setLagerNummer(neuesLager);

        // Assert
        assertEquals(neuesLager, lagerbestand.getLagerNummer());
    }

    @Test
    public void testSetMenge() {
        // Arrange
        Material testArtikel = new Material("A456", "Artikel", "Ein Artikel", "Lieferant", BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        Lager testLager = new Lager("L456", "Lager", new Adresse("Allee", "2", "54321", "Wien", "Österreich"));
        Lagerbestand lagerbestand = new Lagerbestand(testArtikel, testLager, 100);
        int neueMenge = 200;

        // Act
        lagerbestand.setMenge(neueMenge);

        // Assert
        assertEquals(neueMenge, lagerbestand.getMenge());
    }
}

package com.iu.kmi.test;

import com.iu.kmi.entities.Lagerbestand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LagerbestandTest {

    @Test
    public void testLagerbestandConstructorAndGetters() {
        // Arrange
        String artikelNummer = "A123";
        String lagerNummer = "L456";
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
        Lagerbestand lagerbestand = new Lagerbestand("A123", "L456", 100);
        String neuerArtikelNummer = "A789";

        // Act
        lagerbestand.setArtikelNummer(neuerArtikelNummer);

        // Assert
        assertEquals(neuerArtikelNummer, lagerbestand.getArtikelNummer());
    }

    @Test
    public void testSetLagerNummer() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand("A123", "L456", 100);
        String neuerLagerNummer = "L789";

        // Act
        lagerbestand.setLagerNummer(neuerLagerNummer);

        // Assert
        assertEquals(neuerLagerNummer, lagerbestand.getLagerNummer());
    }

    @Test
    public void testSetMenge() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand("A123", "L456", 100);
        int neueMenge = 200;

        // Act
        lagerbestand.setMenge(neueMenge);

        // Assert
        assertEquals(neueMenge, lagerbestand.getMenge());
    }

    @Test
    public void testToString() {
        // Arrange
        Lagerbestand lagerbestand = new Lagerbestand("A123", "L456", 100);
        String expectedString = "Lagerbestand{artikelNummer='A123', lagerNummer='L456', menge=100}";

        // Act
        String actualString = lagerbestand.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }
}

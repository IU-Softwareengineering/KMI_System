package com.iu.kmi.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LagerTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String lagerNummer = "L001";
        String name = "Hauptlager";
        int adresse = 12345;

        // Act
        Lager lager = new Lager(lagerNummer, name, adresse);

        // Assert
        assertEquals(lagerNummer, lager.getLagerNummer());
        assertEquals(name, lager.getName());
        assertEquals(adresse, lager.getAdresse());
    }

    @Test
    public void testSetLagerNummer() {
        // Arrange
        Lager lager = new Lager("L001", "Hauptlager", 12345);
        String newLagerNummer = "L002";

        // Act
        lager.setLagerNummer(newLagerNummer);

        // Assert
        assertEquals(newLagerNummer, lager.getLagerNummer());
    }

    @Test
    public void testSetName() {
        // Arrange
        Lager lager = new Lager("L001", "Hauptlager", 12345);
        String newName = "Nebenlager";

        // Act
        lager.setName(newName);

        // Assert
        assertEquals(newName, lager.getName());
    }

    @Test
    public void testSetAdresse() {
        // Arrange
        Lager lager = new Lager("L001", "Hauptlager", 12345);
        int newAdresse = 54321;

        // Act
        lager.setAdresse(newAdresse);

        // Assert
        assertEquals(newAdresse, lager.getAdresse());
    }

    @Test
    public void testToString() {
        // Arrange
        String lagerNummer = "L001";
        String name = "Hauptlager";
        int adresse = 12345;
        Lager lager = new Lager(lagerNummer, name, adresse);
        String expected = "Lager{lagerNummer=" + lagerNummer + ", name='" + name + "', adresse=" + adresse + "}";

        // Act
        String actual = lager.toString();

        // Assert
        assertEquals(expected, actual);
    }
}

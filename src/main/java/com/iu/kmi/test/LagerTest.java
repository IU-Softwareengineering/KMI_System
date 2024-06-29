package com.iu.kmi.test;

import com.iu.kmi.entities.Adresse;
import com.iu.kmi.entities.Lager;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

public class LagerTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String lagerNummer = "L001";
        String name = "Hauptlager";
        Adresse adresse = new Adresse("abc","hnr","1234","stadt","land");

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
        Adresse adresse = new Adresse("abc", "hnr", "1234", "stadt", "land");
        Lager lager = new Lager("L001", "Hauptlager", adresse);
        String newLagerNummer = "L002";

        // Act
        lager.setLagerNummer(newLagerNummer);

        // Assert
        assertEquals(newLagerNummer, lager.getLagerNummer());
    }

    @Test
    public void testSetName() {
        // Arrange
        Adresse adresse = new Adresse("abc", "hnr", "1234", "stadt", "land");
        Lager lager = new Lager("L001", "Hauptlager", adresse);
        String newName = "Nebenlager";

        // Act
        lager.setName(newName);

        // Assert
        assertEquals(newName, lager.getName());
    }

    @Test
    public void testSetAdresse() {
        // Arrange
        Adresse adresse = new Adresse("abc", "hnr", "1234", "stadt", "land");
        Lager lager = new Lager("L001", "Hauptlager", adresse);
        Adresse newAdresse = new Adresse("def", "hnr2", "5678", "stadt2", "land2");

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
        Adresse adresse = new Adresse("abc", "hnr", "1234", "stadt", "land");
        Lager lager = new Lager(lagerNummer, name, adresse);
        String expected = "Lager{lagerNummer=" + lagerNummer + ", name='" + name + "', adresse=" + adresse + "}";

        // Act
        String actual = lager.toString();

        // Assert
        assertEquals(expected, actual);
    }
}

package com.iu.kmi.test;

import com.iu.kmi.entities.Adresse;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseTest {

    /**
     * Test method for getting the street (Strasse) of an Adresse object.
     */
    @Test
    public void testGetStrasse() {
        // Initialize an Adresse object with a test street
        String testStrasse = "Musterstraße 12";
        Adresse adresse = new Adresse(testStrasse, "Berlin", "10117", "Deutschland");

        // Assert that the getStrasse method returns the correct street
        assertEquals(testStrasse, adresse.getStrasse());
    }

    /**
     * Test method for setting the street (Strasse) of an Adresse object.
     */
    @Test
    public void testSetStrasse() {
        // Initialize an Adresse object with empty fields
        Adresse adresse = new Adresse("", "", "", "");

        // Set a new street and verify the change
        String newStrasse = "Hauptstraße 33";
        adresse.setStrasse(newStrasse);
        assertEquals(newStrasse, adresse.getStrasse());
    }

    // Add similar tests for get/setStadt, getPostleitzahl, getLand, setStadt, etc.
    /**
     * Test method to verify that the constructor correctly sets all fields of
     * an Adresse object.
     */
    @Test
    public void testConstructorSetsFields() {
        // Initialize an Adresse object with test values for all fields
        String testStrasse = "Musterstraße 12";
        String testStadt = "Berlin";
        String testPlz = "10117";
        String testLand = "Deutschland";
        Adresse adresse = new Adresse(testStrasse, testStadt, testPlz, testLand);

        // Assert that each getter method returns the correct value
        assertEquals(testStrasse, adresse.getStrasse());
        assertEquals(testStadt, adresse.getStadt());
        assertEquals(testPlz, adresse.getPostleitzahl());
        assertEquals(testLand, adresse.getLand());
    }
}

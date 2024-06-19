package com.iu.kmi.test;

import com.iu.kmi.entities.Adresse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Adresse class.
 *
 * @autor OT
 */
public class AdresseTest {

    public AdresseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Adresse.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Adresse instance = new Adresse();
        String expResult = null; // Default ID should be null
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Adresse.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "1";
        Adresse instance = new Adresse();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getStrasse method, of class Adresse.
     */
    @Test
    public void testGetStrasse() {
        System.out.println("getStrasse");
        Adresse instance = new Adresse();
        String expResult = null; // Default value should be null
        String result = instance.getStrasse();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStrasse method, of class Adresse.
     */
    @Test
    public void testSetStrasse() {
        System.out.println("setStrasse");
        String strasse = "Musterstrasse";
        Adresse instance = new Adresse();
        instance.setStrasse(strasse);
        assertEquals(strasse, instance.getStrasse());
    }

    /**
     * Test of getHausnummer method, of class Adresse.
     */
    @Test
    public void testGetHausnummer() {
        System.out.println("getHausnummer");
        Adresse instance = new Adresse();
        String expResult = null; // Default value should be null
        String result = instance.getHausnummer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHausnummer method, of class Adresse.
     */
    @Test
    public void testSetHausnummer() {
        System.out.println("setHausnummer");
        String hausnummer = "10A";
        Adresse instance = new Adresse();
        instance.setHausnummer(hausnummer);
        assertEquals(hausnummer, instance.getHausnummer());
    }

    /**
     * Test of getPostleitzahl method, of class Adresse.
     */
    @Test
    public void testGetPostleitzahl() {
        System.out.println("getPostleitzahl");
        Adresse instance = new Adresse();
        String expResult = null; // Default value should be null
        String result = instance.getPostleitzahl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostleitzahl method, of class Adresse.
     */
    @Test
    public void testSetPostleitzahl() {
        System.out.println("setPostleitzahl");
        String postleitzahl = "12345";
        Adresse instance = new Adresse();
        instance.setPostleitzahl(postleitzahl);
        assertEquals(postleitzahl, instance.getPostleitzahl());
    }

    /**
     * Test of getStadt method, of class Adresse.
     */
    @Test
    public void testGetStadt() {
        System.out.println("getStadt");
        Adresse instance = new Adresse();
        String expResult = null; // Default value should be null
        String result = instance.getStadt();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStadt method, of class Adresse.
     */
    @Test
    public void testSetStadt() {
        System.out.println("setStadt");
        String stadt = "Musterstadt";
        Adresse instance = new Adresse();
        instance.setStadt(stadt);
        assertEquals(stadt, instance.getStadt());
    }

    /**
     * Test of getLand method, of class Adresse.
     */
    @Test
    public void testGetLand() {
        System.out.println("getLand");
        Adresse instance = new Adresse();
        String expResult = null; // Default value should be null
        String result = instance.getLand();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLand method, of class Adresse.
     */
    @Test
    public void testSetLand() {
        System.out.println("setLand");
        String land = "Deutschland";
        Adresse instance = new Adresse();
        instance.setLand(land);
        assertEquals(land, instance.getLand());
    }
}

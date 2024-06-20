package com.iu.kmi.entities;
import com.iu.kmi.entities.Kunde;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author OT
 */
public class KundeTest {

    private Kunde kunde;

    @BeforeClass
    public static void setUpClass() {
        // Setup before the entire test class is run
    }

    @AfterClass
    public static void tearDownClass() {
        // Cleanup after the entire test class is run
    }

    @Before
    public void setUp() {
        kunde = new Kunde("12345", "Schmidt", "Max", "max.schmidt@example.com", "+49 123 456789", null);
    }

    @After
    public void tearDown() {
        // Cleanup after each test
    }

    /**
     * Test of getKundennummer method, of class Kunde.
     */
    @Test
    public void testGetKundennummer() {
        String expectedKundennummer = "12345";
        String actualKundennummer = kunde.getKundennummer();
        assertEquals(expectedKundennummer, actualKundennummer);
    }

    /**
     * Test of setKundennummer method, of class Kunde.
     */
    @Test
    public void testSetKundennummer() {
        String newKundennummer = "98765";
        kunde.setKundennummer(newKundennummer);
        assertEquals(newKundennummer, kunde.getKundennummer());
    }

    /**
     * Test of getNachname method, of class Kunde.
     */
    @Test
    public void testGetNachname() {
        String expectedNachname = "Schmidt";
        String actualNachname = kunde.getname();
        assertEquals(expectedNachname, actualNachname);
    }

    /**
     * Test of setNachname method, of class Kunde.
     */
    @Test
    public void testSetNachname() {
        String newNachname = "Müller";
        kunde.setname(newNachname);
        assertEquals(newNachname, kunde.getname());
    }

    /**
     * Test of getVorname method, of class Kunde.
     */
    @Test
    public void testGetVorname() {
        String expectedVorname = "Max";
        String actualVorname = kunde.getVorname();
        assertEquals(expectedVorname, actualVorname);
    }

    /**
     * Test of setVorname method, of class Kunde.
     */
    @Test
    public void testSetVorname() {
        String newVorname = "Peter";
        kunde.setVorname(newVorname);
        assertEquals(newVorname, kunde.getVorname());
    }

    /**
     * Test of getEmail method, of class Kunde.
     */
    @Test
    public void testGetEmail() {
        String expectedEmail = "max.schmidt@example.com";
        String actualEmail = kunde.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    /**
     * Test of setEmail method, of class Kunde.
     */
    @Test
    public void testSetEmail() {
        String newEmail = "peter.mueller@example.com";
        kunde.setEmail(newEmail);
        assertEquals(newEmail, kunde.getEmail());
    }

    /**
     * Test of getTelefon method, of class Kunde.
     */
    @Test
    public void testGetTelefon() {
        String expectedTelefon = "+49 123 456789";
        String actualTelefon = kunde.getTelefon();
        assertEquals(expectedTelefon, actualTelefon);
    }

    /**
     * Test of setTelefon method, of class Kunde.
     */
    @Test
    public void testSetTelefon() {
        String newTelefon = "+49 30 12345678";
        kunde.setTelefon(newTelefon);
        assertEquals(newTelefon, kunde.getTelefon());
    }

    /**
     * Test of getAdresse method, of class Kunde.
     */
    @Test
    public void testGetAdresse() {
        // If Adresse class exists, add test cases to check the relationship
        // between Kunde and Adresse
    }
}

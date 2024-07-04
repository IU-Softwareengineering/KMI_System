package com.iu.kmi.test;

import com.iu.kmi.entities.Adresse;
import com.iu.kmi.entities.Kunde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KundeTest {

    private Kunde kunde;
    private Adresse adresse;

    /**
     * Sets up the test environment before each test. Initializes an Adresse and
     * a Kunde object with test data.
     */
    @BeforeEach
    public void setUp() {
        adresse = new Adresse("Street", "1", "City", "12345", "Country");
        kunde = new Kunde("12345", "Doe", "John",
                "john.doe@example.com", "1234567890", adresse);
    }

    /**
     * Tests the getKundennummer method. Verifies that the customer number is
     * correctly returned.
     */
    @Test
    public void testGetKundennummer() {
        assertEquals("12345", kunde.getKundennummer());
    }

    /**
     * Tests the setKundennummer method. Verifies that the customer number is
     * correctly updated.
     */
    @Test
    public void testSetKundennummer() {
        kunde.setKundennummer("54321");
        assertEquals("54321", kunde.getKundennummer());
    }

    /**
     * Tests the getVorname method. Verifies that the first name is correctly
     * returned.
     */
    @Test
    public void testGetVorname() {
        assertEquals("John", kunde.getVorname());
    }

    /**
     * Tests the setVorname method. Verifies that the first name is correctly
     * updated.
     */
    @Test
    public void testSetVorname() {
        kunde.setVorname("Jane");
        assertEquals("Jane", kunde.getVorname());
    }

    /**
     * Tests the getName method. Verifies that the last name is correctly
     * returned.
     */
    @Test
    public void testGetName() {
        assertEquals("Doe", kunde.getName());
    }

    /**
     * Tests the setName method. Verifies that the last name is correctly
     * updated.
     */
    @Test
    public void testSetName() {
        kunde.setName("Smith");
        assertEquals("Smith", kunde.getName());
    }

    /**
     * Tests the getEmail method. Verifies that the email address is
     * correctly returned.
     */
    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", kunde.getEmail());
    }

    /**
     * Tests the setEmail method. Verifies that the email address is
     * correctly updated.
     */
    @Test
    public void testSetEmail() {
        kunde.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", kunde.getEmail());
    }

    /**
     * Tests the getTelefon method. Verifies that the phone number is
     * correctly returned.
     */
    @Test
    public void testGetTelefon() {
        assertEquals("1234567890", kunde.getTelefon());
    }

    /**
     * Tests the setTelefon method. Verifies that the phone number is
     * correctly updated.
     */
    @Test
    public void testSetTelefonnummer() {
        kunde.setTelefonnummer("0987654321");
        assertEquals("0987654321", kunde.getTelefon());
    }

    /**
     * Tests the getAdresse method. Verifies that the address is correctly
     * returned.
     */
    @Test
    public void testGetAdresse() {
        assertEquals(adresse, kunde.getAdresse());
    }

    /**
     * Tests the setAdresse method. Verifies that the address is correctly
     * updated.
     */
    @Test
    public void testSetAdresse() {
        Adresse newAdresse = new Adresse("Boulevard", "9", "New City", "54321", "New Country");
        kunde.setAdresse(newAdresse);
        assertEquals(newAdresse, kunde.getAdresse());
    }
}

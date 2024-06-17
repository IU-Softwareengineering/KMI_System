package com.iu.kmi.test;

import com.iu.kmi.entities.Adresse;
import com.iu.kmi.entities.Kunde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KundeTest {

//    private Kunde kunde;
//    private Adresse adresse;
//
//    /**
//     * Sets up the test environment before each test. Initializes an Adresse and
//     * a Kunde object with test data.
//     */
//    @BeforeEach
//    public void setUp() {
//        adresse = new Adresse("Street 1", "City", "12345", "Country");
//        kunde = new Kunde("12345", "John", "Doe", "01-01-1980",
//                "john.doe@example.com", "1234567890", adresse,
//                "johndoe", "password123");
//    }
//
//    /**
//     * Tests the getKundennummer method. Verifies that the customer number is
//     * correctly returned.
//     */
//    @Test
//    public void testGetKundennummer() {
//        assertEquals("12345", kunde.getKundennummer());
//    }
//
//    /**
//     * Tests the setKundennummer method. Verifies that the customer number is
//     * correctly updated.
//     */
//    @Test
//    public void testSetKundennummer() {
//        kunde.setKundennummer("54321");
//        assertEquals("54321", kunde.getKundennummer());
//    }
//
//    /**
//     * Tests the getVorname method. Verifies that the first name is correctly
//     * returned.
//     */
//    @Test
//    public void testGetVorname() {
//        assertEquals("John", kunde.getVorname());
//    }
//
//    /**
//     * Tests the setVorname method. Verifies that the first name is correctly
//     * updated.
//     */
//    @Test
//    public void testSetVorname() {
//        kunde.setVorname("Jane");
//        assertEquals("Jane", kunde.getVorname());
//    }
//
//    /**
//     * Tests the getNachname method. Verifies that the last name is correctly
//     * returned.
//     */
//    @Test
//    public void testGetNachname() {
//        assertEquals("Doe", kunde.getNachname());
//    }
//
//    /**
//     * Tests the setNachname method. Verifies that the last name is correctly
//     * updated.
//     */
//    @Test
//    public void testSetNachname() {
//        kunde.setNachname("Smith");
//        assertEquals("Smith", kunde.getNachname());
//    }
//
//    /**
//     * Tests the getGeburtsdatum method. Verifies that the date of birth is
//     * correctly returned.
//     */
//    @Test
//    public void testGetGeburtsdatum() {
//        assertEquals("01-01-1980", kunde.getGeburtsdatum());
//    }
//
//    /**
//     * Tests the setGeburtsdatum method. Verifies that the date of birth is
//     * correctly updated.
//     */
//    @Test
//    public void testSetGeburtsdatum() {
//        kunde.setGeburtsdatum("02-02-1990");
//        assertEquals("02-02-1990", kunde.getGeburtsdatum());
//    }
//
//    /**
//     * Tests the getEmailAdresse method. Verifies that the email address is
//     * correctly returned.
//     */
//    @Test
//    public void testGetEmailAdresse() {
//        assertEquals("john.doe@example.com", kunde.getEmailAdresse());
//    }
//
//    /**
//     * Tests the setEmailAdresse method. Verifies that the email address is
//     * correctly updated.
//     */
//    @Test
//    public void testSetEmailAdresse() {
//        kunde.setEmailAdresse("jane.doe@example.com");
//        assertEquals("jane.doe@example.com", kunde.getEmailAdresse());
//    }
//
//    /**
//     * Tests the getTelefonnummer method. Verifies that the phone number is
//     * correctly returned.
//     */
//    @Test
//    public void testGetTelefonnummer() {
//        assertEquals("1234567890", kunde.getTelefonnummer());
//    }
//
//    /**
//     * Tests the setTelefonnummer method. Verifies that the phone number is
//     * correctly updated.
//     */
//    @Test
//    public void testSetTelefonnummer() {
//        kunde.setTelefonnummer("0987654321");
//        assertEquals("0987654321", kunde.getTelefonnummer());
//    }
//
//    /**
//     * Tests the getAdresse method. Verifies that the address is correctly
//     * returned.
//     */
//    @Test
//    public void testGetAdresse() {
//        assertEquals(adresse, kunde.getAdresse());
//    }
//
//    /**
//     * Tests the setAdresse method. Verifies that the address is correctly
//     * updated.
//     */
//    @Test
//    public void testSetAdresse() {
//        Adresse newAdresse = new Adresse("Street 2", "New City", "54321", "New Country");
//        kunde.setAdresse(newAdresse);
//        assertEquals(newAdresse, kunde.getAdresse());
//    }
//
//    /**
//     * Tests the getBenutzername method. Verifies that the username is correctly
//     * returned.
//     */
//    @Test
//    public void testGetBenutzername() {
//        assertEquals("johndoe", kunde.getBenutzername());
//    }
//
//    /**
//     * Tests the setBenutzername method. Verifies that the username is correctly
//     * updated.
//     */
//    @Test
//    public void testSetBenutzername() {
//        kunde.setBenutzername("janedoe");
//        assertEquals("janedoe", kunde.getBenutzername());
//    }
//
//    /**
//     * Tests the getPasswort method. Verifies that the password is correctly
//     * returned.
//     */
//    @Test
//    public void testGetPasswort() {
//        assertEquals("password123", kunde.getPasswort());
//    }
//
//    /**
//     * Tests the setPasswort method. Verifies that the password is correctly
//     * updated.
//     */
//    @Test
//    public void testSetPasswort() {
//        kunde.setPasswort("newpassword");
//        assertEquals("newpassword", kunde.getPasswort());
//    }
}

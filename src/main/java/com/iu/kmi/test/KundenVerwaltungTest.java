package com.iu.kmi.entities.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KundenVerwaltungTest {

    private KundenVerwaltung kundenVerwaltung;
    private Kunde kunde1;
    private Kunde kunde2;

    /**
     * Setup method to initialize the KundenVerwaltung instance and some test
     * Kunden. This method is run before each test case.
     */
    @Before
    public void setUp() {
        kundenVerwaltung = new KundenVerwaltung();
        // Initializing sample addresses
        Adresse adresse1 = new Adresse("Street 1", "City", "12345", "Country");
        Adresse adresse2 = new Adresse("Street 2", "New City", "54321", "New Country");

        // Initializing sample customers
        kunde1 = new Kunde("12345", "John", "Doe", "01-01-1980",
                "john.doe@example.com", "1234567890", adresse1,
                "johndoe", "password123");
        kunde2 = new Kunde("54321", "Jane", "Smith", "02-02-1990",
                "jane.smith@example.com", "0987654321", adresse2,
                "janesmith", "password456");
    }

    /**
     * Test of the addKunde method in the KundenVerwaltung class. This test
     * verifies that customers are correctly added to the KundenVerwaltung.
     */
    @Test
    public void testAddKunde() {
        System.out.println("addKunde");

        // Add the customers to the KundenVerwaltung
        kundenVerwaltung.addKunde(kunde1);
        kundenVerwaltung.addKunde(kunde2);

        // Retrieve the list of all customers
        List<Kunde> result = kundenVerwaltung.getAlleKunden();

        // Verify that the list size is 2, meaning both customers were added
        assertEquals(2, result.size());

        // Verify that the list contains both customers
        assertTrue(result.contains(kunde1));
        assertTrue(result.contains(kunde2));
    }

    /**
     * Test of the getAlleKunden method in the KundenVerwaltung class. This test
     * verifies that the getAlleKunden method returns the correct list of
     * customers.
     */
    @Test
    public void testGetAlleKunden() {
        System.out.println("getAlleKunden");

        // Add the customers to the KundenVerwaltung
        kundenVerwaltung.addKunde(kunde1);
        kundenVerwaltung.addKunde(kunde2);

        // Retrieve the list of all customers
        List<Kunde> result = kundenVerwaltung.getAlleKunden();

        // Verify that the list size is 2, meaning both customers were added
        assertEquals(2, result.size());

        // Verify that the retrieved customers match the added customers
        assertEquals(kunde1, result.get(0));
        assertEquals(kunde2, result.get(1));
    }
}

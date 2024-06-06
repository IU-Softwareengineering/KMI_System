package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.iu.kmi.entities.CustomerRequest;

public class CustomerRequestTest {

    private CustomerRequest CustomerRequest;

    @Before
    public void setUp() {
        CustomerRequest = new CustomerRequest("1", "Max Mustermann", "01.06.2024", "Produkt xy menge xy", "Hoch", "Offen");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("1", CustomerRequest.getId());
        assertEquals("Max Mustermann", CustomerRequest.getcustomer());
        assertEquals("01.06.2024", CustomerRequest.getrequest_date());
        assertEquals("Produkt xy menge xy", CustomerRequest.getrequest_description());
        assertEquals("Hoch", CustomerRequest.getpriority());
        assertEquals("Offen", CustomerRequest.getStatus());
    }

    @Test
    public void testSetters() {
        CustomerRequest.setId("2");
        CustomerRequest.setcustomer("Maximilian Mustermann");
        CustomerRequest.setrequest_date("02.06.2024");
        CustomerRequest.setrequest_description("Nachfrage");
        CustomerRequest.setpriority("Mittel");
        CustomerRequest.setStatus("Abgeschlossen");

        assertEquals("2", CustomerRequest.getId());
        assertEquals("Maximilian Mustermann", CustomerRequest.getcustomer());
        assertEquals("02.06.2024", CustomerRequest.getrequest_date());
        assertEquals("Nachfrage", CustomerRequest.getrequest_description());
        assertEquals("Mittel", CustomerRequest.getpriority());
        assertEquals("Abgeschlossen", CustomerRequest.getStatus());
    }

    @Test
    public void testToString() {
        String expected = "CustomerRequest{id=1, Customer=Max Mustermann, anfragedatum='01.06.2024', anfragebeschreibung='Produkt xy menge xy', prioritaet='Hoch', status='Offen'}";
        assertEquals(expected, CustomerRequest.toString());
    }
}
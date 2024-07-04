package com.iu.kmi.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

import com.iu.kmi.entities.Adresse;
import com.iu.kmi.entities.Angebot;
import com.iu.kmi.entities.Kondition;
import com.iu.kmi.entities.Kunde;
import com.iu.kmi.entities.Kundenanfrage;

public class AngebotTest {

    private Angebot       angebot;
    private Kunde         kunde;
    private LocalDate          angebotsdatum;
    private LocalDate          gueltigBis;
    private String        waehrung;
    private String        status;
    private Kondition     kondition;
    private Kundenanfrage kundenanfrage;


    public void setUp() {
        kunde = new Kunde("K12345","Mustermann","Max","max@gmail.com","0123456789",new Adresse("Strasse","1","123456","Berlin","Deutschland"));
        angebotsdatum = LocalDate.now();
        gueltigBis = LocalDate.now().plusDays(7); // some future date
        waehrung = "EUR";
        status = "Offen";
        kondition = new Kondition("KondiName","Bar","innerhalb Stadt", BigDecimal.valueOf(33.3));
        //kundenanfrage = new Kundenanfrage("KA123",kunde,"01.01.2001","Will haben!","Dringend","In Zustellung");

        angebot = new Angebot(kunde, angebotsdatum, gueltigBis, waehrung, status, kondition/*, kundenanfrage*/);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(kunde, angebot.getKundeNr());
        assertEquals(angebotsdatum, angebot.getAngebotsdatum());
        assertEquals(gueltigBis, angebot.getGueltigBis());
        assertEquals(waehrung, angebot.getWaehrung());
        assertEquals(status, angebot.getStatus());
        assertEquals(kondition, angebot.getKonditionNr());
        //assertEquals(kundenanfrage, angebot.getKundenanfrageNr());
    }

    @Test
    public void testSetters() {
        Kunde newKundeNr= kunde;
        newKundeNr.setKundennummer("K54321");

        LocalDate newAngebotsdatum = LocalDate.now().minusDays(1);
        LocalDate newGueltigBis = LocalDate.now().plusDays(14); // some future date
        String newWaehrung = "USD";
        String newStatus = "Geschlossen";
        Kondition newKonditionNr = kondition;
        newKonditionNr.setKonditionNr("K87654");

        Kundenanfrage newKundenanfrageNr = kundenanfrage;
        newKundenanfrageNr.setKundenanfrageNr("KA321");

        angebot.setKundeNr(newKundeNr);
        angebot.setAngebotsdatum(newAngebotsdatum);
        angebot.setGueltigBis(newGueltigBis);
        angebot.setWaehrung(newWaehrung);
        angebot.setStatus(newStatus);
        angebot.setKonditionNr(newKonditionNr);
        //angebot.setKundenanfrageNr(newKundenanfrageNr);

        assertEquals(newKundeNr, angebot.getKundeNr());
        assertEquals(newAngebotsdatum, angebot.getAngebotsdatum());
        assertEquals(newGueltigBis, angebot.getGueltigBis());
        assertEquals(newWaehrung, angebot.getWaehrung());
        assertEquals(newStatus, angebot.getStatus());
        assertEquals(newKonditionNr, angebot.getKonditionNr());
        //assertEquals(newKundenanfrageNr, angebot.getKundenanfrageNr());
    }
}

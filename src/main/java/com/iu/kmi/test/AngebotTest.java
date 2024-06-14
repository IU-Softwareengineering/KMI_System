package com.iu.kmi.test;

import java.util.Date;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

import com.iu.kmi.entities.Angebot;

public class AngebotTest {

    private Angebot angebot;
    private String kundeNr;
    private Date   angebotsdatum;
    private Date   gueltigBis;
    private String  waehrung;
    private String  status;
    private String  konditionNr;
    private String  kundenanfrageNr;


    public void setUp() {
        kundeNr = "K12345";
        angebotsdatum = new Date();
        gueltigBis = new Date(angebotsdatum.getTime() + 100000000L); // some future date
        waehrung = "EUR";
        status = "Offen";
        konditionNr = "K45678";
        kundenanfrageNr = "KA98765";

        angebot = new Angebot(kundeNr, angebotsdatum, gueltigBis, waehrung, status, konditionNr, kundenanfrageNr);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(kundeNr, angebot.getKundeNr());
        assertEquals(angebotsdatum, angebot.getAngebotsdatum());
        assertEquals(gueltigBis, angebot.getGueltigBis());
        assertEquals(waehrung, angebot.getWaehrung());
        assertEquals(status, angebot.getStatus());
        assertEquals(konditionNr, angebot.getKonditionNr());
        assertEquals(kundenanfrageNr, angebot.getKundenanfrageNr());
    }

    @Test
    public void testSetters() {
        String newKundeNr = "K54321";
        Date newAngebotsdatum = new Date();
        Date newGueltigBis = new Date(newAngebotsdatum.getTime() + 200000000L); // some future date
        String newWaehrung = "USD";
        String newStatus = "Geschlossen";
        String newKonditionNr = "K87654";
        String newKundenanfrageNr = "KA12345";

        angebot.setKundeNr(newKundeNr);
        angebot.setAngebotsdatum(newAngebotsdatum);
        angebot.setGueltigBis(newGueltigBis);
        angebot.setWaehrung(newWaehrung);
        angebot.setStatus(newStatus);
        angebot.setKonditionNr(newKonditionNr);
        angebot.setKundenanfrageNr(newKundenanfrageNr);

        assertEquals(newKundeNr, angebot.getKundeNr());
        assertEquals(newAngebotsdatum, angebot.getAngebotsdatum());
        assertEquals(newGueltigBis, angebot.getGueltigBis());
        assertEquals(newWaehrung, angebot.getWaehrung());
        assertEquals(newStatus, angebot.getStatus());
        assertEquals(newKonditionNr, angebot.getKonditionNr());
        assertEquals(newKundenanfrageNr, angebot.getKundenanfrageNr());
    }
}

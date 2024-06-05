package com.iu.kmi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.iu.kmi.entities.Angebot;

public class AngebotTest {

    private Angebot angebot;

    @Before
    public void setUp() {
        angebot = new Angebot(123, new Date(), new Date(), "EUR", "aktiv", 10, 456);
    }

    @Test
    public void testKonstruktor() {
        assertNotNull(angebot);
    }

    @Test
    public void testGetterUndSetter() {
        assertEquals(123, angebot.getKunde());
        assertEquals("EUR", angebot.getWaehrung());
        assertEquals("aktiv", angebot.getStatus());
        assertEquals(10, angebot.getKondition());
        assertEquals(456, angebot.getAngebotsId());

        Date now = new Date();
        angebot.setAngebotsdatum(now);
        assertEquals(now, angebot.getAngebotsdatum());

        Date tomorrow = new Date(now.getTime() + (1000 * 60 * 60 * 24));
        angebot.setGueltigBis(tomorrow);
        assertEquals(tomorrow, angebot.getGueltigBis());

        angebot.setKunde(456);
        assertEquals(456, angebot.getKunde());

        angebot.setWaehrung("USD");
        assertEquals("USD", angebot.getWaehrung());

        angebot.setStatus("abgelaufen");
        assertEquals("abgelaufen", angebot.getStatus());

        angebot.setKondition(20);
        assertEquals(20, angebot.getKondition());

        angebot.setAngebotsId(789);
        assertEquals(789, angebot.getAngebotsId());
    }
}


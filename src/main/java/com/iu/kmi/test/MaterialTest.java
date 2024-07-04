package com.iu.kmi.test;

import com.iu.kmi.entities.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialTest {

    private Material material;

    @BeforeEach
    public void setUp() {
        material = new Material("M123", "Testartikel", "Ein Testartikel", "Testlieferant", BigDecimal.valueOf(10), BigDecimal.valueOf(20));
    }

    @Test
    public void testMaterialCreation() {
        assertNotNull(material);
        assertEquals("123", material.getArtikelNr());
        assertEquals("Testartikel", material.getName());
        assertEquals("Ein Testartikel", material.getBeschreibung());
        assertEquals("Testlieferant", material.getLieferant());
        assertEquals(10.0f, material.getEinkaufsPreis());
        assertEquals(20.0f, material.getVerkaufsPreis());
    }

    @Test
    public void testSetters() {
        material.setArtikelNr("456");
        material.setName("Neuer Artikel");
        material.setBeschreibung("Eine neue Beschreibung");
        material.setLieferant("Neuer Lieferant");
        material.setEinkaufsPreis(BigDecimal.valueOf(15));
        material.setVerkaufsPreis(BigDecimal.valueOf(25));

        assertEquals("456", material.getArtikelNr());
        assertEquals("Neuer Artikel", material.getName());
        assertEquals("Eine neue Beschreibung", material.getBeschreibung());
        assertEquals("Neuer Lieferant", material.getLieferant());
        assertEquals(BigDecimal.valueOf(15), material.getEinkaufsPreis());
        assertEquals(BigDecimal.valueOf(25), material.getVerkaufsPreis());
    }

    @Test
    public void testToString() {
        String expected = "Material{artikelNummer=123, name='Testartikel', beschreibung='Ein Testartikel', lieferant='Testlieferant', einkaufsPreis=10.0, verkaufsPreis=20.0}";
        assertEquals(expected, material.toString());
    }
}

//package com.iu.kmi.test;
//
//import com.iu.kmi.entities.Material;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MaterialTest {
//
//    private Material material;
//
//    @BeforeEach
//    public void setUp() {
//        material = new Material(123, "Testartikel", "Ein Testartikel", "Testlieferant", 10.0f, 20.0f);
//    }
//
//    @Test
//    public void testMaterialCreation() {
//        assertNotNull(material);
//        assertEquals(123, material.getArtikelNummer());
//        assertEquals("Testartikel", material.getName());
//        assertEquals("Ein Testartikel", material.getBeschreibung());
//        assertEquals("Testlieferant", material.getLieferant());
//        assertEquals(10.0f, material.getEinkaufsPreis());
//        assertEquals(20.0f, material.getVerkaufsPreis());
//    }
//
//    @Test
//    public void testSetters() {
//        material.setArtikelNummer(456);
//        material.setName("Neuer Artikel");
//        material.setBeschreibung("Eine neue Beschreibung");
//        material.setLieferant("Neuer Lieferant");
//        material.setEinkaufsPreis(15.0f);
//        material.setVerkaufsPreis(25.0f);
//
//        assertEquals(456, material.getArtikelNummer());
//        assertEquals("Neuer Artikel", material.getName());
//        assertEquals("Eine neue Beschreibung", material.getBeschreibung());
//        assertEquals("Neuer Lieferant", material.getLieferant());
//        assertEquals(15.0f, material.getEinkaufsPreis());
//        assertEquals(25.0f, material.getVerkaufsPreis());
//    }
//
//    @Test
//    public void testToString() {
//        String expected = "Material{artikelNummer=123, name='Testartikel', beschreibung='Ein Testartikel', lieferant='Testlieferant', einkaufsPreis=10.0, verkaufsPreis=20.0}";
//        assertEquals(expected, material.toString());
//    }
//}

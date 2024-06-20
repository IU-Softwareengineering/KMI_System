//package com.iu.kmi.test;
//
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//
//import com.iu.kmi.entities.AuftragsPosition;
//
//public class AuftragsPositionTest {
//
//    @Test
//    public void testEntity() {
//
//        AuftragsPosition auftragsPosition = new AuftragsPosition();
//        auftragsPosition.setAuftragspositionNr("AP123");
//        auftragsPosition.setAuftragsNr("A123");
//        auftragsPosition.setArtikelNr("ART123");
//        auftragsPosition.setEinzelpreis(99.99);
//
//        assertEquals("AP123", auftragsPosition.getAuftragspositionNr());
//        assertEquals("A123", auftragsPosition.getAuftragsNr());
//        assertEquals("ART123", auftragsPosition.getArtikelNr());
//        assertEquals(99.99, auftragsPosition.getEinzelpreis(), 0.001);
//
//        auftragsPosition.setAuftragspositionNr("AP456");
//        assertEquals("AP456", auftragsPosition.getAuftragspositionNr());
//
//        auftragsPosition.setAuftragsNr("A456");
//        assertEquals("A456", auftragsPosition.getAuftragsNr());
//
//        auftragsPosition.setArtikelNr("ART456");
//        assertEquals("ART456", auftragsPosition.getArtikelNr());
//
//        auftragsPosition.setEinzelpreis(199.99);
//        assertEquals(199.99, auftragsPosition.getEinzelpreis(), 0.001);
//    }
//
//}

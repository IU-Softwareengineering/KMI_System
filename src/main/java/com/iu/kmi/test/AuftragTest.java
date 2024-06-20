//package com.iu.kmi.test;
//
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//import java.util.Date;
//
//import com.iu.kmi.entities.Auftrag;
//
//public class AuftragTest {
//
//    @Test
//    public void testAuftragEntity() {
//
//        Auftrag auftrag = new Auftrag();
//
//
//        String auftragNr = "A-12345";
//        String kundeNr = "K-34567";
//        Date auftragsdatum = new Date();
//        Date lieferdatum = new Date();
//        String angebotNr = "AN98765";
//        String status = "In Bearbeitung";
//
//
//        auftrag.setAuftragNr(auftragNr);
//        auftrag.setKundeNr(kundeNr);
//        auftrag.setAuftragsdatum(auftragsdatum);
//        auftrag.setLieferdatum(lieferdatum);
//        auftrag.setAngebotNr(angebotNr);
//        auftrag.setStatus(status);
//
//        assertEquals(auftragNr, auftrag.getAuftragNr());
//        assertEquals(kundeNr, auftrag.getKundeNr());
//        assertEquals(auftragsdatum, auftrag.getAuftragsdatum());
//        assertEquals(lieferdatum, auftrag.getLieferdatum());
//        assertEquals(angebotNr, auftrag.getAngebotNr());
//        assertEquals(status, auftrag.getStatus());
//    }
//}

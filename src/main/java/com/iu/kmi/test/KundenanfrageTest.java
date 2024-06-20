package com.iu.kmi.test;

import com.iu.kmi.entities.Kundenanfrage;
import com.iu.kmi.entities.Kunde;

public class KundenanfrageTest {

    public static void main(String[] args) {
        // Create a Kunde instance
        Kunde kunde = new Kunde();
        kunde.setkunde_nr("K12345");
        // Assuming there are other fields in the Kunde class, set them here
        // kunde.setOtherField("someValue");

        // Create a Kundenanfrage instance
        Kundenanfrage kundenanfrage = new Kundenanfrage(
            "KA12345",        // kundenanfrageNr
            kunde,            // kundeNr as a Kunde object
            "2024-06-18",     // anfrageDatum
            "Testbeschreibung", // anfrageBeschreibung
            "Hoch",           // prioritaet
            "Offen"           // status
        );

        // Print out the Kundenanfrage details
        System.out.println(kundenanfrage);

        // Verify the fields
        assert kundenanfrage.getkundenanfrage_nr().equals("KA12345");
        assert kundenanfrage.getkunde_nr().getkunde_nr().equals("K12345");
        assert kundenanfrage.getAnfrageDatum().equals("2024-06-18");
        assert kundenanfrage.getAnfrageBeschreibung().equals("Testbeschreibung");
        assert kundenanfrage.getPrioritaet().equals("Hoch");
        assert kundenanfrage.getStatus().equals("Offen");

        System.out.println("All tests passed.");
    }
}

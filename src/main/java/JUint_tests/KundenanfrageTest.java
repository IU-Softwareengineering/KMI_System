package JUint_tests;
// Junit?

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.iu.kmi.Kundenanfrage;

public class KundenanfrageTest {
    
    private Kundenanfrage kundenanfrage;
    
    @Before
    public void setUp() {
        kundenanfrage = new Kundenanfrage("1", "Max Mustermann", "01.06.2024", "Produkt xy menge xy", "Hoch", "Offen");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("1", kundenanfrage.getId());
        assertEquals("Max Mustermann", kundenanfrage.getKunde());
        assertEquals("01.06.2024", kundenanfrage.getAnfragedatum());
        assertEquals("Produkt xy menge xy", kundenanfrage.getAnfragebeschreibung());
        assertEquals("Hoch", kundenanfrage.getPrioritaet());
        assertEquals("Offen", kundenanfrage.getStatus());
    }

    @Test
    public void testSetters() {
        kundenanfrage.setId("2");
        kundenanfrage.setKunde("Maximilian Mustermann");
        kundenanfrage.setAnfragedatum("02.06.2024");
        kundenanfrage.setAnfragebeschreibung("Nachfrage");
        kundenanfrage.setPrioritaet("Mittel");
        kundenanfrage.setStatus("Abgeschlossen");

        assertEquals("2", kundenanfrage.getId());
        assertEquals("Maximilian Mustermann", kundenanfrage.getKunde());
        assertEquals("02.06.2024", kundenanfrage.getAnfragedatum());
        assertEquals("Nachfrage", kundenanfrage.getAnfragebeschreibung());
        assertEquals("Mittel", kundenanfrage.getPrioritaet());
        assertEquals("Abgeschlossen", kundenanfrage.getStatus());
    }

    @Test
    public void testToString() {
        String expected = "Kundenanfrage{id=1, kunde=Max Mustermann, anfragedatum='01.06.2024', anfragebeschreibung='Produkt xy menge xy', prioritaet='Hoch', status='Offen'}";
        assertEquals(expected, kundenanfrage.toString());
    }
}

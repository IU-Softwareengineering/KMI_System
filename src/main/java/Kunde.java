package classes;

/**
 * Die Klasse repräsentiert einen Kunden mit spezifischen Eigenschaften wie
 * Kundennummer, Name, Vorname, E-Mail, Telefon und Adresse.
 */
public class Kunde {

    // Private Instanzvariablen zur Speicherung der Kundendaten
    private int kundenNr;
    private String name;
    private String vorname;
    private String email;
    private String telefon;
    private String adresse;

    /**
     * Konstruktor zur Initialisierung eines Kundenobjekts mit den angegebenen
     * Werten.
     *
     * @param kundenNr Die eindeutige Kundennummer
     * @param name Der Nachname des Kunden
     * @param vorname Der Vorname des Kunden
     * @param email Die E-Mail-Adresse des Kunden
     * @param telefon Die Telefonnummer des Kunden
     * @param adresse Die Adresse des Kunden
     */
    public Kunde(int kundenNr, String name, String vorname, String email, String telefon, String adresse) {
        this.kundenNr = kundenNr;
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    // Getter-Methoden zum Abrufen der Werte der Instanzvariablen
    /**
     * @return Die Kundennummer
     */
    public int getKundenNr() {
        return kundenNr;
    }

    /**
     * @return Der Nachname des Kunden
     */
    public String getName() {
        return name;
    }

    /**
     * @return Der Vorname des Kunden
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @return Die E-Mail-Adresse des Kunden
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Die Telefonnummer des Kunden
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * @return Die Adresse des Kunden
     */
    public String getAdresse() {
        return adresse;
    }

    // Setter-Methoden zum Setzen der Werte der Instanzvariablen
    /**
     * Setzt die Kundennummer.
     *
     * @param kundenNr Die neue Kundennummer
     */
    public void setKundenNr(int kundenNr) {
        this.kundenNr = kundenNr;
    }

    /**
     * Setzt den Nachnamen des Kunden.
     *
     * @param name Der neue Nachname des Kunden
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt den Vornamen des Kunden.
     *
     * @param vorname Der neue Vorname des Kunden
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Setzt die E-Mail-Adresse des Kunden.
     *
     * @param email Die neue E-Mail-Adresse des Kunden
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setzt die Telefonnummer des Kunden.
     *
     * @param telefon Die neue Telefonnummer des Kunden
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Setzt die Adresse des Kunden.
     *
     * @param adresse Die neue Adresse des Kunden
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Gibt eine String-Darstellung des Kundenobjekts zurück.
     *
     * @return Eine String-Darstellung der Kundendaten
     */
    @Override
    public String toString() {
        return "Kunde{"
                + "kundenNr=" + kundenNr
                + ", name='" + name + '\''
                + ", vorname='" + vorname + '\''
                + ", email='" + email + '\''
                + ", telefon='" + telefon + '\''
                + ", adresse='" + adresse + '\''
                + '}';
    }
}

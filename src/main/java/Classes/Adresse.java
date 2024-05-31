package Classes;

/**
 *
 * @author OT
 */
// Innere Klasse für Adresse
public class Adresse {

    private String strasse;
    private String stadt;
    private String postleitzahl;
    private String land;

    // Konstruktor
    public Adresse(String strasse, String stadt, String postleitzahl, String land) {
        this.strasse = strasse;
        this.stadt = stadt;
        this.postleitzahl = postleitzahl;
        this.land = land;
    }

    // Getter und Setter Methoden
    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}

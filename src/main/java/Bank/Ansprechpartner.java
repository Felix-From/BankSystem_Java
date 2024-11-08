package Bank;

public class Ansprechpartner {
    private String Vorname;
    private String Nachname;
    private String Telefonnummer;

    public Ansprechpartner(String Vorname, String Nachname, String Telefonnummer) {
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Telefonnummer = Telefonnummer;
    }

    String getVorname() {
        return Vorname;
    }
    void setVorname(String Vorname) {
        this.Vorname = Vorname;
    }

    String getNachname() {
        return Nachname;
    }
    void setNachname(String Nachname) {
        this.Nachname = Nachname;
    }

    String getTelefonnummer() {
        return Telefonnummer;
    }
    void setTelefonnummer(String Telefonnummer) {
        this.Telefonnummer = Telefonnummer;
    }

    @Override
    public String toString() {
        return "[" +
                "Vorname='" + Vorname + '\'' +
                ", Nachname='" + Nachname + '\'' +
                ", Telefonnummer='" + Telefonnummer + '\'' +
                ']';
    }
}

package Bank;

public class Adresse {
    private String StreetAndNumber;
    private String Postleitzahl;
    private String Ort;

    public Adresse(String StrasseAndHausNummer, String Postleitzahl, String Ort) {
        this.StreetAndNumber = StrasseAndHausNummer;
        this.Postleitzahl = Postleitzahl;
        this.Ort = Ort;
    }

    String getStreetAndHausNummer() {
        return StreetAndNumber;
    }
    String getPostleitzahl() {
        return Postleitzahl;
    }
    String getOrt() {
        return Ort;
    }

    //Setter
    void setStreet(String Str) {
        StreetAndNumber = Str;
    }
    void setPLZ(String PLZ) {
        Postleitzahl = PLZ;
    }
    void setOrt(String Ort) {
        this.Ort = Ort;
    }

    //Methoden
    @Override
    public String toString(){
        return "["+StreetAndNumber + ", " + Postleitzahl + ", " + Ort+"]" ;
    }
}

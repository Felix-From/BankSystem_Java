package Bank;

import java.util.ArrayList;
import java.util.Arrays;

public class Kunde {
    private int Kundennummer;
    private String Telefonnummer;
    private String Email;
    private Adresse Kunde_Adresse;
    private Konto[] Kontos = new Konto[9];

    public Kunde(int Kundennummer, String Telefonnummer, String Email, Adresse adresse) {
        this.Kundennummer = Kundennummer;
        this.Telefonnummer = Telefonnummer;
        this.Email = Email;
        this.Kunde_Adresse = adresse;
    }

    //Setter
    void setTelefonnummer(String Telefonnummer) {
        this.Telefonnummer = Telefonnummer;
    }
    void setEmail(String Email) {
        this.Email = Email;
    }
    void setAdresse(String StreetAndNumber,String Postleitzahl, String Ort) {
        this.Kunde_Adresse = new Adresse(StreetAndNumber,Postleitzahl,Ort);
    }


    //Getter
    int getKundenNummer() {
        return Kundennummer;
    }
    String getTelefonnummer() {
        return Telefonnummer;
    }
    String getEmail() {
        return Email;
    }
    Adresse getAdresseArray() {
        return Kunde_Adresse;
    }
    String getAdresseString() {
        return Kunde_Adresse.toString();
    }

    //Methoden
    boolean addKonto(Konto konto){
        for(int i=0;i<Kontos.length;i++){
            if(Kontos[i] == null)
            {
                Kontos[i]=konto;
                return true;
            }
        }
        return false;
    }

    boolean removeKonto(Konto konto){
        for(int i=0;i<Kontos.length;i++){
            if(Kontos[i].equals(konto)){
                Kontos[i]=null;
                // Nicht ausführen, Delete nicht auf Bankseite eingefügt.
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        ArrayList<Konto> my_kontos = new ArrayList<>();
        for (Konto konto : Kontos) {
            if (konto != null) {
                my_kontos.add(konto);
            }
        }
        return "Kundennummer=" + Kundennummer +
                ", Telefonnummer='" + Telefonnummer + '\'' +
                ", Email='" + Email + '\'' +
                ", Adresse=" + Kunde_Adresse +
                ", Kontos=" + Arrays.toString(my_kontos.toArray());
    }
}


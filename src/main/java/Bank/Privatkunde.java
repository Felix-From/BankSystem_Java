package Bank;

import java.time.LocalDate;

public class Privatkunde extends Kunde{
    private String Vorname;
    private String Nachname;
    private LocalDate Geburtsdatum;

    public Privatkunde(String Vorname,String Nachname, LocalDate date,int Kundennummer, String Telefonnummer, String Email, Adresse adresse) {
        super(Kundennummer, Telefonnummer, Email, adresse);
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Geburtsdatum = date;
    }

    public String getFullName(){
        return Vorname + " " + Nachname;
    }

    public String getFirstName() {
        return Vorname;
    }
    public String getLastName() {
        return Nachname;
    }

    @Override
    public String toString(){
        return "[Privatkunde] Name: "+getFullName()+", Geburtsdatum "+Geburtsdatum.toString()+", "+super.toString();
    }

}

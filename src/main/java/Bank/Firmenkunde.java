package Bank;

public class Firmenkunde extends Kunde{
    private String Firmenname;
    private Ansprechpartner Ansprechpartner;

    public Firmenkunde(String Firmenname, Ansprechpartner partner,int Kundennummer, String Telefonnummer, String Email, Adresse adresse) {
        super(Kundennummer, Telefonnummer, Email, adresse);
        this.Firmenname = Firmenname;
        this.Ansprechpartner = partner;
    }
    public String getFirmenname() {
        return Firmenname;
    }

    @Override
    public String toString() {
        return "[Firmenkunde] Firmenname: " + getFirmenname() +
                ", Ansprechpartner: " + Ansprechpartner.toString() +
                ", "+ super.toString();
    }
}

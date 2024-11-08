package Bank;

public class Konto {
    private String IBAN;
    private Double Kontostand;

    public Konto(String IBAN, Double Kontostand) {
        this.IBAN = IBAN;
        this.Kontostand = Kontostand;
    }

    //Getter
    public String getIBAN() {
        return IBAN;
    }
    public Double getKontostand() {
        return Kontostand;
    }
    //Setter
    void setKontostand(Double Kontostand) {
        this.Kontostand = Kontostand;
    }
    void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    //Methoden
    boolean GeldAbheben(double sum){
        if(getKontostand()>=sum)
        {
            this.Kontostand -= sum;
            return true;
        }
        return false;
    }

    boolean GeldEinzahlen(double sum){
        if(sum>0)
        {
            this.Kontostand += sum;
            return true;
        }
        return false;
    }

    boolean Ueberweisen(Konto Target,double sum)
    {
        if(this.GeldAbheben(sum))
        {
            if(Target.GeldEinzahlen(sum)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Konto{" +
                "IBAN='" + IBAN + '\'' +
                ", Kontostand=" + Kontostand +
                '}';
    }
}

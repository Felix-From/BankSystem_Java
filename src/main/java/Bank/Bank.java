package Bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private String name;
    private String bic;
    private Adresse adresse;
    private List<Kunde> kundenListe;
    private List<Konto> kontenListe;
    private List<Ansprechpartner> AnsprechpartnerListe;
    private int nextKundenNummer;

    public Bank(String name, String bic, Adresse adresse) {
        this.name = name;
        this.bic = bic;
        this.adresse = adresse;
        this.kundenListe = new ArrayList<>();
        this.kontenListe = new ArrayList<>();
        this.AnsprechpartnerListe = new ArrayList<>();
        this.nextKundenNummer = 1000;
    }

    public Ansprechpartner createAnsprechpartner(String vorname,String nachname,String Telefon) {
        Ansprechpartner Partner = new Ansprechpartner(vorname, nachname, Telefon);
        AnsprechpartnerListe.add(Partner);
        return Partner;
    }

    public Privatkunde createPrivatkunde(String vorname, String nachname, LocalDate Geburtstag, String Telefon, String Email, Adresse adresse) {
        int kundenNummer = generateKundenNummer();
        Privatkunde privatkunde = new Privatkunde(vorname, nachname,Geburtstag,kundenNummer, Telefon, Email, adresse);
        kundenListe.add(privatkunde);
        return privatkunde;
    }

    public Firmenkunde createFirmenkunde(String firmenname,Ansprechpartner Partner,String Telefon, String Email, Adresse adresse) {
        int kundenNummer = generateKundenNummer();
        Firmenkunde firmenkunde = new Firmenkunde(firmenname,Partner,kundenNummer,Telefon,Email, adresse);
        kundenListe.add(firmenkunde);
        return firmenkunde;
    }

    public Konto createKonto(int kundenNummer) {
        Optional<Kunde> kundeOpt = getKundeByNummer(kundenNummer);
        if (kundeOpt.isPresent()) {
            Konto konto = new Konto(generateIBAN(), 0d);
            kontenListe.add(konto);
            kundeOpt.get().addKonto(konto);
            return konto;
        } else {
            System.out.println("Kunde mit dieser Kundennummer existiert nicht.");
            return null;
        }
    }

    public Ansprechpartner getFirstAnsprechpartnerFromFirstName(String name) {
        for (Ansprechpartner Partner : AnsprechpartnerListe) {
                if (Partner.getVorname().equalsIgnoreCase(name)) {
                    return Partner;
                }
        }
        return null;
    }

    public Optional<Kunde> getKundeByNummer(int kundenNummer) {
        return kundenListe.stream()
                .filter(kunde -> kunde.getKundenNummer() == kundenNummer)
                .findFirst();
    }


    public List<Kunde> getKundenByName(String name) {
        List<Kunde> result = new ArrayList<>();
        for (Kunde kunde : kundenListe) {
            if(kunde instanceof Privatkunde) {
                if (((Privatkunde) kunde).getFullName().contains(name)) {
                    result.add(kunde);
                }
            } else if (kunde instanceof Firmenkunde) {
                if (((Firmenkunde) kunde).getFirmenname().equalsIgnoreCase(name)) {
                    result.add(kunde);
                }
            }
        }
        return result;
    }

    public List<Kunde> getAllKunden() {
        return new ArrayList<>(kundenListe);
    }

    public List<Kunde> getAllKundenSortedByKontonummer() {
        List<Kunde> sortedList = new ArrayList<>(kundenListe);
        sortedList.sort((k1, k2) -> Integer.compare(k1.getKundenNummer(), k2.getKundenNummer()));
        return sortedList;
    }

    public List<Konto> getAllKonten() {
        return new ArrayList<>(kontenListe);
    }

    public Konto getKontoByIBAN(String IBAN){
        List<Konto> all = getAllKonten();
        for (Konto konto : all) {
            if(konto.getIBAN().equals(IBAN)){
                return konto;
            }
        }
        return null;
    }

    private int generateKundenNummer() {
        return nextKundenNummer++;
    }

    private String generateIBAN() {
        return "DE" + (1000000000L + kontenListe.size());
    }

}

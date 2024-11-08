package Bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class BankSystem {
    private Bank bank;
    private Scanner scanner;

    public BankSystem() {
        Adresse bankAdresse = new Adresse("Beispiel Street", "City", "12345");
        bank = new Bank("MyBank", "BANK123", bankAdresse);
        scanner = new Scanner(System.in);
    }

    public void Main_Menu() {
        int choice;
        do {
            cPrint menu = new cPrint();
            menu.setMinWidth(60);
            menu.setPrefab(__generateStyling());

            menu.setHeader("Main Menu");
            menu.addBodyContent(new BodyContent("1", "Kunden Management", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("2", "Konto Management", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("3", "Bank Management", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("4",ColorPrint.YELLOW,ColorPrint.RESET, "Load ExampleData for Tests", ColorPrint.CYAN,ColorPrint.RESET));
            menu.addBodyContent(new BodyContent("5", "Beenden", menu.getPrefab()));
            menu.print();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> kundenManagement();
                case 2 -> kontoManagement();
                case 3 -> bankManagement();
                case 4 -> loadDefaultDataForQuickTests();
                case 5 -> System.out.println("Beenden...");
                default -> System.out.println("Ungültige Auswahl, bitte erneut versuchen.");
            }
        } while (choice != 5);
    }

    private void kundenManagement() {
        __clearTerminal();
        int choice;
        do {
            cPrint menu = new cPrint();
            menu.setMinWidth(60);
            menu.setPrefab(__generateStyling());

            menu.setHeader("Kunden Management");
            menu.addBodyContent(new BodyContent("1", "Ansprechpartner Erstellen", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("2", "Privatkunde Erstellen", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("3", "Firmenkunde Erstellen", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("4", "Zurück", menu.getPrefab()));
            menu.print();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createAnsprechpartner();
                case 2 -> createPrivatkunde();
                case 3 -> createFirmenkunde();
                case 4 -> System.out.println("Zurück zum Hauptmenü.");
                default -> System.out.println("Ungültige Auswahl, bitte erneut versuchen.");
            }
        } while (choice != 4);
    }

    private void kontoManagement() {
        __clearTerminal();
        int choice;
        do {
            cPrint menu = new cPrint();
            menu.setMinWidth(60);
            menu.setPrefab(__generateStyling());

            menu.setHeader("Konto Management");
            menu.addBodyContent(new BodyContent("1", "Konto Anlegen (An Kundennummer Zuordnen)", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("2", "Kunde mit Konten Anzeigen (Auswahl an Kundennummer)", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("3", "Kunde mit Konten Anzeigen (Auswahl mit Name)", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("4", "Konto Anzeigen (IBAN eingabe)", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("5", "Zurück", menu.getPrefab()));
            menu.print();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createKonto();
                case 2 -> showKundeMitKontenByNummer();
                case 3 -> showKundeMitKontenByName();
                case 4 -> showKontoByIBAN();
                case 5 -> System.out.println("Zurück zum Hauptmenü.");
                default -> System.out.println("Ungültige Auswahl, bitte erneut versuchen.");
            }
        } while (choice != 5);
    }

    private void bankManagement() {
        __clearTerminal();
        int choice;
        do {
            cPrint menu = new cPrint();
            menu.setMinWidth(60);
            menu.setPrefab(__generateStyling());

            menu.setHeader("Bank Management");
            menu.addBodyContent(new BodyContent("1", "Alle Kunden unsortiert anzeigen", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("2", "Alle Kunden sortiert nach Kontonummer aufsteigend anzeigen", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("3", "Alle Konten unsortiert anzeigen", menu.getPrefab()));
            menu.addBodyContent(new BodyContent("4", "Zurück", menu.getPrefab()));
            menu.print();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showAllKundenUnsorted();
                case 2 -> showAllKundenSorted();
                case 3 -> showAllKontenUnsorted();
                case 4 -> System.out.println("Zurück zum Hauptmenü.");
                default -> System.out.println("Ungültige Auswahl, bitte erneut versuchen.");
            }
        } while (choice != 4);
    }


    // TestDaten
    private void loadDefaultDataForQuickTests(){
        //Ansprechpartner
        bank.createAnsprechpartner("Tom","Sellac","123456");
        bank.createAnsprechpartner("Leo","Klar","654321");

        //PrivatKunden ohne Konten
        bank.createPrivatkunde("Bob","Mayer",LocalDate.of(1987,11,20),"123456","BobMayer@email.xx",new Adresse("Straße 1","13456","ExampleOrt"));
        bank.createPrivatkunde("Anna", "Schmidt", LocalDate.of(1992, 3, 15), "654321", "AnnaSchmidt@email.xx", new Adresse("Musterstraße 2", "23456", "Beispielstadt"));
        bank.createPrivatkunde("Peter", "Müller", LocalDate.of(1980, 7, 12), "987654", "PeterMueller@email.xx", new Adresse("Hauptstraße 10", "34567", "Beispieldorf"));

        //PrivatKunden mit Konto
        Kunde k;
        k = bank.createPrivatkunde("Laura", "Klein", LocalDate.of(1995, 9, 30), "112233", "LauraKlein@email.xx", new Adresse("Südallee 5", "45678", "Musterdorf"));
        bank.createKonto(k.getKundenNummer());
        k = bank.createPrivatkunde("Max", "Mustermann", LocalDate.of(1975, 2, 28), "445566", "MaxMustermann@email.xx", new Adresse("Westweg 20", "56789", "Modellstadt"));
        bank.createKonto(k.getKundenNummer());
        k = bank.createPrivatkunde("Julia", "Berg", LocalDate.of(1990, 5, 18), "778899", "JuliaBerg@email.xx", new Adresse("Nordstraße 15", "67890", "Bergstadt"));
        bank.createKonto(k.getKundenNummer());

        //Privatkunden mit vielen Konten:
        k = bank.createPrivatkunde("Sophie", "Hoffmann", LocalDate.of(1994, 8, 25), "334455", "SophieHoffmann@email.xx", new Adresse("Gartenweg 12", "89012", "Musterhausen"));
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        k = bank.createPrivatkunde("Felix", "Schneider", LocalDate.of(1988, 4, 10), "556677", "FelixSchneider@email.xx", new Adresse("Schulstraße 7", "90123", "Beispielhausen"));
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());

        //Firmenkunden ohne Konto
        bank.createFirmenkunde("GFN GmbH",__getAnsprechpartnerFromFirstName("Tom"),"1234567","Freiburg@gfn.de",new Adresse("Unterwerkstr. 5","79105","Freiburg"));
        bank.createFirmenkunde("Tech Solutions GmbH", __getAnsprechpartnerFromFirstName("Tom"), "2345678", "info@techsolutions.de", new Adresse("Industriestraße 10", "80331", "München"));
        bank.createFirmenkunde("AlphaConsult AG", __getAnsprechpartnerFromFirstName("Leo"), "3456789", "contact@alphaconsult.de", new Adresse("Berliner Allee 45", "40212", "Düsseldorf"));

        //Firmenkunden mit Konto
        k = bank.createFirmenkunde("Digital Future Ltd.", __getAnsprechpartnerFromFirstName("Tom"), "4567890", "support@digitalfuture.de", new Adresse("Parkstraße 1", "60313", "Frankfurt"));
        bank.createKonto(k.getKundenNummer());
        k = bank.createFirmenkunde("InnovaTech GmbH", __getAnsprechpartnerFromFirstName("Leo"), "5678901", "info@innovatech.de", new Adresse("Ringstraße 12", "70173", "Stuttgart"));
        bank.createKonto(k.getKundenNummer());
        k = bank.createFirmenkunde("GlobalTrade AG", __getAnsprechpartnerFromFirstName("Tom"), "6789012", "sales@globaltrade.de", new Adresse("Hafenstraße 3", "20095", "Hamburg"));
        bank.createKonto(k.getKundenNummer());
        k = bank.createFirmenkunde("Smart Solutions KG", __getAnsprechpartnerFromFirstName("Leo"), "7890123", "kontakt@smartsolutions.de", new Adresse("Hauptstraße 15", "04109", "Leipzig"));
        bank.createKonto(k.getKundenNummer());


        //Firmenkunden mit Mehrere Konten.
        k = bank.createFirmenkunde("EcoWare GmbH", __getAnsprechpartnerFromFirstName("Tom"), "8901234", "service@ecoware.de", new Adresse("Grüner Weg 22", "90403", "Nürnberg"));
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        k = bank.createFirmenkunde("MediCare AG", __getAnsprechpartnerFromFirstName("Leo"), "9012345", "kontakt@medicare.de", new Adresse("Klinikweg 8", "50667", "Köln"));
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        k = bank.createFirmenkunde("Logix Logistics GmbH", __getAnsprechpartnerFromFirstName("Tom"), "1234568", "support@logix.de", new Adresse("Bahnhofstraße 20", "01067", "Dresden"));
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());
        k = bank.createFirmenkunde("NextGen Software AG", __getAnsprechpartnerFromFirstName("Leo"), "2345679", "hello@nextgen.de", new Adresse("Technikpark 4", "28209", "Bremen"));
        bank.createKonto(k.getKundenNummer());
        bank.createKonto(k.getKundenNummer());

        System.out.println("Testdaten hinzugefügt. (Man kann es öfters ausführen um mehr leute zu erstellen, aber die haben dann den gleichen Namen!)");
    }


    //Methoden

    private void createAnsprechpartner() {
        System.out.println("Erstelle Ansprechpartner...");

        System.out.print("Bitte Vornamen eingeben: ");
        String name = scanner.nextLine();

        System.out.print("Bitte Nachnamen eingeben: ");
        String lastname = scanner.nextLine();

        System.out.print("Bitte Telefonnummer eingeben: ");
        String telefon = scanner.nextLine();

        System.out.print("Möchten Sie die Daten speichern? (J/N): ");
        String speichern = scanner.nextLine().trim().toUpperCase();

        if (speichern.equals("N")) {
            System.out.println("Vorgang abgebrochen. Die Daten wurden nicht gespeichert.");
            return;
        }

        bank.createAnsprechpartner(name,lastname,telefon);
    }

    private void createPrivatkunde() {
        System.out.println("Erstelle Privatkunde...");

        System.out.print("Bitte Vornamen eingeben: ");
        String name = scanner.nextLine();

        System.out.print("Bitte Nachnamen eingeben: ");
        String lastname = scanner.nextLine();

        System.out.println("Bitte Geburtsdatum eingeben:");
        LocalDate geburtstag = __getDateFromInput();
        System.out.println(geburtstag.getDayOfWeek().name()+", "+geburtstag.format(DateTimeFormatter.ofPattern("dd.MM.uuuu")));

        System.out.print("Bitte Telefonnummer eingeben: ");
        String telefon = scanner.nextLine();

        System.out.print("Bitte E-Mail eingeben: ");
        String email = scanner.nextLine();

        System.out.println("Bitte Adresse eingeben:");
        Adresse anschrift = __getAdresseFromInput();

        System.out.print("Möchten Sie die Daten speichern? (J/N): ");
        String speichern = scanner.nextLine().trim().toUpperCase();

        if (speichern.equals("N")) {
            System.out.println("Vorgang abgebrochen. Die Daten wurden nicht gespeichert.");
            return;
        }

        bank.createPrivatkunde(name,lastname,geburtstag,telefon,email,anschrift);

    }

    private void createFirmenkunde() {
        System.out.println("Erstelle Firmenkunde...");

        System.out.print("Bitte Firmenname eingeben: ");
        String Firmenname = scanner.nextLine();


        Ansprechpartner Partner = null;
        int breakCoolDown = 0;
        do {
            System.out.print("Bitte Vorname vom Ansprechpartner eingeben: ");
            String PartnerName = scanner.nextLine();
            Partner = __getAnsprechpartnerFromFirstName(PartnerName);
            if(Partner == null) {
                System.out.println("Kein Ansprechpartner mit diesem Namen gefunden.");
                breakCoolDown++;
            }
            if(breakCoolDown >3){
                return;
            }
        } while(Partner == null);

        System.out.print("Bitte Telefonnummer eingeben: ");
        String telefon = scanner.nextLine();

        System.out.print("Bitte E-Mail eingeben: ");
        String email = scanner.nextLine();

        System.out.println("Bitte Adresse eingeben:");
        Adresse anschrift = __getAdresseFromInput();

        System.out.print("Möchten Sie die Daten speichern? (J/N): ");
        String speichern = scanner.nextLine().trim().toUpperCase();

        if (speichern.equals("N")) {
            System.out.println("Vorgang abgebrochen. Die Daten wurden nicht gespeichert.");
            return;
        }

        bank.createFirmenkunde(Firmenname,Partner,telefon,email,anschrift);
    }

    private void createKonto() {
        System.out.println("Konto anlegen...");

        System.out.print("Bitte Kundennummer eingeben: ");
        int Kundennummer = Integer.parseInt(scanner.nextLine());;

        Optional<Kunde> k = bank.getKundeByNummer(Kundennummer);
        if(k.isPresent()) {
            bank.createKonto(Kundennummer);
        }
        else {
            System.out.println("Kein Kunden mit der Kundennummer gefunden.");
        }
    }

    private void showKundeMitKontenByNummer() {
        System.out.println("Kunde mit Konten anzeigen (nach Kundennummer)...");

        System.out.print("Bitte Kundennummer eingeben: ");
        int Kundennummer = scanner.nextInt();

        List<Kunde> resultSet = new ArrayList<>();
        for(Kunde k:bank.getAllKunden()){
            if(k.getKundenNummer() == Kundennummer){
                resultSet.add(k);
                System.out.println(k.toString());
            }
        }
        if (resultSet.isEmpty()) {
            System.out.println("Leider keine Einträge gefunden.");
        }
        return;
    }

    private void showKundeMitKontenByName() {
        System.out.println("Kunde mit Konten anzeigen (nach Name)...");

        System.out.print("Bitte Name eingeben: ");
        String Kundenname = scanner.nextLine();

        List <Kunde> resultSet =bank.getKundenByName(Kundenname);
        if (resultSet.isEmpty()) {
            System.out.println("Kein Eintrag für diesen Namen gefunden...");
        } else {
            for(Kunde k : resultSet){
                System.out.println(k.toString());
            }
        }
    }

    private void showKontoByIBAN() {
        System.out.println("Konto anzeigen (nach IBAN)...");

        System.out.print("Bitte IBAN eingeben (DEXXXXXXXXXXXX): ");
        String IBAN = scanner.nextLine();

        Konto k = bank.getKontoByIBAN(IBAN);
        if(k != null) {
            System.out.println(k.toString());
        } else {
            System.out.println("Kein Konto mit dieser Nummer gefunden.");
        }
    }

    private void showAllKundenUnsorted() {
        System.out.println("Alle Kunden unsortiert anzeigen...");
        for(Kunde k : bank.getAllKunden()){
            System.out.println(k.toString());
        }
    }

    private void showAllKundenSorted() {
        System.out.println("Alle Kunden sortiert nach Kontonummer aufsteigend anzeigen...");
        for(Kunde k : bank.getAllKundenSortedByKontonummer()){
            System.out.println(k.toString());
        }
    }

    private void showAllKontenUnsorted() {
        System.out.println("Alle Konten unsortiert anzeigen...");
        for(Konto k : bank.getAllKonten())
        {
            System.out.println(k.toString());
        }
    }


    // Helpers

    private void __clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Ansprechpartner __getAnsprechpartnerFromFirstName(String name){
        return bank.getFirstAnsprechpartnerFromFirstName(name);
    }

    private LocalDate __getDateFromInput()
    {
        int gotDay = 0,gotMonth = 0,gotYear = 0;
        do{
            try {
                if (gotDay == 0) {
                    System.out.print("Bitte Tag eingeben - Tag(1-31): ");
                    int day = Integer.parseInt(scanner.nextLine());
                    if (day > 0 && day < 32) {
                        gotDay = day;
                    }
                }
                if (gotMonth == 0) {
                    System.out.print("Bitte Monat eingeben - Monat(1-12): ");
                    int month = Integer.parseInt(scanner.nextLine());
                    if (month > 0 && month < 13) {
                        gotMonth = month;
                    }
                }
                if (gotYear == 0) {
                    System.out.print("Bitte Jahr eingeben - Jahr(" + LocalDate.MIN.getYear() + "-" + LocalDate.MAX.getYear() + "): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    if (year > LocalDate.MIN.getYear() && year < LocalDate.MAX.getYear()) {
                        gotYear = year;
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (gotDay==0 || gotMonth==0 || gotYear==0);
        return LocalDate.of(gotYear,gotMonth,gotDay);
    }

    private Adresse __getAdresseFromInput()
    {
        String strasseUndHausnummer = "";
        String postleitzahl = "";
        String ort = "";

        do {
            System.out.print("Bitte Strasse und Hausnummer eingeben: ");
            strasseUndHausnummer = scanner.nextLine().trim();
            if (strasseUndHausnummer.isEmpty()) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Straße und Hausnummer ein.");
            }
        } while (strasseUndHausnummer.isEmpty());

        do {
            System.out.print("Bitte Postleitzahl eingeben - (5 Ziffern): ");
            postleitzahl = scanner.nextLine().trim();
            if (!postleitzahl.matches("\\d{5}")) {
                System.out.println("Ungültige Postleitzahl. Bitte geben Sie eine 5-stellige Zahl ein.");
            }
        } while (!postleitzahl.matches("\\d{5}"));

        do {
            System.out.print("Bitte Ort eingeben: ");
            ort = scanner.nextLine().trim();
            if (ort.isEmpty()) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie einen gültigen Ort ein.");
            }
        } while (ort.isEmpty());

        return new Adresse(strasseUndHausnummer, postleitzahl, ort);
    }

    private cPrintPrefab __generateStyling() {
        cPrintPrefab prefab = new cPrintPrefab();
        prefab.border_color = ColorPrint.YELLOW.getCode();
        prefab.header_text_color = ColorPrint.CYAN.getCode();
        prefab.body_text_color_1 = ColorPrint.GREEN.getCode();
        prefab.body_text_color_2 = ColorPrint.PURPLE.getCode();
        prefab.footer_text_color = ColorPrint.BLUE.getCode();
        return prefab;
    }

    //Main
    public static void main(String[] args) {
        BankSystem system = new BankSystem();
        system.Main_Menu();
    }
}

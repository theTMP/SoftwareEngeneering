package org.hbrs.se1.ws22.uebung4;



import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Shell {
    private static Scanner sc;

    private static boolean exit = false;
    private static Container container = Container.getInstance();










    //Methoden
    private void enter(Mitarbeiter mitarbeiter) throws ContainerException {
        container.addEmployee(mitarbeiter);
    }
    private void exit() {
        exit = true;

    }
    private void enter() throws PersistenceException, ContainerException {
        int i = 0;
        boolean correct = false;
        boolean nextExpertise = true;
        int ID = 0;
        String vName;
        String nName;
        String rolle;
        String abteilung;
        String expertise = "";
        Integer expertisenLvl;
        HashMap<String,Integer> expertisen = new HashMap<>();

        do {
            System.out.println("Geben Sie die ID des Mitarbeiters ein: ");
            String IDString = sc.next();
            sc.nextLine();
            try {
                ID = Integer.parseInt(IDString);
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter an int value");
            }

        } while (correct == false) ;

        System.out.println("Geben Sie den Vornamen des Mitarbeiters ein: ");
        vName = sc.nextLine();

        System.out.println("Geben Sie den Nachnamen des Mitarbeiters ein: ");

        nName = sc.nextLine();
        
        System.out.println("Geben Sie die Rolle des Mitarbeiters ein: ");
        rolle = sc.nextLine();
        System.out.println("Geben Sie die Abteilung des Mitarbeiters ein: ");
        abteilung = sc.nextLine();
        System.out.println("Geben Sie die Expertise und das Expertisen-Level (1-3) mit einem Leerzeichen getrennt an: ");
        while (nextExpertise) {
            expertise = sc.next();
            expertisenLvl = Integer.parseInt(sc.next());
            expertisen.put(expertise,expertisenLvl);
            System.out.println("Weitere Exeprtisen vorhanden? Ja / Nein");
            if (sc.next().toLowerCase().equals("nein")) {
                nextExpertise = false;
            }


        }

        MitarbeiterKonkret mNeu = new MitarbeiterKonkret(ID, nName,vName,rolle,abteilung,expertisen);
        container.addEmployee(mNeu);
        System.out.println("Mitarbeiter wurde erfolgreich hinzugefügt");

    }

    private void load() throws PersistenceException {
        PersistenceStrategy<Mitarbeiter> persStrat = new PersistenceStrategyStream<Mitarbeiter>();
        container.setStrategy(persStrat);
        System.out.println("merge / force");
        String command = sc.next();
        try {
            if (command.equals("merge")) {
                container.loadMerge();
            } else if (command.equals("force")) {
                container.load();
            } else {
                throw new UnsupportedOperationException("Enter a valid value");
            }
        } catch(PersistenceException p)  {
            System.out.println("Sie müssen zuerst store aufrufen, bevor Sie die Werte speichern können");
        }
    }

    private void store() throws PersistenceException {
        PersistenceStrategy<Mitarbeiter> persStrat = new PersistenceStrategyStream<Mitarbeiter>();
        container.setStrategy(persStrat);
        container.store();
    }

    private void help() {
        System.out.println("Befehle: \n enter -> Eingabe eines Mitarbeiters \n store -> Abspeicherung der Eingetragenen Mitarbeiter \n load -> Laden der abgespeicherten Mitarbeiter \n dump -> Ausgabe der Mitarbeiter auf dem Bildschirm \n serach -> Suche einen Mitarbeiter basierend auf seiner Expertise \n exit -> Schließen der Anwendung");
    }

    public void dump() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%25s %25s %25s %25s %25s","ID", "Vorname", "Nachname", "Rolle", "Abteilung");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        List<Mitarbeiter> liste = container.getCurrentList();
        liste.sort(null);
      /*  liste.stream()

                .forEach(element -> System.out.format("%15s %15s %15s %15s",element.getId(),element.getVorname(),element.getNachname(),element.getRolle(),element.getAbteilung()));
        */
        for (Mitarbeiter m :liste) {
            System.out.format("%25s %25s %25s %25s %25s",m.getId(),m.getVorname(),m.getNachname(),m.getRolle(),m.getAbteilung());
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");



    }
    //Search Methode sucht Mitarbeiter nach Expertise. Dabei wird die java Hash-Map nach dem Key durchsucht.

    public void search(String key) {
        List<Mitarbeiter> listM = container.getCurrentList();
        for (Mitarbeiter mitarbeiter : listM) {
            if (mitarbeiter.getExpertisen().containsKey(key)) {
                System.out.println(mitarbeiter);
            }
            System.out.println("Mitarbeiter erfolgreich durchsucht");
        }
    }
    public void search() {
        System.out.println("Nach welcher Expertise wollen Sie suchen?");
        String expertise = sc.next();
        search(expertise);
    }

    public void run() throws PersistenceException, ContainerException {
        sc = new Scanner(System.in);

        String s = "";


        while (exit == false) {
            System.out.print(">");
            s = sc.next();




            switch (s) {
                case "enter":
                    enter();
                    break;
                case "store":
                    store();
                    break;
                case "load":
                    load();
                    break;
                case "dump":
                    dump();
                    break;
                case "search":
                    search();
                    break;
                case "exit":
                    exit();
                    break;
                case "help":
                    help();
                    break;
                default:
                    System.out.println("Falsche Eingabe");

            }



        }
        System.out.println("Programm wurde erfolgereich beendet");
        sc.close();
    }



}

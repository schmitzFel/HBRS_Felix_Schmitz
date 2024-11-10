package org.hbrs.se1.ws24.exercises.uebung4.view;


import org.hbrs.se1.ws24.exercises.uebung4.controller.Container;
import org.hbrs.se1.ws24.exercises.uebung4.controller.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.controller.UserStory;

import java.util.Scanner;

public class UserInterface {
    private static Container container = Container.getInstance();

    static {
        container.setPersistenceStrategy(new PersistenceStrategyStream<UserStory>());
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Willkommen beim User Story Management von Coll@HBRS.");
        System.out.println("Befehle: enter, store, load, dump, exit, help");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0];

            switch (command) {
                case "enter":
                    handleEnter();
                    break;
                case "store":
                    handleStore();
                    break;
                case "load":
                    handleLoad();
                    break;
                case "dump":
                    handleDump();
                    break;
                case "help":
                    showHelp();
                    break;
                case "exit":
                    System.out.println("Programm wird beendet.");
                    System.exit(0);
                default:
                    System.out.println("Ungültiger Befehl. Geben Sie 'help' für eine Liste der Befehle ein.");
            }
        }
    }

    private static void handleEnter() {
        System.out.println("Bitte geben Sie die User Story-Daten ein.");

        // Methode zur sicheren Eingabe von Integer-Werten
        int id = safeIntInput("ID");
        String epic = safeStringInput("Epic");
        String description = safeStringInput("Beschreibung");
        String acceptanceCriteria = safeStringInput("Akzeptanzkriterien");
        int effort = safeIntInput("Aufwand");
        int value = safeIntInput("Mehrwert");
        int penalty = safeIntInput("Strafe");
        int risk = safeIntInput("Risiko");

        UserStory story = new UserStory(id, epic, description, acceptanceCriteria, effort, value, penalty, risk);
        try {
            container.addUserStory(story);
            System.out.println("User Story wurde erfolgreich hinzugefügt.");
        } catch (Exception e) {
            System.out.println("Fehler beim Hinzufügen der User Story: " + e.getMessage());
        }
    }

    // Methode für sichere Integer-Eingabe mit Eingabeaufforderung
    private static int safeIntInput(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
                }
            } else {
                System.out.println(prompt + " darf nicht leer sein. Bitte geben Sie eine Zahl ein.");
            }
        }
    }

    // Methode für sichere String-Eingabe mit Eingabeaufforderung
    private static String safeStringInput(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println(prompt + " darf nicht leer sein.");
            }
        }
    }


    private static void handleStore() {
        try {
            container.store();
            System.out.println("User Stories wurden erfolgreich gespeichert.");
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    private static void handleLoad() {
        try {
            container.load();
            System.out.println("User Stories wurden erfolgreich geladen.");
        } catch (Exception e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    private static void handleDump() {
        System.out.println("Aktuelle User Stories:");
        container.dump();
    }

    private static void showHelp() {
        System.out.println("Verfügbare Befehle:");
        System.out.println("  enter  - Eingabe einer neuen User Story");
        System.out.println("  store  - Speichert alle User Stories dauerhaft");
        System.out.println("  load   - Lädt alle User Stories von einem Datenträger");
        System.out.println("  dump   - Zeigt alle User Stories an");
        System.out.println("  exit   - Beendet das Programm");
        System.out.println("  help   - Zeigt diese Hilfe an");
    }
}


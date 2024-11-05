package org.hbrs.se1.ws24.exercises.uebung4;


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
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Epic: ");
        String epic = scanner.nextLine();
        System.out.print("Beschreibung: ");
        String description = scanner.nextLine();
        System.out.print("Akzeptanzkriterien: ");
        String acceptanceCriteria = scanner.nextLine();
        System.out.print("Priorität: ");
        int priority = Integer.parseInt(scanner.nextLine());
        System.out.print("Velocity: ");
        int velocity = Integer.parseInt(scanner.nextLine());

        UserStory story = new UserStory(id, epic, description, acceptanceCriteria, priority, velocity);
        try {
            container.addUserStory(story);
            System.out.println("User Story wurde erfolgreich hinzugefügt.");
        } catch (Exception e) {
            System.out.println("Fehler beim Hinzufügen der User Story: " + e.getMessage());
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


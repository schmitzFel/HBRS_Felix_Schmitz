// Datei: UserInterface.java
package org.hbrs.se1.ws24.exercises.uebung4.view;

import org.hbrs.se1.ws24.exercises.uebung4.model.Container;
import org.hbrs.se1.ws24.exercises.uebung4.model.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.model.UserStory;

import java.util.Scanner;

public class UserInterface {
    private static final Container container = Container.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    static {
        container.setPersistenceStrategy(new PersistenceStrategyStream<UserStory>());
    }

    public void handleEnter() {
        System.out.println("Bitte geben Sie die User Story-Daten ein.");

        int id = safeIntInput("ID");
        String epic = safeStringInput("Epic");
        String description = safeStringInput("Beschreibung");
        String acceptanceCriteria = safeStringInput("Akzeptanzkriterien");
        int effort = safeIntInput("Aufwand");
        int value = safeIntInput("Mehrwert");
        int penalty = safeIntInput("Strafe");
        int risk = safeIntInput("Risiko");
        String projektName = safeStringInput("Projektname"); // Eingabe des Projektnamens

        UserStory story = new UserStory(id, epic, description, acceptanceCriteria, effort, value, penalty, risk, projektName);
        try {
            container.addUserStory(story);
            System.out.println("User Story wurde erfolgreich hinzugefügt.");
        } catch (Exception e) {
            System.out.println("Fehler beim Hinzufügen der User Story: " + e.getMessage());
        }
    }

    public void handleStore() {
        try {
            container.store();
            System.out.println("User Stories wurden erfolgreich gespeichert.");
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void handleLoad() {
        try {
            container.load();
            System.out.println("User Stories wurden erfolgreich geladen.");
        } catch (Exception e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    public void handleDump() {
        System.out.println("Aktuelle User Stories:");
        container.dump();
    }

    public void handleDumpProject(String projektName) {
        System.out.println("User Stories für Projekt: " + projektName);
        container.dumpByProject(projektName);
    }

    public void showHelp() {
        System.out.println("Verfügbare Befehle:");
        System.out.println("  enter  - Eingabe einer neuen User Story");
        System.out.println("  store  - Speichert alle User Stories dauerhaft");
        System.out.println("  load   - Lädt alle User Stories von einem Datenträger");
        System.out.println("  dump   - Zeigt alle User Stories an");
        System.out.println("  exit   - Beendet das Programm");
        System.out.println("  help   - Zeigt diese Hilfe an");
    }

    // Hilfsmethoden für sichere Eingaben
    private int safeIntInput(String prompt) {
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

    private String safeStringInput(String prompt) {
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
}


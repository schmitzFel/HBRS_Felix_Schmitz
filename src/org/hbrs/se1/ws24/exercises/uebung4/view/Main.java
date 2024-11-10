// Datei: Main.java
package org.hbrs.se1.ws24.exercises.uebung4.view;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen beim User Story Management von Coll@HBRS.");
        System.out.println("Befehle: enter, store, load, dump, exit, help");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");

            if (parts[0].equals("dump") && parts.length > 2 && parts[1].equals("projekt")) {
                ui.handleDumpProject(parts[2]);
            } else {
                String command = parts[0];
                switch (command) {
                    case "enter":
                        ui.handleEnter();
                        break;
                    case "store":
                        ui.handleStore();
                        break;
                    case "load":
                        ui.handleLoad();
                        break;
                    case "dump":
                        ui.handleDump();
                        break;
                    case "help":
                        ui.showHelp();
                        break;
                    case "exit":
                        System.out.println("Programm wird beendet.");
                        System.exit(0);
                    default:
                        System.out.println("Ungültiger Befehl. Geben Sie 'help' für eine Liste der Befehle ein.");
                }
            }
        }
    }
}


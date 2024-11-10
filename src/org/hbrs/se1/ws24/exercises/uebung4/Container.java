package org.hbrs.se1.ws24.exercises.uebung4;


import java.util.ArrayList;
import java.util.List;

public class Container {


    private List<UserStory> userStories = new ArrayList<>();  // Liste zur Speicherung der USer-Objekte

    private static Container instance;    //statische Variable zur Speicherung der einzigen Instanz der Klasse

    private PersistenceStrategy<UserStory> persistenceStrategy;

    private Container() {
        //leer, damit niemand von außen Container instanzieren kann
    }

    //Nicht Thread-Save
    public static synchronized Container getInstance() { //Methode um von außen auf das Container Objekt zugreifen zu können
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    // Gibt die aktuelle Liste der gespeicherten USer-Objekte zurück
    public List<UserStory> getCurrentList() {
        return new ArrayList<>(userStories);  // Rückgabe einer Kopie der Liste
    }

    // Fügt ein neues User-Objekt hinzu, wenn die ID nicht bereits vorhanden ist
    public void addUserStory(UserStory userStory) throws IllegalArgumentException {
        // Überprüfen, ob ein null Objekt übergeben wurde
        if (userStory == null) {
            throw new IllegalArgumentException("Das übergebene User-Objekt darf nicht null sein.");
        }

        // Überprüfen, ob die ID bereits vorhanden ist
        for (UserStory u : this.userStories) {
            if (u.getID().equals(userStory.getID())) {
                throw new IllegalArgumentException();
            }
        }
        this.userStories.add(userStory);
    }

    // Löscht ein User-Objekt anhand der ID
    public String deleteUser(Integer id) {
        for (UserStory u : this.userStories) {
            if (u.getID().equals(id)) {
                this.userStories.remove(u);
                return "User mit ID " + id + " wurde gelöscht.";
            }
        }
        return "Fehler: Kein User mit ID " + id + " gefunden.";
    }

    // Gibt alle gespeicherten User-IDs aus
    public void dump() {
        this.userStories.stream()
                .sorted((us1, us2) -> Double.compare(us2.getPriority(), us1.getPriority()))  // nach Priorität sortieren
                .forEach(System.out::println);
    }

    // Gibt die Anzahl der gespeicherten User-Objekte zurück
    public int size() {
        return this.userStories.size();
    }

    public void reset() {
        this.userStories.clear(); // Leert die Liste der User
    }

    public void setPersistenceStrategy(PersistenceStrategy<UserStory> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    //User-Objekte persistent auf einen Datenspeicher abspeichern
    public void store() throws PersistenceException {

        if (persistenceStrategy != null) {
            persistenceStrategy.save(this.userStories);
        } else {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No persistence strategy set");
        }
    }

    //User-Objekte von einem Datenspeicher beziehen
    public void load() throws PersistenceException {
        if (persistenceStrategy != null) {
            this.userStories = persistenceStrategy.load();
        } else {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No persistence strategy set");
        }
    }

}
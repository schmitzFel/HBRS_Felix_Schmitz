package org.hbrs.se1.ws24.exercises.uebung2.control;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {


    private List<Member> memberList = new ArrayList<>();  // Liste zur Speicherung der Member-Objekte

    private static Container instance;    //statische Variable zur Speicherung der einzigen Instanz der Klasse

    private PersistenceStrategy<Member> persistenceStrategy;

    private Container(){
        //leer, damit niemand von außen Container instanzieren kann
    }

    //Nicht Thread-Save
    public static synchronized Container getInstance(){ //Methode um von außen auf das Container Objekt zugreifen zu können
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }

    // Gibt die aktuelle Liste der gespeicherten Member-Objekte zurück
    public List<Member> getCurrentList() {
        return new ArrayList<>(memberList);  // Rückgabe einer Kopie der Liste
    }

    // Fügt ein neues Member-Objekt hinzu, wenn die ID nicht bereits vorhanden ist
    public void addMember(Member member) throws ContainerException, IllegalArgumentException {
        // Überprüfen, ob ein null Objekt übergeben wurde
        if (member == null) {
            throw new IllegalArgumentException("Das übergebene Member-Objekt darf nicht null sein.");
        }

        // Überprüfen, ob die ID bereits vorhanden ist
        for (Member m : memberList) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(member);
            }
        }
        memberList.add(member);
    }

    // Löscht ein Member-Objekt anhand der ID


    public String deleteMember(Integer id) {
        for (Member m : memberList) {
            if (m.getID().equals(id)) {
                memberList.remove(m);
                return "Member mit ID " + id +" wurde gelöscht.";
            }
        }
        return "Fehler: Kein Member mit ID " + id + " gefunden.";
        /*
         * Der Rückgabewert könnte leicht übersehen oder ignoriert werden,
         * während Exceptions den Entwickler zwingen, sich mit dem Fehler auseinanderzusetzen.
         * Der Entwickler muss jedes Mal den Rückgabewert auf Fehler prüfen, was den Code unnötig verkomplizieren kann.
         */
    }

    // Gibt alle gespeicherten Member-IDs aus
    public void dump() {
        for (Member m : memberList) {
            System.out.println(m.toString());
        }
    }

    // Gibt die Anzahl der gespeicherten Member-Objekte zurück
    public int size() {
        return memberList.size();
    }

    public void reset() {
        memberList.clear(); // Leert die Liste der Member
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    //Member-Objekte persistent auf einen Datenspeicher abspeichern
    public void store() throws PersistenceException {

        if (persistenceStrategy != null) {
            persistenceStrategy.save(memberList);
        } else {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No persistence strategy set");
        }
    }

    //Member-Objekte von einem Datenspeicher beziehen
    public void load() throws PersistenceException {
        if (persistenceStrategy != null) {
            memberList = persistenceStrategy.load();
        } else {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No persistence strategy set");
        }
    }

}
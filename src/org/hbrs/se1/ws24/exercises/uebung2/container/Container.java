package org.hbrs.se1.ws24.exercises.uebung2.container;
import org.hbrs.se1.ws24.exercises.uebung2.member.Member;

import java.util.ArrayList;
import java.util.List;

public class Container {
    // Liste zur Speicherung der Member-Objekte
    private List<Member> memberList = new ArrayList<>();

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
}

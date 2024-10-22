package org.hbrs.se1.ws24.exercises.uebung2.view;

import org.hbrs.se1.ws24.exercises.uebung2.control.Member;

import java.util.List;

public class MemberView {

    // Gibt die übergebene Liste von Member-Objekten aus
    public void dump(List<Member> liste) {
        if (liste == null || liste.isEmpty()) {
            System.out.println("Keine Member vorhanden.");
            return;
        }
        for (Member m : liste) {
            System.out.println(m.toString());
        }
    }
}

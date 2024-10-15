package org.hbrs.se1.ws24.exercises.uebung2.container;

import org.hbrs.se1.ws24.exercises.uebung2.member.Member;

public class ContainerException extends Exception {

    public ContainerException(Member member) {
        super("Das Member-Objekt mit der ID "+ member.getID()+" ist bereits vorhanden!");
    }
}

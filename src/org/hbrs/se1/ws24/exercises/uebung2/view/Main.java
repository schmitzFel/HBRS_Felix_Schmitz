package org.hbrs.se1.ws24.exercises.uebung2.view;

import org.hbrs.se1.ws24.exercises.uebung2.control.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.control.Container;
import org.hbrs.se1.ws24.exercises.uebung2.control.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.control.Member;


public class Main {
    public static Container init() throws ContainerException {

        // Container-Instanz holen (Singleton)
        Container container = Container.getInstance();

        // Diverse Member-Objekte erstellen und hinzufügen
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        Member member3 = new ConcreteMember(3);

        container.addMember(member1);
        container.addMember(member2);
        container.addMember(member3);

        return container;

    }
}

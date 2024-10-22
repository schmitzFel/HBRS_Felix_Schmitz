package org.hbrs.se1.ws24.exercises.uebung2.view;

import org.hbrs.se1.ws24.exercises.uebung2.control.Container;
import org.hbrs.se1.ws24.exercises.uebung2.control.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.control.Member;

import java.util.List;

public class Client {
    public static void main(String[] args) throws ContainerException {

        //Initialisierten Container erstellen lassen
        Container container=Main.init();
        // Aktuelle Liste der Member-Objekte aus dem Container holen
        List<Member> members = container.getCurrentList();

        // MemberView für die Ausgabe nutzen
        MemberView view = new MemberView();

        // Ausgabe der Member-Objekte durch MemberView
        view.dump(members);
    }
    /**
     * Hier wird das Strategy Pattern verwendet, um die Persistenz-Strategie flexibel zu gestalten.
     * Es ermöglicht das Festlegen einer Persistenzstrategie zur Laufzeit, ohne die Klasse Container ändern zu müssen.
     * Dies geschieht durch das Interface PersistenceStrategy und die entsprechenden Implementierungen (z.B. PersistenceStrategyStream und PersistenceStrategyMongoDB).
     * Der Container kann mit der Methode setPersistenceStrategy flexibel eine Persistenzstrategie zugewiesen bekommen.
     */


}

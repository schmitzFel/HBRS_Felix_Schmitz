package org.hbrs.se1.ws24.exercises.uebung2.test;

import org.hbrs.se1.ws24.exercises.uebung2.container.Container;
import org.hbrs.se1.ws24.exercises.uebung2.container.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.container.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.container.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container container;

    @BeforeEach
    public void setUp() {
        container = Container.getInstance();
    }

    @Test
    public void testAddMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);

        container.addMember(member1);
        container.addMember(member2);

        assertEquals(2, container.size());
    }

    @Test
    public void testAddDuplicateMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);

        // einmal hinzufügen
        container.addMember(member1);

        // Jetzt versuchen, das gleiche Member Objekt erneut hinzuzufügen, was eine Exception auslösen sollte
        assertThrows(ContainerException.class, () -> container.addMember(member1));
    }


    @Test
    public void testDeleteMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        container.addMember(member1);
        String result = container.deleteMember(1);

        assertEquals("Member mit ID 1 wurde gelöscht.", result);
    }

    @Test
    public void testDeleteNonExistingMember() {
        String result = container.deleteMember(999);
        assertEquals("Fehler: Kein Member mit ID 999 gefunden.", result);
    }

    @Test
    public void testDump() throws ContainerException {
        //Ausgabe der Member mit dump()
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);

        container.addMember(member1);
        container.addMember(member2);

        container.dump(); // Erwartete Ausgabe: Member (ID = 1), Member (ID = 2)
        assertEquals(2, container.size());
    }

    @Test
    public void testSize() throws ContainerException {
        //testet ob size richtig zählt
        assertEquals(0, container.size());
        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());
    }

    @Test
    public void testSizeAfterDeletingMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        container.addMember(member1);
        container.deleteMember(1);
        assertEquals(0, container.size()); //Nach dem Löschen eines Members sollte der Container leer sein.
    }


    @Test
    public void testInitialContainerSize() {
        assertEquals(0, container.size(), "Nach der Initialisierung sollte der Container leer sein.");
    }


    @Test
    public void testAddingNullMemberThrowsException() {
        //Hinzufügen eines null-Members sollte eine Exception werfen
        assertThrows(IllegalArgumentException.class, () -> container.addMember(null));
    }

    @Test
    public void testContainerRemainsEmptyAfterNullAdd() throws ContainerException {
        try {
            container.addMember(null);
        } catch (IllegalArgumentException e) {
            // Erwartete Ausnahme
        }
        assertEquals(0, container.size(), "Der Container sollte leer bleiben, nachdem versucht wurde, ein null-Member hinzuzufügen.");
    }


    // Test 1: Keine Persistenzstrategie gesetzt (Null-Strategie-Test)
    @Test
    public void testNoStrategySet() {
        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            container.store(); // Versucht, ohne gesetzte Strategie zu speichern
        });
        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, exception.getExceptionTypeType());
    }

    // Test 2: Verwendung der PersistenceStrategyMongoDB, die noch nicht implementiert ist
    @Test
    public void testMongoDBStrategy() {
        // Setze die MongoDB-Strategie im Container
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());

        // Speicherung sollte eine UnsupportedOperationException auslösen
        assertThrows(UnsupportedOperationException.class, () -> {
            container.store();
        });

        // Laden sollte ebenfalls eine UnsupportedOperationException auslösen
        assertThrows(UnsupportedOperationException.class, () -> {
            container.load();
        });
    }

    @Test
    public void testInvalidFileLocation() {
        PersistenceStrategyStream<Member> streamStrategy = new PersistenceStrategyStream<>();
        streamStrategy.setLocation("C:\\Users\\schmi\\Documents\\test.txt\""); // Setzt den Speicherort auf ein directory
        container.setPersistenceStrategy(streamStrategy);

        assertThrows(PersistenceException.class, () -> {
            container.store(); // Versucht zu speichern, was fehlschlagen sollte
        });
    }

    @Test
    public void testRoundTrip() throws PersistenceException, ContainerException {
        //Objekt hinzufügen
        Member member = new ConcreteMember(1);
        container.addMember(member);
        assertEquals(1, container.size());

        //Liste persistent abspeichern
        PersistenceStrategyStream<Member> streamStrategy = new PersistenceStrategyStream<>();
        streamStrategy.setLocation("C:\\Users\\schmi\\Documents\\test.txt");
        container.setPersistenceStrategy(streamStrategy);
        container.store();

        //Objekt aus Container löschen
        container.deleteMember(1);
        assertEquals(0, container.size());

        //Liste wieder einladen
        container.load();
        List<Member> loadedList = container.getCurrentList();
        assertEquals(1, loadedList.size());
        assertEquals(member.getID(), loadedList.get(0).getID());
    }


}

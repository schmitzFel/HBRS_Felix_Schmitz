package org.hbrs.se1.ws24.exercises.uebung2.test;

import org.hbrs.se1.ws24.exercises.uebung2.container.Container;
import org.hbrs.se1.ws24.exercises.uebung2.container.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.member.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    private Container container;

    @BeforeEach
    public void setUp() {
        container = new Container();
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
        assertEquals(0, container.size(), "Nach dem Löschen eines Members sollte der Container leer sein.");
    }


    @Test
    public void testInitialContainerSize() {
        assertEquals(0, container.size(), "Nach der Initialisierung sollte der Container leer sein.");
    }


    @Test
    void testAddingNullMemberThrowsException() {
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

}

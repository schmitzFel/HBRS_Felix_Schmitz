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
    public void testAddDuplicateMember() {
        Member member1 = new ConcreteMember(1);

        assertDoesNotThrow(() -> container.addMember(member1));
        ContainerException exception = assertThrows(ContainerException.class, () -> {
            container.addMember(new ConcreteMember(1));
        });

        assertEquals("Das Member-Objekt mit der ID 1 ist bereits vorhanden!", exception.getMessage());
    }

    @Test
    public void testDeleteMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        container.addMember(member1);
        String result = container.deleteMember(1);

        assertEquals("Member mit ID 1 wurde gelöscht.", result);
        assertEquals(0, container.size());
    }

    @Test
    public void testDeleteNonExistingMember() {
        String result = container.deleteMember(999);
        assertEquals("Fehler: Kein Member mit ID 999 gefunden.", result);
    }

    @Test
    public void testDump() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);

        container.addMember(member1);
        container.addMember(member2);

        container.dump(); // Erwartete Ausgabe: Member (ID = 1), Member (ID = 2)
    }

    @Test
    public void testSize() throws ContainerException {
        assertEquals(0, container.size());
        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());
    }
}

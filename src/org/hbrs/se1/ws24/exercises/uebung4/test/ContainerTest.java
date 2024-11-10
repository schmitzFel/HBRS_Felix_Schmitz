package org.hbrs.se1.ws24.exercises.uebung4.test;

import org.hbrs.se1.ws24.exercises.uebung4.controller.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {
    private Container container;
    private PersistenceStrategyStream<UserStory> persistenceStrategyStream;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        container.reset();  // Resetten, um die Tests unabhängig zu machen
        container.setPersistenceStrategy(persistenceStrategyStream);
    }

    @Test
    void testAddUserStory_Success() {
        UserStory story = new UserStory(1, "Epic1", "Description", "Acceptance Criteria", 5, 3, 1, 2);
        container.addUserStory(story);
        assertEquals(1, container.size());
        assertEquals(story, container.getCurrentList().get(0));
    }

    @Test
    void testAddUserStory_DuplicateID_ShouldThrowException() {
        UserStory story1 = new UserStory(1, "Epic1", "Description", "Acceptance Criteria", 5, 3, 1, 2);
        UserStory story2 = new UserStory(1, "Epic2", "Different Description", "Different Criteria", 3, 4, 1, 1);
        container.addUserStory(story1);
        assertThrows(IllegalArgumentException.class, () -> container.addUserStory(story2));
    }

    @Test
    void testAddUserStory_NullStory_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> container.addUserStory(null));
    }

    @Test
    void testDeleteUser_ExistingID_ShouldDeleteUser() {
        UserStory story = new UserStory(1, "Epic1", "Description", "Acceptance Criteria", 5, 3, 1, 2);
        container.addUserStory(story);
        String result = container.deleteUser(1);
        assertEquals("User mit ID 1 wurde gelöscht.", result);
        assertEquals(0, container.size());
    }

    @Test
    void testDeleteUser_NonExistingID_ShouldReturnErrorMessage() {
        String result = container.deleteUser(99);
        assertEquals("Fehler: Kein User mit ID 99 gefunden.", result);
    }

    @Test
    void testStore_WithoutStrategy_ShouldThrowException() {
        container.setPersistenceStrategy(null);
        assertThrows(PersistenceException.class, container::store);
    }

    @Test
    void testLoad_WithoutStrategy_ShouldThrowException() {
        container.setPersistenceStrategy(null);
        assertThrows(PersistenceException.class, container::load);
    }
}

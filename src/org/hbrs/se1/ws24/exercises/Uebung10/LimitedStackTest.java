import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LimitedStackTest {

    @Test
    public void testStackStates() {
        // n = 4
        LimitedStack<Integer> stack = new LimitedStack<>(4);

        // Initial state: empty
        assertTrue(stack.isEmpty());
        assertFalse(stack.isFull());
        assertEquals(0, stack.size());

        // State: filled
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertFalse(stack.isFull());
        assertEquals(1, stack.size());

        stack.push(2);
        stack.push(3);
        assertFalse(stack.isEmpty());
        assertFalse(stack.isFull());
        assertEquals(3, stack.size());

        // State: full
        stack.push(4);
        assertFalse(stack.isEmpty());
        assertTrue(stack.isFull());
        assertEquals(4, stack.size());

        // Attempt to push when full (should throw exception)
        assertThrows(IndexOutOfBoundsException.class, () -> stack.push(5));

        // Pop to transition back to "filled"
        int lastElement = stack.pop();
        assertEquals(4, lastElement); // Last pushed element is popped
        assertFalse(stack.isEmpty());
        assertFalse(stack.isFull());
        assertEquals(3, stack.size());

        // Pop until empty
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
        assertFalse(stack.isFull());
        assertEquals(0, stack.size());

        // Attempt to pop when empty (should throw exception)
        assertThrows(java.util.EmptyStackException.class, stack::pop);
    }
}

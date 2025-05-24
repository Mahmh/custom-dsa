package mahmh.customdsa.arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mahmh.customdsa.utils.Data;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testPushPopSingleItem() {
        Data data = new Data("Maximus");
        stack.push(data);

        assertEquals(1, stack.size());

        Data popped = stack.pop();
        assertEquals("Maximus", popped.get("name"));

        assertEquals(0, stack.size());
    }

    @Test
    public void testPushPopMultipleItems_LIFO() {
        stack.push(new Data("A"));
        stack.push(new Data("B"));
        stack.push(new Data("C"));

        assertEquals(3, stack.size());

        assertEquals("C", stack.pop().get("name"));
        assertEquals("B", stack.pop().get("name"));
        assertEquals("A", stack.pop().get("name"));

        assertEquals(0, stack.size());
    }

    @Test
    public void testPopOnEmptyStackReturnsNull() {
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    public void testPushNullData() {
        stack.push(null);
        assertEquals(1, stack.size());
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    public void testSizeGrowsAndShrinksCorrectly() {
        assertEquals(0, stack.size());
        stack.push(new Data("One"));
        assertEquals(1, stack.size());
        stack.push(new Data("Two"));
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }
}
package mahmh.customdsa.arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testPushPopSingleItem() {
        Stack.Data data = new Stack.Data("Maximus");
        stack.push(data);

        assertEquals(1, stack.size());

        Stack.Data popped = stack.pop();
        assertEquals("Maximus", popped.name());

        assertEquals(0, stack.size());
    }

    @Test
    public void testPushPopMultipleItems_LIFO() {
        stack.push(new Stack.Data("A"));
        stack.push(new Stack.Data("B"));
        stack.push(new Stack.Data("C"));

        assertEquals(3, stack.size());

        assertEquals("C", stack.pop().name());
        assertEquals("B", stack.pop().name());
        assertEquals("A", stack.pop().name());

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
        stack.push(new Stack.Data("One"));
        assertEquals(1, stack.size());
        stack.push(new Stack.Data("Two"));
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }
}
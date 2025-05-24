package mahmh.customdsa.arrays;
import mahmh.customdsa.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
    }

    @Test
    public void testPushPopSingleItem() {
        Data item = new Data("Alpha");
        queue.push(item);

        assertEquals(1, queue.size());
        assertEquals("Alpha", queue.pop().get("name"));
        assertEquals(0, queue.size());
    }

    @Test
    public void testPushPopMultipleItems_FIFO() {
        queue.push(new Data("First"));
        queue.push(new Data("Second"));
        queue.push(new Data("Third"));

        assertEquals(3, queue.size());
        assertEquals("First", queue.pop().get("name"));
        assertEquals("Second", queue.pop().get("name"));
        assertEquals("Third", queue.pop().get("name"));
        assertEquals(0, queue.size());
    }

    @Test
    public void testPopEmptyQueueReturnsNull() {
        assertNull(queue.pop());
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeAfterPushAndPop() {
        queue.push(new Data("X"));
        queue.push(new Data("Y"));
        assertEquals(2, queue.size());

        queue.pop();
        assertEquals(1, queue.size());

        queue.pop();
        assertEquals(0, queue.size());
    }

    @Test
    public void testPushNull() {
        queue.push(null);
        assertEquals(1, queue.size());
        assertNull(queue.pop());
        assertEquals(0, queue.size());
    }
}
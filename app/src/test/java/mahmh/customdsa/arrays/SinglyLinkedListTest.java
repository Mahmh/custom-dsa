package mahmh.customdsa.arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mahmh.customdsa.utils.Data;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {
    private SinglyLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList();
    }

    @Test
    public void testAppendAndGet() {
        list.append(new Data("A"));
        list.append(new Data("B"));
        list.append(new Data("C"));

        assertEquals("A", list.get(0).name());
        assertEquals("B", list.get(1).name());
        assertEquals("C", list.get(2).name());
    }

    @Test
    public void testInsertAtBeginning() {
        list.append(new Data("B"));
        list.insert(new Data("A"), 0);

        assertEquals("A", list.get(0).name());
        assertEquals("B", list.get(1).name());
    }

    @Test
    public void testInsertInMiddle() {
        list.append(new Data("A"));
        list.append(new Data("C"));
        list.insert(new Data("B"), 1);

        assertEquals("A", list.get(0).name());
        assertEquals("B", list.get(1).name());
        assertEquals("C", list.get(2).name());
    }

    @Test
    public void testDeleteAtBeginning() {
        list.append(new Data("A"));
        list.append(new Data("B"));
        list.delete(0);

        assertEquals("B", list.get(0).name());
    }

    @Test
    public void testDeleteInMiddle() {
        list.append(new Data("A"));
        list.append(new Data("B"));
        list.append(new Data("C"));
        list.delete(1);

        assertEquals("A", list.get(0).name());
        assertEquals("C", list.get(1).name());
    }

    @Test
    public void testDeleteLastRemaining() {
        list.append(new Data("Only"));
        list.delete(0);
        assertThrows(Error.class, () -> list.get(0));
    }

    @Test
    public void testGetOutOfBounds() {
        list.append(new Data("A"));
        assertThrows(Error.class, () -> list.get(5));
    }

    @Test
    public void testInsertInvalidIndex() {
        assertThrows(NullPointerException.class, () -> list.insert(new Data("X"), 5));
    }

    @Test
    public void testDeleteInvalidIndex() {
        assertThrows(NullPointerException.class, () -> list.delete(5));
    }

    @Test
    public void testSizeGrowsAndShrinksCorrectly() {
        assertEquals(0, list.size());

        list.append(new Data("A"));
        assertEquals(1, list.size());

        list.append(new Data("B"));
        list.append(new Data("C"));
        assertEquals(3, list.size());

        list.delete(1); // remove "B"
        assertEquals(2, list.size());

        list.delete(0); // remove "A"
        list.delete(0); // remove "C"
        assertEquals(0, list.size());
    }
}
package mahmh.customdsa.arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {

    private SinglyLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList();
    }

    @Test
    public void testAppendAndGet() {
        list.append(new SinglyLinkedList.Data("A"));
        list.append(new SinglyLinkedList.Data("B"));
        list.append(new SinglyLinkedList.Data("C"));

        assertEquals("A", list.get(0).name());
        assertEquals("B", list.get(1).name());
        assertEquals("C", list.get(2).name());
    }

    @Test
    public void testInsertAtBeginning() {
        list.append(new SinglyLinkedList.Data("B"));
        list.insert(new SinglyLinkedList.Data("A"), 0);

        assertEquals("A", list.get(0).name());
        assertEquals("B", list.get(1).name());
    }

    @Test
    public void testInsertInMiddle() {
        list.append(new SinglyLinkedList.Data("A"));
        list.append(new SinglyLinkedList.Data("C"));
        list.insert(new SinglyLinkedList.Data("B"), 1);

        assertEquals("A", list.get(0).name());
        assertEquals("B", list.get(1).name());
        assertEquals("C", list.get(2).name());
    }

    @Test
    public void testDeleteAtBeginning() {
        list.append(new SinglyLinkedList.Data("A"));
        list.append(new SinglyLinkedList.Data("B"));
        list.delete(0);

        assertEquals("B", list.get(0).name());
    }

    @Test
    public void testDeleteInMiddle() {
        list.append(new SinglyLinkedList.Data("A"));
        list.append(new SinglyLinkedList.Data("B"));
        list.append(new SinglyLinkedList.Data("C"));
        list.delete(1);

        assertEquals("A", list.get(0).name());
        assertEquals("C", list.get(1).name());
    }

    @Test
    public void testDeleteLastRemaining() {
        list.append(new SinglyLinkedList.Data("Only"));
        list.delete(0);
        assertThrows(Error.class, () -> list.get(0));
    }

    @Test
    public void testGetOutOfBounds() {
        list.append(new SinglyLinkedList.Data("A"));
        assertThrows(Error.class, () -> list.get(5));
    }

    @Test
    public void testInsertInvalidIndex() {
        assertThrows(NullPointerException.class, () -> list.insert(new SinglyLinkedList.Data("X"), 5));
    }

    @Test
    public void testDeleteInvalidIndex() {
        assertThrows(NullPointerException.class, () -> list.delete(5));
    }
}
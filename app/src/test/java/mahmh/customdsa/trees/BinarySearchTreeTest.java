package mahmh.customdsa.trees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        BinarySearchTree.Node root = new BinarySearchTree.Node(10, new BinarySearchTree.Data("root"));
        bst = new BinarySearchTree(root);
    }

    @Test
    public void testAddAndRetrieveNodes() {
        bst.add(new BinarySearchTree.Node(5, new BinarySearchTree.Data("five")));
        bst.add(new BinarySearchTree.Node(15, new BinarySearchTree.Data("fifteen")));
        bst.add(new BinarySearchTree.Node(3, new BinarySearchTree.Data("three")));
        bst.add(new BinarySearchTree.Node(7, new BinarySearchTree.Data("seven")));

        assertEquals("five", bst.get(5).name());
        assertEquals("fifteen", bst.get(15).name());
        assertEquals("three", bst.get(3).name());
        assertEquals("seven", bst.get(7).name());
    }

    @Test
    public void testRetrieveRootNode() {
        BinarySearchTree.Data result = bst.get(10);
        assertNotNull(result);
        assertEquals("root", result.name());
    }

    @Test
    public void testRetrieveNonExistentNodeReturnsNull() {
        assertNull(bst.get(999));
        assertNull(bst.get(-1));
    }

    @Test
    public void testDuplicateNodeInsertionThrowsError() {
        BinarySearchTree.Node duplicate = new BinarySearchTree.Node(10, new BinarySearchTree.Data("duplicate"));
        assertThrows(Error.class, () -> bst.add(duplicate));
    }

    @Test
    public void testGetDataWithMinId() {
        bst.add(new BinarySearchTree.Node(1, new BinarySearchTree.Data("min")));
        bst.add(new BinarySearchTree.Node(20, new BinarySearchTree.Data("max")));
        assertEquals("min", bst.getDataWithMinId().name());
    }

    @Test
    public void testGetDataWithMaxId() {
        bst.add(new BinarySearchTree.Node(1, new BinarySearchTree.Data("min")));
        bst.add(new BinarySearchTree.Node(20, new BinarySearchTree.Data("max")));
        assertEquals("max", bst.getDataWithMaxId().name());
    }

    @Test
    public void testPrintTreeStructure() {
        bst.add(new BinarySearchTree.Node(5, new BinarySearchTree.Data("five")));
        bst.add(new BinarySearchTree.Node(15, new BinarySearchTree.Data("fifteen")));
        bst.add(new BinarySearchTree.Node(2, new BinarySearchTree.Data("two")));
        bst.add(new BinarySearchTree.Node(7, new BinarySearchTree.Data("seven")));

        // Redirect stdout and capture output
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        bst.print();
        String output = out.toString();

        assertTrue(output.contains("10"));
        assertTrue(output.contains("5"));
        assertTrue(output.contains("15"));
        assertTrue(output.contains("2"));
        assertTrue(output.contains("7"));
    }
}
package mahmh.customdsa.trees;
import mahmh.customdsa.utils.Data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {
    @Test
    void testInsertAndSearch() {
        AVLTree.Node root = new AVLTree.Node(10, new Data("10"));
        AVLTree tree = new AVLTree(root);
        tree.insert(5, new Data("5"));
        tree.insert(15, new Data("15"));

        assertEquals("5", tree.search(5).get("name"));
        assertEquals("15", tree.search(15).get("name"));
        assertEquals("10", tree.search(10).get("name"));
    }

    @Test
    void testDeleteLeafNode() {
        AVLTree.Node root = new AVLTree.Node(20, new Data("20"));
        AVLTree tree = new AVLTree(root);
        tree.insert(10, new Data("10"));
        tree.insert(30, new Data("30"));
        tree.delete(10);

        assertThrows(Error.class, () -> tree.search(10));
    }

    @Test
    void testDeleteNodeWithOneChild() {
        AVLTree.Node root = new AVLTree.Node(20, new Data("20"));
        AVLTree tree = new AVLTree(root);
        tree.insert(10, new Data("10"));
        tree.insert(5, new Data("5"));
        tree.delete(10);

        assertEquals("5", tree.search(5).get("name"));
        assertThrows(Error.class, () -> tree.search(10));
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        AVLTree.Node root = new AVLTree.Node(20, new Data("20"));
        AVLTree tree = new AVLTree(root);
        tree.insert(10, new Data("10"));
        tree.insert(30, new Data("30"));
        tree.insert(25, new Data("25"));
        tree.insert(35, new Data("35"));
        tree.delete(30);

        assertEquals("25", tree.search(25).get("name"));
        assertEquals("35", tree.search(35).get("name"));
        assertThrows(Error.class, () -> tree.search(30));
    }

    @Test
    void testSearchNonExistent() {
        AVLTree.Node root = new AVLTree.Node(50, new Data("50"));
        AVLTree tree = new AVLTree(root);
        assertThrows(Error.class, () -> tree.search(99));
    }

    @Test
    void testInsertDuplicateThrows() {
        AVLTree.Node root = new AVLTree.Node(1, new Data("1"));
        AVLTree tree = new AVLTree(root);
        assertThrows(Error.class, () -> tree.insert(1, new Data("Duplicate")));
    }
}
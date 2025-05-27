package mahmh.customdsa.trees;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LowestCommonAncestorTest {
    // Uses this binary tree:
    //      A
    //     / \
    //    B   C
    //   / \   \
    //  D   E   F
    private final LowestCommonAncestor.Node D = new LowestCommonAncestor.Node("D", null, null);
    private final LowestCommonAncestor.Node E = new LowestCommonAncestor.Node("E", null, null);
    private final LowestCommonAncestor.Node F = new LowestCommonAncestor.Node("F", null, null);
    private final LowestCommonAncestor.Node B = new LowestCommonAncestor.Node("B", D, E);
    private final LowestCommonAncestor.Node C = new LowestCommonAncestor.Node("C", null, F);
    private final LowestCommonAncestor.Node A = new LowestCommonAncestor.Node("A", B, C);

    @Test
    void testLCA_D_E_is_B() {
        assertEquals(B, LowestCommonAncestor.find(A, D, E));
    }

    @Test
    void testLCA_D_F_is_A() {
        assertEquals(A, LowestCommonAncestor.find(A, D, F));
    }

    @Test
    void testLCA_B_F_is_A() {
        assertEquals(A, LowestCommonAncestor.find(A, B, F));
    }

    @Test
    void testLCA_E_E_is_E() {
        assertEquals(E, LowestCommonAncestor.find(A, E, E));
    }

    @Test
    void testLCA_C_F_is_C() {
        assertEquals(C, LowestCommonAncestor.find(A, C, F));
    }

    @Test
    void testLCA_A_F_is_A() {
        assertEquals(A, LowestCommonAncestor.find(A, A, F));
    }

    @Test
    void testThrowsIfQIsNotInTree() {
        LowestCommonAncestor.Node X = new LowestCommonAncestor.Node("X", null, null);
        assertThrows(IllegalArgumentException.class, () -> LowestCommonAncestor.find(A, D, X));
    }

    @Test
    void testThrowsIfBothNotInTree() {
        LowestCommonAncestor.Node X = new LowestCommonAncestor.Node("X", null, null);
        LowestCommonAncestor.Node Y = new LowestCommonAncestor.Node("Y", null, null);
        assertThrows(IllegalArgumentException.class, () -> LowestCommonAncestor.find(A, X, Y));
    }
}
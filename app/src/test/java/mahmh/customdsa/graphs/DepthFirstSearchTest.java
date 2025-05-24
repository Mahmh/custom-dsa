package mahmh.customdsa.graphs;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepthFirstSearchTest {
    private DepthFirstSearch.Node A, B, C, D, E, F, G, H, I, X;
    private DepthFirstSearch graph;

    @BeforeEach
    public void setUp() {
        A = new DepthFirstSearch.Node("A");
        B = new DepthFirstSearch.Node("B");
        C = new DepthFirstSearch.Node("C");
        D = new DepthFirstSearch.Node("D");
        E = new DepthFirstSearch.Node("E");
        F = new DepthFirstSearch.Node("F");
        G = new DepthFirstSearch.Node("G");
        H = new DepthFirstSearch.Node("H");
        I = new DepthFirstSearch.Node("I");
        X = new DepthFirstSearch.Node("X"); // Disconnected node

        graph = new DepthFirstSearch();

        graph.connect(A, B);
        graph.connect(A, C);
        graph.connect(B, D);
        graph.connect(B, E);
        graph.connect(B, F);
        graph.connect(F, H);
        graph.connect(C, G);
        graph.connect(G, I);
    }

    @Test
    public void testPath_AtoI() {
        List<DepthFirstSearch.Node> path = graph.getPath(A, I);
        assertTrue(path.containsAll(Arrays.asList(A, C, G, I)));
        assertEquals(A, path.get(0));
        assertEquals(I, path.get(path.size() - 1));
    }

    @Test
    public void testPath_AtoH() {
        List<DepthFirstSearch.Node> path = graph.getPath(A, H);
        assertTrue(path.containsAll(Arrays.asList(A, B, F, H)));
        assertEquals(A, path.get(0));
        assertEquals(H, path.get(path.size() - 1));
    }

    @Test
    public void testPath_BtoE() {
        List<DepthFirstSearch.Node> path = graph.getPath(B, E);
        assertEquals(List.of(B, E), path);
    }

    @Test
    public void testPathToSelf() {
        List<DepthFirstSearch.Node> path = graph.getPath(D, D);
        assertEquals(List.of(D), path);
    }

    @Test
    public void testUnreachableNode() {
        List<DepthFirstSearch.Node> path = graph.getPath(A, X);
        assertTrue(path.isEmpty());
    }
}
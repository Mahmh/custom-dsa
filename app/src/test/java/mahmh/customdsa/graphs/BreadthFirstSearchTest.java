package mahmh.customdsa.graphs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BreadthFirstSearchTest {
    private BreadthFirstSearch.Node A, B, C, D, E, F, G, H, I, X;
    private BreadthFirstSearch graph;

    @BeforeEach
    public void setUp() {
        A = new BreadthFirstSearch.Node("A");
        B = new BreadthFirstSearch.Node("B");
        C = new BreadthFirstSearch.Node("C");
        D = new BreadthFirstSearch.Node("D");
        E = new BreadthFirstSearch.Node("E");
        F = new BreadthFirstSearch.Node("F");
        G = new BreadthFirstSearch.Node("G");
        H = new BreadthFirstSearch.Node("H");
        I = new BreadthFirstSearch.Node("I");
        X = new BreadthFirstSearch.Node("X"); // Disconnected

        graph = new BreadthFirstSearch();

        graph.connect(A, B);
        graph.connect(A, G);
        graph.connect(B, C);
        graph.connect(B, D);
        graph.connect(B, E);
        graph.connect(E, F);
        graph.connect(G, H);
        graph.connect(H, I);
    }

    @Test
    public void testShortestPath_AtoI() {
        List<BreadthFirstSearch.Node> path = graph.getPath(A, I);
        List<BreadthFirstSearch.Node> expected = List.of(A, G, H, I);
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_AtoF() {
        List<BreadthFirstSearch.Node> path = graph.getPath(A, F);
        List<BreadthFirstSearch.Node> expected = List.of(A, B, E, F);
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_AtoC() {
        List<BreadthFirstSearch.Node> path = graph.getPath(A, C);
        List<BreadthFirstSearch.Node> expected = List.of(A, B, C);
        assertEquals(expected, path);
    }

    @Test
    public void testPathToSelf() {
        List<BreadthFirstSearch.Node> path = graph.getPath(D, D);
        assertEquals(List.of(D), path);
    }

    @Test
    public void testUnreachableNode() {
        List<BreadthFirstSearch.Node> path = graph.getPath(A, X);
        assertTrue(path.isEmpty());
    }
}
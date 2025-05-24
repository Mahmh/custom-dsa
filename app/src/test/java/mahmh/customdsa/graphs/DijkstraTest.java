package mahmh.customdsa.graphs;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {
    private Dijkstra.Node A, B, C, D, E, F, G;
    private Dijkstra graph;

    @BeforeEach
    public void setUp() {
        A = new Dijkstra.Node("A");
        B = new Dijkstra.Node("B");
        C = new Dijkstra.Node("C");
        D = new Dijkstra.Node("D");
        E = new Dijkstra.Node("E");
        F = new Dijkstra.Node("F");
        G = new Dijkstra.Node("G"); // Unconnected

        graph = new Dijkstra(Arrays.asList(A, B, C, D, E, F, G));

        graph.connect(A, B, 2);
        graph.connect(A, D, 8);
        graph.connect(B, D, 5);
        graph.connect(B, E, 6);
        graph.connect(D, E, 3);
        graph.connect(D, F, 2);
        graph.connect(F, E, 1);
        graph.connect(F, C, 3);
        graph.connect(E, C, 9);

        System.out.println(graph.getShortestDistance(A, E));
    }

    @Test
    public void testShortestDistance_AC() {
        double result = graph.getShortestDistance(A, C);
        assertEquals(12.0, result);
    }

    @Test
    public void testShortestDistance_AE() {
        double result = graph.getShortestDistance(A, E);
        assertEquals(8.0, result);
    }

    @Test
    public void testShortestDistance_AF() {
        double result = graph.getShortestDistance(A, F);
        assertEquals(9.0, result);
    }

    @Test
    public void testShortestDistance_AD() {
        double result = graph.getShortestDistance(A, D);
        assertEquals(7.0, result);
    }

    @Test
    public void testShortestDistance_UnreachableNode() {
        double result = graph.getShortestDistance(A, G);
        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    public void testShortestDistance_SameNode() {
        double result = graph.getShortestDistance(A, A);
        assertEquals(0.0, result);
    }

    @Test
    public void testShortestPath_AC() {
        var path = graph.getShortestPath(A, C);
        var expected = Arrays.asList(A, B, D, F, C);
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_AE() {
        var path = graph.getShortestPath(A, E);
        var expected = Arrays.asList(A, B, E); // shortest path = 2 + 6 = 8
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_AF() {
        var path = graph.getShortestPath(A, F);
        var expected = Arrays.asList(A, B, D, F);
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_AD() {
        var path = graph.getShortestPath(A, D);
        var expected = Arrays.asList(A, B, D);
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_Unreachable() {
        var path = graph.getShortestPath(A, G);
        var expected = Arrays.asList(); // G is disconnected
        assertEquals(expected, path);
    }

    @Test
    public void testShortestPath_SameNode() {
        var path = graph.getShortestPath(A, A);
        var expected = Arrays.asList(A);
        assertEquals(expected, path);
    }
}
package mahmh.customdsa.graphs;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {
    private final TopologicalSort.Node A = new TopologicalSort.Node("A");
    private final TopologicalSort.Node B = new TopologicalSort.Node("B");
    private final TopologicalSort.Node C = new TopologicalSort.Node("C");
    private final TopologicalSort.Node D = new TopologicalSort.Node("D");
    private final TopologicalSort.Node E = new TopologicalSort.Node("E");
    private final TopologicalSort.Node F = new TopologicalSort.Node("F");

    @Test
    void testBasicValidSort() {
        List<TopologicalSort.Node> nodes = List.of(A, B, C, D, E, F);
        List<TopologicalSort.Connection> edges = List.of(
            new TopologicalSort.Connection(A, C),
            new TopologicalSort.Connection(B, C),
            new TopologicalSort.Connection(C, D),
            new TopologicalSort.Connection(D, E),
            new TopologicalSort.Connection(E, F)
        );

        List<TopologicalSort.Node> result = TopologicalSort.sort(nodes, edges);
        assertTrue(isTopologicallySorted(result, edges));
    }

    @Test
    void testDisconnectedGraph() {
        List<TopologicalSort.Node> nodes = List.of(A, B, C); // C is disconnected
        List<TopologicalSort.Connection> edges = List.of(
            new TopologicalSort.Connection(A, B)
        );

        List<TopologicalSort.Node> result = TopologicalSort.sort(nodes, edges);
        assertTrue(isTopologicallySorted(result, edges));
        assertTrue(result.contains(C));
    }

    @Test
    void testCycleDetection() {
        List<TopologicalSort.Node> nodes = List.of(A, B);
        List<TopologicalSort.Connection> edges = List.of(
            new TopologicalSort.Connection(A, B),
            new TopologicalSort.Connection(B, A)
        );

        assertThrows(Error.class, () -> TopologicalSort.sort(nodes, edges));
    }

    @Test
    void testNoDependencies() {
        List<TopologicalSort.Node> nodes = List.of(A, B, C);
        List<TopologicalSort.Connection> edges = List.of(); // No edges

        List<TopologicalSort.Node> result = TopologicalSort.sort(nodes, edges);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(nodes));
    }

    // Helper to verify valid topological order
    private boolean isTopologicallySorted(List<TopologicalSort.Node> sorted, List<TopologicalSort.Connection> edges) {
        Map<TopologicalSort.Node, Integer> position = new HashMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            position.put(sorted.get(i), i);
        }
        for (TopologicalSort.Connection edge : edges) {
            if (position.get(edge.from()) >= position.get(edge.to())) {
                return false;
            }
        }
        return true;
    }
}
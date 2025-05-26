package mahmh.customdsa.graphs;
import java.util.*;

public class TopologicalSort {
    public record Node(String id) {}
    public record Connection(Node from, Node to) {}

    /** Returns the topologically-sorted nodes only if the given graph is a directed acyclic graph (DAG). */
    public static List<Node> sort(List<Node> nodes, List<Connection> connections) throws Error {
        List<Node> result = new ArrayList<>();
        Map<Node, Integer> inDegree = new HashMap<>(); // node -> how many nodes point into it
        Map<Node, List<Node>> neighbors = new HashMap<>();
        Queue<Node> nodesWithoutDeps = new LinkedList<>();

        // Initialize in-degree and neighbor lists
        for (Node node : nodes) {
            inDegree.put(node, 0);
            neighbors.put(node, new ArrayList<>());
        }

        // Populate in-degree and neighbor map
        for (Connection conn : connections) {
            neighbors.get(conn.from).add(conn.to);
            inDegree.put(conn.to, inDegree.get(conn.to) + 1);
        }

        // Collect nodes with 0 in-degree
        for (Node node : nodes) {
            if (inDegree.get(node) == 0) nodesWithoutDeps.add(node);
        }

        // Kahn's algorithm main loop
        while (!nodesWithoutDeps.isEmpty()) {
            Node node = nodesWithoutDeps.poll();
            result.add(node);

            // Cut `node` from its neighbor and decrease the neighbor's in-degree by 1
            for (Node neighbor : neighbors.get(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) nodesWithoutDeps.add(neighbor);
            }
        }

        if (result.size() < nodes.size()) {
            throw new Error("Cannot topologically sort this graph because it is cyclic.");
        }

        return result;
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        List<Node> nodes = Arrays.asList(A, B, C, D, E, F);
        List<Connection> connections = Arrays.asList(
            new Connection(A, C),
            new Connection(B, C),
            new Connection(C, D),
            new Connection(D, E),
            new Connection(E, F)
        );

        System.out.println(TopologicalSort.sort(nodes, connections));
    }
}
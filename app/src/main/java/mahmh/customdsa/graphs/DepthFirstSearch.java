package mahmh.customdsa.graphs;
import java.util.*;

public class DepthFirstSearch {
    public static record Node(String id) {}
    public static record Connection(Node a, Node b) {}
    private final List<Connection> connections = new ArrayList<>();

    public DepthFirstSearch() {}

    /** Adds a connection edge between two nodes. */
    public void connect(Node a, Node b) {
        this.connections.add(new Connection(a, b));
    }

    /** Returns a possible path from `from` to `to`. */
    public List<Node> getPath(Node from, Node to) {
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        Stack<Node> stack = new Stack<>();

        stack.push(from);
        visited.put(from, true);
        previous.put(from, null);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (current.equals(to)) break; // found destination

            for (Node neighbor : getUnvisitedNeighbors(current, visited)) {
                if (!Boolean.TRUE.equals(visited.get(neighbor))) {
                    visited.put(neighbor, true);
                    previous.put(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }

        // Backtrack path from `to` to `from`
        List<Node> path = new ArrayList<>();
        Node next = to;

        while (next != null) {
            path.add(next);
            next = previous.get(next);
        }

        Collections.reverse(path);

        // If we couldn't reach `to`, path will not start with `from`
        if (!path.get(0).equals(from)) return List.of();

        return path;
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node G = new Node("G");
        Node H = new Node("H");
        Node I = new Node("I");
        DepthFirstSearch graph = new DepthFirstSearch();

        graph.connect(A, B);
        graph.connect(A, G);
        graph.connect(B, C);
        graph.connect(B, D);
        graph.connect(B, E);
        graph.connect(E, F);
        graph.connect(G, H);
        graph.connect(H, I);

        System.out.println(graph.getPath(A, I));
    }

    private List<Node> getUnvisitedNeighbors(Node reference, Map<Node, Boolean> visited) {
        List<Node> unvisitedNeighbors = new ArrayList<>();
        for (Connection conn : connections) {
            if (conn.a().equals(reference) && !Boolean.TRUE.equals(visited.get(conn.b()))) {
                unvisitedNeighbors.add(conn.b());
            } else if (conn.b().equals(reference) && !Boolean.TRUE.equals(visited.get(conn.a()))) {
                unvisitedNeighbors.add(conn.a());
            }
        }
        return unvisitedNeighbors;
    }
}
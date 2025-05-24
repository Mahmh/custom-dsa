package mahmh.customdsa.graphs;
import java.util.*;

public class Dijkstra {
    public record Node(String id) {}
    public record Connection(Node a, Node b, double distance) {}
    private final List<Connection> connections = new ArrayList<>();
    private final Map<Node, Double> shortestDistances = new HashMap<>();
    private final Map<Node, Node> previousNodes = new HashMap<>();
    private final Map<Node, Boolean> visited = new HashMap<>();

    public Dijkstra(List<Node> nodes) {
        for (Node node : nodes) {
            shortestDistances.put(node, Double.POSITIVE_INFINITY);
            previousNodes.put(node, null);
            visited.put(node, false);
        }
    }

    /** Adds a connection edge between two nodes. */
    public void connect(Node a, Node b, double distance) {
        this.connections.add(new Connection(a, b, distance));
    }

    /** Returns the shortest distance to reach `to` from `from`. */
    public double getShortestDistance(Node from, Node to) {
        resetInternalState();
        shortestDistances.put(from, 0.0);
        previousNodes.put(from, null);

        while (visited.values().contains(false)) {
            // Select the unvisited node with the smallest known distance
            Node minNode = null;
            double minDistance = Double.POSITIVE_INFINITY;

            for (Node node : getUnvisitedNodes()) {
                double distance = shortestDistances.get(node);
                if (distance < minDistance) {
                    minDistance = distance;
                    minNode = node;
                }
            }

            if (minNode == null) break; // all remaining nodes are unreachable

            for (Node neighbor : getAdjacentUnvisitedNodes(minNode)) {
                double distance = getDistanceBetweenAdjacentNodes(minNode, neighbor);
                double newDistance = shortestDistances.get(minNode) + distance;

                if (newDistance < shortestDistances.get(neighbor)) {
                    shortestDistances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, minNode);
                }
            }

            visited.put(minNode, true);
        }

        return shortestDistances.get(to);
    }

    /** Returns the shortest path from `from` to `to`, as a list. */
    public List<Node> getShortestPath(Node from, Node to) {
        resetInternalState();
        shortestDistances.put(from, 0.0);
        previousNodes.put(from, null);

        while (visited.values().contains(false)) {
            // Select the unvisited node with the smallest known distance
            Node minNode = null;
            double minDistance = Double.POSITIVE_INFINITY;

            for (Node node : getUnvisitedNodes()) {
                double distance = shortestDistances.get(node);
                if (distance < minDistance) {
                    minDistance = distance;
                    minNode = node;
                }
            }

            if (minNode == null) break; // all remaining nodes are unreachable

            for (Node neighbor : getAdjacentUnvisitedNodes(minNode)) {
                double distance = getDistanceBetweenAdjacentNodes(minNode, neighbor);
                double newDistance = shortestDistances.get(minNode) + distance;

                if (newDistance < shortestDistances.get(neighbor)) {
                    shortestDistances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, minNode);
                }
            }

            visited.put(minNode, true);
        }

        // Check if the `to` is reachable
        if (shortestDistances.get(to) == Double.POSITIVE_INFINITY) {
            return new ArrayList<>();
        }

        // Construct the shortest path from `to` to `from`, then reverse and return it
        List<Node> shortestPath = new ArrayList<>();
        Node next = to;
        while (next != null) {
            shortestPath.add(next);
            next = previousNodes.get(next);
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Dijkstra graph = new Dijkstra(Arrays.asList(A, B, C, D, E, F));

        graph.connect(A, B, 2);
        graph.connect(A, D, 8);
        graph.connect(B, D, 5);
        graph.connect(B, E, 6);
        graph.connect(D, E, 3);
        graph.connect(D, F, 2);
        graph.connect(F, E, 1);
        graph.connect(F, C, 3);
        graph.connect(E, C, 9);

        System.out.println(graph.getShortestDistance(A, C));
        System.out.println(graph.getShortestPath(A, C));
    }

    /** Returns all unvisited nodes in the whole graph. */
    private List<Node> getUnvisitedNodes() {
        List<Node> unvisitedNodes = new ArrayList<>();
        for (Map.Entry<Node, Boolean> entry : visited.entrySet()) {
            if (!entry.getValue()) unvisitedNodes.add(entry.getKey());
        }
        return unvisitedNodes;
    }

    /** Returns all unvisited nodes connected to the given reference node. */
    private List<Node> getAdjacentUnvisitedNodes(Node reference) {
        List<Node> adjacentUnvisited = new ArrayList<>();
        for (Connection conn : connections) {
            if (conn.a().equals(reference) && !visited.get(conn.b())) {
                adjacentUnvisited.add(conn.b());
            } else if (conn.b().equals(reference) && !visited.get(conn.a())) {
                adjacentUnvisited.add(conn.a());
            }
        }
        return adjacentUnvisited;
    }

    /** Returns the distance between two directly connected nodes. */
    private double getDistanceBetweenAdjacentNodes(Node a, Node b) {
        for (Connection conn : connections) {
            if ((conn.a().equals(a) && conn.b().equals(b)) ||
                (conn.a().equals(b) && conn.b().equals(a))) {
                return conn.distance();
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    /** Sets all internal variables their default values. */
    private void resetInternalState() {
        for (Node node : shortestDistances.keySet()) {
            shortestDistances.put(node, Double.POSITIVE_INFINITY);
            previousNodes.put(node, null);
            visited.put(node, false);
        }
    }
}
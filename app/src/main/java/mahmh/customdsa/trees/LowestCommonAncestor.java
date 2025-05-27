package mahmh.customdsa.trees;

public class LowestCommonAncestor {
    public static record Node(String id, Node left, Node right) {}

    /** 
     * This is the interface function. It runs the LCA algorithm and throws an error
     * if either `p` and `q` are non-existent.
     */
    public static Node find(Node root, Node p, Node q) throws IllegalArgumentException {
        if (!exists(root, p) || !exists(root, q)) {
            throw new IllegalArgumentException("One or both nodes not found in the tree.");
        }
        return _find(root, p, q);
    }

    /** Runnable example */
    public static void main(String[] args) {
        Node F = new Node("F", null, null);
        Node E = new Node("E", null, null);
        Node D = new Node("D", null, null);
        Node C = new Node("C", null, F);
        Node B = new Node("B", D, E);
        Node A = new Node("A", B, C);
        System.out.println(LowestCommonAncestor.find(A, B, F));
    }

    /** 
     * Returns the LCA of `p` and `q`, starting from the `root` node.
     * It attempts to find their intersection point (node) by recursively
     * searching if one exists to the left subtree of a given node AND the
     * other exists on the right subtree of the same given node.
     */
    private static Node _find(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) return root;

        Node left = _find(root.left, p, q);
        Node right = _find(root.right, p, q);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    /** Checks if `target` can be found in the binary tree. */
    private static boolean exists(Node root, Node target) {
        if (root == null) return false;
        if (root == target) return true;
        return exists(root.left, target) || exists(root.right, target);
    }
}
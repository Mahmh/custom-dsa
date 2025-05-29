package mahmh.customdsa.trees;
import mahmh.customdsa.utils.Data;

public class AVLTree {
    private Node root;

    public static class Node {
        private int id;
        private Data data;
        private Node left;
        private Node right;
        private int height = 1;

        public Node(int id, Data data) {
            this.id = id;
            this.data = data;
        }
    }

    public AVLTree(Node root) {
        this.root = root;
    }

    /** Returns the node with the given ID. Otherwise, throws an error if not found. */
    public Data search(int id) throws Error {
        Node current = root;

        while (current != null && id != current.id) {
            if (id < current.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            throw new Error("Node with id=" + id + " does not exist.");
        } else {
            return current.data;
        }
    }

    /** Removes a node from the tree. */
    public void delete(int id) {
        this.root = delete(root, id);
    }

    /** Inserts a node to the tree. */
    public void insert(int id, Data data) {
        this.root = insert(root, id, data);
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Node root = new Node(50, new Data("50"));
        AVLTree tree = new AVLTree(root);

        tree.insert(25, new Data("25"));
        tree.insert(75, new Data("75"));

        System.out.println(tree.search(75).get("name"));
    }

    /** Helper function to recursively find then delete the node to be deleted, and then rebalances the tree when necessary. */
    private Node delete(Node root, int id) {
        if (root == null) return null;

        // Perform standard BST delete
        if (id < root.id) {
            root.left = delete(root.left, id);
        } else if (id > root.id) {
            root.right = delete(root.right, id);
        } else {
            // Node found
            if (root.left == null || root.right == null) {
                root = (root.left != null) ? root.left : root.right;
            } else {
                // Node with 2 children: get in-order successor (smallest in right subtree)
                Node successor = getMin(root.right);
                root.id = successor.id;
                root.data = successor.data;
                root.right = delete(root.right, successor.id);
            }
        }

        // Update height & rebalance the tree
        if (root == null) return null; // after deletion, return early if needed
        updateHeight(root);
        return rebalance(root);
    }

    /** Helper function */
    private Node insert(Node current, int id, Data data) throws Error {
        if (current == null) return new Node(id, data);

        if (id < current.id) {
            current.left = insert(current.left, id, data);
        } else if (id > current.id) {
            current.right = insert(current.right, id, data);
        } else {
            throw new Error("Node with id=" + id + " already exists.");
        }

        // Maintain AVL
        updateHeight(current);
        return rebalance(current);
    }

    /** New height = 1 + max(height of left node, height of right node) */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /** Returns 0 if `node` is null, or its height otherwise. */
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    /** Balance factor = height of left node - height of right node */
    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /** Attempts to automatically balance the tree when it needs to. */
    private Node rebalance(Node node) {
        int bf = getBalanceFactor(node);

        // LEFT HEAVY
        if (bf > 1) {
            // Left-Right Case (LR) -> First rotate left on left child
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            // Left-Left Case (LL)
            return rotateRight(node);
        }

        // RIGHT HEAVY
        if (bf < -1) {
            // Right-Left Case (RL) -> First rotate right on right child
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            // Right-Right Case (RR)
            return rotateLeft(node);
        }

        return node; // balanced
    }

    /** Performs right rotation on the tree. */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node subtree2 = x.right;

        // Rotate
        x.right = y;
        y.left = subtree2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /** Performs left rotation on the tree. */
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node subtree2 = x.left;

        // Rotate
        x.left = y;
        y.right = subtree2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /** Returns the node with the minimum ID in the tree. */
    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
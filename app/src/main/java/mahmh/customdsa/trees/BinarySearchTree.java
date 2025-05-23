package mahmh.customdsa.trees;

public class BinarySearchTree {
    private Node root;
    public static record Data(String name) {}
    public static class Node {
        public int id;
        public Node leftChild;
        public Node rightChild;
        public Data data;

        public Node(int id, Node leftChild, Node rightChild, Data data) {
            this.id = id;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.data = data;
        }

        public Node(int id, Data data) {
            this(id, null, null, data);
        }
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    /** Prints the BST to stdout. */
    public void print() {
        print(root, "", true, true);
    }

    /** Searches & retrieves the node's data using its ID. */
    public Data get(int id) {
        return checkNode(id, root);
    }

    /** Adds a node to the BST. */
    public void add(Node node) {
        checkNodeToAdd(node, root);
    }

    /** Retrieves the node with the minimum ID in the whole BST. */
    public Data getDataWithMinId() {
        return checkNodeWithMinId(root);
    }

    /** Retrieves the node with the maximum ID in the whole BST. */
    public Data getDataWithMaxId() {
        return checkNodeWithMaxId(root);
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Node root = new Node(5, new Data("5"));
        BinarySearchTree bst = new BinarySearchTree(root);

        bst.add(new Node(3, new Data("3")));
        bst.add(new Node(2, new Data("2")));
        bst.add(new Node(10, new Data("10")));
        bst.add(new Node(4, new Data("4")));
        bst.add(new Node(17, new Data("17")));
        bst.add(new Node(23, new Data("23")));
        bst.add(new Node(18, new Data("18")));

        System.out.println("Retrieved: " + bst.get(3));
        System.out.println("Retrieved: " + bst.get(5));
        System.out.println("Retrieved: " + bst.get(6));
        System.out.println("Retrieved: " + bst.get(2));
        System.out.println("Retrieved: " + bst.get(1));

        bst.print();
        System.out.println("Min Id: " + bst.getDataWithMinId());
        System.out.println("Max Id: " + bst.getDataWithMaxId());
    }

    /** Recursive function to check each node and subnode. */
    private Data checkNode(int id, Node currentNode) {
        if (currentNode == null) return null;

        if (id == currentNode.id) {
            return currentNode.data;
        } else if (id < currentNode.id) {
            return checkNode(id, currentNode.leftChild);
        } else {
            return checkNode(id, currentNode.rightChild);
        }
    }

    /** Recursive function for adding a node. */
    private void checkNodeToAdd(Node nodeToAdd, Node currentNode) throws Error {
        if (nodeToAdd.id == currentNode.id) {
            throw new Error("An existing node in the BST already has id=" + nodeToAdd.id);
        } else if (nodeToAdd.id < currentNode.id) {
            if (currentNode.leftChild != null) checkNodeToAdd(nodeToAdd, currentNode.leftChild);
            else currentNode.leftChild = nodeToAdd;
        } else {
            if (currentNode.rightChild != null) checkNodeToAdd(nodeToAdd, currentNode.rightChild);
            else currentNode.rightChild = nodeToAdd;
        }
    }  

    /** Recursive function that returns the left-most node. */
    private Data checkNodeWithMinId(Node currentNode) {
        if (currentNode.leftChild != null) {
            return checkNodeWithMinId(currentNode.leftChild);
        } else {
            return currentNode.data;
        }
    }

    /** Recursive function that returns the right-most node. */
    private Data checkNodeWithMaxId(Node currentNode) {
        if (currentNode.rightChild != null) {
            return checkNodeWithMaxId(currentNode.rightChild);
        } else {
            return currentNode.data;
        }
    }

    /** Recursive function for printing the BST. */
    private void print(Node node, String prefix, boolean isTail, boolean isRoot) {
        if (node == null) return;
        if (isRoot) {
            System.out.println(node.id);
        } else {
            System.out.println(prefix + (isTail ? "└─ " : "├─ ") + node.id);
        }

        // Adjust prefix for children: no extra spaces for root's children
        String childPrefix = isRoot ? "" : prefix + (isTail ? "    " : "│   ");

        boolean hasLeft = node.leftChild != null;
        boolean hasRight = node.rightChild != null;

        if (hasLeft || hasRight) {
            if (hasLeft && hasRight) {
                print(node.leftChild, childPrefix, false, false);
                print(node.rightChild, childPrefix, true, false);
            } else if (hasLeft) {
                print(node.leftChild, childPrefix, true, false);
            } else if (hasRight) {
                // Print null for missing left child
                System.out.println(childPrefix + "├─ null");
                print(node.rightChild, childPrefix, true, false);
            }
        }
    }
}
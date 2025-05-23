package mahmh.customdsa.arrays;
import mahmh.customdsa.utils.Data;

public class SinglyLinkedList {
    private Node root;
    public static class Node {
        public Node next;
        public Data data;

        public Node(Data data) {
            this.next = null;
            this.data = data;
        }
    }

    public SinglyLinkedList() {}

    /** Prints the whole linked list as an array. */
    public void print() {
        Node currentNode = root;
        System.out.print("[");
        while (true) {
            if (currentNode != null) {
                System.out.print(currentNode.data);
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                    System.out.print(", ");
                } else {
                    System.out.println("]");
                    break;
                }
            } else {
                System.out.println("]");
                break;
            }
        }
    }

    /** Returns the data item given its index, by traversing the linked list. */
    public Data get(int idx) throws AssertionError, Error {
        assert idx >= 0 : "Index must be non-negative.";
        Node currentNode = root;
        int currentIdx = 0;

        while (true) {
            if (idx == 0 && currentNode == null) {
                throw new Error("Item at index 0 was not found.");
            } else if (currentIdx == idx) {
                return currentNode.data;
            } else if (currentNode.next == null) {
                throw new Error("Item at index " + idx + " was not found.");
            } else {
                currentNode = currentNode.next;
                currentIdx++;
            }
        }
    }

    /** Adds a data item to the end of the list. */
    public void append(Data data) {
        Node nodeToAdd = new Node(data);
        Node currentNode = root;

        while (true) {
            if (currentNode != null && currentNode.next == null) {
                currentNode.next = nodeToAdd;
                break;
            } else if (currentNode == null) {
                root = nodeToAdd;
                break;
            } else {
                currentNode = currentNode.next;
            }
        }
    }

    /** Inserts a data item at a given index. */
    public void insert(Data data, int idx) {
        assert idx >= 0 : "Index must be non-negative.";
        Node nodeToAdd = new Node(data);
        Node currentNode = root;
        int currentIdx = 0;

        while (true) {
            if (idx == 0) {
                nodeToAdd.next = currentNode;
                root = nodeToAdd;
                break;
            } else if (currentIdx+1 == idx && currentNode.next != null) {
                nodeToAdd.next = currentNode.next;
                currentNode.next = nodeToAdd;
                break;
            } else {
                currentNode = currentNode.next;
                currentIdx++;
            }
        }
    }

    /** Removes a data item out of the list. */
    public void delete(int idx) {
        assert idx >= 0 : "Index must be non-negative.";
        Node currentNode = root;
        int currentIdx = 0;

        while (true) {
            if (idx == 0) {
                root = currentNode.next != null ? currentNode.next : null;
                break;
            } else if (currentIdx+1 == idx) {
                currentNode.next = currentNode.next.next != null ? currentNode.next.next : null;
                break;
            } else {
                currentNode = currentNode.next;
                currentIdx++;
            }
        }
    }

    /** Returns the number of items in the list. */
    public int size() {
        Node currentNode = root;
        int size = 0;

        while (currentNode != null) {
            size++;
            currentNode = currentNode.next;
        }

        return size;
    }

    /** Runnable example. */
    public static void main(String[] args) {
        SinglyLinkedList ls = new SinglyLinkedList();
        ls.append(new Data("Sam"));
        ls.append(new Data("John"));
        ls.append(new Data("Bob"));
        ls.delete(0);
        ls.delete(1);
        ls.print();
    }
}
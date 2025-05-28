package mahmh.customdsa.trees;
import java.util.*;

public class Trie {
    private Node root = new Node();

    public static class Node {
        boolean isCompleteWord = false;
        Map<Character, Node> children = new HashMap<>();
    }

    /** Prints all words stored in this Trie. */
    public void print() {
        printRecursive(root, new StringBuilder(), "", true);
    }

    /** Stores a given word in the Trie to expand the auto-completion knowledge base. */
    public void store(String word) {
        Node currentNode = root;
        int currentIdx = 0;

        while (currentNode != null && currentIdx < word.length()) {
            char currentChar = word.charAt(currentIdx);
            Node existing = currentNode.children.get(currentChar);

            if (existing != null) {
                currentNode = existing;
            } else {
                currentNode.children.put(currentChar, new Node());
                currentNode = currentNode.children.get(currentChar);
            }

            currentIdx++;
        }

        currentNode.isCompleteWord = true; // mark final node as a complete word
    }

    /** Stores multiple words at once. */
    public void store(List<String> words) {
        for (String word : words) store(word);
    }

    /** Returns the list of possible auto-completed form of the given prefix. */
    public List<String> complete(String prefix) {
        List<String> predictions = new ArrayList<>();
        Node currentNode = root;
        int currentIdx = 0;

        // Check if any word has this prefix
        while (currentNode != null && currentIdx < prefix.length()) {
            char currentChar = prefix.charAt(currentIdx);
            Node existing = currentNode.children.get(currentChar);

            if (existing != null) {
                currentNode = existing;
            } else {
                return predictions;
            }

            currentIdx++;
        }

        // currentNode is now equal to the last letter of the prefix
        collect(currentNode, new StringBuilder(prefix), predictions);

        return predictions;
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Trie tree = new Trie();

        tree.store(List.of(
            "cat",
            "cut",
            "card",
            "car",
            "interface",
            "internet",
            "crust",
            "cream"
        ));
        tree.print();
        
        System.out.println(tree.complete("c"));
        System.out.println(tree.complete("cr"));
        System.out.println(tree.complete("cre"));
    }

    /** Helper function to recursively print every stored word in the Trie. */
    private void printRecursive(Node node, StringBuilder prefix, String indent, boolean isLast) {
        if (prefix.length() > 0) {
            System.out.println(indent + (isLast ? "└── " : "├── ") + prefix.charAt(prefix.length() - 1) + (node.isCompleteWord ? " (word)" : ""));
            indent += isLast ? "    " : "│   ";
        }

        int count = 0;
        int size = node.children.size();

        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            printRecursive(entry.getValue(), prefix, indent, ++count == size);
            prefix.deleteCharAt(prefix.length() - 1); // backtrack
        }
    }

    /** Helper function to recursively search for complete words that contain the given prefix. */
    private void collect(Node currentNode, StringBuilder currentPath, List<String> predictions) {
        if (currentNode.isCompleteWord) {
            predictions.add(currentPath.toString()); // found a full word
        }

        for (Map.Entry<Character, Node> child : currentNode.children.entrySet()) {
            currentPath.append(child.getKey()); // go deeper
            collect(child.getValue(), currentPath, predictions); // recursive call
            currentPath.deleteCharAt(currentPath.length() - 1); // backtrack
        }
    }
}
package mahmh.customdsa.arrays;
import mahmh.customdsa.utils.Data;

public class Queue {
    private final SinglyLinkedList array = new SinglyLinkedList();

    /** Puts the data item to the end of the queue. */
    public void push(Data data) {
        array.append(data);
    }

    /** Removes & returns the data item from the front of the queue. */
    public Data pop() {
        if (array.size() == 0) {
            return null;
        } else {
            Data data = array.get(0);
            array.delete(0);
            return data;
        }
    }

    /** Returns the number of items in the queue. */
    public int size() {
        return array.size();
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Queue s = new Queue();
        s.push(new Data("First"));
        s.push(new Data("Second"));
        s.push(new Data("Third"));
        System.out.println("size: " + s.size());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println("size: " + s.size());
    }
}
package mahmh.customdsa.arrays;
import java.util.ArrayList;

public class Stack {
    private final ArrayList<Data> array = new ArrayList<>();
    public static record Data(String name) {}

    /** Puts the data item on the top of the stack. */
    public void push(Data data) {
        array.add(data);
    }

    /** Removes & returns the data item on the top of the stack. */
    public Data pop() {
        final int idx = array.size() - 1;

        if (idx < 0) {
            return null;
        } else {
            Data data = array.get(idx);
            array.remove(idx);
            return data;
        }
    }

    /** Returns the number of items in the stack. */
    public int size() {
        return array.size();
    }

    /** Runnable example. */
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(new Data("John"));
        s.push(new Data("Andrew"));
        System.out.println("size: " + s.size());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println("size: " + s.size());
    }
}
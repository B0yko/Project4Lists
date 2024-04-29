import java.util.LinkedList;

public class Part3Queue<T> extends LinkedList<T> {

    public void enqueue(T item) {
        addLast(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return removeFirst();
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public int size() {
        return super.size();
    }

    public void printQueue() {
        System.out.println("Queue:");
        for (T item : this) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Part3Queue<Integer> queue = new Part3Queue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.printQueue();

        System.out.println("Dequeued element: " + queue.dequeue());

        queue.printQueue();
    }
}


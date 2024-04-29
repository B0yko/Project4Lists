public class Part2Stack<T> extends Part1LinkedList<T> {
    public void push(T item) {
        addFirst(item);
    }

    public T pop() {
        if (getSize() == 0) {
            throw new IllegalStateException("Cannot pop from an empty stack.");
        }
        T item = get(0);
        remove(0);
        return item;
    }
    public static void main(String[] args) {
        Part2Stack<Integer> stack = new Part2Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
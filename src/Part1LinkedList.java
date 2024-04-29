import java.util.Iterator;

public class Part1LinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(last, element, null);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            add(element);
        } else {
            Node<T> nextNode = getNode(index);
            Node<T> prevNode = nextNode.prev;
            Node<T> newNode = new Node<>(prevNode, element, nextNode);
            if (prevNode == null) {
                first = newNode;
            } else {
                prevNode.next = newNode;
            }
            nextNode.prev = newNode;
            size++;
        }
    }

    public T get(int index) {
        return getNode(index).item;
    }

    public void remove(T element) {
        Node<T> node = first;
        while (node != null) {
            if (node.item.equals(element)) {
                removeNode(node);
                return;
            }
            node = node.next;
        }
    }

    public void remove(int index) {
        Node<T> node = getNode(index);
        removeNode(node);
    }

    public void removeAll(T element) {
        Node<T> node = first;
        while (node != null) {
            if (node.item.equals(element)) {
                Node<T> next = node.next;
                removeNode(node);
                node = next;
            } else {
                node = node.next;
            }
        }
    }

    public void addFirst(T element) {
        Node<T> newNode = new Node<>(null, element, first);
        if (first == null) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> node = first;
        while (node != null) {
            sb.append(node.item);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void removeNode(Node<T> node) {
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;
        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            node.prev = null;
        }
        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
            node.next = null;
        }
        node.item = null;
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Part1LinkedList<Integer> list = new Part1LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Initial list: " + list);

        list.add(1, 4);
        System.out.println("After adding 4 at index 1: " + list);

        list.remove(2);
        System.out.println("After removing element at index 2: " + list);

        list.removeAll(1);
        System.out.println("After removing all occurrences of 1: " + list);
    }
}


import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public void add(T e) {
        if (size == 0) {
            first = new Node<>(null, e, null);
            last = first;
        } else {
            Node<T> newNode = new Node<>(last, e, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void add(int i, T e) {
        if (i < 0 || i > size) throw new IndexOutOfBoundsException();
        if (i == size) {
            add(e);
            return;
        }
        Node<T> x = getNode(i);
        Node<T> newNode = new Node<>(x.prev, e, x);
        if (x.prev == null) {
            first = newNode;
        } else {
            x.prev.next = newNode;
        }
        x.prev = newNode;
        size++;
    }

    public T get(int i) {
        return getNode(i).element;
    }

    public void remove(T e) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (e.equals(x.element)) {
                unlink(x);
                return;
            }
        }
    }

    public void remove(int i) {
        unlink(getNode(i));
    }

    public void removeAll(T e) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (e.equals(x.element)) {
                unlink(x);
            }
        }
    }

    public void addFirst(T e) {
        Node<T> newNode = new Node<>(null, e, first);
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
        Node<T> x = first;
        while (x != null) {
            sb.append(x.element.toString());
            sb.append(" ");
            x = x.next;
        }
        return sb.toString();
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> x;
        if (index < size / 2) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    private void unlink(Node<T> x) {
        if (x.prev == null) {
            first = x.next;
        } else {
            x.prev.next = x.next;
        }
        if (x.next == null) {
            last = x.prev;
        } else {
            x.next.prev = x.prev;
        }
        size--;
    }

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator() {
            this.current = (Node<T>) first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.element;
            current = current.next;
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>();
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;

    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            first.next = null;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        first.previous = null;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = null;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = oldLast;
            oldLast.next = last;
        }
        size++;

    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue");
        Item firstItem = first.item;
        first.previous = null;
        if (size == 1) {
            setFirstAndLastToNull();
        } else {
            first = first.next;
        }
        size--;
        return firstItem;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue");
        Item lastItem = last.item;
        if (size == 1) {
            setFirstAndLastToNull();
        } else {
            last = last.previous;
            last.next = null;
        }
        size--;
        return lastItem;
    }

    // In case queue is empty
    private void setFirstAndLastToNull() {
        last = null;
        first = null;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot call remove on this iterator");
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException("No item left in the queue");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(1);
        deque.removeLast();
    }
}
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public int size() {
        return n;
    }


    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null");
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Cannot call dequeue on an empty queue");
        if (n == queue.length / 4) resize(queue.length / 2);
        int indexForDequeue = StdRandom.uniform(n);
        Item dequeueItem = queue[indexForDequeue];
        queue[indexForDequeue] = queue[--n];
        queue[n] = null;
        return dequeueItem;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Cannot call sample on an empty queue");
        int randomIndex = StdRandom.uniform(n);
        return queue[randomIndex];
    }


    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int iterationIndex;
        private int[] indices;

        public RandomizedQueueIterator() {
            iterationIndex = 0;
            indices = StdRandom.permutation(n);
        }

        @Override
        public boolean hasNext() {
            return iterationIndex < n;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot call remove on this iterator");
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("Iteration has no more next elements");
            int currentIndex = indices[iterationIndex++];
            return queue[currentIndex];
        }

    }

    public static void main(String[] args) {

    }
}
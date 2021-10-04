import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private int maxSize, size;
    private Item[] items;

    public RandomizedQueue() {
        maxSize = 1;
        size = 0;
        items = (Item[]) new Object[maxSize];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void enqueue(Item item) throws IllegalArgumentException{
        if(item == null)
            throw new IllegalArgumentException();
        if(size == maxSize){
            maxSize *= 2;
            resize(maxSize);
        }
        items[size] = item;
        size++;
    }
    public Item dequeue(){
        if(size == 0)
            throw new NoSuchElementException();
        if(size < maxSize / 2 && maxSize != 1){
            maxSize /= 2;
            resize(maxSize);
        }
        int x = StdRandom.uniform(size);
        Item item = items[x];
        items[x] = items[size - 1];
        size--;
        return item;
    }
    public Item sample() throws NoSuchElementException {
        if(size == 0)
            throw new NoSuchElementException();
        int x = StdRandom.uniform(size);
        return items[x];
    }

    private Item get(int i){
        return items[i];
    }

    private void resize(int newSize){
        Item[] _items = (Item[]) new Object[newSize];
        for(int i = 0; i < size; ++i)
            _items[i] = items[i];
        items = _items;
    }

    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>(this);
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private int size, currentIndex;
        private int[] order;
        private RandomizedQueue<Item> queue;

        public RandomizedQueueIterator(RandomizedQueue<Item> items) {
            size = items.size();
            currentIndex = 0;
            order = new int[size];
            queue = items;
            for(int i = 0; i < size; ++i)
                order[i] = i;
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Item next() {
            if(size == 0)
                throw new NoSuchElementException();
            return queue.get(order[currentIndex++]);
        }
    }
    public static void main(String[] args){
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for(int i = 0; i < n; ++i){
            queue.enqueue(i);
        }
//        Scanner sc = new Scanner(System.in);
//        sc.next();
        for(int a: queue){
            for (int b: queue){
                StdOut.print(a + "-" + b + " ");
            }
            StdOut.println();
        }
    }
}

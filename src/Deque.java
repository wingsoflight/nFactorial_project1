import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> head, tail;

    public Deque(){
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> node = new Node<>(item, null, head);
        if(head == null)
            head = tail = node;
        else{
            head.setPrevNode(node);
            head = node;
        }
        size++;
    }

    public void addLast(Item item){
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> node = new Node<>(item, tail, null);
        if(tail == null)
            head = tail = node;
        else{
            tail.setNextNode(node);
            tail = node;
        }
        size++;
    }

    public Item removeFirst(){
        if (this.isEmpty())
            throw new NoSuchElementException();
        Item data = head.getData();
        head = head.getNextNode();
        if(head != null)
            head.setPrevNode(null);
        else
            tail = null;
        size--;
        return data;
    }

    public Item removeLast(){
        if (this.isEmpty())
            throw new NoSuchElementException();
        Item data = tail.getData();
        tail = tail.getPrevNode();
        if (tail != null)
            tail.setNextNode(null);
        else
            head = null;
        size--;
        return data;
    }

    private Node<Item> getHead(){
        return head;
    }

    private Node<Item> getTail(){
        return tail;
    }


    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<>(this);
    }

    static class DequeIterator<Item> implements Iterator<Item> {
        Node<Item> curr;

        public DequeIterator(Deque<Item> items) {
            curr = items.getHead();
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Item next() {
            if(!this.hasNext())
                throw new NoSuchElementException();
            Item data = curr.getData();
            curr = curr.getNextNode();
            return data;
        }
    }
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.removeFirst();
        for(Integer i: deque) {
            System.out.println(i);
        }
    }
}

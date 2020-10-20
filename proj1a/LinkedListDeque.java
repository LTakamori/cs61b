public class LinkedListDeque<T> implements Deque<T>{
    private int size;
    private Node sentinel;

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T i, Node next_n, Node prev_n) {
            item = i;
            next = next_n;
            prev = prev_n;
        }

        private T getRecursive_Node(int index){
            if (index == 0)
                return item;
            else
                return next.getRecursive_Node(index-1);
        }
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item){
        size = size + 1;
        sentinel.next = new Node(item, sentinel.next , sentinel);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T item){
        size = size + 1;
        sentinel.prev = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public boolean isEmpty() {
        return(size == 0);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node index = sentinel;
        for (int i = 0; i < size; i++){
            index = index.next;
            System.out.print(index.item);
            System.out.print(' ');
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size == 0)
            return null;
        size = size - 1;
        T the_item = sentinel.next.item;
        Node temp = sentinel.next.next;
        temp.prev = sentinel;
        sentinel.next = temp;
        return the_item;
    }

    @Override
    public T removeLast(){
        if (size == 0)
            return null;
        size = size - 1;
        T the_item = sentinel.prev.item;
        Node temp = sentinel.prev.prev;
        temp.next = sentinel;
        sentinel.prev = temp;
        return the_item;
    }

    @Override
    public T get(int index){
        if(index >= size)
            return null;
        Node temp = sentinel.next;
        for(int i= 0; i < index; i++){
            temp = temp.next;
        }
        return temp.item;
    }

    public T getRecursive(int index){
        if (index >= size)
            return null;
        return sentinel.next.getRecursive_Node(index);
    }
}
public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T[] items;
    private int next_first, next_last, cap;
    private double taken_ratio;

    public ArrayDeque(){
        size = 0;
        items =(T[])new Object[8];
        cap = 8;
        next_first = cap/2;
        next_last = (cap/2+1)%cap;
        taken_ratio = size /cap;
    }


    private void resize(int new_cap){
        T[] new_array = (T[])new Object[cap];
        System.arraycopy(items,0, new_array, 0, new_cap);
        this.cap = new_cap;
        items = new_array;
    }

    @Override
    public void addFirst(T item){
        if (size == cap) this.resize(size*2);
        size = size + 1;
        items[next_first] = item;
        next_first = (next_first + cap - 1) % cap;
    }

    @Override
    public void addLast(T item){
        if (size == cap) this.resize(size*2);
        size = size + 1;
        items[next_last] = item;
        next_last = (next_last + 1) % cap;
    }

    @Override
    public boolean isEmpty(){
        return (size == 0);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        int index = (this.next_first+1) % this.cap;
        for(int i = 1 ; i <= size; i++){
            System.out.print(items[index]);
            System.out.print(' ');
            index = (index + i) % this.cap;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        size = size - 1;
        next_first = (next_first + 1) % cap;
        T ret = items[next_first];
        items[next_first] = null;
        taken_ratio = size /cap;
        if (taken_ratio <= 0.25 && size > 8){
            this.resize(cap/2);
        }
        return ret;
    }

    @Override
    public T removeLast(){
        size = size - 1;
        next_last = (next_last - 1 +cap) % cap;
        T ret = items[next_last];
        items[next_last] = null;
        taken_ratio = size /cap;
        if (taken_ratio <= 0.25 && size > 8){
            this.resize(cap/2);
        }
        return ret;
    }

    @Override
    public T get(int index){
        if(index >= size) return null;
        return items[(index + next_first + 1) % cap];
    }
}
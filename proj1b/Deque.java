public interface Deque<T> {
    public void addFirst(T T);
    public void addLast(T T);
    public int size();
    public boolean isEmpty();
    public T removeFirst();
    public T removeLast();
    public void printDeque();
    public T get(int index);
}

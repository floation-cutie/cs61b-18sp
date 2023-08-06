package byog.Core;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>{
    /**
     * we use circular sentinels here,saving one.
     */
    private int size;
    private TNode sentinel;

    private class TNode {
        T val;
        TNode prev;
        TNode next;

        private TNode(T x) {
            val = x;
        }
    }

    /**
     * to create an empty LinkedListDeque
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null); // null is available to generics
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * @source josh hug
     *         to create a "deep copy" of "other"!!!
     *         rather than just create a swallow copy,like in Python
     *         saying that : sentinel = other.sentinel; size = other.size;
     *         this will change the origin Object by making a subtle modulation on
     *         your cloned one!!
     *         first: to create an empty list,then addLast by iterative ways
     *         we can use <T> in the beginning instead of casting
     *         we don't need to change the size at first!s
     *
    public LinkedListDeque(LinkedListDeque other) {

        sentinel = new TNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < size; i++) {
            addLast((T) other.get(i));
        }
    }*/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    /* a little awkward here */
    public void addFirst(T T) {
        TNode t = new TNode(T);
        if (isEmpty()) {
            sentinel.next = t;
            sentinel.prev = t;
            t.prev = sentinel;
            t.next = sentinel;
            size = 1;
            return;
        }
        sentinel.next.prev = t;
        t.next = sentinel.next;
        sentinel.next = t;
        t.prev = sentinel;
        size++;
    }

    public void addLast(T T) {
        TNode t = new TNode(T);
        if (isEmpty()) {
            sentinel.next = t;
            sentinel.prev = t;
            t.prev = sentinel;
            t.next = sentinel;
            size = 1;
            return;
        }
        t.prev = sentinel.prev;
        t.next = sentinel;
        sentinel.prev.next = t;
        sentinel.prev = t;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        TNode tmp = sentinel.next;
        T returnValue = tmp.val;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        tmp = null;
        size--;
        return returnValue;
    }

    /**
     * We won't maintain references to Ts that are no longer in the deque.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        TNode tmp = sentinel.prev;
        T returnValue = tmp.val;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        tmp = null;
        size--;
        return returnValue;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        TNode ptr = sentinel;

        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.val;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, TNode node) {
        if (index == 0) {
            return node.val;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    public void printDeque() {
        TNode ptr = sentinel;
        for (int i = 0; i < size; i++) {
            ptr = ptr.next;
            if (i == size - 1) {
                System.out.println(ptr.val);
            } else {
                System.out.print(ptr.val + " ");
            }
        }
    }

    public void remove(T o) {
        TNode tmp = sentinel;
        int i;
        for (i = 0; i < size; i++) {
            tmp = tmp.next;
            if (tmp.val.equals(o)) {
                tmp.prev.next = tmp.next;
                tmp.next.prev = tmp.prev;
                tmp = null;
                break;
            }
        }
        if (i == size) {
            System.out.println("no such position");
        }
        size--;
    }

    public Iterator<T> iterator() {
        return new myDequeIterator();
    }

    private class myDequeIterator implements Iterator<T> {

        private int wizPos;
        private myDequeIterator() {
            wizPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            TNode ptr = sentinel.next;
            for (int i = 0; i < wizPos; i++) {
                ptr = ptr.next;
            }
            wizPos++;
            return ptr.val;
        }
    }
}

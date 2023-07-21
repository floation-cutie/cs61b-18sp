/**
 * We want to stimulate a circular array list and implement the Deque API on it.
 */

public class ArrayDeque<T> {
    private int size;
    private int totalSize;
    private int markFront;
    private int markEnd;
    private T[] arrayList;
    private final int REFACTOR = 2;

    public ArrayDeque() {
        arrayList = (T[]) new Object[8];
        totalSize = 8;
        markFront = 0;
        markEnd = 1;
        size = 0;
    }

    // public ArrayDeque(ArrayDeque other) {
    // size = other.size;
    // totalSize = other.totalSize;
    // markEnd = other.markEnd;
    // markFront = other.markFront;
    // arrayList = (T[]) new Object[totalSize];
    // for (int i = 0; i < totalSize; i++) {
    // arrayList[i] = (T) other.arrayList[i];
    // }
    // }

    /**
     * this method below helps us to calculate the previous index value
     * of the given index ,esp in "our own circular Array"!!!
     */
    private int minusOne(int index) {
        if (index > 0) {
            return index - 1;
        } else {
            return totalSize - 1;
        }
    }

    private int plusOne(int index) {
        if (index < totalSize - 1) {
            return index + 1;
        } else {
            return 0;
        }

    }

    public void addFirst(T T) {
        if (size == arrayList.length) {
            resize(size * REFACTOR);
        }
        arrayList[markFront] = T;
        markFront = minusOne(markFront);
        size++;
    }

    public void addLast(T T) {
        if (size == arrayList.length) {
            resize(size * REFACTOR);
        }
        arrayList[markEnd] = T;
        markEnd = plusOne(markEnd);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        markFront = plusOne(markFront);
        T tmp = arrayList[markFront];
        arrayList[markFront] = null;
        size--;
        double usageRatio = 1.0 * size / totalSize;
        if (usageRatio < 0.25 && totalSize >= 16) {
            resize(totalSize / REFACTOR);
        }
        return tmp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        markEnd = minusOne(markEnd);
        T tmp = arrayList[markEnd];
        arrayList[markEnd] = null;
        size--;
        double usageRatio = 1.0 * size / totalSize;
        if (usageRatio < 0.25 && totalSize >= 16) {
            resize(totalSize / REFACTOR);
        }
        return tmp;
    }

    /**
     * different situations as followed.
     */
    public void printDeque() {
        int head = markFront;
        for (int i = 0; i < size; i++) {
            head = plusOne(head);
            if (i == size - 1) {
                System.out.println(arrayList[head]);
            } else {
                System.out.print(arrayList[head] + " ");
            }
        }
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return arrayList[(index + markFront + 1) % totalSize];
    }

    /**
     * in the decrement resize,we rewrite own "arraycopy" and set the member
     * appropriately.
     * and should be removed private
     * 
     * @param tarSize
     */
    private void resize(int tarSize) {
        T[] a = (T[]) new Object[tarSize];
        int head = markFront;
        for (int i = 0; i < size; i++) {
            head = plusOne(head);
            a[i] = arrayList[head];
        }
        arrayList = a;
        totalSize = tarSize;
        markFront = minusOne(0);
        markEnd = size;
    }
}

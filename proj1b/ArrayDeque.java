/**
 * We want to stimulate a circular array list and implement the Deque API on it.
 */

public class ArrayDeque<T> implements Deque<T> {
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

    @Override
    public void addFirst(T T) {
        if (size == arrayList.length) {
            resize(size * REFACTOR);
        }
        arrayList[markFront] = T;
        markFront = minusOne(markFront);
        size++;
    }

    @Override
    public void addLast(T T) {
        if (size == arrayList.length) {
            resize(size * REFACTOR);
        }
        arrayList[markEnd] = T;
        markEnd = plusOne(markEnd);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
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

    @Override

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

    @Override

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
     * don't simply arraycopy the origin array,we need to reassign the array
     * index...
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

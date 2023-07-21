/**
 * We want to stimulate a circular array list and implement the Deque API on it.
 * */

public class ArrayDeque <T>{
    private int size;
    private int totalSize;
    private int markFront;
    private int markEnd;
    private T[] AList;
    private final int REFACTOR = 2;

    public ArrayDeque(){
        AList = (T[]) new Object[8];
        markFront = 0;
        markEnd = 1;
        totalSize = 8;
        size = 0;
    }

    /**
     * this method below helps us to calculate the previous index value
     * of the given index ,esp in "our own circular Array"!!!
     * */
    public int minusOne(int index){
        if(index > 0) return index - 1;
        else return totalSize - 1;
    }


    public int plusOne(int index){
        if(index < totalSize - 1) return index + 1;
        else return 0;
    }

    public void addFirst(T item){
        if(size==AList.length)
        {
            resize(size* REFACTOR);
        }
        AList[markFront] = item;
        markFront = minusOne(markFront);
        size++;
    }

    public void addLast(T item){
        if(size==AList.length)
        {
            resize(size* REFACTOR);
        }
        AList[markEnd] = item;
        markEnd = plusOne(markEnd);
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        double usage_ratio = 1.0*size/totalSize;
        if(usage_ratio < 0.25){
            resize(totalSize/ REFACTOR);
        }
        markFront = plusOne(markFront);
        T tmp = AList[markFront];
        size--;
        return tmp;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        double usage_ratio = 1.0*size/totalSize;
        if(usage_ratio < 0.25){
            resize(totalSize/ REFACTOR);
        }
        markEnd = minusOne(markEnd);
        T tmp = AList[markEnd];
        size--;
        return tmp;
    }

    /**
     * different situations as followed.
     * */
    public void printDeque(){
        int head=markFront;
        for(int i = 0;i < size;i++){
            head=plusOne(head);
            if(i == size - 1){
                    System.out.println(AList[head]);
                }
                else {
                    System.out.print(AList[head]+ " ");
                }
        }
    }

    public T get(int index){
        if(index > size - 1){
            return null;
        }
        int target=markFront;
        for(int i=0;i<index;i++){
            target = plusOne(target);
        }
        return AList[target];
    }

    /**
     * in the decrement resize,we rewrite own "arraycopy" and set the member appropriately.
     * @param tar_size
     */
    public void resize(int tar_size){
        if(tar_size > totalSize){
            T[] a = (T[]) new Object[tar_size];
            System.arraycopy(AList,0,a,0,totalSize);
            AList = a;
            totalSize = tar_size;
        }
        else{
                int head=markFront;
                T[] a = (T[]) new Object[tar_size];
                for(int i=0;i<size;i++){
                    head = plusOne(head);
                    a[i] =AList[head];
                }
                AList=a;
                totalSize = tar_size;
                markFront = minusOne(0);
                markEnd = size;
            }
    }
}

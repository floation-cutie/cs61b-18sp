public class LinkedListDeque <Item> {
    /**
     * we use circular sentinels here,saving one.
     * */
    private int size;
    private ItemNode sentinel;
    private class ItemNode{
        Item val;
        ItemNode prev;
        ItemNode next;
        private ItemNode(Item x) {
        val = x;
        }
    }
    /**
     *  to create an empty LinkedListDeque
     *  */
    public LinkedListDeque(){
        size = 0;
        sentinel = new ItemNode(null); //null is available to generics
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * @source josh hug
     * to create a "deep copy" of "other"!!!
     * rather than just create a swallow copy,like in Python
     * saying that : sentinel = other.sentinel; size = other.size;
     * this will change the origin Object by making a subtle modulation on your cloned one!!
     * first: to create an empty list,then addLast by iterative ways
     * we can use <Item> in the beginning instead of casting
     * */
    public LinkedListDeque(LinkedListDeque other){

        sentinel = new ItemNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = other.size();
        for(int i = 0; i < size;i++){
            addLast((Item) other.get(i));
        }
    }
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size==0)?true:false;
    }

    /* a little awkward here */
    public void addFirst(Item item){
        ItemNode t = new ItemNode(item);
        if(isEmpty()){
            sentinel.next = sentinel.prev = t;
            t.prev = t.next = sentinel;
            size = 1;
            return;
        }
        sentinel.next.prev = t;
        t.next = sentinel.next;
        sentinel.next = t;
        t.prev = sentinel;
        size++;
    }

    public void addLast(Item item)
    {
        ItemNode t = new ItemNode(item);
        if(isEmpty()){
            sentinel.next = sentinel.prev = t;
            t.prev = t.next = sentinel;
            size = 1;
            return;
        }
        sentinel.prev.next = t;
        t.prev = sentinel.prev;
        t.next = sentinel;
        sentinel.prev = t;
        size++;
    }

    public Item removeFirst(){
        if(isEmpty()) return null;
        ItemNode tmp = sentinel.next;
        Item returnValue = tmp.val;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        tmp = null;
        size--;
        return returnValue;
    }

    /**
     * We won't maintain references to items that are no longer in the deque.
     * */
    public Item removeLast(){
        if(isEmpty()) return null;
        ItemNode tmp = sentinel.prev;
        Item returnValue = tmp.val;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        tmp = null;
        size--;
        return returnValue;
    }

    public Item get(int index){
        if(index > size - 1) {
            return null;
        }
        ItemNode ptr = sentinel;

        for(int i = 0;i <= index;i++){
            ptr = ptr.next;
        }
        return ptr.val;
    }

    public Item getRecursive(int index){
        if(index > size - 1) {
            return null;
        }
        if(index == 0) return sentinel.next.val;
        LinkedListDeque next_List = new LinkedListDeque(this);
        next_List.removeFirst();
        return (Item) next_List.getRecursive(index-1);
    }

    public void printDeque(){
        ItemNode ptr = sentinel;
        for(int i = 0; i < size; i++){
            ptr = ptr.next;
            if(i == size - 1){
                System.out.println(ptr.val);
            }
            else {
                System.out.print(ptr.val + " ");
            }
        }
    }
}

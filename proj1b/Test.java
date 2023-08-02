import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addFirst(9);
        D.addLast(10);
        D.addLast(11);
        D.addLast(13);
        for (int item : D ) {
            System.out.println(item);
        }
//        Iterator<Integer> seer = D.iterator();
//        while (seer.hasNext()) {
//            System.out.println(seer.next());
//        }
    }
}

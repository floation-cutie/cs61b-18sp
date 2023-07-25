import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> stDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rtDeque = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 1000; i += 1) {
            double randomNum = StdRandom.uniform(-1.0, 1.0);
            if (randomNum < -0.5) {
                Integer x = StdRandom.uniform(9);
                stDeque.addFirst(x);
                rtDeque.addFirst(x);
                message += "addFirst(" + x + ")\n";
            } else if (randomNum > 0.5) {
                Integer x = StdRandom.uniform(9);
                stDeque.addLast(x);
                rtDeque.addLast(x);
                message += "addLast(" + x + ")\n";
            } else if (randomNum < 0 && rtDeque.size() != 0) {
                Integer a = stDeque.removeFirst();
                Integer b = rtDeque.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, a, b);
                if (a != b) {
                    break;
                }
            } else if (randomNum > 0 && rtDeque.size() != 0) {
                Integer a = stDeque.removeLast();
                Integer b = rtDeque.removeLast();
                message += "removeLast()\n";
                assertEquals(message, a, b);
                if (a != b) {
                    break;
                }
            }
        }
    }
}

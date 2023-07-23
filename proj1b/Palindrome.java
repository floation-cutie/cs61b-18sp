public class Palindrome {
    /** to return a deque where the characters appear in the same order as in the String "words"*/
    public Deque<Character> wordToDeque(String word) {
        int length = word.length();
        Deque<Character> deque =  new LinkedListDeque<Character>();
        for (int i = 0; i < length; i++) {
            char node = word.charAt(i);
            deque.addLast(node);
        }
        return deque;
    }

    /**use recursion to cover all circumstances skillfully,very smart!!*/
    public boolean isPalindrome(String word) {
        Palindrome pl = new Palindrome();
        Deque<Character> D = pl.wordToDeque(word);
        if (D.size() < 2) {
            return true;
        }
        char a, b;
        a = D.removeFirst();
        b = D.removeLast();
        if (a != b) return false;
        return isPalindrome(DequeToWord(D));
    }

    private String DequeToWord(Deque<Character> D) {
        String ans = "";
        while (!D.isEmpty()) {
            ans+=D.removeFirst();
        }
        return ans;
    }
}

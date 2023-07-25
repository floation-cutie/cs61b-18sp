public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int dif = x - y;
        if (dif == n || dif == -n) {
            return true;
        }
        return false;
    }
}

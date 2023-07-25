public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int dif = x - y;
        if (dif == 1 || dif == -1) {
            return true;
        }
        return false;
    }
}

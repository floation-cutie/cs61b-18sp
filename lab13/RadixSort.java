/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max_length = 0;
        for (String s : asciis) {
            max_length = Math.max(max_length, s.length());
        }
        // to be non-destructive
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = new String(asciis[i]);
        }
        for (int i = max_length - 1;i >= 0;i--) {
            // to be destructive
            sortHelperLSD(sorted,i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        final int NUM = 256;
        int[] start = new int[NUM];
        String[] sortedIndexArray = new String[asciis.length];
        int[] recordIndex = new int[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            // 当捕捉到StringOutOfBound 异常时，进行操作
            String s = asciis[i];
            int ch_index;
            try {
                ch_index = (int) s.charAt(index);
            }catch (StringIndexOutOfBoundsException e) {
                ch_index = 0;
            }
            recordIndex[i] = ch_index;
            start[ch_index]++;
        }
        int pos = 0;
        for (int i = 0; i < start.length; i++) {
            int tmp = start[i];
            start[i] = pos;
            pos += tmp;
        }

        for (int i = 0; i < asciis.length; i++) {
            int item = recordIndex[i];
            int place = start[item];
            // 可以直接表示指向同一对象，destructive
            sortedIndexArray[place] = asciis[i];
            start[item]++;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sortedIndexArray[i];
        }
        return;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"jeg", "jobber", "med", "dette", "i", "100", "arhundrer"};
        strings = sort(strings);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort

        return;
    }
}

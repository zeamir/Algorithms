package DynamicProgramming;

public class FindLongestNonDescendingSequence {
    public static void main(String[] args) {
        //int[] array = new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9}; //4
        int[] array = new int[]{0, 4,8, 4, 12, 2, 10, 6, 14, 1, 9};
        Integer[] sequencesMinElement = new Integer[array.length + 1];
        calcSequences(array, 0, sequencesMinElement);

        int maxSequenceLen = 1;
        for (int i = 1; i < sequencesMinElement.length; i++) {
            if (sequencesMinElement[i] != null) {
                maxSequenceLen = i;
            }
        }

        System.out.println("max sequence len is: " + maxSequenceLen);

    }

    private static void calcSequences(int[] array, int beginIndex, Integer[] sequencesMinElement) {
        int currentValue = array[beginIndex];
        if (beginIndex == array.length - 1) {
            sequencesMinElement[0] = Integer.MAX_VALUE; // last element in array
            sequencesMinElement[1] = currentValue; // last element in array
            return;
        }

        calcSequences(array, beginIndex + 1, sequencesMinElement);

        for (int i = sequencesMinElement.length - 2; i >= 0; i--) {
            if (sequencesMinElement[i] == null) {
                // there is no sequence matching this len. no need to look farther;
                continue;
            }
            // now we need to add the current value to try and create a larger sequence
            if (currentValue <= sequencesMinElement[i]) {
                // can generate a sequence with len + 1
                if (sequencesMinElement[i + 1] == null || sequencesMinElement[i + 1] < currentValue) {
                    sequencesMinElement[i + 1] = currentValue;
                }
            }
        }
    }
}

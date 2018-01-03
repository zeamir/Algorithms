package DynamicProgramming.FindLongestNonDescendingSubSequence;

import java.util.Arrays;

//https://leetcode.com/problems/longest-increasing-subsequence/solution/
public class LongestSequenceUsingDynamicProgramming {

    public static void main(String []args) {
        int[] arr = new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9};
        System.out.println("longest subsuq = " + findLongestSubSeq(arr));
    }
    private static int findLongestSubSeq(int[] arr) {
        int[] maxLen = new int[arr.length];
        Arrays.fill(maxLen, 1);
        
        for (int i = 0; i < arr.length; i++) {
            // check for smaller or equal prev entiries in arr, and check
            // the max of their length
            int prevMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j]) {
                    prevMaxLen = Math.max(prevMaxLen, maxLen[j]);
                }
            }
            maxLen[i] = 1 + prevMaxLen;
        }
        int totalMax = 0;
        for (int i = 0; i < maxLen.length; i++) {
            totalMax = Math.max(totalMax, maxLen[i]);
        }
        return totalMax;
    }
    
}
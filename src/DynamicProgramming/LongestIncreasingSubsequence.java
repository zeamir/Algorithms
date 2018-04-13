package DynamicProgramming;

// see https://leetcode.com/problems/longest-increasing-subsequence/description/
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] nums = new int[]{4, 2, 17, 7, 3 , 14, 8, 46, 5, 7, 47, 68, 33};
		//                     0  1  2  3  4  5  6
		System.out.println("max len using DP  = " + dpLIS(nums));

		int intMaxLenRecursice = recursiveLengthOfLIS(nums, Integer.MIN_VALUE,0);
		System.out.println("max len using recursion = " + intMaxLenRecursice);

		int memo[][] = new int[nums.length + 1][nums.length];
		int maxWithMemorization= recursiveWithMemorizationLIS(nums, -1,0, memo);
		System.out.println("max len using recursion with momorization = " + maxWithMemorization);
	}

	private static int recursiveLengthOfLIS(int[] nums, int prev, int curpos) {
		//System.out.println("recursiveLengthOfLIS");
		if (curpos == nums.length) {
			return 0;
		}
		int maxIfTaken = 0;
		if (nums[curpos] > prev) {
			// since element is greather then lastElement taken, we try and include it.
			// if current element > last element then the current becomes the last element
			maxIfTaken = 1 + recursiveLengthOfLIS(nums, nums[curpos], curpos + 1);
		}

		int maxIfNotTaken = recursiveLengthOfLIS(nums, prev, curpos + 1);
		// the return result is the max len between the two possible options
		return Math.max(maxIfTaken, maxIfNotTaken);
	}

	private static int recursiveWithMemorizationLIS(int[] nums, int prevPosIncluded, int curpos, int memo[][]) {
		//System.out.println("recursiveWithMemorizationLengthOfLIS");
		if (curpos == nums.length) {
			return 0;
		}
		// we use memo[prevPosIncluded+1] since prevPos might be -1
		if (memo[prevPosIncluded+1][curpos] > 0) {
			return memo[prevPosIncluded+1][curpos];
		}

		int maxIfTaken = 0;
		if (prevPosIncluded == -1 || nums[curpos] > nums[prevPosIncluded]) {
			// since element is greather then lastElement taken, we try and include it.
			// if current element > last element then the current becomes the last element
			maxIfTaken = 1 + recursiveWithMemorizationLIS(nums, curpos, curpos + 1, memo);
		}
		int maxIfNotTaken = recursiveWithMemorizationLIS(nums, prevPosIncluded, curpos + 1, memo);
		// the return result is the max len between the two possible options
		memo[prevPosIncluded+1][curpos] = Math.max(maxIfTaken, maxIfNotTaken);
		return memo[prevPosIncluded+1][curpos];
	}


	// this is the method leetcode asks to implement
	private static int dpLIS(int[] nums) {
		if (nums.length == 0) return 0;
		int[] memoMaxLenghts = new int[nums.length];

		int lastPos;
		int totalMaxLen = 1;
		for (lastPos = 0; lastPos < nums.length; lastPos++) {
			// worst case - assume the element in lastPos just started a sequence
			memoMaxLenghts[lastPos] = 1;

			for (int k = 0; k <= lastPos - 1; k++) {
				if (nums[lastPos] > nums[k]) {
					// last pos is a candidate for max len
					memoMaxLenghts[lastPos] = Math.max(memoMaxLenghts[lastPos], memoMaxLenghts[k] + 1);
				}
				if (totalMaxLen < memoMaxLenghts[lastPos]) {
					totalMaxLen = memoMaxLenghts[lastPos];
				}
			}
		}

		return totalMaxLen;
	}

}

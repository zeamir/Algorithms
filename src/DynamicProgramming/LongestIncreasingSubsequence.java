package DynamicProgramming;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] nums = new int[]{1, 5, 2, 7, 3, 4};
		int maxLen = lengthOfLIS(nums);
		System.out.println("LongestIncreasingSubsequence = " + maxLen);
	}

	// this is the method leetcode asks to implement
	public static int lengthOfLIS(int[] nums) {
		int[] arr = new int[nums.length + 1];
		System.arraycopy(nums, 0, arr, 1, nums.length);
		//arr[0] = Integer.MAX_VALUE;
		return dp(arr);

	}

	private static int dp(int[] arr) {

		int[] maxValues = new int[arr.length];
		int[] maxLens = new int[arr.length];
		//Arrays.fill(maxValues, Integer.MIN_VALUE);
		//Arrays.fill(maxLens, 0);

		int lastPos;
		for (lastPos = 1; lastPos < arr.length; lastPos++) {
			// worst case - assume the element in lastPos just started a sequence
			maxLens[lastPos] = 1;
			maxValues[lastPos] = arr[lastPos];

			for (int k = 1; k <= lastPos - 1; k++) {
				if (arr[lastPos] > arr[k]) {
					// last pos is a candidate for max len
					maxValues[lastPos] = Math.min(maxValues[lastPos], arr[k]);
					maxLens[lastPos] = Math.max(maxLens[lastPos], maxLens[k] + 1);
				}
			}
		}

		return maxLens[arr.length - 1];
	}
}

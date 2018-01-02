package DynamicProgramming;

//https://leetcode.com/problems/maximum-subarray/description/
public class MaximumSubarray {

	public static void main(String[] args) {
		//int[] array = new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9}; //4
		int[] array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		int maxSum = maxSubArray(array);

		System.out.println("max subarray sum is: " + maxSum);

	}

	static int maxSubArray(int[] nums) {

			int[] maxSums = new int[nums.length];

			if (nums == null ||  nums.length == 0) {
				return -1;
			}
			if (nums.length == 1) {
				return nums[0];
			}
			maxSums[0] = nums[0];
			int overallMax = nums[0];
			for (int i = 1; i < nums.length ; i++) {
				//System.out.println("iter :" + i);
				int newMax = Math.max(nums[i], (maxSums[i-1] + nums[i]));
				//System.out.println("newMax = " + newMax);
				maxSums[i] = newMax;
				overallMax = Math.max(overallMax, newMax);
			}
			return overallMax;

		}
	}
package DynamicProgramming;
//https://leetcode.com/problems/house-robber/description/
@SuppressWarnings("Duplicates")
class HouseRobber {

	public static void main(String[] args) {
		System.out.println(rob(new int[]{2, 1, 1, 2}));
	}

	private static int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int maxExludingPrev = nums[0];
		int maxIncludingPrev = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			int maxWithCellI = maxExludingPrev + nums[i];
			if (maxWithCellI < maxIncludingPrev) {
				maxWithCellI = maxIncludingPrev;
			}
			maxExludingPrev = maxIncludingPrev;
			maxIncludingPrev = maxWithCellI;
		}
		return maxIncludingPrev;
	}
}
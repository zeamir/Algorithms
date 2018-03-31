package DynamicProgramming;
//https://leetcode.com/problems/house-robber/description/
@SuppressWarnings("Duplicates")
class HouseRobber {

	public static void main(String[] args) {
		System.out.println(rob(new int[]{2, 1, 1, 2}));
		System.out.println(rob(new int[]{2}));
	}

	private static int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int maxExcludingPrev = 0;
		int maxIncludingPrev = 0;

		for (int num : nums) {
			int maxWithCurrentCell = maxExcludingPrev + num;
			if (maxWithCurrentCell < maxIncludingPrev) {
				maxWithCurrentCell = maxIncludingPrev;
			}
			maxExcludingPrev = maxIncludingPrev;
			maxIncludingPrev = maxWithCurrentCell;
		}
		return maxIncludingPrev;
	}
}
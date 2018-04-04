package DynamicProgramming;

//https://leetcode.com/problems/house-robber/description/
class HouseRobber {

	public static void main(String[] args) {
		System.out.println(rob(new int[]{2, 1, 1, 2}));
		System.out.println(rob(new int[]{2}));
	}

	private static int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int maxBeforePrev = 0;
		int maxPrev = 0;

		for (int num : nums) {
			int maxCurrent = Math.max(maxBeforePrev + num, maxPrev);
			maxBeforePrev = maxPrev;
			maxPrev = maxCurrent;
		}
		return maxPrev;
	}
}
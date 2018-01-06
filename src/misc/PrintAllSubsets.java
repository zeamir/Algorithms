package misc;

import java.util.ArrayList;
import java.util.List;

//Problem: Find all the subsets of a given set.
//		Input:
//		S = {a, b, c, d}
//		Output:
//		{}, {a} , {b}, {c}, {d}, {a,b}, {a,c},
//		{a,d}, {b,c}, {b,d}, {c,d}, {a,b,c},
//		{a,b,d}, {a,c,d}, {b,c,d}, {a,b,c,d}

public class PrintAllSubsets {
	public static void main(String[] args) {
		int[] nums = new int[]{0, 1, 2, 3, 4, 5};
		ArrayList<List<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>());

		findAllSubsets(nums, 0, subsets);

		for (int i = 0; i < subsets.size(); i++) {
			System.out.println("subset " + i + " : " + subsets.get(i).toString());
		}
	}

	private static void findAllSubsets(int[] nums, int beginIndex, ArrayList<List<Integer>> subsets) {
		if (beginIndex == nums.length) {
			return;
		}
		int value = nums[beginIndex];
		List<List<Integer>> subsetsToAdd = new ArrayList<>();
		for (List<Integer> subSet : subsets) {
			List<Integer> copyPlusThisElement = new ArrayList<>(subSet);
			copyPlusThisElement.add(value);
			subsetsToAdd.add(copyPlusThisElement);
		}
		subsets.addAll(subsetsToAdd);

		findAllSubsets(nums, beginIndex + 1, subsets);
	}
}

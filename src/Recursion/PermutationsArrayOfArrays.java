package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a list of array, return a list of arrays, each array is a combination of one element in each given array.
// Suppose the input is [[1, 2, 3], [4], [5, 6]],
// the output should be [[1, 4, 5], [1, 4, 6], [2, 4, 5], [2, 4, 6], [3, 4, 5], [3, 4, 6]].
//http://blog.gainlo.co/index.php/2017/01/05/uber-interview-questions-permutations-array-arrays/
public class PermutationsArrayOfArrays {

	public static void main(String[] args) {
		int[][] arrayOfArrays = new int[][]{{1, 2, 3}, {4}, {5, 6}};

		System.out.println("Input:");
		Arrays.stream(arrayOfArrays).forEach( val -> System.out.print(Arrays.toString(val)));
		System.out.println("\nOutput:");
		List<List<Integer>> ret = getArraysOfArrays(arrayOfArrays, 0);

		System.out.println(ret);
	}

	private static List<List<Integer>> getArraysOfArrays(int[][] arrayOfArrays, int startIndex) {
		ArrayList<List<Integer>> ret = new ArrayList<>();

		int[] currArray = arrayOfArrays[startIndex];
		if (startIndex == arrayOfArrays.length - 1) {
			for (int value : currArray) {
				List<Integer> singleValueList = new ArrayList<>();
				singleValueList.add(value);
				ret.add(singleValueList);
			}
			return ret;
		}

		List<List<Integer>> subPerms = getArraysOfArrays(arrayOfArrays, startIndex + 1);

		for (int value : currArray) {
			for (List subPerm : subPerms) {
				List<Integer> newList = new ArrayList<>();
				newList.add(value);
				newList.addAll(subPerm);
				ret.add(newList);
			}
		}
		return ret;
	}

}

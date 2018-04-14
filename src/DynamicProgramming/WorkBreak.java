package DynamicProgramming;

import java.util.Arrays;
import java.util.List;

//https://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
//Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words. See following examples for more details.
//		This is a famous Google interview question, also being asked by many other companies now a days.
//
//		Consider the following dictionary
//		{ i, like, sam, sung, samsung, mobile, ice,
//		cream, icecream, man, go, mango}
//
//		Input:  ilike
//		Output: Yes
//		The string can be segmented as "i like".
//
//		Input:  ilikesamsung
//		Output: Yes
//		The string can be segmented as "i like samsung"
//		or "i like sam sung".
public class WorkBreak {

	public static void main(String[] args) {
		WorkBreak workBreak = new WorkBreak();
		System.out.println(workBreak.wordBreak("GoodMorning", Arrays.asList("Good", "Mor", "ning")));

	}

	public boolean wordBreak(String s, List<String> wordDict) {
		int[] memo = new int[s.length()];
		java.util.Arrays.fill(memo, -1);
		return canWordBreak(s, 0, wordDict, memo);

	}

	private boolean canWordBreak(String w, int from, List<String> wordDict, int[] memo) {
		if (from >= w.length()) return true;

		if (memo[from] != -1) {
			return memo[from] == 0 ? false : true;
		}

		for (int i = from; i < w.length(); i++) {
			if (wordDict.contains(w.substring(from, i + 1))) {
				if (canWordBreak(w, i + 1, wordDict, memo)) {
					memo[from] = 1;
					return true;
				} else {
					memo[from] = 0;
				}
			}
		}
		return false;
	}

}

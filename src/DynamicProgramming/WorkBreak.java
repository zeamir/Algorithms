package DynamicProgramming;

import java.util.*;

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
		workBreak.wordBreak("YouGoodMorning", Arrays.asList("Good", "Morning", "to", "You"));

	}

	private boolean wordBreak(String s, List<String> wordDict) {
		int[] memo = new int[s.length()];
		java.util.Arrays.fill(memo, -1);
		Set<String> wordsFound = new HashSet<>();
		boolean ret = canWordBreak(s, 0, wordDict, memo, wordsFound);
		System.out.println(ret);
		System.out.println("words found: " + wordsFound);
		return ret;
	}

	private boolean canWordBreak(String w, int from, List<String> wordDict, int[] memo, Set<String> wordsFound) {
		if (from >= w.length()) return true;

		if (memo[from] != -1) {
			return memo[from] != 0;
		}

		for (int i = from; i < w.length(); i++) {
			String wordCandidate = w.substring(from, i + 1);
			if (wordDict.contains(wordCandidate)) {
				if (canWordBreak(w, i + 1, wordDict, memo, wordsFound)) {
					wordsFound.add(wordCandidate);
					memo[from] = 1;
					return true;
				} else {
					// rest of the string does not contain only words from the dictionary, so remove the candidateWord as well.
					//wordsFound.remove(wordCandidate);
					memo[from] = 0;
				}
			}
		}
		return false;
	}

}

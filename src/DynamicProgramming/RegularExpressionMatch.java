package DynamicProgramming;

public class RegularExpressionMatch {
}
//"ab"
//".*c"

class Solution {
	public boolean isMatch(String text, String pattern) {
		return innerIsMatch("", text, pattern);
	}
	private boolean innerIsMatch(String prefix, String text, String pattern) {
		print(prefix + "**text = " + text);
		print(prefix + "**pattern = " + pattern);
		if (pattern.isEmpty()) {
			print(prefix + "pattern is empty. return " + text.isEmpty());
			return text.isEmpty();
		}
		boolean first_match = (!text.isEmpty() &&
				(pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.' || pattern.charAt(0) == '*'));

		print(prefix + "first_match = " + first_match);

		if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
			print(prefix + "--> if (encountered '*' in the 2nd char");
			print(prefix + "Inside If: Recursive call 1");
			boolean match1 = innerIsMatch(prefix + "    ", text, pattern.substring(2));
			if (match1) {
				print(prefix + "return true (no need for another recursice call)");
				return true;
			}
			boolean restMatches = false;
			if (first_match) {
				print(prefix + "Inside If: Recursive call 2 sice first_match is true");
				restMatches = innerIsMatch(prefix + "    ", text.substring(1), pattern.substring(1));
			}
			boolean ret = first_match && restMatches;
			print(prefix + "return: " + ret);
			return ret;

		} else {
			boolean restMatches = false;
			print(prefix + "--> else (NOT encountered '*' in the 2nd char)");

			if (first_match) {
				print(prefix + "Another recursice call since first_match is true");
				restMatches = innerIsMatch(prefix + "    ", text.substring(1), pattern.substring(1));
			} else {
				print(prefix + "no point checking match for (" + text.substring(1) + ", " + pattern.substring(1) + ") since first match is false");
			}
			boolean ret = first_match && restMatches;
			print(prefix + "return: " + ret);
			return ret;
		}
	}

	private void print(String txt) {
		System.out.println(txt);
	}
}

package Recursion;

public class ParenthesisPerms {
	static int permsCount = 0;
	static String OPENER = "( ";
	static String CLOSER = ") ";

	public static void main(String[] args) {
		parenthesisPerms(0, 0, 3, "");
	}

	private static void parenthesisPerms(int openersUsed, int closersUsed, int available, String prefix) {
		if (openersUsed == available && closersUsed == available) {
			System.out.println(++permsCount + ") " + prefix);
			return;
		}
		if (openersUsed > available || closersUsed > available) {
			throw new IllegalStateException("should not get here!");
		}
		if (openersUsed > closersUsed) {
			// can add closer
			parenthesisPerms(openersUsed, closersUsed+1, available, prefix + CLOSER);
		}
		if (openersUsed < available) {
			// can add opener
			parenthesisPerms(openersUsed+1, closersUsed, available, prefix + OPENER);
		}

	}
}

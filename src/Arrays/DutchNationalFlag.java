package Arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
// question 5.1 on page 41 of Elements of Programming Interviews in Java

public class DutchNationalFlag {

	public enum Color {ONE, TWO, THREE};

	public static void main(String[] args) {
		List<Color> arr;
		//arr = new int[]{1, 5, 2, 2, 1, 4, 6};
		arr = Arrays.asList(Color.THREE, Color.TWO, Color.ONE, Color.ONE, Color.ONE, Color.ONE, Color.TWO, Color.THREE);
		dutchFlagPartition(1, arr);
	}

	public static void dutchFlagPartition(int pivotIndex, List<Color> arrayList) {
		//System.out.println("******************");
		System.out.println("before: " + arrayList + "; pivot index = " + pivotIndex + "; pivot value: " + arrayList.get(pivotIndex));
		if (pivotIndex > arrayList.size() - 1) {
			return;
		}
		printArray(arrayList);
		int nextLarger = arrayList.size() - 1;
		int pivot = arrayList.get(pivotIndex).ordinal();
		int nextSmaller = 0;
		while (nextSmaller <= nextLarger) {
			if (arrayList.get(nextSmaller).ordinal() > pivot) {
				Collections.swap(arrayList, nextSmaller, nextLarger--);
				printArray(arrayList);
			} else {
				nextSmaller++;
			}
		}

		System.out.println("intermediate array: " + arrayList + "; nextSmaller = " + nextSmaller + "; nextLarger = " + nextLarger);

		// now fill the pivot
		int nextPivot = nextSmaller - 1;
		int pivotCandidate = 0;
		printArray(arrayList);
		while (pivotCandidate <= nextPivot) {
			if (arrayList.get(pivotCandidate).ordinal() == pivot) {
				Collections.swap(arrayList, pivotCandidate, nextPivot--);
				printArray(arrayList);
			} else {
				pivotCandidate++;
			}
		}
		printArray(arrayList);
	}

	private static void printArray(List<Color> arrayList) {
		System.out.println("-->" + arrayList);
	}

}

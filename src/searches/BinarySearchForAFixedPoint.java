package searches;

// Given an array of n distinct integers sorted in ascending order,
// write a function that returns a Fixed Point in the array,
// if there is any Fixed Point present in array, else returns -1.
// Fixed Point in an array is an index i such that arr[i] is equal to i.
// Note that integers in array can be negative.
// Examples:
//
//		Input: arr[] = {-10, -5, 0, 3, 7}
//		Output: 3  // arr[3] == 3
//
//		Input: arr[] = {0, 2, 5, 8, 17}
//		Output: 0  // arr[0] == 0
//
//
//		Input: arr[] = {-10, -5, 3, 4, 7, 9}
//		Output: -1  // No Fixed Point

// https://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
// https://www.geeksforgeeks.org/find-fixed-point-array-duplicates-allowed/
public class BinarySearchForAFixedPoint {
	private static int cntBinarySearchFixedPointWithDuplicates = 0;
	private static int cntBinarySearchFixedPointDistinctValues = 0;

	public static void main(String[] args) {
		int[] arr = new int[10000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i * 2 + 1;
		}
		int ret = binarySearchFixedPointWithDuplicates(arr, 0, arr.length - 1);
		System.out.println("returned: " + ret);

		ret = binarySearchFixedPointDistinctValues(arr, 0, arr.length - 1);
		System.out.println("returned: " + ret);
	}

	private static int binarySearchFixedPointDistinctValues(int arr[], int low, int high) {
		System.out.println(cntBinarySearchFixedPointDistinctValues++ + ": binarySearchFixedPointDistinctValues called; low =  " + low + "; high = " + high);

		if (high >= low) {
			/* low + (high - low)/2; */
			int mid = (low + high) / 2;
			if (mid == arr[mid])
				return mid;
			if (mid > arr[mid])
				return binarySearchFixedPointDistinctValues(arr, (mid + 1), high);
			else
				return binarySearchFixedPointDistinctValues(arr, low, (mid - 1));
		}

        /* Return -1 if there is
           no Fixed Point */
		return -1;
	}

	private static int binarySearchFixedPointWithDuplicates(int arr[], int low, int high) {
		System.out.println(cntBinarySearchFixedPointWithDuplicates++ + ": binarySearchWithDuplicatesAllowed called; low =  " + low + "; high = " + high);
		if (high < low)
			return -1;

		int mid = (low + high) / 2; /*low + (high - low)/2;*/
		int midValue = arr[mid];

		if (mid == arr[mid])
			return mid;

		/* Search left */
		int leftIndex = Math.min(mid - 1, midValue);
		System.out.println("search left...");
		int left = binarySearchFixedPointWithDuplicates(arr, low, leftIndex);

		if (left >= 0)
			return left;

		/*Search right */
		System.out.println("search right...");
		int rightIndex = Math.max(mid + 1, midValue);

		int right = binarySearchFixedPointWithDuplicates(arr, rightIndex, high);

		return right;
	}

}

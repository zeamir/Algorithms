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
		int[] arr = new int[1000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (1+i/50) * 50;
		}
		arr[0] = -1;
		int ret = binarySearchFixedPointWithDuplicates(arr, 0, arr.length - 1);
		System.out.println("binarySearchFixedPointWithDuplicates returned: " + ret);

		System.out.println("linerSearchFixedPoint returned: " + linearSearchFixedPoint(arr));

		ret = binarySearchFixedPointDistinctValues(arr, 0, arr.length - 1);
		System.out.println("returned: " + ret);
	}

	private static int linearSearchFixedPoint(int arr[]) {
		int from = 0;
		int cnt = 0;
		while (from < arr.length) {
			cnt++;
			if (arr[from] == from) {
				System.out.println("**** linearSearchFixedPoint completed after " + cnt + " iterations.");
				return from;
			} else {
				from = Math.max(arr[from], from + 1);
			}
		}
		System.out.println("**** linearSearchFixedPoint completed after " + cnt + " iterations.");
		return -1;

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
		cntBinarySearchFixedPointWithDuplicates++;
		//System.out.println(cntBinarySearchFixedPointWithDuplicates + ": binarySearchWithDuplicatesAllowed called; low =  " + low + "; high = " + high);
		if (high < low)
			return -1;

		int mid = (low + high) / 2; /*low + (high - low)/2;*/
		int midValue = arr[mid];

		if (mid == arr[mid])
			return mid;

		/* Search left */
		int leftIndex = Math.min(mid - 1, midValue);//mid value is 3 and mid-1 is 4
		if (leftIndex < mid - 1) {
			System.out.println("left range reduced by: " + ((mid - 1) - leftIndex));
		}
		//System.out.println("search left...");
		int left = binarySearchFixedPointWithDuplicates(arr, low, leftIndex);

		if (left >= 0) {
			System.out.println("****  binarySearchWithDuplicatesAllowed completed after " + cntBinarySearchFixedPointWithDuplicates + " iterations. ");
			return left;
		}

		/*Search right */
		//System.out.println("search right...");
		int rightIndex = Math.max(mid + 1, midValue);
		if (rightIndex > mid + 1) {
			System.out.println("right range reduced by: " + (rightIndex - (mid - 1)));
		}

		int right = binarySearchFixedPointWithDuplicates(arr, rightIndex, high);

		System.out.println("****  binarySearchWithDuplicatesAllowed completed after " + cntBinarySearchFixedPointWithDuplicates + " iterations. ");
		return right;
	}

}

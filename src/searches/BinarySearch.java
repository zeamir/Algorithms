package searches;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String []args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 2;
		}
		int key = 2;
		assertEquals(Arrays.binarySearch(arr, key), regularBinarySearch(arr, key));

		int[] array2 = new int[] {-14, -10, 2, 108, 108, 243, 285, 285, 401};
		assertEquals(5, binarySearchForFirstIndexOfValueGreatherThen(array2, 108));
		assertEquals(2, binarySearchForFirstIndexOfValueGreatherThen(array2, -1));
		assertEquals(0, binarySearchForFirstIndexOfValueGreatherThen(array2, -30));
		assertEquals(8, binarySearchForFirstIndexOfValueGreatherThen(array2, 285));
		assertEquals(-1, binarySearchForFirstIndexOfValueGreatherThen(array2, 401));
		assertEquals(-1, binarySearchForFirstIndexOfValueGreatherThen(array2, 1000));
	}

	// regular binary search

	private static int regularBinarySearch(int[] arr, int num) {

        int beginIndex = 0;
        int endIndex = arr.length - 1;
        int midIndex;
        while (beginIndex <= endIndex) {
			midIndex = beginIndex + (endIndex - beginIndex) / 2;
            if (arr[midIndex] == num) {
                //System.out.println(num + " found at index: " + midIndex);
                return midIndex;
            } else if (arr[midIndex] > num) {
                //System.out.println("turn left at index: " + midIndex);
                endIndex = midIndex - 1;
            } else {
                //System.out.println("turn right at index: " + midIndex);
                beginIndex = midIndex + 1;
            }
        }
        return -1;
    }

	// Main Function to find fixed index
	// using binary search

	// for array:
	/**
	 *
	 * @param arr sorted array
	 * @param num value to look for
	 * @return the array idx of the first number greater then num,
	 *  or -1 if no such index (meaning all numbers are smaller or equal to num)
	 */
	private static int binarySearchForFirstIndexOfValueGreatherThen(int[] arr, int num) {

        int beginIndex = 0;
        int endIndex = arr.length - 1;
        int midIndex;
        int result = -1;
        while (beginIndex <= endIndex) {
			midIndex = beginIndex + (endIndex - beginIndex) / 2;
            if (arr[midIndex] <=  num) {
            	// continue with right side
            	beginIndex = midIndex + 1;
			} else {
            	// possible result and continue with left side
				result = midIndex;
				endIndex = midIndex - 1;
			}
        }
        return result;
    }
	// func(arr, 108) = 5 (since 243 is the the idx of the fist number greater then 108)

	// values: -14  10  2  108  108  243  285  285  401
	// index:	0    1  2   3    4    5    7    7    8
	private static void assertEquals(int expected, int actual) {
		if (expected != actual) {
			System.out.println("********* ERROR !!! expected :" + expected + "; actual: " + actual);
		} else {
			System.out.println("Assertion passed. actual: " + actual);
		}
	}
}
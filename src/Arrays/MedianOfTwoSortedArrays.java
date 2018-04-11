package Arrays;

import java.util.Arrays;

// calculate median of two arrays.
// https://www.geeksforgeeks.org/median-of-two-sorted-arrays/
public class MedianOfTwoSortedArrays {


	static int getMedian(int ar1[], int ar2[], int n) {
		int i = 0;
		int j = 0;
		int count;
		int m1 = -1, m2 = -1;

        /* Since there are 2n elements, median will
           be average of elements at index n-1 and
           n in the array obtained after merging ar1
           and ar2 */
		for (count = 0; count <= n; count++) {
			printVars(i, j, count, m1, m2);
            /* Below is to handle case where all
              elements of ar1[] are smaller than
              smallest(or first) element of ar2[] */
			if (i == n) {
				m1 = m2;
				m2 = ar2[0];
				break;
			}

            /* Below is to handle case where all
               elements of ar2[] are smaller than
               smallest(or first) element of ar1[] */
			else if (j == n) {
				m1 = m2;
				m2 = ar1[0];
				break;
			}

			if (ar1[i] < ar2[j]) {
				System.out.println("ar1[" + i + "] (" + ar1[i] + ") < ar2[" + j + "] (" + ar2[j] + ")");
				System.out.println("Setting m2 to ar1[" + i + "] (" + ar1[i] + ")");
				/* Store the prev median */
				m1 = m2;
				m2 = ar1[i];
				i++;
			} else {
				/* Store the prev median */
				System.out.println("ar1[" + i +"] (" + ar1[1] + ") >= ar2[" + j + "] (" + ar2[j] + ")");
				System.out.println("Setting m2 to ar2[" + j + "] (" + ar2[j] + ")");
				m1 = m2;
				m2 = ar2[j];
				j++;
			}
		}

		return (m1 + m2) / 2;
	}

	private static void printVars(int arr1Idx, int arr2Idx, int count, int m1, int m2) {
		System.out.println("arr1Idx = [" + arr1Idx + "], arr2Idx = [" + arr2Idx + "], count = [" + count + "], m1 = [" + m1 + "], m2 = [" + m2 + "]");
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		int ar1[] = {1, 12, 15, 26, 38};
		int ar2[] = {2, 13, 17, 30, 45};
		System.out.println("ar1 = " + Arrays.toString(ar1));
		System.out.println("ar2 = " + Arrays.toString(ar2));

		int n1 = ar1.length;
		int n2 = ar2.length;
		if (n1 == n2)
			System.out.println("Median is " +
					getMedian(ar1, ar2, n1));
		else
			System.out.println("Doesn't work for arrays are of unequal size");
	}


}

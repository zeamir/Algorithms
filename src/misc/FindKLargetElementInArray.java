package misc;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.function.BiFunction;

public class FindKLargetElementInArray {
	public static void main(String[] args) {
		int size = 1000000 * 200;
		Integer k = 20000;
		int maxNumber = 1000000;
		//int maxNumber = Integer.MAX_VALUE;

		int[] nums = buildArray(size, maxNumber);

		BiFunction<int[], Integer, Integer> kLargestUsingQP = (arr, kk) -> findKthLargestUsingPQ(arr, kk, new PriorityQueue<>());
		//BiFunction<int[], Integer, Integer> kLargestUsingCollection = (arr, kk) -> findKthLargestUsingCollection(arr, kk, new PriorityQueue<>());
		BiFunction<int[], Integer, Integer> kLargestUsingSort = FindKLargetElementInArray::findKthLargestUsingSort;

		activateAndTrackTimes("PriorityQ", nums, k, kLargestUsingQP);
		//activateAndTrackTimes("Collection", nums, k, kLargestUsingCollection);
		activateAndTrackTimes("Sorting", nums, k, kLargestUsingSort);
	}

	private static long activateAndTrackTimes(String activationMethod, int[] nums, int k, BiFunction<int[], Integer, Integer> func) {
		System.out.println("starting find largest " + activationMethod);
		long begin1 = System.nanoTime();
		//int ret1 = findKthLargestUsingPriorityQ(kLargestArray, 1000);
		Integer kLargestNumber = func.apply(nums, k);
		System.out.println(k + " largest is ****(using " + activationMethod + ")**** = " + kLargestNumber);
		long end1 = System.nanoTime();
		long ret = (end1 - begin1) / 100000L;
		System.out.println("total for " + activationMethod + " = " + ret);
		return ret;
	}

	private static int[] buildArray(int arraySize, int maxNumber) {
		int[] array = new int[arraySize];
		Random random = new Random();
		for (int i = 0; i < arraySize; i++) {
			int randNum = random.nextInt();
			if (randNum > maxNumber || randNum < 0) {
				randNum = Math.abs(randNum) % maxNumber;
			}
			array[i] = randNum;
		}
		return array;
	}


	private static int findKthLargestUsingSort(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	private static int findKthLargestUsingPQ(int[] nums, int k, PriorityQueue<Integer> kLargest) {
		int minK = Integer.MAX_VALUE;
		int numberOfRemovals = 0;

		for (int value : nums) {
			//System.out.println("value is: " + value + "; current kLargest:" + kLargest.toString());
			if (kLargest.size() < k) {
				//System.out.println("adding since size is smaller then k: " + value);
				kLargest.add(value);
				if (minK > value) {
					//System.out.println("new minK: " + value);
					minK = value;
				}
				continue;
			}

			if (value >= minK) {
				// need to remove the curent min and set the minK to value
				//System.out.println("adding: " + value);
				kLargest.add(value);

				//kLargest.remove(minK);
				kLargest.poll();

				numberOfRemovals++;

				//minK = kLargest.iterator().next();
				minK = kLargest.peek();

				//System.out.println("new minK : " + minK);
			}
		}
		System.out.println("numberOfRemovals = " + numberOfRemovals);
		return minK;
	}

//	private static int findKthLargestUsingCollection(int[] nums, int k, Collection<Integer> kLargest) {
//		int minK = Integer.MAX_VALUE;
//		int numberOfRemovals = 0;
//
//		for (int value : nums) {
//			//System.out.println("value is: " + value + "; current kLargest:" + kLargest.toString());
//			if (kLargest.size() < k) {
//				//System.out.println("adding since size is smaller then k: " + value);
//				kLargest.add(value);
//				if (minK > value) {
//					//System.out.println("new minK: " + value);
//					minK = value;
//				}
//				continue;
//			}
//
//			if (value >= minK) {
//				// need to remove the curent min and set the minK to value
//				//System.out.println("adding: " + value);
//				kLargest.add(value);
//				kLargest.remove(minK);
//				numberOfRemovals++;
//				minK = kLargest.iterator().next();
//				//System.out.println("new minK : " + minK);
//			}
//		}
//		System.out.println("numberOfRemovals = " + numberOfRemovals);
//		return minK;
//	}

}

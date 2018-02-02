package DynamicProgramming;

import java.util.Arrays;

/**
 * You are a renowned thief who has recently
 switched from stealing precious metals to stealing
 cakes because of the insane pro􀃒t margins. You
 end up hitting the jackpot, breaking into the
 world's largest privately owned stock of cakes—
 the vault of the Queen of England.

 While Queen Elizabeth has a limited number of types of cake, she has an unlimited
 supply of each type.

 Each type of cake has a weight and a value, stored in a tuple with two indices:
 0. An integer representing the weight of the cake in kilograms
 1. An integer representing the monetary value of the cake in British pounds

 For example:
 You brought a duffel bag that can hold limited weight, and you want to make off with
 the most valuable haul possible.
 Write a function max_duffel_bag_value() that takes a list of cake type tuples and a
 weight capacity, and returns the maximum monetary value the du􀃗el bag can
 hold.
 # weighs 7 kilograms and has a value of 160 pounds
 (7, 160)
 # weighs 3 kilograms and has a value of 90 pounds
 (3, 90)
 For example:
 Weights and values may be any non-negative integer. Yes, it's weird to think about cakes that weigh
 nothing or duffel bags that can't hold anything. But we're not just super mastermind criminals—we're
 also meticulous about keeping our algorithms flexible and comprehensive.
 */

public class CakeThief {

	private static long calls = 0;
	private static long cachedCalls = 0;
	private static long nonCachedCalls = 0;

	public static void main(String[] args) {

		CakeType[] cakeTypes = new CakeType[]{
				new CakeType(7, 160),
				new CakeType(3, 90),
				new CakeType(2, 15)
		};

		int max = maxDuffelBagCakeValue(cakeTypes, 20);
		System.out.println("max value = " + max);
		System.out.println("calls          = " + calls);
		System.out.println("cachedCalls    = " + cachedCalls);
		System.out.println("nonCachedCalls = " + nonCachedCalls);
	}


	private static int maxDuffelBagCakeValue(CakeType[] cakeTypes, int capacity) {

		int[][] mem = new int[cakeTypes.length][capacity+1];
		for (int[] aMem : mem) {
			Arrays.fill(aMem, -1);
		}

		return calcMaxValue(0, cakeTypes, capacity, mem);
	}

	private static int calcMaxValue(int firstTypeIndex, CakeType[] cakeTypes, int remainingCapacity, int[][] mem) {
		calls++;
		int retMax = 0;

		if (firstTypeIndex > cakeTypes.length - 1 || remainingCapacity < 0) {
			return 0;
		}
		if (mem[firstTypeIndex][remainingCapacity] >= 0) {
			cachedCalls++;
			return mem[firstTypeIndex][remainingCapacity];
		}

		nonCachedCalls++;

		for (int i = firstTypeIndex; i < cakeTypes.length; i++) {
			CakeType cakeType = cakeTypes[i];
			if (remainingCapacity - cakeType.weight < 0) {
				mem[firstTypeIndex][remainingCapacity] = 0;
				return 0;
			}
			int totalWithCake = cakeType.value + calcMaxValue(firstTypeIndex, cakeTypes, remainingCapacity - cakeType.weight, mem);

			int totalWithoutCake = calcMaxValue(firstTypeIndex + 1, cakeTypes, remainingCapacity, mem);
			// need to store that
			int maxTotalSoFar = Math.max(totalWithCake, totalWithoutCake);
			if (maxTotalSoFar > retMax) {
				retMax = maxTotalSoFar;
			}

		}
		mem[firstTypeIndex][remainingCapacity] = retMax;
		return retMax;
	}


	static class CakeType {
		int weight;
		int value;

		CakeType(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
}

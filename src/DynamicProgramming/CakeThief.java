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

		assertValue(maxDuffelBagCakeValue(cakeTypes, 20), 555);
		System.out.println("calls          = " + calls);
		System.out.println("cachedCalls    = " + cachedCalls);
		System.out.println("nonCachedCalls = " + nonCachedCalls);
	}

	private static void assertValue(Object actual, Object expected) {
		if (actual.equals(expected)) {
			System.out.println("assertion OK");
		} else {
			System.out.println("assertion failed: actual: " + actual.toString() + " ; expected: " + expected.toString());
		}

	}


	private static int maxDuffelBagCakeValue(CakeType[] cakeTypes, int capacity) {

		int[] mem = new int[capacity + 1];
		Arrays.fill(mem, -1);

		int ret = calcMaxValue(cakeTypes, capacity, mem);

		System.out.println("max cakes value = " + ret);

		return ret;
	}

	private static int calcMaxValue(CakeType[] cakeTypes, int remainingCapacity, int[] mem) {
		calls++;
		int retMax = 0;

		if (mem[remainingCapacity] >= 0) {
			cachedCalls++;
			return mem[remainingCapacity];

		}
		nonCachedCalls++;

		for (CakeType cakeType : cakeTypes) {
			if (remainingCapacity - cakeType.weight < 0) {
				continue;
			}
			int totalWithCake = cakeType.value + calcMaxValue(cakeTypes, remainingCapacity - cakeType.weight, mem);

			if (totalWithCake > retMax) {
				retMax = totalWithCake;
			}

		}
		mem[remainingCapacity] = retMax;
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

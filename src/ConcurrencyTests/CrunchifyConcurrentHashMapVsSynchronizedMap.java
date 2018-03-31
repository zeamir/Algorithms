package ConcurrencyTests;
 
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
/**
 * @author Crunchify.com
 *
 */
 
public class CrunchifyConcurrentHashMapVsSynchronizedMap {
 
	public final static int THREAD_POOL_SIZE = 5;
 
	public static Map<String, Integer> crunchifyHashTableObject = null;
	public static Map<String, Integer> crunchifySynchronizedMapObject = null;
	public static Map<String, Integer> crunchifyConcurrentHashMapObject = null;
 
	static void testAccessTimes() {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

		ArrayList<Integer> randNumbers = new ArrayList<>(1000);
		for (int i = 0; i < 10000000; i++) {
			Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);
			// Put value
			map.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
		}

		System.out.println("starting");
		long startTime = System.nanoTime();
		for (Integer num: randNumbers) {
			map.get(randNumbers);
		}
		long endTime = System.nanoTime();
		long totalTime = (endTime - startTime) / 1000000L;
		System.out.println("## Entries retrieved in " + totalTime + " ms");


	}
	public static void main(String[] args) throws InterruptedException {

		testAccessTimes();
		// Test with Hashtable Object
		crunchifyHashTableObject = new Hashtable<String, Integer>();
		crunchifyPerformTest(crunchifyHashTableObject);

		// Test with synchronizedMap Object
		crunchifySynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
		crunchifyPerformTest(crunchifySynchronizedMapObject);
 
		// Test with ConcurrentHashMap Object
		crunchifyConcurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
		crunchifyPerformTest(crunchifyConcurrentHashMapObject);

	}
 
	public static void crunchifyPerformTest(final Map<String, Integer> crunchifyThreads) throws InterruptedException {
 
		System.out.println("Test started for: " + crunchifyThreads.getClass());
		long averageTime = 0;
		for (int i = 0; i < 5; i++) {
 
			long startTime = System.nanoTime();
			ExecutorService crunchifyExServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
 
			for (int j = 0; j < THREAD_POOL_SIZE; j++) {
				crunchifyExServer.execute(new Runnable() {
					@SuppressWarnings("unused")
					@Override
					public void run() {
 
						for (int i = 0; i < 500000; i++) {
							Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);
 
							// Retrieve value. We are not using it anywhere
							Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));
 
							// Put value
							crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
						}
					}
				});
			}
 
			// Make sure executor stops
			crunchifyExServer.shutdown();
 
			// Blocks until all tasks have completed execution after a shutdown request
			crunchifyExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
 
			long entTime = System.nanoTime();
			long totalTime = (entTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
		}
		System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
	}
}
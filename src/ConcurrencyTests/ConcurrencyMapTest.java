package ConcurrencyTests;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Crunchify.com
 */

public class ConcurrencyMapTest {

    public final static int THREAD_POOL_SIZE = 50;

    public static Map<String, Integer> crunchifySynchronizedMapObject = null;
    public static Map<String, Integer> crunchifyConcurrentHashMapObject = null;

    public static void main(String[] args) throws InterruptedException {
        // Test with Hashtable Object

        // Test with synchronizedMap Object
        HashMap<String, Integer> myHashMap = new HashMap<>();
        crunchifySynchronizedMapObject = Collections.synchronizedMap(myHashMap);
        System.out.println("running concurrency test for myHashMap");
        crunchifyPerformTest(myHashMap);
        //System.out.println("running concurrency test for synchronized map");
        //crunchifyPerformTest(crunchifySynchronizedMapObject);

        // Test with ConcurrentHashMap Object
        //crunchifyConcurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
        //crunchifyPerformTest(crunchifyConcurrentHashMapObject);

    }

    public static void crunchifyPerformTest(final Map<String, Integer> crunchifyThreads) throws InterruptedException {

        System.out.println("Test started for: " + crunchifyThreads.getClass());
        long averageTime = 0;
        long startTime = System.nanoTime();
        ExecutorService crunchifyExServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int j = 0; j < THREAD_POOL_SIZE; j++) {
            crunchifyExServer.execute(new Runnable() {
                @SuppressWarnings("unused")
                @Override
                public void run() {

                    for (int i = 0; i < 5000000; i++) {
                        Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);

                        // Put value
                        crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);

                        // Retrieve value. We are not using it anywhere
                        Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));

                        crunchifyThreads.remove(String.valueOf(crunchifyRandomNumber));


                    }
                    System.out.println("thread id: " + Thread.currentThread().getId() + " done !");

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
        System.out.println("thread id: " + Thread.currentThread().getId() + " >> 2500K entried added/retrieved in " + totalTime + " ms");


        System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }
}
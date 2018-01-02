package searches;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String []args) {
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 2;
        }        
        
        System.out.println("found = " + binarySearch(arr, 997));
    }
    private static boolean binarySearch(int[] arr, int num) {
        
        int beginIndex = 0;
        int endIndex = arr.length - 1;
        int midIndex;
        while (beginIndex <= endIndex) {
            midIndex = (beginIndex + endIndex) / 2;
            if (arr[midIndex] == num) {
                System.out.println(num + " found at index: " + midIndex);
                return true;
            } else if (arr[midIndex] > num) {
                System.out.println("turn left at index: " + midIndex);
                endIndex = midIndex - 1;
            } else {
                System.out.println("turn right at index: " + midIndex);
                beginIndex = midIndex + 1;
            }
        }
        return false;
    }
    
}
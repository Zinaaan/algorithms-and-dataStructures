package algorithms.TopKElements;

import java.util.Arrays;
import java.util.PriorityQueue;

class KthLargest {

    // constructor to initialize topKHeap and add values in it
    /**
     * Add method: add number to head may cost O(log n) time
     * returnKthLargest: Constant time
     * <p>
     * step1: Initialize a k size min-Heap so that we can get the kth largest number from the head
     * step2: Keep the size of min-heap equals to k, if not, compare the current number with the head number of min-heap and put the largest number in the min-heap
     */
    PriorityQueue<Integer> minHeapForNumber;
    int max;

    public KthLargest(int k, int[] nums) {
        // Write your code Here
        minHeapForNumber = new PriorityQueue<>();
        for (int num : nums) {
            minHeapForNumber.offer(num);
        }
        while (minHeapForNumber.size() > k) {
            minHeapForNumber.poll();
        }
        max = k;
    }

    // adds element in the topKHeap
    public int add(int val) {
        // Your code will replace this placeholder return statement
        if (minHeapForNumber.size() < max) {
            minHeapForNumber.offer(val);
        } else {
            if (!minHeapForNumber.isEmpty() && val > minHeapForNumber.peek()) {
                minHeapForNumber.poll();
                minHeapForNumber.offer(val);
            }
        }
        return returnKthLargest();
    }

    // returns kth largest element from topKHeap
    public int returnKthLargest() {
        if (minHeapForNumber.isEmpty()) {
            return -1;
        }
        return minHeapForNumber.peek();
    }

    public static void main(String args[]) {
        int[] nums = {3, 6, 9, 10};
        int[] temp = {3, 6, 9, 10};
        System.out.println("Initial stream: " + Arrays.toString(nums));
        System.out.println("k: " + 3);
        KthLargest kLargest = new KthLargest(3, nums);
        int[] val = {4, 7, 10, 8, 15};
        System.out.println();
        for (int value : val) {
            System.out.println("\tAdding a new number " + value + " to the stream");
            temp = Arrays.copyOf(temp, temp.length + 1);
            temp[temp.length - 1] = value;
            System.out.println("\t\tNumber stream: " + Arrays.toString(temp));
            System.out.println("\tKth largest element in the stream: " + kLargest.add(value));
        }
    }
}
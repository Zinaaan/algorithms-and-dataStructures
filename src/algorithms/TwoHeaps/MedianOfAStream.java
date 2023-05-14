package algorithms.TwoHeaps;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianOfAStream {
    /**
     * step1: Initialize a max-heap to store the number which smaller than the median number
     * step2: Initialize a min-heap to store the number which greater than the median number
     * step3: We can find the greatest number which smaller than median number in the top of max-heap,
     * and find the smallest number which greater than median number in the top of min-heap
     * step4: If the length of total head is odd, the median number is top of max-heap
     * Else the median number is the mean of max-heap top number and min-heap top number
     */
    PriorityQueue<Integer> maxHeapForSmaller;
    PriorityQueue<Integer> minHeapForGreater;
    int capacity;

    public MedianOfAStream() {
        //1
        maxHeapForSmaller = new PriorityQueue<>(Collections.reverseOrder());
        //2, 3
        minHeapForGreater = new PriorityQueue<>();
        capacity = 0;
    }

    public void insertNum(int num) {
        // Write your code here
        capacity++;
        if (maxHeapForSmaller.isEmpty() || maxHeapForSmaller.peek() >= num) {
            maxHeapForSmaller.offer(num);
        } else {
            minHeapForGreater.offer(num);
        }
        if (maxHeapForSmaller.size() > minHeapForGreater.size() + 1) {
            minHeapForGreater.offer(maxHeapForSmaller.poll());
        } else if (maxHeapForSmaller.size() < minHeapForGreater.size()) {
            maxHeapForSmaller.offer(minHeapForGreater.poll());
        }

    }

    public double findMedian() {
        if (capacity == 0) {
            return 0.0;
        }
        if (maxHeapForSmaller.size() == minHeapForGreater.size()) {
            return maxHeapForSmaller.peek() / 2.0 + minHeapForGreater.peek() / 2.0;
        }
        return maxHeapForSmaller.peek();
    }

    public static void main(String[] args) {
        // Driver code
//        int[] nums = {35, 22, 30, 25, 1};
        int[] nums = {1, 2, 3};
        MedianOfAStream medianOfAges;
        for (int i = 0; i < nums.length; i++) {
            System.out.print(i + 1);
            System.out.print(".\tData stream: [");
            medianOfAges = new MedianOfAStream();
            for (int j = 0; j <= i; j++) {
                System.out.print(nums[j]);
                if (j != i) {
                    System.out.print(", ");
                }
                medianOfAges.insertNum(nums[j]);
            }
            System.out.println("]");
            System.out.println("\t\tThe median for the given numbers is: " + medianOfAges.findMedian());
        }

    }
}
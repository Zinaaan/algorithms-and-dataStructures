package algorithms.TopKElements;

import java.util.PriorityQueue;

public class KthLargestElement{
   /**
      time: O(nlogk), space: O(k)
      step1: Initialize a min-heap to store each number of the input list
      step2: If the size of min-heap is greater than k, than poll the root of the min-heap
      step3: Finally, the root of the min-heap is the top k number
   */
   public static int findKthLargest(int[] arr, int k) {
      if(arr.length < 2){
         return arr.length == 0 ? 0 : arr[0];
      }
      PriorityQueue<Integer> minHeapForLargest = new PriorityQueue<>();
      for(int num : arr){
         minHeapForLargest.offer(num);
         if(minHeapForLargest.size() > k){
            minHeapForLargest.poll();
         }
      }

      return minHeapForLargest.peek();
   }
}
package algorithms.TopKElements;

import java.util.*;

public class FrequentElements{
    /**
        time: O(n) + O(klogn) = O(klogn)
        step1: Initialize a HashMap to store the frequency of number(key: number, value: frequency)
        step2: Initialize a maxHeap to store the frequency of number so that we can find the most frequently
               occuring number from the root
    */
    public static List<Integer> topKFrequent(int[] arr, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for(int num : arr){
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> maxHeapForFrequency = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for(Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()){
            maxHeapForFrequency.offer(new int[]{entry.getValue(), entry.getKey()});
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++){
            ans.add(maxHeapForFrequency.poll()[1]);
        }

        return ans;
    }
}
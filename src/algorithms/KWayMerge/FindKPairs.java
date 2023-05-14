package algorithms.KWayMerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class FindKPairs {
    public static List<List<Integer>> kSmallestPairs(int[] list1, int[] list2, int target) {
        PriorityQueue<int[]> minHeapForSum = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < list1.length; i++) {
            //int[sum, i, j(index of list2)]
            minHeapForSum.offer(new int[]{list1[i] + list2[0], i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();
        int count = 0;
        while (!minHeapForSum.isEmpty() && count < target) {
            int[] minSumArray = minHeapForSum.poll();
            int indexOfList1 = minSumArray[1];
            int indexOfList2 = minSumArray[2];
            ans.add(Arrays.asList(list1[indexOfList1], list2[indexOfList2]));
            int nextOfList2 = indexOfList2 + 1;
            if (list2.length > nextOfList2) {
                minHeapForSum.offer(new int[]{list1[indexOfList1] + list2[nextOfList2], indexOfList1, nextOfList2});
            }
            count++;
        }

        return ans;
    }

    public static void main(String[] args) {

        int[][] list1 = {{2, 8, 9},
                {1, 2, 300},
                {1, 1, 2},
                {4, 6},
                {4, 7, 9},
                {1, 1, 2}
        };

        int[][] list2 = {
                {1, 3, 6},
                {1, 11, 20, 35, 300},
                {1, 2, 3},
                {2, 3},
                {4, 7, 9},
                {1}
        };
        int[] k = {9, 30, 1, 2, 5, 4};
        for (int i = 0; i < k.length; i++) {
            List<List<Integer>> result = kSmallestPairs(list1[i], list2[i], k[i]);
            System.out.print(i + 1);
            System.out.println(".\tInput lists: " + Arrays.toString(list1[i]) + ", " + Arrays.toString(list2[i]));
            System.out.println("\tK = " + k[i]);
            System.out.print("\tPairs with smallest sum are: " + result);
            System.out.println("\n");
        }
    }
}
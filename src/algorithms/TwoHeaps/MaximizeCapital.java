package algorithms.TwoHeaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximizeCapital {
    /**
     * step1: Initialize a min-heap for capitals, override the comparator by compare adjacent capitals
     * step2: Traverse capitals and put [capitals[i], i] into min-heap
     * step3: Initialize a max-heap for profits, override the comparator by compare adjacent profits
     * step4: Loop from 0 to k and compare current capitals with the capital in min-heap
     * step5: If top of min-heap <= current capitals, push the profit in current index to the max-heap
     * step6: If max-heap is not null, add it to the answer
     */
    public static int maximumCapital(int c, int k, int[] capitals, int[] profits) {
        //step1
        PriorityQueue<int[]> minCapitalsHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        //step2
        for (int i = 0; i < capitals.length; i++) {
            minCapitalsHeap.offer(new int[]{capitals[i], i});
        }
        //step3
        PriorityQueue<int[]> maxProfitsHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int currentProject = 0;
        //step4
        while (currentProject < k) {
            //step5
            while (!minCapitalsHeap.isEmpty() && minCapitalsHeap.peek()[0] <= c) {
                int[] minCapital = minCapitalsHeap.poll();
                maxProfitsHeap.offer(new int[]{profits[minCapital[1]], minCapital[1]});
            }
            if (maxProfitsHeap.isEmpty()) {
                break;
            }
            c += maxProfitsHeap.poll()[0];
            currentProject++;
        }
        return currentProject;
    }

    public static void main(String[] args) {
        int[] c = { 0, 1, 2, 1, 7, 2 };
        int[] k = { 1, 2, 3, 3, 2, 4 };
        int[][] capitals = {
                {1, 1, 2},
                {1, 2, 2, 3},
                {1, 3, 4, 5, 6},
                {1, 2, 3, 4},
                {6, 7, 8, 10},
                {2, 3, 5, 6, 8, 12}
        };
        int[][] profits = {
                {1, 2, 3},
                {2, 4, 6, 8},
                {1, 2, 3, 4, 5},
                {1, 3, 5, 7},
                {4, 8, 12, 14},
                {1, 2, 5, 6, 8, 9}
        };
        for (int i = 0; i<k.length; i++) {
            System.out.println((i + 1) + ".\tProject capital requirements: " + Arrays.toString(capitals[i]));
            System.out.println("\tProject expected profits: " + Arrays.toString(profits[i]));
            System.out.println("\tNumber of projects: " + k[i]);
            System.out.println("\tStart-up capital: " + c[i]);
            System.out.println("\n\tMaximum Capital earned: " + maximumCapital(c[i], k[i], capitals[i], profits[i]));
        }
    }
}
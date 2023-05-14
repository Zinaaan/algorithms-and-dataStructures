package algorithms.TwoHeaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SlidingWindow {
    /**
     * time: O(n*logn), space: O(k)
     * step1: Initialize a Deque as a sliding window
     * step2: Sorted the first window number and get the median number
     * step3: Since the window number is sorted, remove the first number
     * and use binary search to find current number in which position in o(logn) time
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> medianList = new ArrayList<>();
        for (int i = 0; i <= nums.length - k; i++) {
            int right = i;
            List<Integer> window = new ArrayList<>();
            while (right < k + i) {
                window.add(nums[right]);
                right++;
            }
            Collections.sort(window);
            double mid = getMidFromWindow(k, window);
            medianList.add(mid);
        }
        double[] ans = new double[medianList.size()];
        for (int i = 0; i < medianList.size(); i++) {
            ans[i] = medianList.get(i);
        }
        return ans;
    }

    public static double getMidFromWindow(int k, List<Integer> window) {
        double median;
        if (k % 2 != 0) {
            median = window.get(k / 2);
        } else {
            median = window.get((k / 2) - 1) / 2.0 + window.get(k / 2) / 2.0;
        }
        return median;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3, -1, -3, 5, 3, 6, 7}, {1, 2}, {4, 7, 2, 21}, {22, 23, 24, 56, 76, 43, 121, 1, 2, 0, 0, 2, 3, 5}, {1, 1, 1, 1, 1}};
        int[] k = {3, 1, 2, 5, 2};
        for (int i = 0; i < k.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput array =" + Arrays.toString(arr[i]) + ", k = " + k[i]);
            double[] output = medianSlidingWindow(arr[i], k[i]);
            System.out.println("\tMedians =" + Arrays.toString(output));
        }
    }
}
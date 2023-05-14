package algorithms.SlidingWindows;

import java.util.Arrays;

class MinimumSubArraySum {
    /**
     * step1: Initialize a sum
     * step2: Traverse arr and sum the each element
     * - If sum greater than target, adjust the left index until the sum smaller than or equal to the target
     * - Else if sum smaller than target, add to the sum
     * - Else if sum equals to target, update the max length of subarray
     */
    public static int minSubArrayLen(int target, int[] arr) {
        int length = arr.length;
        if (length == 0) {
            return 0;
        }
        //step1
        int sum = 0, ans = Integer.MAX_VALUE, left = 0, right = 0;
        while (right < length) {
            sum += arr[right];
            //step2 - 1
            while(sum > target){
                sum -= arr[left];
                left++;
            }
            //step2 - 3
            if(sum == target){
                ans = Math.min(ans, right - left + 1);
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int[] target = {7, 4, 11, 10, 5, 15};
        int[][] inputArr = {
                {2, 3, 1, 2, 4, 3},
                {1, 4, 4},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4},
                {1, 2, 1, 3},
                {5, 4, 9, 8, 11, 3, 7, 12, 15, 44}
        };
        for (int i = 0; i < target.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tminSubarraylen(" + target[i] + ", " + Arrays.toString(inputArr[i]) + ") : " + minSubArrayLen(target[i], inputArr[i]));
//            System.out.println(PrintHyphens.repeat("-", 100));
        }
    }
}
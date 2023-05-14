package algorithms.BinarySearch;

import java.util.Arrays;
import java.util.List;

public class SearchInRotatedSortedArray {
    /**
     * O(log n)
     * Binary search the input list
     * we can divide the input into two part, the left part is rotate, the right part is not rotate
     * <p>
     * case1: If we are in the left part now, it means that the middle number is greater than the left number
     * - If "target < middle number && target >= left number", right = mid - 1;
     * - Else left = mid + 1
     * case2: If we are in the right part now, it means that the middle number is smaller than the right number
     * - If "target > middle number && target <= right number", left = mid + 1;
     * - Else right = mid - 1;
     * case3: If the middle number equal to target, return the middle index
     * <p>
     * [7, 8, 9, 1, 2, 3, 4, 5, 6, 7]
     *
     * @param nums
     * @param target
     * @return
     */
    //4, 5, 6, 1, 2, 3
    public static int binarySearchRotated(List<Integer> nums, int target) {
        int length = nums.size();
        if (length == 0) {
            return -1;
        }
        int left = 0, right = length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums.get(mid) == target) {
                return mid;
            }
            if (nums.get(mid) >= nums.get(left)) {
                if (target < nums.get(mid) && target >= nums.get(left)) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums.get(mid) && target <= nums.get(right)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String args[]) {
        List<Integer> targetList = Arrays.asList(3, 6, 3, 6);
        List<List<Integer>> numList = Arrays.asList(Arrays.asList(6, 7, 1, 2, 3, 4, 5), Arrays.asList(6, 7, 1, 2, 3, 4, 5), Arrays.asList(4, 5, 6, 1, 2, 3), Arrays.asList(4, 5, 6, 1, 2, 3));
        for (int i = 0; i < targetList.size(); i++) {
            System.out.println((i + 1) + ".\tSorted array: " + numList.get(i) + "\n\ttarget " + targetList.get(i) + " found at index " + binarySearchRotated(numList.get(i), targetList.get(i)));
        }
    }
}
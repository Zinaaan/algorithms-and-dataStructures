package algorithms.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindClosestElements {
    /**
     * case1: If x <= arr[0], the closest elements must in [arr[0]...arr[k]]
     * case2: Else if x >= arr[length - 1], the closest elements must in [arr[length - k]...arr[length - 1]]
     * case3: the x is in [arr[0]......arr[length - 1]], use binary search to find the position of x
     * -If k is odd, add the number in [(k - 1) / 2, mid - 1], mid and [mid + 1...(k - 1) / 2] to answer
     * -Else k is even, add the number in [k / 2, mid - 1], mid and [mid + 1...(k - 1) / 2] to answer
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int length = arr.length;
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = length - 1;
        if (x <= arr[left]) {
            for (int i = 0; i < k; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
        if (x >= arr[right]) {
            for (int i = length - k; i < length; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int leftIndex = left - 1, rightIndex = left;
        while (rightIndex - leftIndex - 1 < k) {
            if (leftIndex < 0) {
                rightIndex++;
                continue;
            }
            if (rightIndex == length || (Math.abs(arr[leftIndex] - x) <= Math.abs(arr[rightIndex] - x))) {
                leftIndex--;
            } else {
                rightIndex++;
            }
        }
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {

        int[][] inputs = {
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5},
                {1, 2, 4, 5, 6},
                {1, 2, 3, 4, 5, 10},
                {1, 1, 1, 10, 10, 10}
        };
        int[] k = {4, 4, 2, 3, 1};
        int[] x = {4, 3, 10, -5, 9};
        for (int i = 0; i < k.length; i++) {
            List<Integer> kList = findClosestElements(inputs[i], k[i], x[i]);
            System.out.print(i + 1);
            System.out.println(".\tThe " + k[i] + " closest elements for the number " + x[i] + " in the array " + Arrays.toString(inputs[i]) + " are: ");
            System.out.print("\t[");
            for (int j = 0; j < k[i] - 1; j++) {
                System.out.print(kList.get(j) + ", ");
            }
            System.out.println(kList.get(k[i] - 1) + "]");
        }

    }
}
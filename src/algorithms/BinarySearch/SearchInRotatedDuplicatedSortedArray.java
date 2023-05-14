package algorithms.BinarySearch;

public class SearchInRotatedDuplicatedSortedArray {
    /**
     * step1: The rotated array contains two ascending part,
     * the number of the left part is greater than or equal to the number of the right part
     * <p>
     * step2: So we need to excluded the duplicate number if left == mid == right, like[1, 0, 1, 1, 1],
     * so that we can use the binary search to find the target exist or not
     */
    public static boolean SearchInRotatedDuplicatedSortedArray(int[] arr, int target) {
        int length = arr.length;
        if (length < 2) {
            return length != 0 && arr[0] == target;
        }
        int left = 0, right = length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                return true;
            }
            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                left++;
                right--;
                //in the left side
            } else if (arr[left] <= arr[mid]) {
                if (target < arr[mid] && target >= arr[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target < arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
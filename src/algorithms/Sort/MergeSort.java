package algorithms.Sort;

/**
 * @author lzn
 * @date 2022/11/03 10:40
 * @description 归并排序
 */
public class MergeSort {
    public int[] sortArray(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length];
        mergeSort(nums, 0, length - 1, temp);
        return nums;
    }

    /**
     * @param nums:  original array
     * @param start: the left index of array
     * @param end:   the right index of array
     * @param temp:  the temporary array used to merge two ordered array to avoid multiple creation and destruction
     * @description mergeSort the array from start to end
     * @date 2022/11/3 10:53
     */
    private void mergeSort(int[] nums, int start, int end, int[] temp) {
        //if start equals end , stop recurse
        if (start == end) {
            return;
        }
        //get the mid index
        int mid = start + ((end - start) >> 1);
        //divide the array to [start...mid]
        mergeSort(nums, start, mid, temp);
        //divide the array to [mid+1,end];
        mergeSort(nums, mid + 1, end, temp);
        //end of the nums divide,start merge
        mergeOfTwoSortedArray(nums, start, mid, end, temp);
    }

    /**
     * @param nums:  original array
     * @param start: the left index of array
     * @param mid:   the mid index of array
     * @param end:   the right index of array
     * @param temp:  the temporary array used to merge two ordered array to avoid multiple creation and destruction
     * @description merge two sorted array: copy the array to temp,compare the element in temp array and merge to the original array
     * @date 2022/11/3 11:15
     */
    private void mergeOfTwoSortedArray(int[] nums, int start, int mid, int end, int[] temp) {
        for (int i = start; i <= end; i++) {
            temp[i] = nums[i];
        }
        int i = start, j = mid + 1;
        for (int index = start; index <= end; index++) {
            if (i == mid + 1) {
                nums[index] = temp[j++];
            } else if (j == end + 1) {
                nums[index] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[index] = temp[i++];
            } else {
                nums[index] = temp[j++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
//        int[] nums = new int[]{8, 6, 7, 2, 3, 5, 4, 1};
        int[] nums = new int[]{1, 2, 3, 4, -3, 2, 5};
        mergeSort.sortArray(nums);
    }
}

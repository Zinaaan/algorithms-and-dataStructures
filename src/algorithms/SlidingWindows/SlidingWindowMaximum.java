package algorithms.SlidingWindows;

import java.util.*;

class SlidingWindowMaximum {

    /**
     * Key points:
     *          1. Choose a structure which can remove number from head and add number to the end in O(1) time complexity---Deque
     *          2. The int number should keep mono decreasing to ensure that the greatest number is in the head of the window, smallest number is in the end of the window
     *          3. Compare the current number in the list and last number in the window, remove the smaller number from the window
     *          4. Only store the index of nums in window
     *
     * step1: compare the current element of the window with the last element of the window
     * step2: remove the index of the smaller element from the window
     * step3: add the index of the current element at the end of the queue
     */
    public static List<Integer> findMaxSlidingWindow(List<Integer> nums, int windowSize) {
        List<Integer> result = new ArrayList<>();
        int length = nums.size();
        if (length == 0 || windowSize == 0) {
            return result;
        }
        if (windowSize > length) {
            windowSize = length;
        }
        Deque<Integer> window = new ArrayDeque<>();
        int index = 0;
        while (index < windowSize) {
            //if current value greater than the number in window, remove the number in window to ensure
            //the number in the end of window is greatest.
            while (!window.isEmpty() && nums.get(index) >= nums.get(window.peekLast())) {
                window.removeLast();
            }
            //add the current index to window
            window.addLast(index);
            index++;
        }
        result.add(nums.get(window.peekFirst()));
        for (int i = windowSize; i < length; i++) {
            while (!window.isEmpty() && nums.get(i) >= nums.get(window.peekLast())) {
                window.removeLast();
            }
            System.out.println(i - windowSize);
            //remove greatest number(the first number) of the window
            if(!window.isEmpty() && window.peekFirst() <= i - windowSize){
                window.removeFirst();
            }
            //add the current index to the end
            window.addLast(i);
            //the head number of window is always greatest
            result.add(nums.get(window.peekFirst()));
        }
        return result;
    }

    public static void main(String args[]) {
        List<Integer> targetList = Arrays.asList(2, 3, 3, 3, 2, 4, 3, 2, 3, 18);
        List<List<Integer>> numLists = Arrays.asList(
//				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                Arrays.asList(10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67)
//                Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
//                Arrays.asList(1, 5, 8, 10, 10, 10, 12, 14, 15, 19, 19, 19, 17, 14, 13, 12, 12, 12, 14, 18, 22, 26, 26, 26, 28, 29, 30),
//                Arrays.asList(10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67),
//                Arrays.asList(4, 5, 6, 1, 2, 3),
//                Arrays.asList(9, 5, 3, 1, 6, 3),
//                Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16),
//                Arrays.asList(-1, -1, -2, -4, -6, -7),
//                Arrays.asList(4, 4, 4, 4, 4, 4)
        );
        for (int i = 0; i < numLists.size(); i++) {
            System.out.println(i + 1 + ".\tOriginal array:\t" + numLists.get(i));
            System.out.println("\tWindow size:\t" + targetList.get(i));
            System.out.println("\n\tMax:\t" + findMaxSlidingWindow(numLists.get(i), targetList.get(i)));
//			System.out.println(PrintHyphens.repeat("-", 100));
        }
    }
}
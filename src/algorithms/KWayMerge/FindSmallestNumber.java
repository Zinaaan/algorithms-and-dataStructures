package algorithms.KWayMerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class FindSmallestNumber {
    public static int kSmallestNumber(List<List<Integer>> lists, int k) {
        List<Integer> mergedList = mergeKLists(lists);
        if (k > mergedList.size()) {
            return mergedList.get(mergedList.size() - 1);
        }
        return mergedList.get(k - 1);
    }

    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        int length = lists.size();
        if (length == 0) {
            return new ArrayList<>();
        }
        if (length == 1) {
            return lists.get(0);
        }
        List<Integer> emptyList = Collections.emptyList();
        int mid = length / 2;
        List<List<Integer>> leftList = new ArrayList<>(mid);
        for (int i = 0; i < mid; i++) {
            if (!lists.get(i).equals(emptyList)) {
                leftList.add(lists.get(i));
            }
        }
        List<List<Integer>> rightList = new ArrayList<>(length - mid);
        for (int i = mid; i < length; i++) {
            if (!lists.get(i).equals(emptyList)) {
                rightList.add(lists.get(i));
            }
        }

        return mergeTwoSortedList(mergeKLists(leftList), mergeKLists(rightList));
    }

    public static List<Integer> mergeTwoSortedList(List<Integer> list1, List<Integer> list2) {
        List<Integer> ans = new ArrayList<>();
        int index1 = 0, index2 = 0;
        int length1 = list1.size(), length2 = list2.size();
        while (index1 < length1 && index2 < length2) {
            if (list1.get(index1) <= list2.get(index2)) {
                ans.add(list1.get(index1));
                index1++;
            } else {
                ans.add(list2.get(index2));
                index2++;
            }
        }
        if (index1 < length1) {
            for (; index1 < length1; index1++) {
                ans.add(list1.get(index1));
            }
        }
        if (index2 < length2) {
            for (; index2 < length2; index2++) {
                ans.add(list2.get(index2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        List<List<List<Integer>>> lists = Arrays.asList(
//                Arrays.asList(
//                        Arrays.asList(2, 6, 8),
//                        Arrays.asList(3, 6, 10),
//                        Arrays.asList(5, 8, 11)
//                ),
//                Arrays.asList(
//                        Arrays.asList(1, 2, 3),
//                        Arrays.asList(4, 5),
//                        Arrays.asList(6, 7, 8, 15),
//                        Arrays.asList(10, 11, 12, 13),
//                        Arrays.asList(5, 10)
//                ),
//                Arrays.asList(
//                        Arrays.asList(),
//                        Arrays.asList(),
//                        Arrays.asList()
//                ),
//                Arrays.asList(
//                        Arrays.asList(1, 1, 3, 8),
//                        Arrays.asList(5, 5, 7, 9),
//                        Arrays.asList(3, 5, 8, 12)
//                ),
                Arrays.asList(
                        Arrays.asList(5, 8, 9, 17),
                        Arrays.asList(),
                        Arrays.asList(8, 17, 23, 24)
                )
        );

        int[] k = {8};

        // loop to execute till the length of list k
        for (int i = 0; i < k.length; i++) {
            System.out.println(i + 1 + ".\t Input lists: " + lists.get(i) +
                    "\n\t K = " + k[i] +
                    "\n\t " + k[i] + "th smallest number from the given lists is: " +
                    kSmallestNumber(lists.get(i), k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

    }

}
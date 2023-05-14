package algorithms.KWayMerge;

import com.practice.grokking.InplaceReversalOfLinkedList.LinkedList;
import com.practice.grokking.InplaceReversalOfLinkedList.LinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeSortList {
    /**
     * step1: Divide the input list into two parts, each part have the same length
     * step2: Recurse the step1 until the list only have two parts
     * step3: Merge two sorted linked list
     * step4: Recurse the step3 until the total linked list is merged
     */
    public static LinkedListNode mergeKLists(List<LinkedList> lists) {
        int length = lists.size();
        if (length == 0) {
            return null;
        }
        if(length == 1){
            return lists.get(0).head;
        }
        //step1
        int mid = length / 2;
        List<LinkedList> leftListNode = new ArrayList<>(mid);
        for (int i = 0; i < mid; i++) {
            leftListNode.add(lists.get(i));
        }
        List<LinkedList> rightListNode = new ArrayList<>(length - mid);
        for (int i = mid, j = 0; i < length; i++, j++) {
            rightListNode.add(lists.get(i));
        }

        return mergeTwoSortedList(mergeKLists(leftListNode), mergeKLists(rightListNode));
    }

    //step3
    public static LinkedListNode mergeTwoSortedList(LinkedListNode list1, LinkedListNode list2) {
        LinkedListNode temp1 = list1, temp2 = list2, p = new LinkedListNode(-1);
        LinkedListNode dummy = p;
        while (temp1 != null && temp2 != null) {
            if (temp1.data <= temp2.data) {
                dummy.next = temp1;
                temp1 = temp1.next;
            } else {
                dummy.next = temp2;
                temp2 = temp2.next;
            }
            dummy = dummy.next;
        }
        if (temp1 != null) {
            dummy.next = temp1;
        }
        if (temp2 != null) {
            dummy.next = temp2;
        }
        return p.next;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        List<List<List<Integer>>> inputLists = Arrays.asList(
                Arrays.asList(Arrays.asList(21, 23, 42), Arrays.asList(1, 2, 4)),
                Arrays.asList(Arrays.asList(11, 41, 51), Arrays.asList(21, 23, 42)),
                Arrays.asList(Arrays.asList(2), Arrays.asList(1, 2, 4), Arrays.asList(25, 56, 66, 72)),
                Arrays.asList(Arrays.asList(11, 41, 51), Arrays.asList(2), Arrays.asList(2), Arrays.asList(2), Arrays.asList(1, 2, 4)),
                Arrays.asList(Arrays.asList(10, 30), Arrays.asList(15, 25), Arrays.asList(1, 7), Arrays.asList(3, 9), Arrays.asList(100, 300), Arrays.asList(115, 125), Arrays.asList(10, 70), Arrays.asList(30, 90))
        );
        for (int i = 0; i < inputLists.size(); i++) {
            System.out.println((i + 1) + ".\tInput lists:");
            List<LinkedList> llList = new ArrayList<>();
            for (List x : inputLists.get(i)) {
                LinkedList a = new LinkedList();
                a.createLinkedList(x);
                llList.add(a);
                System.out.print("\t");
                PrintList.printListWithForwardArrow(a.head);
                System.out.println();
            }
            System.out.print("\tMerged list: \n\t");
            PrintList.printListWithForwardArrow(mergeKLists(llList));
        }
    }
}
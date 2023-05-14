package algorithms.InplaceReversalOfLinkedList;

import java.util.Arrays;
import java.util.List;

public class ReverseLinkedListInKGroup {
    public static List<LinkedListNode> reverse(LinkedListNode head, int k) {
        LinkedListNode previous = null;
        LinkedListNode current = head;
        LinkedListNode next = null;
        int index = 0;
        while (current != null && index < k) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            index += 1;
        }
        List<LinkedListNode> resultList = Arrays.asList(previous, current, next);
        return resultList;
    }

    public static int findLength(LinkedListNode start) {
        LinkedListNode current = start;
        int count = 0;
        while (current != null) {
            current = current.next;
            count += 1;
        }
        return count;
    }

//    public static LinkedListNode reverseLinkedList(LinkedListNode head, int k) {
//        if (k <= 1 || head == null)
//            return head;
//        int i = 0;
//        int count = 0;
//        LinkedListNode current = head;
//        LinkedListNode previous = null;
//        LinkedListNode lastNodeOfPreviousPart = null;
//        LinkedListNode lastNodeOfCurrentPart = null;
//        int totalLength = findLength(head);
//        while (true) {
//            i += 1;
//            lastNodeOfPreviousPart = previous;
//            lastNodeOfCurrentPart = current;
//            LinkedListNode next = null;
//            List<LinkedListNode> res = reverse(lastNodeOfCurrentPart, k);
//            previous = res.get(0);
//            current = res.get(1);
//            next = res.get(2);
//            count += k;
//            if (lastNodeOfPreviousPart != null)
//                lastNodeOfPreviousPart.next = previous;
//            else
//                head = previous;
//            lastNodeOfCurrentPart.next = current;
//            if (current == null || (totalLength - count) < k)
//                break;
//            previous = lastNodeOfCurrentPart;
//        }
//        return head;
//    }

    /**
     * Recursive approach, time: O(n), space: stack space
     * step1: Initialize a curr pointer points to head, and try to iterate forward k distance on the input head node
     *          - If the remaining node is less than k, dont reverse the node and just return the head
     * step2: Reverse the input head node from head to k distance
     * step3: Initialize a tail node pointer points to the prev node(as the head node was reversed it was became the head of this segment), and
     *        move to the end of current segment
     * step4: Splicing the tail.next = reverseLinkedList(head, k);
     *
     * @param head
     * @param k
     * @return
     */
    public static LinkedListNode reverseLinkedListRecursive(LinkedListNode head, int k) {
        int index = 0;
        LinkedListNode prev = null, curr = head;
        while(index < k && curr != null){
            curr = curr.next;
            index++;
        }
        if(index < k){
            return head;
        }
        while(index > 0 && head != null){
            LinkedListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            index--;
        }
        LinkedListNode tail = prev;
        while(tail != null && tail.next != null){
            tail = tail.next;
        }
        if(tail != null){
            tail.next = reverseLinkedListRecursive(head, k);
        }
        return prev;
    }

    /**
     * Iterative approach, time:O(n), space:O(1)
     * step1: Initialize a dummy node as a previous node of head, and a start node as the head of k segment node, end node as the next node of the end of segment node
     * step2: Forward the end node k distance, if the remaining distance of head node less than k, then dont reverse the node
     * step3: Reverse the k segment node and slice it into the next node of dummy, forward the dummy node to the start position as the previous node of next k segment,
     *        then forward the start node to the end node
     * @param head
     * @param k
     * @return
     */
    public static LinkedListNode reverseLinkedListIterative(LinkedListNode head, int k) {
        if(head == null || k < 1){
            return head;
        }
        //step1
        LinkedListNode prev = new LinkedListNode(-1);
        prev.next = head;
        LinkedListNode dummy = prev;
        LinkedListNode segmentStart = head;
        LinkedListNode segmentEnd = head;
        while(segmentEnd != null){
            int i = 0;
            //step2
            for (; i < k && segmentEnd != null; i++) {
                segmentEnd = segmentEnd.next;
            }
            //step3
            if(i == k){
                LinkedListNode next = reverseNode(segmentStart, segmentEnd);
                dummy.next = next;
                dummy = segmentStart;
                segmentStart = segmentEnd;
            } else {
                dummy.next = segmentStart;
            }
        }

        return prev.next;
    }

    private static LinkedListNode reverseNode(LinkedListNode start, LinkedListNode end){
        LinkedListNode prev = null, curr = start;
        while(curr != end){
            LinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] inputList = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        LinkedList<Integer> inputLinkedList = new LinkedList<>();
        inputLinkedList.createLinkedList(inputList);
        System.out.print("The original linked list: ");
        PrintList.printListWithForwardArrow(inputLinkedList.head);
        LinkedListNode result = reverseLinkedListIterative(inputLinkedList.head, 3);
        System.out.print("\nReversed linked list, with k = " + 3 + ": ");
        PrintList.printListWithForwardArrow(result);
    }
}
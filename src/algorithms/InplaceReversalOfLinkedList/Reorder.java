package algorithms.InplaceReversalOfLinkedList;

public class Reorder {
    /**
     * Recursive approach: time:O(n), space:stack space
     *
     * step1: Reverse the input list node from head.next to end
     * step2: Merge the reversed list with the head position node
     * step3: Recurse the step1 and step2
     */
    public static LinkedListNode reorderListRecursive(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = reverseListNode(head.next);
        reorderListRecursive(head.next);
        return head;
    }

    /**
     * Iterative approach: time:O(n), space:O(1)
     *
     * step1: Initialize a dummy node points to head
     * step2: Iterate dummy node and reverse the input list node from head.next to end
     * step3: merge the reversed list with the head position node
     * step4: Move forward the dummy node to the next position node
     */
    public static LinkedListNode reorderListIterative(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode dummy = head;
        while (dummy != null) {
            dummy.next = reverseListNode(dummy.next);
            dummy = dummy.next;
        }
        return head;
    }

    public static LinkedListNode reverseListNode(LinkedListNode head) {
        LinkedListNode prev = null, curr = head;
        while (curr != null) {
            LinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
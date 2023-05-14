package algorithms.InplaceReversalOfLinkedList;

import java.util.Arrays;
import java.util.List;

public class SwapNodesInPairs {
    /**
     * Iterative approach, time:O(n), space:O(1)
     *
     * step1: Initialize a prev node(the previous position node of the window),
     * a start node(the start position node of the window) and a end node(the next position of the window)
     * step2: Iterate the input list node and reverse every adjacent node, then merge the reversed list node
     * with the rest of the input node
     */
    public static LinkedListNode swapPairs(LinkedListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        LinkedListNode prev = new LinkedListNode(-1);
        prev.next = root;
        LinkedListNode dummy = prev, start = root, end = root;
        while (end != null && end.next != null) {
            end = end.next.next;
            dummy.next = reverseAdjacentNode(start, end);
            dummy = start;
            start = end;
            dummy.next = start;
        }
        return prev.next;
    }

    public static LinkedListNode reverseAdjacentNode(LinkedListNode start, LinkedListNode end) {
        LinkedListNode prev = null, curr = start;
        while (curr != end) {
            LinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {

        // Declaring and creating a linked list
        int[] inputList = new int[]{1, 2, 3, 4};
        int[] inputList1 = new int[]{11, 12, 13, 14, 15, 16, 17};
        int[] inputList2 = new int[]{15};
        int[] inputList3 = new int[]{16, 17};

        LinkedList<Integer> inputLinkList1 = new LinkedList<>();
        inputLinkList1.createLinkedList(inputList);

        LinkedList<Integer> inputLinkList2 = new LinkedList<>();
        inputLinkList2.createLinkedList(inputList1);

        LinkedList<Integer> inputLinkList3 = new LinkedList<>();
        inputLinkList3.createLinkedList(inputList2);

        LinkedList<Integer> inputLinkList4 = new LinkedList<>();
        inputLinkList4.createLinkedList(inputList3);

        List<LinkedListNode> listHeads = Arrays.asList(inputLinkList1.head, inputLinkList2.head, inputLinkList3.head, inputLinkList4.head);
//        List<LinkedListNode> listHeads = Arrays.asList(inputLinkList2.head);
        for (int i = 0; i < listHeads.size(); i++) {
            System.out.println(i + 1 + ".\tIf we reverse the even length groups of the linked list:");
            PrintList.printListWithForwardArrow(listHeads.get(i));
            System.out.println("\n\n\twe will get: \t");
            PrintList.printListWithForwardArrow(swapPairs(listHeads.get(i)));
            System.out.println("\n");
        }

    }
}
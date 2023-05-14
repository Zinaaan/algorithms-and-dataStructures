package algorithms.InplaceReversalOfLinkedList;

import java.util.Arrays;
import java.util.List;

public class ReverseNodesInEvenGroup {
    /**
     * step1: Initialize a start node(start position of the group), a end node(next position of the end position of the group)
     * and prev node(previous position of the start of the group)
     * step2: Iterate the input list node and calculate the current group size
     * step3: If current group size % 2 == 0, reverse the list node from start to end, then merge the reversed list node with the rest of the input list node
     * step4: Else(current group size is odd), move forward the start node and the end node to the start position of the next group,
     * and move forward the prev node to the end position of the last group
     */
    public static LinkedListNode reverseEvenLengthGroups(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode prev = new LinkedListNode(-1);
        prev.next = head;
        LinkedListNode dummy = prev;
        LinkedListNode start = head, end = head;
        int count = 1;
        while (end != null) {
            int index = 0;
            while (index < count && end != null) {
                end = end.next;
                index++;
            }
            if (index % 2 == 0) {
                LinkedListNode reverseNode = reverseListNode(start, end);
                dummy.next = reverseNode;
                dummy = start;
                start = end;
                dummy.next = start;
            } else {
                while (index > 0 && dummy != null) {
                    dummy = dummy.next;
                    index--;
                }
                start = end;
            }
            count++;
        }
        return prev.next;
    }

    public static LinkedListNode reverseListNode(LinkedListNode start, LinkedListNode end) {
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
//        int[] inputList = new int[]{1, 2, 3, 4};
        int[] inputList1 = new int[]{11, 12, 13, 14, 15, 16, 17};
//        int[] inputList2 = new int[]{15};
//        int[] inputList3 = new int[]{16, 17};

//        LinkedList<Integer> inputLinkList1 = new LinkedList<>();
//        inputLinkList1.createLinkedList(inputList);

        LinkedList<Integer> inputLinkList2 = new LinkedList<>();
        inputLinkList2.createLinkedList(inputList1);
//
//        LinkedList<Integer> inputLinkList3 = new LinkedList<>();
//        inputLinkList3.createLinkedList(inputList2);
//
//        LinkedList<Integer> inputLinkList4 = new LinkedList<>();
//        inputLinkList4.createLinkedList(inputList3);

//        List<LinkedListNode> listHeads = Arrays.asList(inputLinkList1.head, inputLinkList2.head, inputLinkList3.head, inputLinkList4.head);
        List<LinkedListNode> listHeads = Arrays.asList(inputLinkList2.head);
        for (int i = 0; i < listHeads.size(); i++) {
            System.out.println(i + 1 + ".\tIf we reverse the even length groups of the linked list:");
            PrintList.printListWithForwardArrow(listHeads.get(i));
            System.out.println("\n\n\twe will get: \t");
            PrintList.printListWithForwardArrow(reverseEvenLengthGroups(listHeads.get(i)));
            System.out.println("\n");
        }

    }
}
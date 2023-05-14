package algorithms.InplaceReversalOfLinkedList;

class ReverseLinkedList {
    /**
     * Iterative approach, time: O(n), space: O(1)
     * <p>
     * step1: Initialize a dummy node as the previous node of head, and initialize a start node as the node in the left position,
     * initialize a end node as the node int the next of right position
     * step2: Reverse the list node from left to right position node
     * step3: Merge the reverse list with the rest of the list node
     */
    public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        LinkedListNode prev = new LinkedListNode(-1);
        prev.next = head;
        LinkedListNode dummy = prev, start = head, end = head;
        int index = 1;
        while (index < right && end != null) {
            if (index < left) {
                dummy = dummy.next;
                start = start.next;
            }
            end = end.next;
            index++;
        }
        LinkedListNode[] startToEnd = reverseFromStartToEnd(start, end == null ? null : end.next);
        LinkedListNode middle = startToEnd[0], last = startToEnd[1];
        LinkedListNode tempMiddle = middle;
        while (tempMiddle != null && tempMiddle.next != null) {
            tempMiddle = tempMiddle.next;
        }
        tempMiddle.next = last;
        dummy.next = middle;
        return prev.next;
    }

    private static LinkedListNode[] reverseFromStartToEnd(LinkedListNode start, LinkedListNode end) {
        LinkedListNode prev = null, curr = start;
        while (curr != end) {
            LinkedListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return new LinkedListNode[]{prev, curr};
    }

    // Driver Code
    public static void main(String[] args) {
        int[][] input = {
                {1, 2, 3, 4, 5},
//                {6, 9, 3, 10, 7, 4, 6},
//                {6, 9, 3, 4},
//                {6, 2, 3, 6, 9},
//                {6, 2}
        };
        int[] left = {2};
        int[] right = {4};
        for (int i = 0; i < input.length; i++) {
            System.out.print(i + 1);
            LinkedList<Integer> list = new LinkedList<>();
            list.createLinkedList(input[i]);
            System.out.print(".\tOriginal linked list is:  ");
            PrintList.printListWithForwardArrow(list.head);
            System.out.print("\tReversed linked list is:  ");
            PrintList.printListWithForwardArrow(reverseBetween(list.head, left[i], right[i]));
        }
    }
}
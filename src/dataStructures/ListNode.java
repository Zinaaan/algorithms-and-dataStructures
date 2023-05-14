package dataStructures;

/**
 * @author lzn
 * @date 2022/10/24 18:09
 * @Description
 */
public class ListNode {
    public int val;
    public com.practice.dataStructureDesign.ListNode next;
    public com.practice.dataStructureDesign.ListNode prev;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, com.practice.dataStructureDesign.ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val, com.practice.dataStructureDesign.ListNode next, com.practice.dataStructureDesign.ListNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

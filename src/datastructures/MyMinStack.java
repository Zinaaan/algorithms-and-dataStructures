package datastructures;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 最小栈-使用一个链表实现
 */
class MyMinStack {
    Node head;

    public MyMinStack() {
    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val);
        } else {
            head = new Node(val, Math.min(val, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    class Node {
        public int val;
        public int min;
        public Node next;

        public Node(int val, int min) {
            this(val, min, null);
        }

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
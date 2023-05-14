package dataStructures;

import java.util.HashMap;

/**
 * @author lzn
 * @date 2022/11/08 17:09
 * @Description LRU cache
 */
public class LRUCache {

    //use hashMap to save the cache key and values relation in order to find key in O(1) times;
    HashMap<Integer, Node> cacheMap;
    //use linkedList to save the Node which contains key and value can remove the head node and tail node in O(1) times;
    //when hashMap combine with the linkedList, we can get and put the node on O(1) times;
    //the head node collect recently used, the tail node collect the least recently used;
    DoubleLinkedList linkedList;
    int capacity;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>();
        linkedList = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        int val = cacheMap.get(key).val;
        //查看过，放到头节点
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        Node curr = new Node(key, val);
        if (cacheMap.containsKey(key)) {
            linkedList.removeNode(cacheMap.get(key));
        } else {
            if (cacheMap.size() == capacity) {
                Node lastNode = linkedList.removeLast();
                cacheMap.remove(lastNode.key);
            }
        }
        cacheMap.put(key, curr);
        linkedList.addFirst(curr);
    }
}

class DoubleLinkedList {
    Node head;
    Node tail;

    public DoubleLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public Node removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        return node;
    }

    public Node removeLast() {
        if (head.next == tail) {
            return head;
        }
        return removeNode(tail.prev);
    }
}

class Node {
    public int key;
    public int val;
    public Node prev;
    public Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
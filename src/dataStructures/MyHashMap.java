package dataStructures;

import java.util.Arrays;
import java.util.List;

class MyHashMap {

    private int capacity;
    private Node[] nodes;

    //Initialize a capacity of hash map
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        nodes = new Node[capacity];
    }

    /**
     * Put the key-value pair into the hash map
     * step1: If the hash map contains key, it may have two cases:
     * case1: If the key in the key-value pair which located in the hash index position is equals to the input key, update the value
     * case2: Else if the key-value node in the linked list which key is equals to the input key and update the corresponding value
     * case3: Else put the node to the end of the linked list
     * step2: If the hash map do not contains this key, just put the key-value pair into the hash indexposition
     */
    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        int index = getIndex(key);
        //step1
        if (contains(index)) {
            Node curr = nodes[index];
            //case1
            if (curr.key == key) {
                curr.value = value;
            } else {
                Node prev = null, dummy = curr;
                //case2
                while (dummy != null) {
                    if (dummy.key == key) {
                        dummy.value = value;
                        return;
                    }
                    prev = dummy;
                    dummy = dummy.next;
                }
                //case3
                prev.next = newNode;
            }
        } else {
            nodes[index] = newNode;
        }
    }

    /**
     * Get the value from the hash map
     * step1: If the hash map contains key, it may have two cases:
     * case1: If the key in the key-value pair which located in the hash index position is equals to the input key, return the value
     * case2: Else we need to traverse the linked list node to find that which key is equals to the input key, return the corresponding value
     * step2: If the hash map do not contains this key, just return -1
     */
    public int get(int key) {
        int index = getIndex(key);
        if (!contains(index)) {
            return -1;
        }
        Node curr = nodes[index];
        if (curr.key == key) {
            return curr.value;
        }
        Node dummy = curr;
        while (dummy != null) {
            if (dummy.key == key) {
                return dummy.value;
            }
            dummy = dummy.next;
        }
        return -1;
    }

    /**
     * Remove the key-value pair from the hash map
     * step1: If the hash map contains key, traverse the linked list node to find that which key is equals to the input key, remove the node from linked list
     * step2: Update the linked list with the new linked list
     * step3: If the hash map do not contains this key, just return
     */
    public void remove(int key) {
        int index = getIndex(key);
        if (!contains(index)) {
            return;
        }
        Node curr = nodes[index];
        Node prev = new Node(-1, -1);
        prev.next = curr;
        Node dummy = prev;
        while (dummy.next != null) {
            if (dummy.next.key == key) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        //step2
        nodes[index] = prev.next;
    }

    private boolean contains(int index) {
        return nodes[index] != null;
    }

    public int getIndex(int key) {
        int hash = Integer.hashCode(key);
        hash ^= hash >>> 16;
        return hash & (capacity - 1);
    }

    private class Node {
        public int key;
        public int value;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String args[]) {

        int keySpace = 11;
        MyHashMap inputHashMap = new MyHashMap(keySpace);
//        List<Integer> keysList = new ArrayList<>(Arrays.asList(5, 11, 12, 15, 22, 10));
        List<String> funcs = Arrays.asList("Put", "Put", "Put", "Put", "Get", "Remove", "Get");
        List<List<Integer>> funcKeys = Arrays.asList(Arrays.asList(22, 14), Arrays.asList(11, 300), Arrays.asList(51, 300), Arrays.asList(15, 750), Arrays.asList(51), Arrays.asList(51), Arrays.asList(51));
//        List<String> funcs = Arrays.asList("Get", "Get", "Put", "Get", "Put", "Get", "Get", "Remove", "Get", "Get", "Remove", "Get");
//        List<List<Integer>> funcKeys = Arrays.asList(Arrays.asList(5), Arrays.asList(15), Arrays.asList(15, 250), Arrays.asList(15), Arrays.asList(121, 110), Arrays.asList(121), Arrays.asList(10), Arrays.asList(11), Arrays.asList(11), Arrays.asList(13), Arrays.asList(13), Arrays.asList(0));

        for (int i = 0; i < funcs.size(); i++) {
            if ("Put".equals(funcs.get(i))) {
                System.out.println(i + 1 + ".\t put(" + funcKeys.get(i).get(0) + ", " + funcKeys.get(i).get(1) + ")");
//                if (!(keysList.contains(funcKeys.get(i).get(0)))) {
//                    keysList.add(funcKeys.get(i).get(0));
//                }
                inputHashMap.put(funcKeys.get(i).get(0), funcKeys.get(i).get(1));
            } else if ("Get".equals(funcs.get(i))) {
                System.out.println(i + 1 + ".\t get(" + funcKeys.get(i).get(0) + ")");
                System.out.println("\t Value returned: " + inputHashMap.get(funcKeys.get(i).get(0)));
            } else if ("Remove".equals(funcs.get(i))) {
                System.out.println(i + 1 + ". \t remove(" + funcKeys.get(i).get(0) + ")");
                inputHashMap.remove(funcKeys.get(i).get(0));
            }

            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
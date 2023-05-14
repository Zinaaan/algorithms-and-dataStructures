package dataStructures;

/**
 * @author lzn
 * @date 2022/11/09 11:03
 * @Description Design HashSet
 */
class MyHashSet {

    private final Node[] nodes;
    int capacity;

    public MyHashSet() {
        capacity = 10000;
        nodes = new Node[capacity];
    }

    public void add(int key) {
        //根据key获取哈希桶的位置
        int index = getIndex(key);
        Node curr = nodes[index], dummy = curr;
        //在链表数组中存在
        if (curr != null) {
            //拼接到已存在链表的末尾
            Node prev = null;
            while (dummy != null) {
                if (dummy.key == key) {
                    break;
                }
                prev = dummy;
                dummy = dummy.next;
            }
            dummy = prev;
        }
        Node node = new Node(key);
        if (dummy == null) {
            nodes[index] = node;
        } else {
            dummy.next = node;
        }
    }

    public void remove(int key) {
        int index = getIndex(key);
        Node curr = nodes[index];
        if (curr != null) {
            Node dummy = new Node(-1);
            dummy.next = curr;
            Node prev = dummy;
            while (prev.next != null) {
                if (prev.next.key == key) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            //更新链表节点
            nodes[index] = dummy.next;
        }
    }

    public boolean contains(int key) {
        int index = getIndex(key);
        Node curr = nodes[index], dummy = curr;
        if (curr != null) {
            while (dummy != null) {
                if (dummy.key == key) {
                    return true;
                }
                dummy = dummy.next;
            }
        }
        return false;
    }

    public int getIndex(int key) {
        int length = nodes.length;
        int hash = Integer.hashCode(key);
        //hash值与hash值无符号右移16位的结果进行异或
        //hash值转为二进制是32位，右移16位意味着高16位不动，将高16位右移至低16位，高16位补0
        //原来：01101010 11101111 11100010 11000100
        //右移：00000000 00000000 01101010 11101111
        //然后将hash与右移后的值进行异或（相同为0不同为1）运算，高16位的参与，增加了结果的随机性
        //异或：01101010 11101111 10001000 00101011
        hash ^= (hash >>> 16);
        //索引对数组长度取余就是索引位置
        return hash % length;
    }

    class Node {
        int key;
        Node next;

        public Node(int key) {
            this.key = key;
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */



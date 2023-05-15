package algorithms.TreeNode;

import common.Node;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 填充每个节点的下一个右侧节点指针-迭代版
 */
public class ConnectNextPointerIterative {

    //time:O(n)
    //space:O(n)
    //step1: use queue to simulate the LevelorderTraversalIterative
    //step2: when we deQueue the node,make the nodes next point to the next node
    //step3: return the root node
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i < size;i++){
                Node head = queue.poll();
                if(i < size - 1){
                    head.next = queue.peek();
                }
                if(head.left != null){
                    queue.offer(head.left);
                }    
                if(head.right != null){
                    queue.offer(head.right);
                }
            }
        }
        return root;
    }
}

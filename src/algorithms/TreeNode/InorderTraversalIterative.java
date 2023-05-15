package algorithms.TreeNode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 二叉树的中序遍历-迭代版
 */
public class InorderTraversalIterative {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.removeFirst();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}

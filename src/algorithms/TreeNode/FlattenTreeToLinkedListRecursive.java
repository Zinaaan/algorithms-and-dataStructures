package algorithms.TreeNode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 二叉树展开为链表-迭代版
 */
public class FlattenTreeToLinkedListRecursive {

    //time:O(n)
    //space:O(n)
    List<TreeNode> list = new ArrayList<>();
    public void flatten(TreeNode root) {
        preOrderTraversal(root);
        int size = list.size();
        for(int i = 1;i < size;i++){
            TreeNode prev = list.get(i-1),curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }
    public void preOrderTraversal(TreeNode root){
        if(root == null) {
            return;
        }
        list.add(root);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}

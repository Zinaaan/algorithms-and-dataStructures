package algorithms.TreeNode;

import common.TreeNode;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 二叉树的最大深度
 */
public class MaxDepthOfBinaryTree {

    //time:O(n)
    //space:O(K),k=height。recursion space depends on stack space,and stack space depends on trees height
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);
    }
}

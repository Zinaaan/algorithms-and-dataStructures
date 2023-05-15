package algorithms.TreeNode;

import common.TreeNode;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 判断是否为对称二叉树-递归版
 */
public class IsTreeSymmetricRecursive {

    //time:O(n)
    //space:O(n)
    public boolean isSymmetric(TreeNode root) {
        return compare(root,root);
    }

    public boolean compare(TreeNode left,TreeNode right){
        if(left == null && right == null) {
            return true;
        }
        if(left == null || right == null) {
            return false;
        }
        return left.val == right.val && compare(left.left,right.right) && compare(left.right,right.left);
    }
}

package algorithms.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzn
 * @date 2023/03/07 14:44
 * @Description
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 *
 * Example: Given binary tree
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Returns [4, 5, 3], [2], [1].
 */
public class FindLeavesOfBinaryTree {
    static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> findLeaves(TreeNode root) {
        findMaxDepth(root);
        return ans;
    }

    private static int findMaxDepth(TreeNode root){
        if(root == null){
            return -1;
        }
        int maxDepth = Math.max(findMaxDepth(root.left), findMaxDepth(root.right)) + 1;
        if(maxDepth >= ans.size()){
            ans.add(new ArrayList<>());
        }
        ans.get(maxDepth).add(root.val);
        return maxDepth;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        System.out.println(findLeaves(treeNode));
    }
}

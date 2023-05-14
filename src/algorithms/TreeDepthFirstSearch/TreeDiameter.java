package algorithms.TreeDepthFirstSearch;

public class TreeDiameter {

    /**
     * step1: Depth search the left and right node of the root
     * step2: Get the maximum diameter of current node
     * step3: Recurse the step1 and step2 so that the total binary tree is traversed
     *
     * @param root
     * @return
     */
    static int maxDiameter = 0;
    public static int diameterOfBinaryTree(BinaryTreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    public static int maxDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }
}
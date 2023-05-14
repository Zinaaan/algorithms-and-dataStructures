package algorithms.TreeDepthFirstSearch;


import java.util.*;

public class SerializeDeserialize {

    /**
     * Serialize: Preorder traverse root node and slice each node with ",", if node is null then slice "#" and put the total value into the serialized list
     * Deserialize: Traverse the input list and slice each left node and right node;
     *
     * @param root
     * @return
     */
    private static String nullVal = "#";
    private static List<String> stream = new ArrayList<>();

    public static List<String> serialize(BinaryTreeNode root) {
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode top = stack.pop();
            if (top == null) {
                stream.add(nullVal);
                continue;
            }
            stream.add(String.valueOf(top.data));
            stack.push(top.right);
            stack.push(top.left);
        }

        return stream;
    }

    private static int index = 0;

    public static BinaryTreeNode deserialize(List<String> stream) {
        String value = stream.get(index);
        if (nullVal.equals(value)) {
            index++;
            return null;
        }
        BinaryTreeNode n = new BinaryTreeNode(Integer.parseInt(value));
        index++;
        n.left = deserialize(stream);
        n.right = deserialize(stream);
        return n;
    }

    public static void main(String[] args) {
        // Creating a binary search tree
        List<Integer> input1 = new ArrayList<Integer>();
        input1.add(100);
        input1.add(50);
        input1.add(200);
        input1.add(25);
        input1.add(75);
        input1.add(350);
        BinaryTree tree1 = new BinaryTree(input1);
        BinaryTree orgTree1 = tree1.getTreeDeepCopy();

        // Creating a binary tree which is not a BST
        BinaryTree tree2 = new BinaryTree(100);
        tree2.insertBT(200);
        tree2.insertBT(75);
        tree2.insertBT(50);
        tree2.insertBT(25);
        tree2.insertBT(350);
        BinaryTree orgTree2 = tree2.getTreeDeepCopy();

        // Creating a binary tree which is not a BST
        BinaryTree tree3 = new BinaryTree(200);
        tree3.insertBT(350);
        tree3.insertBT(100);
        BinaryTreeNode tmp;
        tmp = tree3.findInBT(350);
        tmp.right = new BinaryTreeNode(75);
        tmp.right.right = new BinaryTreeNode(50);
        tmp = tree3.findInBT(100);
        tmp.left = new BinaryTreeNode(25);
        BinaryTree orgTree3 = tree3.getTreeDeepCopy();

        // Creating a right degenerate binary search tree
        List<Integer> input4 = new ArrayList<Integer>();
        input4.add(100);
        input4.add(50);
        input4.add(200);
        input4.add(25);
        input4.add(75);
        input4.add(350);
        Collections.sort(input4);
        BinaryTree tree4 = new BinaryTree(input4);
        BinaryTree orgTree4 = tree4.getTreeDeepCopy();

        // Creating a left degenerate binary search tree
        List<Integer> input5 = new ArrayList<Integer>();
        input5.add(100);
        input5.add(50);
        input5.add(200);
        input5.add(25);
        input5.add(75);
        input5.add(350);
        Collections.sort(input5, Collections.reverseOrder());
        BinaryTree tree5 = new BinaryTree(input5);
        BinaryTree orgTree5 = tree5.getTreeDeepCopy();

        // Creating a single node binary search tree
        BinaryTree tree6 = new BinaryTree(100);
        BinaryTree orgTree6 = tree6.getTreeDeepCopy();

        BinaryTreeNode[] inputs =
                {tree1.root, tree2.root, tree3.root, tree4.root, tree5.root, tree6.root, null};
        BinaryTree[] originalTrees =
                {orgTree1, orgTree2, orgTree3, orgTree4, orgTree5, orgTree6, null};

        for (int i = 0; i < inputs.length; i++) {
            if (i > 0) {
                System.out.print("\n");
            }
            System.out.println((i + 1) + ".\tBinary tree:");

            // Serialization
            List<String> ostream = serialize(inputs[i]);
            System.out.println("\n\tSerialized integer list:");
            System.out.println("\t" + ostream);

            // Deserialization
            BinaryTreeNode deserializedRoot = deserialize(ostream);
            System.out.println("\n\tDeserialized binary tree:");
        }
    }
}
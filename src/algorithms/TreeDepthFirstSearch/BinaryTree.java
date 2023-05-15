package algorithms.TreeDepthFirstSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    public BinaryTreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(Integer nodeData) {
        this.root = new BinaryTreeNode(nodeData);
    }

    public BinaryTree(List<Integer> nodeDataList) {
        this.root = null;
        for (Integer nodeData : nodeDataList) {
            insert(nodeData);
        }
    }

    // for normal BT level by level insertion
    public void insertBT(int key) {
        Queue<BinaryTreeNode> tempQueue = new LinkedList<BinaryTreeNode>();
        BinaryTreeNode temp = this.root;
        tempQueue.add(temp);

        while (!tempQueue.isEmpty()) {
            temp = tempQueue.peek();
            tempQueue.remove();

            if (temp.left == null) {
                temp.left = new BinaryTreeNode(key);
                break;
            } else {
                tempQueue.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new BinaryTreeNode(key);
                break;
            } else {
                tempQueue.add(temp.right);
            }
        }
    }

    // for BST insertion
    public void insert(int nodeData) {
        BinaryTreeNode newNode = new BinaryTreeNode(nodeData);
        if (this.root == null) {
            this.root = newNode;
        } else {
            BinaryTreeNode parent = null;
            BinaryTreeNode tempNode = this.root;
            while (tempNode != null) {
                parent = tempNode;
                if (nodeData <= tempNode.data) {
                    tempNode = tempNode.left;
                } else {
                    tempNode = tempNode.right;
                }
            }
            if (nodeData <= parent.data) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    private BinaryTreeNode findInBSTRec(BinaryTreeNode node, int nodeData) {
        if (node == null) {
            return null;
        }

        if (node.data == nodeData) {
            return node;
        } else if (node.data > nodeData) {
            return findInBSTRec(node.left, nodeData);
        } else {
            return findInBSTRec(node.right, nodeData);
        }
    }

    public BinaryTreeNode findInBST(int nodeData) {
        return findInBSTRec(this.root, nodeData);
    }

    private void populateParentsRec(BinaryTreeNode node, BinaryTreeNode parent) {
        if (node != null) {
            node.parent = parent;
            populateParentsRec(node.left, node);
            populateParentsRec(node.right, node);
        }
    }

    public void populateParents() {
        populateParentsRec(this.root, null);
    }

    public int getSubTreeNodeCount(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSubTreeNodeCount(node.left) + getSubTreeNodeCount(node.right);
        }
    }

    private void populateCountRec(BinaryTreeNode node) {
        if (node != null) {
            node.count = getSubTreeNodeCount(node);
            populateCountRec(node.left);
            populateCountRec(node.right);
        }
    }

    public void populateCount() {
        populateCountRec(this.root);
    }

    private BinaryTreeNode getTreeDeepCopyRec(BinaryTreeNode node) {
        if (node != null) {
            BinaryTreeNode newNode = new BinaryTreeNode(node.data);
            newNode.left = getTreeDeepCopyRec(node.left);
            newNode.right = getTreeDeepCopyRec(node.right);
            return newNode;
        } else {
            return null;
        }
    }

    public BinaryTree getTreeDeepCopy() {
        if (this.root == null) {
            return null;
        } else {
            BinaryTree treeCopy = new BinaryTree();
            treeCopy.root = getTreeDeepCopyRec(root);
            return treeCopy;
        }
    }

    private BinaryTreeNode findInBTRec(BinaryTreeNode node, int nodeData) {
        if (node == null) {
            return null;
        }

        if (node.data == nodeData) {
            return node;
        }

        BinaryTreeNode leftNode = findInBTRec(node.left, nodeData);
        if (leftNode != null) {
            return leftNode;
        }

        BinaryTreeNode rightNode = findInBTRec(node.right, nodeData);
        return rightNode;
    }

    public BinaryTreeNode findInBT(int nodeData) {
        return findInBTRec(this.root, nodeData);
    }
}

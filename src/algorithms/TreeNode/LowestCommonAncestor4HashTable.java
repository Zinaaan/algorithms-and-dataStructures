package algorithms.TreeNode;

import common.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 二叉树的最近公共祖先4-哈希表版
 */
public class LowestCommonAncestor4HashTable {

    //time: O(m+n)
    //space: O(m)
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while(p != null){
            set.add(p);
            p = p.parent;
        }
        while(q != null){
            if(!set.add(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }
}

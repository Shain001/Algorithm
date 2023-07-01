package com.shain.binaryTree.serilization;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees_L652 {
    // wrong version. this would make result has duplicated value, (Different nodes that have same value)
//    Set<String> occurred;
    Map<String, Integer> occurred;
    List<TreeNode> result;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        occurred = new HashMap<>();
        result = new ArrayList<>();
        getSerializedTree(root);
        return result;
    }

    private String getSerializedTree(TreeNode root) {
        if (root == null)
            // "#" is necessary, can't be "", also for the uniquely identify a tree
            return "#";

        String leftVal = getSerializedTree(root.left);
        String rightVal = getSerializedTree(root.right);

        // NOTE: Here, "," is necessary, otherwise the serialization cannot uniquely identify a tree!
        // 比如 curVal = 12, nextVal = 4, 不加逗号变成了 124， 你不知道 哪个节点是 12， 哪个节点是4
        String curVal = root.val + "," + leftVal + "," + rightVal;

        if (occurred.getOrDefault(curVal, 0) == 1) {
            result.add(root);
        }

        occurred.put(curVal, occurred.getOrDefault(curVal, 0) + 1);


        return curVal;
    }
}

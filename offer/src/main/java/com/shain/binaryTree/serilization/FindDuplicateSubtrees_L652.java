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
        String curVal = root.val + "," + leftVal + "," + rightVal;

        if (occurred.getOrDefault(curVal, 0) == 1) {
            result.add(root);
        }

        occurred.put(curVal, occurred.getOrDefault(curVal, 0) + 1);


        return curVal;
    }
}

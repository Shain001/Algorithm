package com.shain.binaryTree.divide;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PreTraverse_Divide {

    public List<Integer> preTraverse(TreeNode root) {
        return getTraverse(root);
    }

    public List<Integer> getTraverse(TreeNode root) {
        List<Integer> cur = new ArrayList<>();
        if (root == null) {
            return cur;
        }

        cur.addAll(getTraverse(root.left));
        cur.add(root.val);
        cur.addAll(getTraverse(root.right));
        return cur;
    }
}

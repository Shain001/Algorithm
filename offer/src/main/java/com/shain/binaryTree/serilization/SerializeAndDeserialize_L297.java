package com.shain.binaryTree.serilization;

import com.shain.common.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

public class SerializeAndDeserialize_L297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        doPreTraverse(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(split));
        return doSerialize(nodes);
    }

    private TreeNode doSerialize(LinkedList<String> nodes) {
        if (nodes.isEmpty())
            return null;

        if (nodes.getFirst().equals("#")) {
            nodes.removeFirst();
            return null;
        }

        String cur = nodes.removeFirst();
        int val = Integer.parseInt(cur);

        TreeNode root = new TreeNode(val);
        root.left = doSerialize(nodes);
        root.right = doSerialize(nodes);

        return root;
    }

    public void doPreTraverse(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }

        sb.append(root.val).append(",");
        doPreTraverse(root.left, sb);
        doPreTraverse(root.right, sb);
    }
}

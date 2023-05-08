package com.shain.binaryTree.traverse.traverseAndCompare.commonAncestor;

import com.shain.common.tree.TreeNode;
import com.shain.common.tree.TreeUtils;

import java.util.HashSet;
import java.util.Set;

public class LCA4_1676 {
    static Set<Integer> vals;
    static TreeNode result;
    static int count;

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode re = lowestCommonAncestor(root, new TreeNode[]{new TreeNode(3), new TreeNode(6)});
        System.out.println(re.val);
    }

    // This version will exceed time limit in leetcode
//    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
//        count = nodes.length;
//        vals = Arrays.stream(nodes).map(s -> s.val).collect(Collectors.toSet());
//        result = doFind(root);
//        return result;
//    }
//
//    public static TreeNode doFind(TreeNode root) {
//        if (root == null)
//            return null;
//
//        TreeNode left = doFind(root.left);
//        if (left != null)
//            return left;
//
//        TreeNode right = doFind(root.right);
//        if (right != null)
//            return right;
//
//        if (countNodes(root) == count){
//            result = root;
//        }
//
//        return result;
//    }
//
//    private static int countNodes(TreeNode root) {
//        if (root == null)
//            return 0;
//
//        if (vals.contains(root.val))
//            return 1 + countNodes(root.left) + countNodes(root.right);
//        else
//            return countNodes(root.left) + countNodes(root.right);
//    }

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        // 将列表转化成哈希集合，便于判断元素是否存在
        HashSet<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }

        return find(root, values);
    }

    static TreeNode find(TreeNode root, HashSet<Integer> values) {
        if (root == null) {
            return null;
        }

        // 该判断在此处的意义为， 判断当前的节点是不是目标节点之一 （如果是LCA2， 只找两个节点的祖先， 也是同理）
        // 如果是目标节点， 那么下列代码中的left就已经不是空了。
        // 接下来只需要判断， 剩余的目标节点在不在其他分支中。
        // 例如， 以lt中题目描述的例图为例， 假设要找的节点是 5，6。 5，6 在同一个路线中， 且5 就是 最近祖先。
        // 此时， 已经找到了5， 那么只需要判断 6 是不是在 以 1 为副节点的子树中
        // 如果在以 1 为父节点的子树中找到了6， 那么result则是通过 if 语句返回， 结果是父节点3
        // 但是如果 以 1 为父节点的子树中没有 6， 那么结果就是5， 因为 题干说了 目标节点必定在树中
        // 换句话说， 该写法的意义为， 其可以减少几个节点的遍历。
        // 而LCA2 的写法， 是无论如何都遍历所有的节点， 因此LCA2的写法能够应对 目标节点可能不在树中的情况。
        if (values.contains(root.val)) {
            return root;
        }

        TreeNode left = find(root.left, values);
        TreeNode right = find(root.right, values);
        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }
}

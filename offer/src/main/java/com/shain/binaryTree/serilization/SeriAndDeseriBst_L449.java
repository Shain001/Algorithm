package com.shain.binaryTree.serilization;

import com.shain.common.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给bst的作用在于， 无需序列化空节点， 也能唯一的还原出一颗bst。
 *
 * 因为序列化时由 我们自己选择 序列化方式。 所以可选 前序/后序。
 *
 * 有了 前序或者后序， 由于bst的中序遍历是有序的。 所以自然就有了中序遍历的 结果。
 *
 *
 * 以下是题解的代码。 写的比较炫酷，可以学习一下。
 * 但是本质上没有变。 依然是反序列化时， 得到根节点的值， 然后通过利用"左子树均小于root，right均大于root"的特性， 构建二叉树。 即没有必要真的
 * 将 前序遍历数组 sort成一个新的中序数组， 然后像L297 那样做。
 *
 * 题解用的后序。 I would prefer preOrder.
 *
 */
public class SeriAndDeseriBst_L449 {
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        postOrder(root, list);
        String str = list.toString();
        return str.substring(1, str.length() - 1);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(", ");
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            stack.push(Integer.parseInt(arr[i]));
        }
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    private void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    private TreeNode construct(int lower, int upper, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
            return null;
        }
        int val = stack.pop();
        TreeNode root = new TreeNode(val);
        root.right = construct(val, upper, stack);
        root.left = construct(lower, val, stack);
        return root;
    }
}

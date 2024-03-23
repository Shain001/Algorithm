package com.shain.binaryTree.traverse.pureTraverse.inoder;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;

public class ModeInBST_L501 {
    private TreeNode pre;
    private ArrayList<Integer> ans;
    private int count;
    private int max;

    public int[] findMode(TreeNode root) {
        this.ans = new ArrayList<>();
        this.count = 1;
        this.max = 0;

        inOrder(root);

        // 处理最后一个节点序列
        if (count > max) {
            ans.clear();
            ans.add(pre.val);
        } else if (count == max) {
            ans.add(pre.val);
        }

        var arr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }
        return arr;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        /**
         您在处理最后一个节点的逻辑中的主要问题是，您尝试在 inOrder 方法中直接处理 root == null 的情况来对最后一个节点进行特殊处理。
         但实际上，由于 inOrder 方法是递归调用的，每一次递归到达叶子节点的左右子节点时（它们本身为null），都会进入到这个判断逻辑中，
         而不仅仅是在遍历结束时。因此，这种方式并不适合于在遍历结束后处理最后一个节点
         */
        // if (root == null && pre != null) {
        //     if (count > max) {
        //             ans.clear();
        //             ans.add(pre.val);
        //             max = count;
        //         } else if (count == max) {
        //             ans.add(pre.val);
        //         }
        //     return;
        // }

        inOrder(root.left);

        if (pre != null) {
            if (pre.val == root.val) {
                count++;
            } else {
                if (count > max) {
                    ans.clear();
                    ans.add(pre.val);
                    max = count;
                } else if (count == max) {
                    ans.add(pre.val);
                }
                count = 1;
            }
        }

        pre = root;


        inOrder(root.right);
    }
}

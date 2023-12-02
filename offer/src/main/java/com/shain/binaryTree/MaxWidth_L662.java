package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 唯一重点在于对一颗 完全二叉树 中的节点的编号。
 * 虽然这题不是完全二叉树。 但是由于 null 也算长度。 所以可以按照完全二叉树处理
 *
 * 所谓对节点的编号， 其实隐含的是 对于 一颗二叉数的存储。
 *
 * 每个节点的编号相当于在 数组中 该节点的下标。
 *
 * copy:
 * 普通二叉树 一般采用 链式存储结构 ，比如 二叉链表，三叉链表；
 * 完全二叉树 一般采用 顺序存储结构，比如 数组；
 * 我们熟悉的 堆，即 二叉堆 就是一棵 完全二叉树，采用数组存储。
 * 那么怎么把 二叉树 怎么存在一个 数组 中呢？
 *
 * 假设数组下标从 1 开始，二叉树 的根结点存储在位置 1，如果根结点有左孩子，左孩子存储在位置 2 = 2 * 1，如果根结点有右孩子，右孩子存储在位置 3 = 2 * 1 + 1。对于存储在位置 i 的结点，如果它有左孩子，左孩子存储在位置 2 * i，如果它有右孩子，右孩子存储在位置 2 * i + 1。
 *
 * todo: 二叉树的存储
 */
public class MaxWidth_L662 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        queue.offer(root);
        map.put(root, 1);
        int result = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            int start = map.get(queue.peek());
            int end = start;
            while (size > 0) {
                TreeNode cur = queue.poll();
                int inx = map.get(cur);
                if (size == 1) {
                    end = inx;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                    // 唯一重点在这里， 对于节点的编号。
                    // left 节点编号 2* parent's index
                    map.put(cur.left, inx*2);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    // right 编号 2* parent's index + 1
                    map.put(cur.right, inx*2 + 1);
                }
                size--;
            }
            result = Math.max(result, end-start+1);
        }
        return result;
    }
}

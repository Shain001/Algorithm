package com.shain.binaryTree.traverse.pureTraverse.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRight_L116 {
    public TreeNodeWithPointer connect(TreeNodeWithPointer root) {
        //doPopulate_v1(root);

//        Queue<TreeNodeWithPointer> curLevel = new LinkedList<>();
//        curLevel.add(root);
//        doPopulate_v2(curLevel);

        if (root == null) return root;
        doPopulate_V3(root.left, root.right);
        return root;
    }

    // queue bfs
    public void doPopulate_v1(TreeNodeWithPointer root) {
        if (root == null) return;

        Queue<TreeNodeWithPointer> queue = new LinkedList<>();
        queue.offer(root);

        // use queue.peed()!=null to check if it's finished the last level of the tree
        // since this is a perfect binary tree
        while (!queue.isEmpty() && queue.peek() != null) {
            int size = queue.size();
            while (size > 0) {
                size--;
                // connect pointer to next
                TreeNodeWithPointer cur = queue.poll();
                TreeNodeWithPointer next = size == 0 ? null : queue.peek();
                cur.next = next;
                // put next level nodes
//                if (cur.left != null)
                queue.add(cur.left);
//                if (cur.right != null)
                queue.add(cur.right);
            }
        }
    }

    // recursive bfs
    public void doPopulate_v2(Queue<TreeNodeWithPointer> curLevel) {
        if (curLevel.peek() == null) {
            return;
        }
        int size = curLevel.size();
        while (size > 0) {
            // connect
            size--;
            TreeNodeWithPointer cur = curLevel.poll();
            TreeNodeWithPointer next = size == 0 ? null : curLevel.peek();
            cur.next = next;
            // add next level
            curLevel.offer(cur.left);
            curLevel.offer(cur.right);
        }

        doPopulate_v2(curLevel);
    }

    // preOrder solution
    public void doPopulate_V3(TreeNodeWithPointer left, TreeNodeWithPointer right) {
        if (left == null && right == null) return;

        left.next = right;

        doPopulate_V3(left.left, left.right);
        doPopulate_V3(right.left, right.right);
        // connect the neighbor nodes from different parents
        doPopulate_V3(left.right, right.left);
    }

    public class TreeNodeWithPointer {
        public int val;
        public TreeNodeWithPointer left;
        public TreeNodeWithPointer right;
        public TreeNodeWithPointer next;

        public TreeNodeWithPointer() {
        }

        public TreeNodeWithPointer(int _val) {
            val = _val;
        }

        public TreeNodeWithPointer(int _val, TreeNodeWithPointer _left, TreeNodeWithPointer _right, TreeNodeWithPointer _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

package com.shain.binaryTree.traverse.pureTraverse.bfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRight_L116 {
    public static void main(String[] args) {
        Queue<Integer> test = new LinkedList<>();
        test.offer(null);
        System.out.println(test.size());
        System.out.println(test.poll());
    }
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

        // 加了 right.next = null; 会错
        // 无需在意 每一层最右侧的节点的next指针， 因为 初始值就是null。
        // 为什么加了right.next = null 会出错？
        //       1
        //   /     \
        //  2       3
        // / \     / \
        //4   5   6   7
        // 一句话， 主要原因在于 doPopulate_V3(left.right, right.left); 时， 3 的left 6 变成了 传进函数的 right， 然后其next被设置成null了， 但这是不应该发生的， 因为此时 6 的next本来已经指向7了
        // 为什么一定已经指向 7 了？ 因为时preOrder， 6 与 7 在遍历到3 的时候被连接， 而3 一定在6 之前被遍历到。
        // 如何解决？ 加一个 if != null 就解决了， 但是没必要。
        //  if(right.next == null)
        //            right.next = null;

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

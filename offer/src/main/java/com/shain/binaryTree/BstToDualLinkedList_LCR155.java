package com.shain.binaryTree;

public class BstToDualLinkedList_LCR155 {
    private Node pre;
    private Node head;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        traverse(root);
        // 因为题目要的是循环双向链表。 头尾需项链
        // 这里traverse结束后， pre就是指向 最 右下角 的节点， 也即链表尾节点
        // 如果忘了， 想一下中序遍历顺序就行了。
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void traverse(Node cur) {
        if (cur == null) {
            return;
        }

        traverse(cur.left);
        // 第一次递归到这， 说明到了最左下角的节点。 此时pre还没有被更新，为null
        if (pre == null) {
            head = cur;
        }
        if (pre != null) {
            pre.right = cur;
        }
        // 这一链接是必须的。 虽然节点的左节点就是前驱节点。 但是两者并不完全等价
        // 你可以画一个稍微复杂一点的树过一遍。 虽然在一条路径上 左侧节点确实是前驱节点， 但是当
        // 从一个子树跳跃到另一个子树时， 这一情况就不成立了。
        // 其次， 注意这里还有一个细节。 cur.left 在这里可以改变指向且没有问题， 是因为
        // 走到这里， cur.left 已经不会再被访问了， 所以不会造成丢失节点的问题。
        cur.left = pre;
        pre = cur;
        traverse(cur.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}

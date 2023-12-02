package com.shain.binaryTree.bst;

import com.shain.common.tree.TreeNode;

/**
 * 方法一为使用 快慢指针， 找到链表的中点， 迭代的进行创建bst。
 *
 * 以下方法为优化版本。
 * 即利用了 输入的链表是有序的这一点。 有序即意味着 他是bst的中序遍历结果。
 * 然而 只通过 中序遍历， 无法唯一的确定一颗bst。 也即， 只知道中序遍历的结果， 我们无法保证所创建的 bst是平衡的。
 *
 * 然而， 可以通过 线遍历一遍链表， 得到其长度， 然后递归的通过 限制 left， right这一区间， 控制 每棵子树中节点的数量， 保证平衡。
 *
 */
public class OrderedLinkedListToBst_L109 {
    private ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        ListNode dumpHead = head;
        this.head = head;
        int len = getLen(dumpHead);
        return doConstruct(0, len - 1);
    }

    private TreeNode doConstruct(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode leftHead = doConstruct(left, mid - 1);

        TreeNode root = new TreeNode(head.val);
        head = head.next;
        root.left = leftHead;
        root.right = doConstruct(mid + 1, right);

        return root;
    }

    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

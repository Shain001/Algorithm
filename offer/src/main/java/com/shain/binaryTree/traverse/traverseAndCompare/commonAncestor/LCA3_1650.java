package com.shain.binaryTree.traverse.traverseAndCompare.commonAncestor;

public class LCA3_1650 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node pHead = p;
        Node qHead = q;
        while (p != q) {
            p = p.parent == null ? qHead : p.parent;
            q = q.parent == null ? pHead : q.parent;
        }

        return p;
    }

    // wrong version:
    // This is actually a 单链表相交问题
//    public Node lowestCommonAncestor(Node p, Node q) {
//        if (p == null)
//            return null;
//
//        if (q == null)
//            return null;
//
//        if (p == q)
//            return p;
//
//        Node pParent = lowestCommonAncestor(p.parent, q);
//
//        if (pParent != null)
//            return pParent;
//
//        Node qParent = lowestCommonAncestor(p, q.parent);
//
//        if (qParent != null)
//            return qParent;
//
//        return lowestCommonAncestor(p.parent, q.parent);
//    }

    private final static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

}

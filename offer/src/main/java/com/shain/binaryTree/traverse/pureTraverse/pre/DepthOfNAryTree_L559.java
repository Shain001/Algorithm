package com.shain.binaryTree.traverse.pureTraverse.pre;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DepthOfNAryTree_L559 {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        public int maxDepth(Node root) {
            if (root == null) {
                return 0;
            }

            int result = 0;

            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            int count = 1;

            while (!queue.isEmpty()) {
                int tempCount = 0;
                while (count > 0) {

                    Node cur = queue.poll();
                    List<Node> children = cur.children;

                    for (Node c : children) {
                        if (c == null) {
                            continue;
                        }

                        queue.offer(c);
                        tempCount++;
                    }

                    count--;
                }
                result++;
                count = tempCount;
            }

            return result;
        }
    }

    // v2
    private int result;

    public int maxDepth(Node root) {
        result = 0;
        traverse(root, 1);
        return result;
    }

    private void traverse(Node root, int depth) {
        if (root == null) {
            return;
        }

        if (root.children == null || root.children.size() == 0) {
            result = Math.max(result, depth);
        }

        for (Node n : root.children) {
            traverse(n, depth+1);
        }
    }

    // v3
    private int result_v3 = 0;

    public int maxDepth_v3(Node root) {
        if (root == null) {
            return 0;
        }

        traverse(root.children, 1);
        return result;
    }

    private void traverse(List<Node> children, int depth) {
        if (children == null || children.size() == 0) {
            result = Math.max(result, depth);
            return;
        }

        for (Node c : children) {
            traverse(c.children, depth+1);
        }
    }
}


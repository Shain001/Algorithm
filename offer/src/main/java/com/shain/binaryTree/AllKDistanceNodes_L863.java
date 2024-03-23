package com.shain.binaryTree;

import com.shain.common.tree.TreeNode;

import java.util.*;

/**
 * 树转图
 *
 */
public class AllKDistanceNodes_L863 {
    private Map<TreeNode, TreeNode> map = new HashMap<>();
    private TreeNode t;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        generateGraph(root, null);

        int count = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        List<Integer> ans = new ArrayList<>();
        queue.offer(target);
        visited.add(target);

        while ( count < k) {
            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();

                // down
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.offer(cur.left);
                    visited.add(cur.left);
                }

                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.offer(cur.right);
                    visited.add(cur.right);
                }

                // up
                if (map.get(cur) != null && !visited.contains(map.get(cur))) {
                    queue.offer(map.get(cur));
                    visited.add(map.get(cur));
                }

                size--;
            }

            count++;
        }

        for (TreeNode t: queue) {
            ans.add(t.val);
        }

        return ans;
    }

    private void generateGraph(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }

        map.put(root, parent);

        generateGraph(root.left, root);
        generateGraph(root.right, root);
    }
}

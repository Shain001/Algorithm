package com.shain.binaryTree.traverse.bfs;

import com.shain.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private static List<Integer> result = new ArrayList<>();

    public static List<Integer> bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null)
                continue;
            result.add(cur.val);
            queue.add(cur.left);
            queue.add(cur.right);
        }

        return result;
    }

    public List<Integer> bfs_recursive(TreeNode root) {
        if (root == null)
            return result;
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        doTraverseLevel(treeNodes);
        return result;
    }

    private void doTraverseLevel(Queue<TreeNode> curLevelNodes) {
        if (curLevelNodes.size() == 0) {
            return;
        }

        int curLevelNodesCount = curLevelNodes.size();

        while(curLevelNodesCount != 0) {
            TreeNode cur = curLevelNodes.poll();

            result.add(cur.val);

            if (cur.right != null)
                curLevelNodes.offer(cur.right);
            if (cur.left != null)
                curLevelNodes.offer(cur.left);
            curLevelNodesCount--;
        }
        doTraverseLevel(curLevelNodes);
    }
}

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
}

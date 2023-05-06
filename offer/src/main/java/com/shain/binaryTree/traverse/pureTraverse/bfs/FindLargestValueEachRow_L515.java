package com.shain.binaryTree.traverse.pureTraverse.bfs;

import com.shain.common.tree.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueEachRow_L515 {
    private List<Integer> result = new ArrayList<>();

    // v1: BFS queue
    public List<Integer> largestValues(TreeNode root) {
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            while (size!= 0) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);
                size--;
            }

            result.add(max);
        }
        return result;
    }

    // v2: dfs
    public List<Integer> largestValues_v2(TreeNode root) {
        doDfs(root, 0);
        return result;
    }

    public void doDfs(TreeNode cur, int depth) {
        if (cur == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(cur.val);
        } else {
            result.set(depth, Math.max(cur.val, result.get(depth)));
        }

        doDfs(cur.left, depth+1);
        doDfs(cur.right, depth+1);
    }

    // v3: bfs recursive
    public List<Integer> largestValues_v3(TreeNode root) {
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

        int max = Integer.MIN_VALUE;
        int curLevelNodesCount = curLevelNodes.size();

        while(curLevelNodesCount != 0) {
            TreeNode cur = curLevelNodes.poll();
            max = Math.max(cur.val, max);

            if (cur.right != null)
                curLevelNodes.offer(cur.right);
            if (cur.left != null)
                curLevelNodes.offer(cur.left);
            curLevelNodesCount--;
        }
        result.add(max);
        doTraverseLevel(curLevelNodes);
    }
}

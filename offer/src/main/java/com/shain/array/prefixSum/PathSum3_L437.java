package com.shain.array.prefixSum;

import com.shain.common.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class PathSum3_L437 {
    // key = curSum, value = 前缀和为 curSum 的节点个数
    private Map<Long, Long> map;
    private int result;
    private int target;
    public int pathSum(TreeNode root, int targetSum) {
        map = new HashMap<>();
        target = targetSum;
        result = 0;
        // 这个0， 1 类似于 prefixSum数组中的index0 = 0；
        // 换句话说， 树形的prefixSum 没有办法记录成数组， 并且由于所求的结果是 "路径个数"， 所以我们不妨直接将map的value记录为路径个数。
        // 这样就带来了一个问题， 即你没有办法在map中记录 单个节点的值， 因为map中的key全部是前缀和。
        // 那么 当一个节点的val正好等于 target时你要怎么处理？
        // 利用 在map中存 0，1 即可解决这个问题。
        // imagine 任意节点的值=target时， 此时在map中找 key = val-target 的个数。 正好找到key=0, val=1， 所以能够得到正确结果。
        // 因为对于每一个val=target的节点而言， result数量均只应该+1。
        map.put(0L, 1L);
        dfs(root, 0L);
        return result;
    }

    private void dfs(TreeNode root, long curSum) {
        if (root == null)
            return;

        curSum += root.val;
        result += map.getOrDefault(curSum - target, 0L);
        map.put(curSum, map.getOrDefault(curSum, 0L) + 1);

        dfs(root.left, curSum);
        dfs(root.right, curSum);

        // 在从下到上的过程中， 即已经遍历了所以子节点， 从子节点返回到当前节点时， 要移除当前的cursum，因为"路径必须是向下的"。
        // Imagine 你已经遍历了根结点的所有左子树， 当前序遍历到 右子树时， 你的map中是不应该再有左子树的sum的
        // 同时，这里的还有一个隐含意思是， 这题求的路径只能是从 根结点开始， 到某个节点结束， 而不可能是根结点是 路径中的某一个节点。
        map.put(curSum, map.get(curSum)-1);
    }

}

package com.shain.dp.pathSum.reverseTraverse;

import com.shain.common.tree.TreeNode;
import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3_L337 {
    // 这题必须加缓存。否则用例超时
    private Map<TreeNode, Integer> cache = new HashMap<>();

    // 方程含义为得到当前节点能够抢到的最大值。
    // 当前节点最大值 = max（不抢 -> 两个子节点最大值的和， 抢-> 当前节点val + 四个四个子节点最大值的和）
    // 该方法为自底向上遍历， 也正因如此， 才能得到正确答案。
    // 所说"才能得到正确答案"， 意思是：
    // 如果从上向下遍历的时候， 站在当前节点是无法决定 偷不偷 child.child这一层的。
    // P.S. 这题不是说只要隔层就偷， which you was wrong, e.g. [4, 1, n, 2, n, 3]
    // 而自底向上是可以得到正确答案的。
    // 因为你站在叶子节点， 知道了当前叶子节点的最大值， 然后一层层往上， 每一层都能得到之前（更下层）节点的最大值， 进而决定偷不偷当前节点。
    // "这也就是相当于逆向的更新了dp数组， 符合之前总结的结论"
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (cache.containsKey(root)){
            return cache.get(root);
        }

        // calculate rob

        // 左侧两个孙子节点的值。 因为要越级访问所以要null check
        int leftChildren = root.left == null ? 0 : rob(root.left.left) + rob(root.left.right);
        int rightChildren = root.right == null ? 0 : rob(root.right.left) + rob(root.right.right);
        int doRob = root.val + leftChildren + rightChildren;

        // don't rob
        int notRob = rob(root.left) + rob(root.right);
        int result = Math.max(doRob, notRob);
        cache.put(root, result);

        return result;
    }

    // wrong version
    // 不是只要隔代就偷， 当currentNode不偷时， 其 孙子节点 有可能偷也有可能不偷。
//    public int rob(TreeNode root) {
//        return Math.max(getSum(root, true), getSum(root, false));
//    }
//
//    private int getSum(TreeNode root, boolean use) {
//        if (root == null) {
//            return 0;
//        }
//
//        int left = getSum(root.left, !use);
//        int right = getSum(root.right, !use);
//
//        return use ? root.val + left + right : left + right;
//    }

    public int rob_v2(TreeNode root) {
        int[] dp = doRob(root);
        return Math.max(dp[0], dp[1]);
    }

    // 得到当前节点， 偷 与 不偷时分别的最大值。
    // 当 偷 -> 偷的最大值= curVal + child 不偷的最大值， 即 child[1]
    // ！！！当 不偷 -> 子节点可偷可不偷， 当前节点不偷的最大值为 max(child[1], child[0]) -> 这是本题得重点与关键。
    // int[0] -> 偷的最大值 int[1] -> 不偷的最大值。
    // 这种方法相当于在tree中实现了动态规划的思路， 更符合dp的思维， 但是本质是没变的， 还是下到上遍历树。
    // 但是这个版本相对于 使用cache的版本 的优点在于：
    // 1. 不用记录cache， 省了空间， 且节约了查找cache， 插入cache的时间。
    // 2. 每个节点真真正正的只走了一次 （实际是两次， 上->下过程一次， 回来再一次）， 是真正的从下至上遍历。
    //    Specifically, 方法1 中， 计算当前节点时， 需要遍历child和grandchild。 即对于grandchild而言， 他被爷爷访问了一次， 遍历到自己时，又被访问了一次
    //    而这种方法就节约了stack次数。
    private int[] doRob(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = doRob(root.left);
        int[] right = doRob(root.right);

        int rob = root.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{rob, notRob};
    }
}

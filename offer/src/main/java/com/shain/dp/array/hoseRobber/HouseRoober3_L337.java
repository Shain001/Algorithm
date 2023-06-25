package com.shain.dp.array.hoseRobber;

import com.shain.common.tree.TreeNode;

public class HouseRoober3_L337 {
    public int rob(TreeNode root) {
        return getMaximum(root)[0];
    }

    // i=0 -> curNode Max; i=1 -> prev max
    public int[] getMaximum(TreeNode root) {
        if (root == null) {
            return new int[]{0,0};
        }

        int[] leftMaxes = getMaximum(root.left);
        int[] rightMaxes = getMaximum(root.right);

        int prevMax = leftMaxes[0] + rightMaxes[0];
        int curMax = Math.max(root.val + leftMaxes[1] + rightMaxes[1], prevMax);

        return new int[]{curMax, prevMax};
    }
}

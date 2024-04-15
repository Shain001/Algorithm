package com.shain.binaryTree.bst;

public class ValidateBst_LCR152 {
    private int[] postorder;

    public boolean verifyTreeOrder(int[] postorder) {
        this.postorder = postorder;
        return doVerify(0, postorder.length-1);
    }

    private boolean doVerify(int start, int end) {
        if (start >= end) {
            return true;
        }

        int root = postorder[end];

        int inx=start;

        while (postorder[inx] < root) {
            inx++;
        }

        for (int i = inx; i < end; i++) {
            if (postorder[i] < root)
                return false;
        }

        return doVerify(start, inx-1) && doVerify(inx, end-1);
    }
}

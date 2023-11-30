package com.shain.binaryTree.bst;

public class UniqueBST_L96 {
    private int[] mem;
    public int numTrees(int n) {
        mem = new int[n+1];
        return count(n);
    }

    public int count(int n) {
        if (mem[n] != 0) {
            return mem[n];
        }

        // 为什么 0 的时候return1？
        // 因为是相乘
        if (n <= 1) {
            return 1;
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int left = count(i-1);
            int right = count(n-i);
            result += left * right;
        }
        mem[n] = result;
        return result;
    }
}

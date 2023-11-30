package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combination_L77 {
    private List<List<Integer>> result = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backTrack(new LinkedList<>(), 1);
        return result;
    }

    public void backTrack(LinkedList<Integer> path, int start) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            if (n - i + 1 < k - path.size())
                break;
            path.add(i);
            backTrack(path, i + 1);
            path.removeLast();
        }
    }
}

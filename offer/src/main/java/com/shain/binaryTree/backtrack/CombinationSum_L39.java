package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum_L39 {
    private List<List<Integer>> result = new ArrayList<>();
    private int[] candidates;
    private int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        backtrack(new LinkedList<Integer>(), 0, 0);
        return result;
    }

    public void backtrack(LinkedList<Integer> path, int curSum, int start) {
        if (curSum == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        if (curSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            curSum += candidates[i];
            path.add(candidates[i]);
            backtrack(path, curSum, i);
            curSum -= candidates[i];
            path.removeLast();
        }
    }
}

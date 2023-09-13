package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum2_L40 {
    private int[] candidates;
    private int target;
    private LinkedList<Integer> path;
    private List<List<Integer>> result;


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.path = new LinkedList<>();
        this.result = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;

        Arrays.sort(this.candidates);

        backTrack(0, 0);

        return result;
    }

    private void backTrack(int start, int sum) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target)
                continue;

            // 避免同一层级中的重复元素。
            // 看blog回溯篇
            if (i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            path.add(candidates[i]);
            backTrack(i+1, sum + candidates[i]);
            path.removeLast();
        }
    }
}

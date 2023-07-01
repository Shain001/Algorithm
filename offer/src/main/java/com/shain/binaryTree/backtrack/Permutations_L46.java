package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permutations_L46 {
    private final List<List<Integer>> result = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        backtrack(new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            backtrack(path, used);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}

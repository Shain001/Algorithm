package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubSet2_L90 {
    private LinkedList<Integer> path;
    private List<List<Integer>> result;
    private int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.nums = nums;
        Arrays.sort(this.nums);
        this.path = new LinkedList<>();
        this.result = new ArrayList<>();

        backTrack(0);

        return result;
    }

    private void backTrack(int start) {
        result.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }

            path.add(nums[i]);
            backTrack(i+1);
            path.removeLast();
        }
    }
}

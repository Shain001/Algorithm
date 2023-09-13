package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubSet2_L90 {
    private List<List<Integer>> result;
    private LinkedList<Integer> path;
    private int[] nums;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.result = new ArrayList<>();
        this.path = new LinkedList<>();
        this.nums = nums;
        Arrays.sort(nums);
        backTrack(new boolean[nums.length], 0);
        return result;
    }

    public void backTrack(boolean[] used, int start) {
        result.add(new ArrayList<>(path));


        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i-1] == nums[i] && !used[i-1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backTrack(used, i+1);
            path.removeLast();
            used[i] = false;
        }

    }
}

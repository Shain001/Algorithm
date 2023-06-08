package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum3_L216 {
    int target;
    int k;
    List<List<Integer>> result = new ArrayList<>();

    // k -> number of digits can be used, n -> target
    public List<List<Integer>> combinationSum3(int k, int target) {
        this.k = k;
        this.target = target;
        backTrack(0, new LinkedList<Integer>(), 1);
        return result;
    }

    public void backTrack(int curSum, LinkedList<Integer> path, int start) {
        if (path.size() > k) {
            return;
        }

        if (path.size() == k && curSum == target) {
            result.add(new ArrayList<Integer>(path));
        }

        for (int i = start; i <= 9; i++) {
            if (curSum + i > target)
                break;
            curSum += i;
            path.add(i);
            backTrack(curSum, path, i+1);
            curSum -= i;
            path.removeLast();
        }
    }
}

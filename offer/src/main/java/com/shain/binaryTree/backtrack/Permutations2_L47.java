package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutations2_L47 {
    private LinkedList<Integer> path;
    private List<List<Integer>> result;
    private boolean[] used;
    private int[] nums;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        this.result = new ArrayList<>();
        this.path = new LinkedList<>();
        this.used = new boolean[nums.length];
        Arrays.sort(nums);

        backTrack();

        return result;
    }

    private void backTrack() {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++ ) {
            // ！！注意这里的 !used[i-1] 这个条件， 错了好几次了
            // 这个条件的作用为： "仅 在同一层 跳过 相同的节点"
            // 画一个 【1，1，2】 这个用例的图， 我们想要实现的是 在第一层的 三个节点 1，1，2 中 （能理解啥是第一层把， 排除虚拟头节点后的第一层）
            // 跳过第二个1， 因为他会得到重复的组合。
            // 但是如果这里不加!used[i-1]， 只用 i > 0  && nums[i] == nums[i-1] 的话。 确实第一层中的 第二个 1 被跳过了， 但是
            // 后面 第二层， 第三层 的 第二个 index=1 处的 1 都被跳过了， 于是 导致 path的size 永远到3。
            // 为什么？ 因为如果你不限制这个 !used[i-1] 的话， 程序不知道你到哪一层了。
            // 那么为什么要限制的是 !used[i-1] 这个条件？ 因为同一层中， 当走到 第二个1 时， 第一个1 的used状态已经被'回溯' 成false了，
            // 反之， 只要 第二个1 跟第一个1 不在一层， 第一个 1 的used 一定是 true
            if (i > 0 && !used[i-1] && nums[i] == nums[i-1] || used[i]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            backTrack();
            path.removeLast();
            used[i] = false;
        }
    }
}

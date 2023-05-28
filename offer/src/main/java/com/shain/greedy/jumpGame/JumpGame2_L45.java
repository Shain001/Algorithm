package com.shain.greedy.jumpGame;

import java.util.Enumeration;

public class JumpGame2_L45 {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
    // 相当于BFS
    // todo: this is shitting implementation, there is a better version of implementation with same logic, to tied to to think now
    public static int jump(int[] nums) {
        if (nums.length == 1 && nums[0] != 0) {
            return 0;
        }
        int furthest = 0;
        int steps = 0;
        int i = 0;
        while (i < nums.length) {
            int curFurthest = i + nums[i];
            // 防止 input = [0] 这种情况， 即一进来就卡住了。
            if (curFurthest > i)
                steps++;
            // 遍历当前格子所能到达的所有格子， 找到这些格子中能够跳的最远的， 就是当前格子要跳的目标。
            int j = i;
            while (j <= curFurthest) {
                if (j == nums.length-1){
                    return steps;
                }
                if (j+nums[j] > furthest) {
                    // 即代表， 下一次要从j起跳
                    i = j;
                    furthest = j+nums[j];
                }
                j++;
            }
        }

        return -1;
    }

}

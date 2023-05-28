package com.shain.greedy.jumpGame;

public class JumpGame1_L55 {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
    public static boolean jump(int[] nums) {

        // curFurthest 是遍历到i为止， 所能达到的最远距离， 注意， 不是当前从当前i能够跳到的最远距离， 最远距离可能是i-n那个格子跳出来的。
        int curFurthest = 0;

        // 此处i< nums.length 减不减1 都可， 减1逻辑上更通， 因为你要跳到最后一个格子， 不用管最后一个格子能跳多远。
        for (int i = 0; i < nums.length-1; i++) {
            // 如果走到一个格子以后， 发现之前的格子最远都跳不到这里， 就代表到头了。
            if (i > curFurthest) {
                break;
            }
            curFurthest = Math.max(curFurthest, i+nums[i]);
        }

        // 至此， 只需判断最远距离能不能超过数组最后一个即可。
        return curFurthest >= nums.length-1;
    }
}

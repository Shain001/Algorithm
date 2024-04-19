package com.shain.array.others;

public class LoopArray_L457 {
    private int[] nums;
    public boolean circularArrayLoop(int[] nums) {
        this.nums = nums;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }

            int slow = i;
            int fast = next(i);

            // 开始判断
            while (nums[fast] * nums[slow] > 0 && nums[slow] * nums[next(fast)] > 0) {
                if (fast == slow) {
                    if (next(slow) != slow) {
                        return true;
                    }

                    break;
                }
                fast = next(next(fast));
                slow = next(slow);
            }

            // 将判断过的路径全部清0， 进而提高速度
            while (nums[i] * nums[next(i)] > 0) {
                int temp = i;
                i = next(i);
                nums[temp] = 0;
            }
        }

        return false;
    }

    private int next(int i) {
        return ( (i + nums[i]) % nums.length + nums.length) % nums.length;
    }
}

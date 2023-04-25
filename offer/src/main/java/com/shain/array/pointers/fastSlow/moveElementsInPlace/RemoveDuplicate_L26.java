package com.shain.array.pointers.fastSlow.moveElementsInPlace;

public class RemoveDuplicate_L26 {
    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return -1;

        // 无需初始化 fast = 1, while 中会处理。
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
            fast ++;
        }
        return slow+1;
    }
}

package com.shain.array.pointers.fastSlow.moveElementsInPlace;

public class MoveZeros_L283 {
    public static void main(String[] args) {

    }

    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != 0)
                nums[slow++] = nums[fast];
            fast++;
        }

        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}

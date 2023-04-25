package com.shain.array.pointers.fastSlow.moveElementsInPlace;

public class RemoveElement_L27 {
    public static void main(String[] args) {

    }

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != val)
                nums[slow++] = nums[fast];
            fast++;
        }

        return slow;
    }

}

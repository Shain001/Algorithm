package com.shain.array.pointers.twoPointer.nSum;

public class TowSum2_L167 {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target)
                return new int[]{left + 1, right + 1};
            if (sum < target)
                left++;
            else
                right--;
        }


        return new int[]{-1, -1};
    }
}

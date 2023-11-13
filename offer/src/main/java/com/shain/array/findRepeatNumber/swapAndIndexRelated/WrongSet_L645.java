package com.shain.array.findRepeatNumber.swapAndIndexRelated;

import java.util.Arrays;

//todo: 位运算
public class WrongSet_L645 {
    public static void main(String[] args) {
        var test = new WrongSet_L645();
        System.out.println(Arrays.toString(test.findErrorNums(new int[]{1, 2, 2, 4})));
    }

    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]-1 != i) {
                if (nums[i] == nums[nums[i]-1]) {
                    result[0] = nums[i];
                    result[1] = nums[i]-1;
                    break;
                }

                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                result[1] = i+1;
                return result;
            }
        }

        return result;
    }
}

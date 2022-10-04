package com.shain.offer.chapter_2;

/**
 * @date: 04/10/2022
 */
public class FindRepeatNumberInArray {
    private static int[] nums;

    public static void main(String[] args) {
        nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeat());
    }

    public static int findRepeat() {
        for (int i = 0; i < nums.length; i++) {
            int curValue = nums[i];
            while (curValue != i) {
                if (curValue == nums[curValue])
                    return curValue;
                curValue = swap(i, curValue);
            }
        }
        return -1;
    }

    public static int swap(int from, int to) {
        int newCurValue = nums[to];
        nums[to] = nums[from];
        nums[from] = newCurValue;
        return newCurValue;
    }
}

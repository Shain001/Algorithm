package com.shain.array.randomNumber;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPickWithWeight_L528 {
    // preDiff
    static int[] boundaries;
    static int maxRandom;

    public static void main(String[] args) {
        boundaries = new int[]{1, 5, 7};
        System.out.println(pickIndex());
    }

    public RandomPickWithWeight_L528(int[] w) {
        boundaries = new int[w.length+1];
        boundaries[0] = 0;

        for (int i = 1; i < w.length+1; i++) {
            boundaries[i] = w[i-1] + boundaries[i - 1];
        }

        maxRandom = boundaries[boundaries.length - 1];
    }


    public static int pickIndex() {
        // 1. get a random number
         int random = ThreadLocalRandom.current().nextInt(1, maxRandom + 1);


        // 2. search the nearest random number among boundaries, return the index of the closest large number.
        int left = 0;
        int right = boundaries.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (boundaries[mid] >= random) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        // 减1 是因为 前缀和中的第1位对应着原数组中的第0位。
        return right-1;

    }
}

package com.shain.array.randomNumber;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPickWithWeight_L528 {
    // preDiff
    static int[] boundaries;
    static int maxRandom;

    public RandomPickWithWeight_L528(int[] w) {
        boundaries = new int[w.length + 1];
        boundaries[0] = 0;

        for (int i = 1; i < w.length + 1; i++) {
            boundaries[i] = w[i - 1] + boundaries[i - 1];
        }

        maxRandom = boundaries[boundaries.length - 1];
    }

    public static void main(String[] args) {
        boundaries = new int[]{1, 5, 7};
        System.out.println(pickIndex());
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
                left = mid + 1;
            }
        }

        // 减1 是因为 前缀和中的第1位对应着原数组中的第0位。
        return right - 1;

    }


    // V2 -> 一样的思路， 只不过 二分时 找的是 比target小的最大值， 省去了 prefix下标需-1的麻烦
    private Random random;
    private int[] prefix;

//    public Solution(int[] w) {
//        random = new Random();
//        prefix = new int[w.length+1];
//        int i = 1;
//        for (int num : w) {
//            prefix[i] = prefix[i-1] + num;
//            i++;
//        }
//    }

//    public int pickIndex() {
//        // 无需考虑 w = 0， 条件里面限制了不会为0
//        int r = ThreadLocalRandom.current().nextInt(0, prefix[prefix.length-1]);
//        int result = binarySearch(r);
//        return result;
//    }

    private int binarySearch(int r) {
        int left = 0;
        int right = prefix.length-1;

        while (left < right) {
            int mid = left + (right-left+1) /2;

            if (prefix[mid] <= r)
                left = mid;
            else
                right = mid-1;
        }

        // 区别在这
        return left;
    }
}

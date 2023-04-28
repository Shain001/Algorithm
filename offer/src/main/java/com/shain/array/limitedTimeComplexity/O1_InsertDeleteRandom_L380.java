package com.shain.array.limitedTimeComplexity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class O1_InsertDeleteRandom_L380 {
    private Map<Integer, Integer> map;
    private int[] nums;
    private int curLast;
    private Random random = new Random();

    public O1_InsertDeleteRandom_L380() {
        map = new HashMap<>();
        nums = new int[200000];
        curLast = -1;
    }

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;

        curLast += 1;
        nums[curLast] = val;
        map.put(val, curLast);

        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;


        int targetIndex = map.remove(val);
        if (targetIndex != curLast){
            map.put(nums[curLast], targetIndex);
            nums[targetIndex] = nums[curLast];
        }
        nums[curLast] = 0;
        curLast -= 1;
        return true;
    }

    public int getRandom() {
        return nums[random.nextInt(curLast + 1)];
    }
}

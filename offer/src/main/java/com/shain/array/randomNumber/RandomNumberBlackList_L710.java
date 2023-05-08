package com.shain.array.randomNumber;

import java.util.HashMap;
import java.util.Map;

public class RandomNumberBlackList_L710 {
    Map<Integer, Integer> blackListMap;
    private int maxIndex;

    public RandomNumberBlackList_L710(int n, int[] blacklist) {
        int last = n - 1;
        maxIndex = n - blacklist.length;
        blackListMap = new HashMap<>();

        for (int b : blacklist) {
            blackListMap.put(b, 666);
        }

        for (int b : blacklist) {
            if (b >= maxIndex) {
                continue;
            }
            while (blackListMap.containsKey(last)) {
                last--;
            }
            blackListMap.put(b, last);
            last--;
        }


    }

    public int pick() {
        int index = (int) (Math.random() * maxIndex);
        return blackListMap.getOrDefault(index, index);
    }
}

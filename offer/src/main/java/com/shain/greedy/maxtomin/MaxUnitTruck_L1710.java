package com.shain.greedy.maxtomin;

import java.util.Arrays;

public class MaxUnitTruck_L1710 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> {
            return o2[1] - o1[1];
        });

        int ans = 0;
        int selected = 0;
        for (int[] boxes : boxTypes) {
            int unit = boxes[1];
            int n = boxes[0];
            if (n + selected < truckSize) {
                ans += unit * n;
            } else {
                ans += (truckSize - selected) * unit;
                break;
            }
            selected += n;
        }

        return ans;
    }
}

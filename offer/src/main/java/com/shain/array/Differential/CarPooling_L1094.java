package com.shain.array.Differential;

public class CarPooling_L1094 {
    private int[] diff = new int[1001];

    public boolean carPooling(int[][] trips, int capacity) {

        // initialize
        for (int i = 0; i < trips.length; i++) {
            int from = trips[i][1];
            int to = trips[i][2];
            int passengers = trips[i][0];

            // get on
            diff[from] += passengers;
            diff[to] -= passengers;
        }


        // 检查负载
        int cur = diff[0];
        for (int i = 1; i < diff.length; i++) {
            if (cur > capacity)
                return false;
            cur = cur + diff[i];
        }
        return true;
    }
}

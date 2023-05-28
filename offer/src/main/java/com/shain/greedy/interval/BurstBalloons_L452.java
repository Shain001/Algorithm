package com.shain.greedy.interval;

import java.util.Arrays;
import java.util.Comparator;

public class BurstBalloons_L452 {
    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}));
    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int count = 0;

        int i = 0;

        while (i < points.length) {
            count++;
            int end = points[i][1];
            int j = i + 1;
            while (j < points.length && points[j][0] <= end) {
                j++;
            }
            i = j;
        }

        return count;
    }
}

package com.shain.greedy.interval;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals_L435 {
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }
    public static int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        int result = 0;
        int i = 0;
        while (i < intervals.length) {
            int end = intervals[i][1];
            int j = i+1;
            while (j < intervals.length && intervals[j][0] < end) {
                result++;
                j++;
            }
            i = j;
        }

        return result;
    }
}

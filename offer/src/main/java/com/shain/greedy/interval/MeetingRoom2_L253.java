package com.shain.greedy.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 本质上即统计， 整个坐标轴内 "最大重叠数量".
 */
public class MeetingRoom2_L253 {
    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{13, 15}, {1, 13}}));
    }

    public static int minMeetingRooms(int[][] intervals) {
        List<int[]> flat = new ArrayList<>();

        // 将各个区间扁平化到一个坐标轴中， 即投影到坐标轴上。
        // 当各个点投应到x轴上以后， 接下来需要做的， 只需判断每个点，是start还是end， 如果是start就+1， end就-1
        // 那么不妨直接通过以下结构， 将start的点赋值+1， end的点赋值-1。
        // 注意， 此时for循环以后， 我们已经把所有的点投影到了坐标轴上。
        // 但是为了让代码能够"从左到右遍历坐标轴， 我们需对List进行排序"
        for (int[] interval : intervals) {
            int[] start = {interval[0], 1};
            int[] end = {interval[1], -1};
            flat.add(start);
            flat.add(end);
        }

        // 排序时， 仅对start时间进行升序排列即可保证遍历的顺序为 s1, s2,s3,E2, s4, E3... （见图）
        // 但需注意， 在 s4=e1这种情况时， 要保证line 41 先进行-1操作， 否则会错误的多算一间会议室， 因为一个会议结束的同时另一个会议同时开始的话， 可以使用同一间屋子
        // 所以在两个点的值相等（o[0]）时， 要将 结束的那个点 （即o[1]=-1）排在前面，保证运算顺序。
        flat.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        int result = 0;
        int count = 0;

        while (!flat.isEmpty()) {
            count += flat.remove(0)[1];
            result = Math.max(count, result);
        }

        return result;
    }
}

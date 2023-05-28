package com.shain.greedy.interval;

import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching_L1024 {
    public static void main(String[] args) {
        System.out.println(videoStitching(new int[][]{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}}, 10));
    }

    /**
     * 要选出最少所需video数量， 只需：
     * 1. 对start 升序排序
     * 2. 在起点小于 E1 的所有区间， 选取最远的end。
     * 3. 重复进行, 直到 遍历完所有区间， 或者 end>=time。
     *
     * In conclusion: 只需： 永远选取最长的区间-> line13， 并且要保证 区间之间没有间隔 -> line14。
     * Some more to be noticed:
     *
     * 1. 为什么无需对end进行降序排序？
     * 原因在于 初始化了 curEnd=0; 并且 外层while中加了条件  clips[i][0] <= curEnd
     * 因此想象以下， 当第一次进入while时， 假设有三个区间 start = 0; 这个clips[i][0] <= curEnd （curEnd=0） 相当于for了所有start=0
     * 的区间， 然后选取了最长的区间的end值，当成了第一个curEnd；
     *
     * 2. 为什么 count 初始化为0 而不是1？
     * 同样是因为 curEnd 初始化成了0， 这使得 在第一次进入for循环时， 实际是在选取 "第一个区间"
     *
     * 3. 为什么这道题不像 L435, L 253 那样， 在while中初始化一个j， 然后另 i = j？
     * 因为这道题我们"仅需关注curEnd达到time要求"。 L435， L253 中的j， 其实作用相当于 从input列表中删除不用的区间， j的存在是简化了真正
     * 的删除这一操作。
     * 而这道题， 我们只需"挑选出" 最长且想连的几个区间， 无需删除， 因此"仅需关注curEnd达到time要求"
     *
     * 4. 对于 [[0,2],[4,8]] 这种输入， 怎么处理？
     *  这一输入， 无法进入 内层while循环， 导致curEnd无法更新， 然后被外层while中的 clips[i][0] <= curEnd 拒绝。
     *
     * 5. 如果clips输入中， 没有start=0 的区间怎么处理？
     * 与 Line20 第一点中的回答类似， 由于curEnd初始化为0， clips[i][0] <= curEnd这个外层判断条件。 如果没有start=0的区间，
     * 不会进入while循环， 那么自然return时会返回-1。
     *
     * 因为 while中的第一次循环， 就是在找 "start=0 的所有区间中， 最长的那一条"
     *
     *
     * @param clips
     * @param time
     * @return
     */
    public static int videoStitching(int[][] clips, int time) {

        Arrays.sort(clips, Comparator.comparingInt(o -> o[0]));

        int i = 0;
        int count = 0;
        int curEnd = 0;
        int newEnd = 0;


        while (i < clips.length && curEnd < time && clips[i][0] <= curEnd) {
            while (i < clips.length && clips[i][0] <= curEnd) {
                newEnd = Math.max(clips[i][1], newEnd);
                i++;
            }
            curEnd = newEnd;
            count++;
        }


        return curEnd >= time ? count : -1;
    }
}

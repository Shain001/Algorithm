package com.shain.greedy.maxtomin;

import java.util.Arrays;

public class Destroy_L2126 {
    /*
    证明贪心策略的正确性：

排序操作的合理性：

将所有的小行星按照质量从小到大排序。排序操作确保每次优先考虑质量最小的小行星，从而保证行星在初期尽可能多地增长质量，为后续摧毁更大质量的小行星做准备。
贪心策略的局部最优性：

设定当前行星质量为 cur，贪心策略选择当前可以摧毁的最小质量的小行星 a。如果 cur < a，行星无法摧毁小行星，直接返回 false。
如果 cur >= a，则摧毁小行星后，行星质量增加为 cur + a。这种选择最大化了当前局部的质量增长，使得行星更有可能摧毁后续的小行星。
贪心策略的全局最优性：

假设有一个更优的摧毁顺序能让行星摧毁所有小行星。根据贪心选择的定义，贪心选择会在最初期尽可能多地增加行星质量，使得后续摧毁大质量小行星的概率更大。因此，如果存在能摧毁所有小行星的顺序，贪心策略也能达到这一目标。
     */
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        long cur = mass;
        for (Integer a : asteroids) {
            if (cur < a) {
                return false;
            }
            cur += a;
        }

        return true;
    }

}

package com.shain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Temp {
    public static void main(String[] args) {
        var test = new Temp();
        System.out.println(test.findKthSmallest(new int[]{3,8,7}, 4));
    }

    public long findKthSmallest(int[] coins, int k) {
        if (coins.length == 1) {
            int ans = 0;
            for (int i = 0; i < k; i++) {
                ans += coins[0];
            }
            return ans;
        }
        Arrays.sort(coins);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
        int[] t = new int[coins.length];
        for (int i = 0; i < coins.length; i++) {
            t[i] = coins[i];
        }
        int i = 0;

        while (pq.size() < k) {
            while (pq.size() < k && i < coins.length-1 && coins[i] < 2 * coins[i+1]) {
                if (!pq.contains(coins[i]))
                    pq.add(coins[i]);
                coins[i] += t[i];
            }

            while (pq.size() < k && i == coins.length-1 && coins[i] < coins[i-1]) {
                if (!pq.contains(coins[i]))
                    pq.add(coins[i]);
                coins[i] += t[i];
            }

            i = (i+1) % coins.length;
        }

        return pq.peek();
    }
}

package com.shain.array.pointers.slidingWindow.multiPointer;

/**
 * 和 L930 一样
 */
public class NumberOfBeauty_L1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        int right = 0;
        int left = 0;
        int count = 0;
        int ans = 0;

        while (right < nums.length) {
            if (nums[right] % 2 != 0)
                count++;

            // 注意这个 移动left的逻辑必须写在 判断tempLeft的逻辑之前， 否则会漏判。
            // 因为每次移动right之后， 要先移动左指针， 找到符合条件的窗口以后， 才能开始在窗口中 找符合条件的子数组。
            // 而不是 先 判断窗口是不是符合条件的并且找子数组， 再移动left使窗口符合条件， 这样你每次判断的 子数组是 out of date 的。 有可能会漏掉一组子数组。
            // debug以下就清楚了， 逻辑理解上 就是因为以上原因。
            // e.g. 1,1,2,1,1 k = 3/ 当right 到末尾的时候， 还有一组子数组符合条件。 即，1,2,1,1 。 但是如果把两个while顺序调换， 这个解就会漏判， 因为 如果调换了的话， left移动以后， 窗口符合条件了， 但是 已经出了外层的while了，
            // 没有机会在++ans 了。
            while (count > k) {
                if (nums[left] %2 != 0) {
                    count--;
                }
                left++;
            }

            int tempCount = count;
            int tempLeft = left;
            while (tempLeft <= right && tempCount == k) {
                ans++;
                if (nums[tempLeft] % 2 != 0) {
                    tempCount--;
                }
                tempLeft++;
            }

            right++;
        }

        return ans;
    }

    /**
     * 根 L930 的方法二一个思路。 不过用了数组。
     * @param nums
     * @param k
     * @return
     */
    public int v2(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        int odd = 0, ans = 0;
        cnt[0] = 1;
        for (int i = 0; i < n; ++i) {
            odd += nums[i] & 1;
            ans += odd >= k ? cnt[odd - k] : 0;
            cnt[odd] += 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        var test = new NumberOfBeauty_L1248();
        System.out.println(test.numberOfSubarrays(new int[]{1,1,2,1,1}, 3));
    }
}

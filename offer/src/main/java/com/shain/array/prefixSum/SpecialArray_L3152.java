package com.shain.array.prefixSum;

/**
 * 注意思维灵活。
 *
 * 想到了需要的是一个boolean[]的累加和。
 * 但是boolean不行， 因为要便利【s, e】 中的所有元素看有没有false。
 *
 * 只要把true false变成0， 1 就行了。 然后利用累加和看 和是不是0即可。
 */
public class SpecialArray_L3152 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int[] isSpecial = new int[nums.length];
        int[] prefix = new int[nums.length+1];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == nums[i-1]%2) {
                isSpecial[i] = 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + isSpecial[i];
        }

        boolean[] ans = new boolean[queries.length];

        int k=0;
        for (int[] q : queries) {
            int from = q[0];
            int to = q[1];

            ans[k++] = prefix[to+1]-prefix[from+1] == 0;
        }

        return ans;
    }
}

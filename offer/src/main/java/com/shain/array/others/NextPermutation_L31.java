package com.shain.array.others;

/**
 * 找到 输入数组的下一个字典序全排列
 *
 * 注意，不是全排列的题， 是单纯找规律然后操作数组。
 *
 * 看这个题解即可。
 * https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/?company_slug=microsoft
 *
 * 唯一重点在于找规律， 规律如下：
 * 标准的 “下一个排列” 算法可以描述为：
 *
 * 从后向前 查找第一个 相邻升序 的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
 * 在 [j,end) 从后向前 查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
 * 将 A[i] 与 A[k] 交换
 * 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
 * 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 *
 */
public class NextPermutation_L31 {
    private int[] nums;
    public void nextPermutation(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        int bound=0;
        int i = len-1;
        while (i >= 0) {
            if (i-1 >= 0 && nums[i] <= nums[i-1]) {
                i--;
                continue;
            }
            bound = i;
            break;
        }

        if (i == 0) {
            reverse(0, len-1);
            return;
        }

        i = len-1;

        while (i >= bound) {
            if (nums[i] > nums[bound-1]) {
                break;
            }
            i--;
        }

        swap(i, bound-1);
        reverse(bound, len-1);
        return;

    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int i, int j) {
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }
    }
}

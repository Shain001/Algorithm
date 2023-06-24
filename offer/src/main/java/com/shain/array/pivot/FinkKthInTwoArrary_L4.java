package com.shain.array.pivot;

public class FinkKthInTwoArrary_L4 {
    public static void main(String[] args) {
        var test = new FinkKthInTwoArrary_L4();
        System.out.println(test.findMedianSortedArrays(new int[]{1}, new int[]{-1,3,4,9,11,15,80,99}));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums2.length + nums1.length;

        if (len % 2 == 0) {
            return (double) (getKthSmallest(nums1, nums2, 0, 0, len / 2) +
                    getKthSmallest(nums1, nums2, 0, 0, len / 2 + 1)) / 2;
        } else {
            return getKthSmallest(nums1, nums2, 0, 0, len / 2 + 1);
        }
    }

    public int getKthSmallest(int[] nums1, int[] nums2, int start1, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }

        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 注意这两行的理解， 这两行的的作用是： 如果当前， 其中一个数组剩余长度已经不够 k/2 个元素了， 那么把他的 start + k/2 处的值当成无穷大
        // 进而强行抛弃 另一个数组的前k/2个元素。
        // 为什么可以这样？ 首先， 假设 list1 的剩余长度已经不够 k/2 了， 这代表 目标数一定在list2 中， 并且这个target一定在 当前start的更后方
        // which means, 就算list2 被抛弃的元素 比 list1 中剩余元素小 （即正常不应该扔它们），也不会影响正确答案。
        // 这样操作以后， 再经过n次迭代以后， 由于 k的值不断在减小， 所以最终一定会有一个 <= list1 剩余长度的k值出现， 然后可以正常进行比较进行判断。
        int val1 = (start1 + k / 2 - 1 < nums1.length) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int val2 = (start2 + k / 2 - 1 < nums2.length) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;

        if (val1 <= val2) {
            return getKthSmallest(nums1, nums2, start1 + k / 2, start2, k - k / 2);
        } else {
            return getKthSmallest(nums1, nums2, start1, start2 + k / 2, k - k / 2);
        }
    }
}

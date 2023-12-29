package com.shain.array.pointers.twoPointer;

public class Merge_L88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;

        while (p >= 0) {
            if (m < 1) {
                nums1[p--] = nums2[n - 1];
                n--;
                continue;
            }
            if (n < 1) {
                nums1[p--] = nums1[m - 1];
                m--;
                continue;
            }

            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[p--] = nums1[m - 1];
                m--;
            } else {
                nums1[p--] = nums2[n - 1];
                n--;
            }
        }
    }
}

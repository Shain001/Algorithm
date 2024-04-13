package com.shain.sort.mergeSort;

public class CountInversions_LCR170 {
    private int ans = 0;

    public int reversePairs(int[] record) {
        if (record.length == 0) {
            return 0;
        }
        mergeSort(record, 0, record.length - 1);
        return ans;
    }

    private int[] mergeSort(int[] record, int left, int right) {
        if (left == right) {
            return new int[]{record[left]};
        }

        int mid = left + (right - left) / 2;
        int[] leftSorted = mergeSort(record, left, mid);
        int[] rightSorted = mergeSort(record, mid + 1, right);
        return merge(leftSorted, rightSorted);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int lp = 0;
        int rp = 0;
        int i = 0;
        while (lp < left.length && rp < right.length) {
            if (left[lp] <= right[rp]) {
                result[i++] = left[lp++];
            } else {
                // 逆序对的计算应该增加左侧数组剩余的元素数量，
                // 而不是右侧数组的索引位置。此外，ans += rp + 1; 这行代码逻辑有误，应该改为增加left.length - lp，
                // 因为每次右侧数组的元素小于左侧数组的元素时，意味着左侧数组当前元素及其之后的所有元素都与这个右侧元素构成逆序对。
                result[i++] = right[rp++];
                ans += left.length - lp;
            }
        }

        while (lp < left.length) {
            result[i++] = left[lp++];
        }

        while (rp < right.length) {
            result[i++] = right[rp++];
        }

        return result;
    }
}

package com.shain.array.binarySearch;

public class ValidSqure_L367 {
    public boolean isPerfectSquare(int num) {
        int left = 0;
        // 可以取成一半， 更快一点
        int right = num/2+1;

        while (left <= right) {
            int mid = left + (right-left) / 2;

            // 注意转成long， 否则溢出
            if ((long)mid*mid == num) {
                return true;
            } else if ((long)mid*mid > num) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return false;
    }
}

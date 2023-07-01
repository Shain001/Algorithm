package com.shain.array.pointers.twoPointer.binarySearch;

public class XSquart_L69 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;

        while (left < right) {
            long mid = left + (right - left) / 2 + 1;

            if (mid <= x / mid)
                left = (int) mid;
            else
                right = (int) mid - 1;
        }

        return left;
    }
}

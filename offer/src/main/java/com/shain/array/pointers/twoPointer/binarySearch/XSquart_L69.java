package com.shain.array.pointers.twoPointer.binarySearch;

/**
 * 这题两个关键， 1。 对于溢出的处理。
 * 2. 他本质上就是搜索 小于等于target的数。 target存在就返回target， 不存在就返回小于且最接近target的数
 *
 *
 */
public class XSquart_L69 {
    public static void main(String[] args) {
        var test = new XSquart_L69();
//        System.out.println(test.mySqrt_test(2147483647));
    }
    public int mySqrt(int x) {
        int left = 0;
        int right = x;

        while (left < right) {
            // 这里之所以 把+1 写在了外面， 是因为 写在里面会溢出， e.g. input = 2147483647 时 会溢出。
            long mid = left + (right - left) / 2 + 1;

            if (mid <= x / mid)
                left = (int) mid;
            else
                // 为什么这里写 right = mid -1? 因为如果 mid*mid > x 时， 当前mid绝对不可能是答案了。 所以可以放心将当前mid排除出下一个区间。
                right = (int) mid - 1;
        }

        return left;
    }

//    public int mySqrt_test(int x) {
//        int left = 0;
//        int right = x;
//
//        while (left < right) {
//            long mid = left + (right - left + 1) / 2;
//
//            if (mid <= x / mid)
//                left = (int) mid;
//            else
//                right = (int) mid - 1;
//        }
//
//        return left;
//    }
}

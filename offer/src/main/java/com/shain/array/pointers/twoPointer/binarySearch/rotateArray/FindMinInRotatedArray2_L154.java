package com.shain.array.pointers.twoPointer.binarySearch.rotateArray;

public class FindMinInRotatedArray2_L154 {
    public static void main(String[] args) {
        int[] test = new int[]{3, 1, 3};
        System.out.println(findMin(test));
    }


    public static int findMin(int[] nums) {
        if (nums.length == 0)
            return -1;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
                // NOTE: 如果都写if 会出错， 除非加continue， 因为倒数两个if 都在判断right， 而right又被line22 修改了
                // NOTE2: 该题与 L153 唯一区别在于数组中会有重复元素
                // 那么正常来说， 只需在while中判断 当nums[mid] == nums[right] 时， 区间应该往 mid的哪个方向移动即可
                // However, the difficulty 在于， 在mid的哪个方向都有可能。
                // e.g. [0, 1, 1, 1, 1, 1, 1] & [1, 1, 1, 1, 0, 1, 1]
                // 所以解决方案为： 如果相等时， 只需要将right向左移动一位即可。
                // why?
                // 假设target在mid的左侧 -> right - 1 可以使mid向左移
                // 假设target在mid的右侧 -> right -1 最终会使得right到达target位置 （e.g. line 27, right 最后会指向0）， 之后left会与right汇合出循环。
                // 假设target即mid， [1,1,0,0,0] -> right -1 以后， 会进入到正常逻辑
            else if (nums[mid] == nums[right])
                right--;
        }

        return nums[left];
    }

    /**
     * update more：
     * <p>
     * 当 mid 大于或者小于 right时， 与153 一样的操作。
     * <p>
     * 唯一问题在于 为什么 相等时 可以让 right--。
     * <p>
     * 根本原因在于， 原本的数组在旋转之前是生序的对吧。 这代表着， 如果有一段元素是重复的， 整个数组的最小值一定：
     * 要么是这段重复的元素， 要么是这段重复元素 左侧的元素。
     * <p>
     * 如果 最小值就是这段重复元素， 那么让right--， 最终会逼仄到 这段重复元素的 最左边的元素， e.g. 2,2,2,2,2 中的第一个2
     * 如果 最小值 在这段元素的 左边， 让right--， 也会使得 mid "一位一位的"左移，左移到一定程度， 一定会让mid达到一个新的值，  然后 继续二分找到 最小元素。
     * <p>
     * 而之所以 这样right-- 能够确定成立， 是因为我们知道， 如果当前 left right 已经夹到一个区间了， 我们的target一定是在这个区间中的。
     * <p>
     * 即， 我们知道 在 mid 大于或者小于 right 这个情况时， 我们对于区间的改变是一定正确的。
     * <p>
     * 想象一下 left right 中第一次使得 mid 到了一段重复数字。 之前的操作只有 mid大于或者小于 right， 我们能够知道之前抛弃的区间肯定都是对的。
     * 即我们知道 target此时一定在 left right 这个新区间之中。
     *
     * @param nums
     * @return
     */
    public int findMin_revie(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] == nums[right]) {
                right--;
            }
        }

        return nums[left];
    }
}


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
}

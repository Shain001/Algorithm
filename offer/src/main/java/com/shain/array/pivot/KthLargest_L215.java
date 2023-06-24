package com.shain.array.pivot;

public class KthLargest_L215 {
    public static void main(String[] args) {
        var test = new KthLargest_L215();
        System.out.println(test.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }


    public int findKthLargest(int[] nums, int k) {
        int index = -1;
        int left = 0;
        int right = nums.length-1;
        while (index != nums.length-k) {
            index = getPivot(nums, left, right);

            // 必要， 否则死循环
            if (index < nums.length-k+1) {
                left = index+1;
            } else
                right = index-1;

        }

        return nums[index];

    }

    private int getPivot(int[] nums, int left, int right) {
        int pivot = nums[left];
        int lt = left;  // 小于 pivot 的指针
        int gt = right;  // 大于 pivot 的指针
        int i = left;  // 当前遍历的指针

        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i, lt);
                i++;
                lt++;
            } else if (nums[i] > pivot) {
                swap(nums, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        return lt;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}

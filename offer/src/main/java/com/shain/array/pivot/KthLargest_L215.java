package com.shain.array.pivot;

import java.util.PriorityQueue;

public class KthLargest_L215 {
    public static void main(String[] args) {
        var test = new KthLargest_L215();
        System.out.println(test.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    /**
     * 最坏情况时间复杂度：O(n^2)
     *
     * 当分区步骤总是选择最大或最小的元素作为枢轴时，会发生最坏情况，导致不平衡的分区。在这种情况下，每次递归调用处理的数组子集只比之前的子集小一个元素，导致时间复杂度为O(n^2)。
     *
     * 相当于程序在while中 运行了 n 个数据（第一次getPivot） + n-1 (第二次) + n-2 + .. + 1 -> 根据累加和公式， 为 O(n^2)
     *
     *
     *
     * 平均情况时间复杂度：O(n)
     *
     * 平均来说，分区会将数组分成两半，每次递归处理一半大小的数组，所以平均时间复杂度是O(n)。这是因为我们每次至少可以排除掉一半的元素，递归的深度大约是O(log n)，每层的工作量大约是O(n)，所以总的平均时间复杂度是O(n)。
     * 最好情况时间复杂度：O(n)
     *
     * 平均来说， 每次 getPivot 总共处理了 : n + 1/2 n + 4/1 n + 1/8 n + ... + 1 -> 和为 O (2n)
     *
     * 最好情况发生在每次都能恰好将数组分成两等份的情况下。虽然最好情况的时间复杂度也是O(n)，但是这种情况下的常数因子比平均情况要小，因为每次分区都非常平衡。
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;
        while (index != nums.length - k) {
            index = getPivot(nums, left, right);

            // 必要， 否则死循环
            if (index < nums.length - k + 1) {
                left = index + 1;
            } else
                right = index - 1;

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

    public int v2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Integer n : nums) {
            pq.add(n);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }

}

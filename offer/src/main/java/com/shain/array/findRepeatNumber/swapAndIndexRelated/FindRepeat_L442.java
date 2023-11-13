package com.shain.array.findRepeatNumber.swapAndIndexRelated;

import java.util.ArrayList;
import java.util.List;

public class FindRepeat_L442 {
    public static void main(String[] args) {
        var test = new FindRepeat_L442();
        System.out.println(test.findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
    }
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // 这题与 swap.FindRepeatNumber_1 的区别在于
        // 虽然都是用原地交换， 并且都是要把元素放到对应下标。
        // 但是， swap 中的那个题， 是 在一个下标处循环， 直到"该下标找到了元素"以后才移动指针
        // 这道题是 "给每个元素找到对应的下标"，即指针不会一直在 index=0处循环。
        // 因为 swap 中是 只有一个元素重复， 再给下标找元素的过程中一定会找到重复的那个数。
        // 但是这道题， 有多个数字重复， which means 大部分下标是没有自己对应的元素的。
        // 比如4,3,2,7,8,2,3,1 ； 在下标0处， 你会产生死循环。
        // 所以 这题是 "给当前指针指向的元素值 找到自己的位置" -》 此时， while循环的条件应该是 while (nums[nums[i]-1] != nums[i])
        // 放完以后， 在遍历数组，找到下标不对应的元素即可
        // 为什么不能是if？ debug以下就知道了
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[i]-1 != i && nums[i] != nums[nums[i]-1]) {
//                int temp = nums[i];
//                nums[i] = nums[nums[i]-1];
//                nums[nums[i]-1] = temp;
//            }
//        }

//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i-1) {
//                int temp = nums[nums[i]-1];
//                nums[nums[i]-1] = nums[i];
//                nums[i] = temp;
//            }
//        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i]-1] != nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                result.add(nums[i]);
            }
        }

        return result;
    }

    public List<Integer> method2(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // 也是利用元素值与下标的对应关系。
        // 但是instead of swapping
        // 我们直接将 下标， 当作了一个 类似hash 表
        // 当指针走向一个元素， 由于元素肯定能找到对应的下标， 那么我们可以直接将 当前元素对应的下标 取为负数。
        // 这样， 当第二次走到该元素， 通过判断其对应的下标处的 元素值是不是负的， 就能判断当前元素是否已经出现过， 进而判断重复。
        // 那么不难发现， 这种方法成立的前提是 元素取值为 【1,n] 不能有0。
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])-1] > 0) {
                nums[Math.abs(nums[i])-1] *= -1;
            } else {
                result.add(Math.abs(nums[i]));
            }
        }

        return result;
    }

}

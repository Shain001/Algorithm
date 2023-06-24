package com.shain.array.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_L15 {
    /**
     * 维护三个指针。
     * i -> 依次遍历所有数字 （需跳过重复）， 进而得到一个 当前需要的target
     * j,k-> 将 i+1 - end 视作一个新数组， 分别从首尾开始， 寻找和尾target的下标。
     *
     * note:
     *  1. 由于数组以排序， j, k 移动哪一个根据 当前sum与target大小关系判断。
     *  2. 之所以threesum不再需要hashmap， 因为已经排了序， 根据point1 的优化已经足够。
     *  3. 由于已经拍了序， 当 nums[i] 已经大于target （这题来说是0）以后， 已经可以break， 因为i以后的数 nums[i] 大， 已经不可能有正确答案。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                break;
            if (i >= 1 && nums[i-1] == nums[i])
                continue;


            int target = 0 - nums[i];
            int j = i+1;
            int k = nums.length-1;

            while (j < k) {

                int sum = nums[j] + nums[k];

                if (sum == target){
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    while (j < k && nums[j] == nums[j-1]) {
                        j++;
                    }

                    while (j < k && nums[k] == nums[k+1]) {
                        k--;
                    }
                }
                else if (sum > target) {
                    k--;
                }
                else {
                    j++;
                }
            }
        }

        return result;

    }
}

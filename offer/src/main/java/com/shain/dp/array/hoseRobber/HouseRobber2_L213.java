package com.shain.dp.array.hoseRobber;

public class HouseRobber2_L213 {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 1, 1}));
    }

    /**
     * 与1 的区别在于， 数组是环形的， 所以无法想 robber1 一样初始化dp0。
     * 解决方法即 通过分成两个dp数组， 把数组再次变成线性， 然后比较两个数组的dp-1 最大值。
     * 两个数组即对应着： 抢第一家， 及不抢第一家。
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 包括第0家， 可偷范围 0 - n-1 结束
        int[] dp1 = new int[nums.length - 1];
        // 不包括第0家， 但包括最后一家， 范围 1 - n
        int[] dp2 = new int[nums.length - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);

        // 注意i下标的对应关系
        // 此处两个for要实现的逻辑， 跟 把nums copy 两份， 分别为 0-n-1 跟 1-n 一样。
        // 只不过为了节省空间， 这里利用指针实现相同目的。
        // dp1， dp2 的长度都是len-1， 因为只有n-1个可偷元素。
        // 两个for循环中的i， "都是指向dp的指针， 而非nums"
        // 第一for， 循环dp1， 由于第0家可偷， 所以对应nums为 nums[0-n-1], dp1的i指针正好与nums中的指针一样， 即dp1[0] 指向的正好是nums[0]
        // 第二个for， 循环dp2, 从第1家开始头， 对应nums为[1-n]， 即dp2[0] 对应这 nums[1]， 所以第二个for中的i，对应这第i+1个nums元素。

        // update 26/06/2023: for中的i还是对应 nums 好理解点， 这里没改， 看leetcode提交记录把。都一样但是反正。
        for (int i = 2; i < nums.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
        }

        for (int i = 2; i + 1 < nums.length; i++) {
            dp2[i] = Math.max(dp2[i - 2] + nums[i + 1], dp2[i - 1]);
        }
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}

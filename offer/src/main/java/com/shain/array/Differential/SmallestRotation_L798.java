package com.shain.array.Differential;

/**
 * point 1:
 * 要找 得分最多的 k 值， 则 无非是 算出 各个k值能够得到的分数， 然后找到 result。
 *
 * 则正常的思路是-> 取 k = 1,2,3,... 先让k=1， 然后 挪数组， 计算得分， 记录得分。 再让k=2， .....
 * 该暴力方法， 时间复杂度为 O(n^2)，因为k有n个， 挪动数组又需要 On。
 *
 * 但是暴力方法忽略了一个 point, 即， "对于每一个元素， 能够使得元素得分的下标 其实是有范围的"， 那么不妨 只遍历一遍数组， 在遍历时对于每个元素，
 * 计算能够使其得分的 k 的范围， 对这个范围内的 k 值 统一 +1， 当所有元素都遍历结束后， 所有的 k 值能够得到的分数也就确定了。
 *
 * 即相当于 你填一个table， row是k， column是 各个元素， 暴力方式是 从左到右， 从上到下的填满这张表格， 优化的方法是 从上到下， 从左到右的填。
 *
 * 进而， 当对于每个元素的 能够得分的 k 的范围确定以后， 记录每个k能够得分的这个过程， 自然就可以想到差分。
 *
 * Point 2: How to find the range of valid K?
 *
 * 1. 首先确定， 对于下标i， 左移 k 位， 则 挪动后的下标值为 i-k, but 如果i-k 小于0， 则 挪动后的下标应为 i-k+n where n is length of array
 *    不妨将 大于0 小于 0 的情况合并， 则 挪动后的下标值可统一表示为 (i-k+n) % n
 * 2. 其次， 设 元素值为 x， 记下标 为 i， 那么当前i 能够得分的范围为 [x, n-1]
 * 3. 将i 替换为 (i-k+n)%n 有： (i-k+n)%n >= x --> (i-x+n)%n >= k 进而确定 k的上界
 * 4.  (i-k+n)%n <= n-1 -->  (i-n+1+n)%n <= k ---> k >= (i+1) % n
 * 5. Finally, 我们得到对于每一个 元素， 能够使其得分的 k 的范围为： [(i+1)%n, (i-x+n) % n], 左右均为闭区间。
 *
 *
 * Conclusion:
 * 这题本质上是个数学题， 其实没有什么太大的意义。 但是 这种
 * "发现 k 的范围， 然后 想到 '转换填表格的方向' 这种思想是值得理解的"
 *
 */
public class SmallestRotation_L798 {
    public int bestRotation(int[] nums) {
        int length = nums.length;
        int[] diffK = new int[length +1];

        for (int i = 0; i < length; i++) {
            int lowIndexOfK =  (i + 1) % length;
            // 注意此处的 +1， 是因为k的范围是闭区间， 则需要找到差分数组中区间外的第一个index。
            // 由于 k 的最小值是 (i+1) % n， 则可以发现 k不可能=0
            // 即， 差分数组diff中， k的值与index of diff 的值是直接对应的， 即 k=1 就对应着 index=1。
            // 所以 当对 k = [1, 8] 更新score时， 要进行 - x 的端点就是 9。
            // 并且这里的+1 是不能写在%外面的， 即不能写成
            // int highIndexOfK = (i - nums[i] + nums.length) % nums.length + 1;
            // 因为这样就可能超届了。
            int highIndexOfK = (i - nums[i] + length + 1) % length;

            diffK[lowIndexOfK] += 1;
            diffK[highIndexOfK] -= 1;
        }

        int maxScore = 0;
        int result = 0;
        int count = 0;
        for (int i = 1; i < diffK.length; i++) {
            count += diffK[i];
            if (count > maxScore) {
                result = i;
                maxScore = count;
            }
        }

        return result;
    }
}

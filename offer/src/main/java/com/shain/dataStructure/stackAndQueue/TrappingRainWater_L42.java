package com.shain.dataStructure.stackAndQueue;

/**
 * 单调栈。
 *
 * 1. stack里存的都是 递减区间。 每当遇到一个递增区间， 就开始pop， 计算。 因为每当遇到一个递增区间， 代表着形成了 凹槽。
 *
 * 问题：
 * 1. pop到什么时候停止？pop 到 stack为空， 或者， 栈顶元素 大于当前 element being pushed
 * 2. 每次pop要做什么？ ->
 *      计算面积。 怎么计算？ -> width * height;
 *
 *      width = 当前要push 的 元素的 index - left - 1 where left 等于 pop出当前栈顶元素以后， 新的栈顶元素。 （one important point:
 *      这种计算方式， 相当于 横着一层一层的计算， which is why width = index - left - 1 todo: to be think further later）.
 *
 *      height = 要push的元素的高度， 与 pop出当前栈顶元素以后， 新的栈顶元素 的 高度 取最小。 - 当前pop出的元素。
 *
 * 3. 是每次pop都要计算面积， 或者记录一个值， 或者进行一次操作？ 还是 pop结束以后， 在进行操作。
 *      每次pop都在计算， 但是， 当pop出去一个元素以后， 新的栈顶元素 与 pop的元素高度相同时， 实际计算出来的面积是0。 所以没有影响。
 *      而由于 width 值是通过 left计算出来的， 所以pop到 下一个栈顶元素 有高度时， 能得出正确面积。
 *
 * Tips.
 */
public class TrappingRainWater_L42 {
    public int trap(int[] height) {
        return -1;
    }
}

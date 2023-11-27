package com.shain.array.randomAlgorithms;

import com.shain.common.linkList.ListNode;

import java.util.Random;

/**
 * 补充随机性证明笔记/blog
 *
 * 重点在于理解， 对于一个动态的stream， 该算法要保证 每个数字， 都有1/n 的可能性是最终结果。
 *
 * 那么由于是动态的， 就有一个问题， 当我遍历到一个新的 元素， 需要有概率 选定当前元素， 代替我之前已经做出的选择。
 *
 * 所以对于最终概率的计算，实际由两部分组成。
 *
 * 对于每个元素， 其是最终被选定的结果的概率= 遍历到该数字时选到他的概率 * 遍历到后面数字以后， 一直没有替换掉他的概率。
 *
 * https://labuladong.github.io/algo/di-san-zha-24031/shu-xue-yu-659f1/tan-tan-yo-b4bb5/
 * 公式推论看这个图把， 懒得写一看就懂了。
 */
public class ReservoirAlgorithm_L382 {
    private int result;
    private Random random;
    private ListNode head;

    public ReservoirAlgorithm_L382(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        ListNode p = this.head;
        int count = 1;

        while (p != null) {
            // radom.nextInt取值左闭右开
            if (random.nextInt(0, count) == 0) {
                this.result = p.val;
            }

            p = p.next;
            count++;
        }

        return result;
    }
}

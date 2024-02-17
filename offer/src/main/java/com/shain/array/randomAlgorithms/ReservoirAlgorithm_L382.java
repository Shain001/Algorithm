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
 * https://labuladong.github.io/algo/frequency-interview/random-algorithm/#%E6%B0%B4%E5%A1%98%E6%8A%BD%E6%A0%B7%E7%AE%97%E6%B3%95
 * 公式推论看这个图把， 懒得写一看就懂了。
 *
 *
 * 补充：
 * 当选取的元素不是1个， 而是m个时 （也包含 数组不是一个stream， 而是从n个元素中选取m个，一样的）：
 *
 * 选取第i个元素的概率变为 m/i, 而不是 1/i.
 * 证明时， 每个元素被选取的概率都是 m/n, 而不是 1/n
 * todo： 推导证明过程。
 *
 * import (
 *     "fmt"
 *     "math/rand"
 *     "time"
 * )
 *
 * func generateRandomSample(n int, m int) []int {
 *     rand.Seed(time.Now().UnixNano()) // 初始化随机数种子
 *
 *     // 初始化样本集合
 *     sample := make([]int, m)
 *     for i := 0; i < m; i++ {
 *         sample[i] = i + 1
 *     }
 *
 *     // 从第 m+1 个元素开始处理
 *     for i := m + 1; i <= n; i++ {
 *         // 以 m/i 的概率决定是否替换样本集合中的元素
 *         r := rand.Intn(i) + 1 // 生成一个 [1, i] 范围内的随机数
 *         if r <= m {
 *             // 随机选择样本集合中的一个元素进行替换
 *             replaceIndex := rand.Intn(m)
 *             sample[replaceIndex] = i
 *         }
 *     }
 *
 *     return sample
 * }
 *
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

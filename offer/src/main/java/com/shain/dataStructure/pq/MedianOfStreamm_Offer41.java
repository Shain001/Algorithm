package com.shain.dataStructure.pq;

import java.util.PriorityQueue;

class MedianOfStreamm_Offer41 {
    private PriorityQueue<Integer> low;
    private PriorityQueue<Integer> high;
    private boolean isEven;

    public MedianOfStreamm_Offer41() {
        this.low = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        this.high = new PriorityQueue<Integer>();
        this.isEven = false;
    }

    /**
     * 总体来说， 我们需要保证：
     * 0. 为什么要维护两个pq？ 因为是stream， 元素不停再来， 长度是动态的， 我们无法维护一个固定长度的pq来维护中位数。
     * 1. 两个pq的大小最多只能差1 -> 这是为了为了能计算到中位数。 具体的，we need to ensure 所有元素都被排序了， 只不过排序以后被分成了两份，一份大的一份小的。
     * 2. 其次， 当元素总量为奇数时， 我们需要保证多的那一个元素在 low 中。为什么？ 因为low是 最大堆， high是最小堆。 所以如果把多出来的这个数放在high中，那么第 n/2+1 个元素就在high中，但是high只能得到最小值， 所以 我们无法正确得到 median的值。
     * 换句话说， 我们永远要保证： 两个pq的长度， 要么相同， 要么是 low比high大1.
     * 3. 那么为什么 low 必须是大顶堆， high必须是小顶堆？ 因为对于 计算中位数来说， e.g. 1,2,3 /split/ 4,5,6. 我们需要3 和 4， 而1，2，3 在low中， 为了得到3 自然要维护大顶堆
     * 4,5,6 在high中， 为了得到 4， 自然要维护小顶堆。
     * 4. 另外一个要注意的问题： 每当有新元素进来， 实际是需要有一个 排序的过程的， 只不过这个排序的过程我们是利用pq做了。 但是仍然有一个问题， 在对新元素x进行排序之前， 我们怎么知道要把x
     * 放到哪个堆？ 答案是我们其实不知道， 也不care， 我们要做的只是 插入x以后保证 low 和 high都是有序的， 同时保证 两个pq的长度满足 point2 的要求。
     * 所以每当我们要插入 一个 元素到 low或者high时（假设插入的那个pq代号Q）， 我们需要先将新元素x 插入到另一个pq中， 这一步的目的是进行排序， 然后从x第一次的pq中把堆顶元素拿出来， 放到Queue中。
     * 此时 放进去Q的元素不一定是x， 可能是任何一个元素， 但是它一定是 Q中 最大/小 的那个， 进而保证了 low中所有元素都 <= high
     *
     * @param num
     */
    public void addNum(int num) {
        // 不相等 -> 一定是 high < low -> 插入high中。
        if (low.size() != high.size()) {
            // 想插入high， 先利用low进行排序
            low.add(num);
            // 此时 low.size = high.size + 2, 从low中得到最大的， 加入high中， 两者长度相等，且 all values in low <= high
            high.add(low.poll());
        } else {
            high.add(num);
            low.add(high.poll());
        }
        isEven = !isEven;
    }

    public double findMedian() {
        return isEven ? low.peek() : 0.5 * (low.peek() + high.peek());
    }
}
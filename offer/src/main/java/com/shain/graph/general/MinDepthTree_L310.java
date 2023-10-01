package com.shain.graph.general;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinDepthTree_L310 {
    private int[] head;
    private int[] neighbors;
    private int[] nextNeighbor;
    private int[] maxDepthDown;
    private int[] secondDepthDown;
    private int[] maxDepthUp;
    private int[] provider;

    private int edgeIdx;

    /**
     * 向邻接表中添加边。
     *
     * 三个数组作用：
     *
     * head 数组的下标， 对应着 每个节点的值。 元素值对应着当前节点的第一个邻居在 nextNeighbor 中的下标。
     * 而 neighbors 数组中的每个元素的值， 就是 节点的值。
     * nextNeighbors 中每个元素的值， 为neighbors数组中的一个下标。
     *
     * 即， head 与 nextNeighbor 中的值， 都是指向 neighbors 中的某一个元素。
     *
     * 如果 nextNeighbor[n] = -1, 表示当前头节点没有更多的neighbors。
     *
     *
     * 插入过程：
     * below is from ChatGPT, but basically the point is that head数组中各个下标处的元素值是在变化的， 其始终指向 最新插入的neighbor，
     * 也即所谓头插法
     *
     * 例子：
     * 假设我们有以下的边需要添加到图中：
     *
     * 0 -> 1
     * 0 -> 2
     * 1 -> 2
     * 这些操作的执行顺序如下：
     *
     * 添加边 0 -> 1：
     *
     * toNode[0] = 1
     * nextNode[0] = head[0]（初始值是-1，因为目前还没有为节点0添加任何边）
     * head[0] = 0
     * 添加边 0 -> 2：
     *
     * toNode[1] = 2
     * nextNode[1] = head[0]（目前的值是0，因为我们已经为节点0添加了一条边）
     * head[0] = 1
     * 添加边 1 -> 2：
     *
     * toNode[2] = 2
     * nextNode[2] = head[1]（初始值是-1，因为目前还没有为节点1添加任何边）
     * head[1] = 2
     * 此时，邻接表为：
     *
     * head = [1, 2, -1]
     * toNode = [1, 2, 2]
     * nextNode = [-1, 0, -1]
     *
     *这意味着：
     *
     * 节点0有两个相邻节点：从head[0]开始，第一个相邻节点是toNode[1]=2，然后从nextNode[1]取得下一个索引是0，所以第二个相邻节点是toNode[0]=1，之后就没有更多相邻节点了。
     * 节点1只有一个相邻节点：从head[1]开始，是toNode[2]=2，之后没有更多相邻节点了。
     * 节点2没有相邻节点。
     *
     * @param start 当前边的起始节点
     * @param end 当前遍的结束节点
     */
    private void addEdge(int start, int end) {
        neighbors[start] = edgeIdx;
        nextNeighbor[edgeIdx] = head[edgeIdx];
        head[start] = edgeIdx;
        edgeIdx++;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 保证 nextNeighbor[edgeIdx] 处能够总是先初始化为-1
        Arrays.fill(head, -1);

        // initiate
        head = new int[n];
        neighbors = new int[4*n];
        nextNeighbor = new int[4*n];
        maxDepthDown = new int[n];
        secondDepthDown = new int[n];
        maxDepthUp = new int[n];
        provider = new int[n];

        // 建立邻接表
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            addEdge(a, b);
            addEdge(b, a);
        }

        // todo: debug and conrim understand why this can traverse every node
        getMaxAndNextMaxChildDepth(0, -1);
        getMaxParentDepth(0, -1);

        // 注意， 这题求的是 能够使整颗树高度最小的根。
        // 所以， 要求每个根的最大高度， 然后取最小值。
        List<Integer> roots = new ArrayList<>();
        int minHeight = n;
        for (int i = 0; i < n; i++) {
            int currentHeight = Math.max(maxDepthDown[i], maxDepthUp[i]);
            if (currentHeight < minHeight) {
                minHeight = currentHeight;
                roots.clear();
                roots.add(i);
            } else if (currentHeight == minHeight) {
                roots.add(i);
            }
        }


        return roots;
    }

    private int getMaxAndNextMaxChildDepth(int node, int parent) {
        for (int indexOfNext = head[node]; indexOfNext != -1; indexOfNext = nextNeighbor[node]) {
            int child = neighbors[indexOfNext];

            // 防止无限循环， 即在parent和当前节点来回横跳。
            // 也因此， 这个function无法计算到 从 当前节点到parent节点的路径深度。
            if (child == parent) {
                continue;
            }

            int curMaxDepth = 1 + getMaxAndNextMaxChildDepth(child, node);

            //
            if (curMaxDepth > maxDepthDown[node]) {
                secondDepthDown[node] = maxDepthDown[node];
                maxDepthDown[node] = curMaxDepth;
                provider[node] = child;
            } else if (curMaxDepth > secondDepthDown[node]) {
                secondDepthDown[node] = curMaxDepth;
            }
        }

        return maxDepthDown[node];
    }

    private void getMaxParentDepth(int node, int parent) {
        for (int indexOfNext = head[node]; indexOfNext != -1; indexOfNext = nextNeighbor[node]) {
            // 由于是无向图， 所以此处所指的child实际也是parent。
            int child = neighbors[indexOfNext];

            if (child == parent) {
                continue;
            }

            if (child == provider[node]) {
                maxDepthUp[child] = Math.max(maxDepthDown[child], secondDepthDown[node] + 1);
            } else {
                maxDepthUp[child] = Math.max(maxDepthDown[child], maxDepthDown[node] + 1);
            }

            // todo: debug
            maxDepthUp[child] = Math.max(maxDepthUp[child], maxDepthUp[node] + 1);

            getMaxParentDepth(child, node);
        }

    }
}

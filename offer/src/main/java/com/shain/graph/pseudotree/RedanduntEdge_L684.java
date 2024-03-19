package com.shain.graph.pseudotree;

import java.util.*;

/**
 * 一棵树中， 所有节点都联通， 有且仅有一个环， 则为 基环树。
 * 如果确定了是 基环树无论 有向图还是无向图， 均可用 拓扑排序求解。
 * <p>
 * 无向图的基环树还可以同 并查集求解 （see unionFind包里面的这道题）
 * <p>
 * 但是注意， 如果是 无向图 用并查集求解，只能求出 多余的一条边 属于 哪两个节点。 没法做到 排除所有 环以外的节点， 即枝叶，只留下环中的所有节点。
 * 但是用拓扑排序可以做到。怎么做？ 如这题代码， 最后遍历degree数组， 找到所有 degree >=1 的节点即为环的所有节点。
 * <p>
 * 注意这题的while条件和L310 的区别。
 * <p>
 * 这题即为 利用 拓扑排序， 找到 基环树 的环 等问题的模版代码。
 * <p>
 * 本题为无向图。
 * <p>
 * more： 单纯判断有向图中是否 有环 （这里还没仔细看过， 也没做过， 还不是很确定）可以用dfs 实现， 不限制于只有一个环这个条件。
 * <p>
 * topological包中的课程表问题， 即检查循环依赖的问题， 其实本质上也是在检测有没有环， 只不过那个题更简单， 只需要判断环是否存在即可。
 */
public class RedanduntEdge_L684 {
    public static void main(String[] args) {
        var test = new RedanduntEdge_L684();
        System.out.println(Arrays.toString(test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}})));
    }

    public int[] findRedundantConnection(int[][] edges) {
        if (edges.length == 2) {
            return new int[]{1, 2};
        }
        int[] degree = new int[edges.length];
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            degree[edge[0] - 1]++;
            degree[edge[1] - 1]++;

            adj.get(edge[0] - 1).add(edge[1] - 1);
            adj.get(edge[1] - 1).add(edge[0] - 1);
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        // 由于 只有一条多余的边， 所以最后必定留下， 且只留下两个节点。
        while (queue.size() > 0) {

            int cur = queue.poll();
            // 不需要， 因为是无向图， 在遍历到cur的neibor的neibor时， cur的degree会被--
            // 上面这个注释说的不对， 比如用例2 [[1,2],[2,3],[3,4],[1,4],[1,5]]
            // 只有 5 的度是1， 5 入q以后， 1 的度减1， 但是 就算 1 的度-1以后还是2， 不等于1， 直接就推出大while了。
            // 所以这里-- 更好理解。
            degree[cur]--;

            for (Integer neighbor : adj.get(cur)) {
                degree[neighbor]--;
                if (degree[neighbor] == 1) {
                    queue.offer(neighbor);
                }
            }

        }

        // 这里之所以 倒叙遍历是为了满足题目要求。
        // 注意， 这题不能 在更新queue时用 while （count>2) 的逻辑， 即， 这题不能保证 依次更新degree为1 的节点以后答案会留在 queue内
        // 比如用例 [[1,2],[1,3],[2,3]]， 每个节点的degree都是2， queue中压根就没有过节点。
        // 所以要在更新完以后， 检查dgree依然>=1 的节点。
        // L310 为什么可以那么写while？ 因为310 保证了没有环！所以一定总是会有dgree=1 的节点！
        for (int i = edges.length - 1; i > 0; i--) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (degree[a] >= 1 && degree[b] >= 1) {
                return new int[]{a, b};
            }
        }

        return null;
    }
}

package com.shain.graph.unionFindSet;

import java.util.stream.IntStream;

/**
 * https://leetcode.cn/circle/discuss/qmjuMW/
 *
 * 只写了按rank合并 以及 带 路经压缩的 find。
 * 还有按高度合并， 以及不带 路经压缩的 find， 若需要可看上面文章
 */
public class UnionFind {
        // parent数组的下标与元素值一一对应。 其意义为存储 value=i 的节点i 的根结点的值， 也即下标。
        // 进而初始时， parent数组中对应着n颗树， 每棵树都只有自身一个节点， 即root等于自身。
        // 在union过程中， 树的数量因为合并不断减少。 
        // 也即， 只有当 parent[i] == i 时，代表当前的i就是某棵树的根结点。
        // 如果 parent[i] = j, 则当前i的根结点值（也即下标）为j， 最终能够追踪到根结点。
        private int[] parent;
        // 合并的次数
        // 或者 树的个数， 两者可以按需求变换。
        // 如果是合并的次数， 则初始化为0， 合并时++
        // 如果是 树的个数， 则初始化为数组长度， 合并时--
        private int count;
        private int[] rank;

        // n -> number of nodes
        public UnionFind(int n) {
            this.rank = IntStream.generate(() -> 1).limit(n).toArray();
            this.parent = IntStream.range(0, n).toArray();
            count = 0;
        }

        public void union(int x, int y) {
            // 如果两个节点的根结点相同， 则已经在同一颗树中， 进而无需合并。
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY)
                return;

            // 将rank 较低的树接到rank高的树上， 进而保证树的平衡， 防止 出现高度极不平衡的情况 e.g. 接成了一个链表。
            if (rank[x] <= rank[y]) {
                // 注意是rootX 和rootY。
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
            }

            // 若x，y rank相等， 则连接以后的树的高度会+1
            // 若两棵树高度不同， 则合并以后新树的高度就等于原root的高度。
            // 在应用带路径压缩的查询和按秩求并后，rank[root] 记录的数字是树实际高度的一个上界，树的实际高度可能小于此值 -> rank的定义
            // 按高度求并的优化原本是用「高度 ( height )」来指导树的合并，但因为 「同时」 应用了 「路径压缩」 优化，导致程序记录的height 
            // 信息不再是严格定义下的「高度」，这就好像树高被 find 给压缩了，但height 这个变量没察觉到这个变化，导致height 不再能表达「真实高度」
            // 。因此为了严谨，我们不能再使用height 这个单词 (这个概念) ，转而采用另一个词 (另一个概念) 来指代此时的「高度」。-> rank
            rank[x] = rank[x] == rank[y] ? rank[x] + 1 : rank[x];
            count++;
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
            // if (x == parent[x]) 
            //     return x;

            // // 路径压缩过程， 即原本高度为 N 的树的所有子节点全部直接接到根结点， 进而使得书的高度为1， 优化查询根结点的复杂度从O(logN) -> O(1)
            // int curParent = find(parent[x]);
            // parent[x] = curParent;


            // return curParent;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int getCount() {
            return count;
        }
    }
package com.shain.dp.pathSum.bottomUpTraverse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreedomTrail_L514 {
    public static void main(String[] args) {
        System.out.println(findRotateSteps("godding", "godding"));
    }

    /**
     * key point:
     * 1. 明确需两个指针i，j 分别指向 key and ring。 要移动j，使当前j 指向的字符等于 k指向的key。
     * <p>
     * <p>
     * 2. KEY POINT:
     * 而要使 当前的两个指针达到这种状态，对于当前j而言， 有 n 种选择， n等于 ring 中出现 key[i] 的次数。
     * 换句话说，即相当于建立一个n叉树， 从一个 虚拟头节点开始， 有n个子节点。每个子节点 又 分出n个子节点。  （todo：见图）
     * <p>
     * 每个子节点的value， 则是 从 当前 ring[j]开始，使得 所有key [i：-1] 找到对应的ring[j]所需的最小移动步数之和。也即 min(到达各个子节点的路径值curMin + 各个子节点的value）
     * 发现问题了么？ Node 的value 指的是， 当前节点下的所有子路径的最小和。 也即， 要先计算 所有子节点的value， 才能得到 node 的
     * value。"也即后序遍历"， "因为在 前序遍历过程中（上到下遍历）， 你无法得到子节点的value, 只能得到curMin， 所以只能从下往上遍历所有路径"，
     * 这就是所谓的 "当前的状态， 依赖于后面的状态， 但是并不依赖于父节点的状态"。 这就是为什么 "需要倒序的更新dpTable的原因"。
     * <p>
     * 反之，如果我们要求的值是所有 curMin 的最小和， 那么可以进行 前序遍历，因为对于curMin的和， 只需要在 向下遍历到每一个节点的时候， 就可以得到（当然， 也是可以
     * 进行后序遍历的， 这就是为什么 二维表格类型的dp题， 基本都可以后序遍历）。 这就是为什么 L64, L174这种题可以正向更新dp数组， 但是 L174 和
     * L 514这种题必须 逆序更新dp数组。
     * <p>
     * 如果你还是不明白上文讲的 curMin 的和 vs Value 的和 的区别， 即如果你不明白为什么  Sum（curMin） 可以正向求， sum(value)必须逆向求。
     * 那么想象你站在头节点：
     * "当你第一次 去到 1号子节点， 你能知道 value 的值吗？ 不能，因为他需要先走到 所有 leaf Node 再回来才知道"
     * "当你第一次 去到 1号子节点， 你能知道 curMin的值吗？ 能， 因为curMin不取决于子节点。你可以直接得到当前curMin的值"
     * <p>
     * 此处的curMin， 其实就相当于 L64 这种题中的 每个格子的 值， 你可以直接在 从 头节点遍历到叶子节点的过程中 得到所有 curMin的和。所以可以正向
     * 更新dp。
     * <p>
     * 3. 当然， 对于这道题本身而言， you also should realize that curMin的最小路径和， 不等同于value的最小路径和。 but you got this
     * when you first saw the question, so I believe you should be fine.
     * <p>
     * 4. dpTable的定义：
     * 从 j = x 开始运动， 使得 key[i:-1] 全部被对应所需要的最小值。 其中， x 代表父节点的j值。
     *
     *
     * <p>
     * 6. Another Key Point:
     * dp数组如何初始化？ 答： 可以初始化成 ring.length+1 * key.length+1 的数组。
     * 第 ring.length+1 列，
     *
     * @param ring
     * @param key
     * @return
     */
    public static int findRotateSteps(String ring, String key) {
        int[][] dp = new int[key.length() + 1][ring.length()];

        // 建立hashtable 存储 ring中所有字符对应的index. 由于有重复字符， 所以value是list。
        Map<Character, List<Integer>> hash = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            hash.putIfAbsent(ring.charAt(i), new ArrayList<>());
            hash.get(ring.charAt(i)).add(i);
        }

        // i -> 当前要找到的key
        for (int i = key.length() - 1; i >= 0; i--) {
            // j -> 父节点的j值
            for (int j = 0; j <= ring.length() - 1; j++) {
                List<Integer> indexes = hash.get(key.charAt(i));
                // line74 的遍历过程， 即相当于遍历一个父节点的所有子节点的过程。
                // 在这一过程中， 计算了所有到达子节点所需的 "路径值"， 也即curMin。
                // 当前节点的value = min(到达各个子节点的路径值curMin + 各个子节点的value）
                // !!注意， value的含义是 从当前节点出发， 到所有叶子节点的路径 的最小值， 对于一个节点的value， 是不包含当前节点的curMin的。
                int curValue = Integer.MAX_VALUE;
                for (Integer index : indexes) {
                    int pos = Math.abs(j - index);
                    int neg = ring.length() - pos;
                    int curMin = Math.min(pos, neg);
                    int childValue = curMin + dp[i + 1][index] + 1;
                    curValue = Math.min(curValue, childValue);
                }
                dp[i][j] = curValue;
            }
        }

        return dp[0][0];
    }
}

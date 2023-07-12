package com.shain.binaryTree.traverse.pureTraverse.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 将二维board转换成一维，然后交换0的位置， 转换成常规bfs。
 * 唯一tricky在于将board转成一维， 然后发现是bfs问题。
 * 为了能够在 flatten以后交换0与原先二维数组中的neighbor， 需要通过一个二维数组记录邻居在一维数组中的index， 而由于输入永远是2x3 的数组， 所以
 * 这些index是固定的
 */
public class SlidingPuzzle_L773 {
    private int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(sb.toString());
        count = 1;
        int depth = 0;

        while (!queue.isEmpty()) {
            while (count > 0) {
                count--;

                String cur = queue.poll();

                if (cur.equals("123450")) {
                    return depth;
                }

                if (visited.contains(cur)) {
                    continue;
                }

                visited.add(cur);

                int zeroIndex = getIndexOfZero(cur);

                if (zeroIndex == -1) {
                    return -1;
                }

                int[] neighbor = this.neighbors[zeroIndex];

                for (Integer n : neighbor) {
                    queue.add(swap(n, zeroIndex, cur));
                }
            }

            count = queue.size();
            depth++;
        }

        return -1;
    }

    private String swap(int n, int zero, String cur) {
        char[] converted = cur.toCharArray();
        converted[zero] = converted[n];
        converted[n] = '0';

        return new String(converted);
    }

    private int getIndexOfZero(String s) {
        int i = 0;
        for (Character c : s.toCharArray()) {
            if (c == '0')
                return i;
            i++;
        }

        return -1;
    }
}

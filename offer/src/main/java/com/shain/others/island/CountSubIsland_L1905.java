package com.shain.others.island;

public class CountSubIsland_L1905 {
    private int[][] grid1;
    private int[][] grid2;

    /**
     * 这个思路是 比较两个岛屿想不想等。
     * 这个思路更有写的价值， 另一个方法跟别的题是一样的， copy paste改一改就能过。
     * 这里注意， compare方法中的注释。
     * <p>
     * 另一个方法是 找到不可能是subiland的 grid1 中的区域， 将其全部变0， 然后数剩下的还是1 的区域。 which is similar to other questions.
     *
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        this.grid1 = grid1;
        this.grid2 = grid2;

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1 && compare(i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean compare(int i, int j) {
        if (i < 0 || i > grid1.length - 1 || j < 0 || j > grid1[0].length - 1) {
            return true;
        }

        if (grid2[i][j] == 0) {
            return true;
        }

        boolean flag = true;

        // 同理， 这里也不能直接返回false， 会导致有的相邻的1 没有消除
        if (grid2[i][j] == 1 && grid1[i][j] == 0) {
            flag = false;
        }


        grid2[i][j] = 0;

        // 此处一定要这么写， 不能写成 return compare() && compare ...
        // 因为像那么写的话， 当第一个compare返回false， 后面的compare就不会执行了， 导致无法把grid2 中所有相邻的1 都消除。
        boolean up = compare(i, j + 1);
        boolean down = compare(i, j - 1);
        boolean left = compare(i - 1, j);
        boolean right = compare(i + 1, j);

        return flag && up && down && left && right;
    }
}

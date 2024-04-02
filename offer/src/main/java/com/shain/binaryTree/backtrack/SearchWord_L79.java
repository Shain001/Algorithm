package com.shain.binaryTree.backtrack;

public class SearchWord_L79 {
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        this.board = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] used = new boolean[board.length][board[0].length];
                if (traverse(i, j, word, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean traverse(int i, int j, String word, boolean[][] used) {
        if (word.equals("")) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (used[i][j]) {
            return false;
        }


        if (word.charAt(0) != board[i][j]) {
            return false;
        }

        // 注意， used 必须放在 判断 当前字符已经是可以添加到路径之后， 不能放在 if char != board[i][j] 之前。
        //  因为 used数组 作用为： “防止一个字符， 重复被算作 答案路径的一部分”。
        // 理解了这句话以后 自然能方队 used 数组的位置。
        used[i][j] = true;
        String next = word.substring(1);
        boolean result = traverse(i - 1, j, next, used) || traverse(i + 1, j, next, used) || traverse(i, j - 1, next, used) || traverse(i, j + 1, next, used);
        // 同时， used 数组也必须回溯。
        used[i][j] = false;
        return result;
    }
}

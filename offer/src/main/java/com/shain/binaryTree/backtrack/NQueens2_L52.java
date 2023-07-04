package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.List;

public class NQueens2_L52 {
    private int count = 0;
    private List<StringBuilder> board;
    private int n;

    public int totalNQueens(int n) {
        this.n = n;

        this.board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            board.add(sb);
        }

        backTrack(0);
        return count;
    }

    private void backTrack(int row) {
        if (row == n) {
            count++;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                StringBuilder cur = board.get(row);
                cur.setCharAt(col, 'Q');
                board.set(row, cur);
                backTrack(row + 1);
                cur.setCharAt(col, '.');
                board.set(row, cur);

            }
        }
    }

    private boolean isValid(int row, int col) {
        if (row == 0) {
            return true;
        }

        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (board.get(row - i).charAt(col - i) == 'Q')
                return false;
        }

        for (int i = 1; row - i >= 0 && col + i <= n - 1; i++) {
            if (board.get(row - i).charAt(col + i) == 'Q')
                return false;
        }

        return true;
    }
}

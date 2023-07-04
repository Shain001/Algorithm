package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens_L51 {
    private LinkedList<String> path;
    private List<List<String>> result;
    private List<StringBuilder> board;
    private int n;

    // 不用path， 直接改board就行， 懒得改了
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.path = new LinkedList<>();
        this.result = new ArrayList<>();
        this.board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            board.add(sb);
        }

        backTrack(0);
        return result;
    }

    private void backTrack(int row) {
        if (row == n) {
            result.add(new ArrayList<>(path));
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                StringBuilder cur = board.get(row);
                cur.setCharAt(col, 'Q');
                path.add(cur.toString());
                backTrack(row+1);
                cur.setCharAt(col, '.');
                path.removeLast();
            }
        }
    }

    private boolean isValid(int row, int col) {
        if (path.size() == 0) {
            return true;
        }

        for (int i = 0; i < row; i++) {
            if (path.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        for (int i = 1; row-i >= 0 && col-i>=0; i++) {
            if (path.get(row-i).charAt(col-i) == 'Q')
                return false;
        }

        for (int i = 1; row-i >=0 && col+i<=n-1; i++) {
            if (path.get(row-i).charAt(col+i) == 'Q')
                return false;
        }

        return true;
    }
}

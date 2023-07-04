package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_L22 {
    private List<String> result;
    private int n;
    private StringBuilder path;


    public List<String> generateParenthesis(int n) {
        this.n = n;
        this.result = new ArrayList<>();
        this.path = new StringBuilder();

        backTrack(0, 0);
        return result;
    }

    private void backTrack(int left, int right) {
        if (right > left) {
            return;
        }

        if (left + right == 2 * n) {
            result.add(path.toString());
            return;
        }

        if (left < n) {
            path.append("(");
            backTrack(left + 1, right);
            path.deleteCharAt(path.length() - 1);
        }

        if (right < n) {
            path.append(")");
            backTrack(left, right + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

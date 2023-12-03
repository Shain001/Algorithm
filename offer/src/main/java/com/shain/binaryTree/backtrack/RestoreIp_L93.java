package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIp_L93 {
    private LinkedList<String> path;
    private List<String> result;
    private String s;
    public List<String> restoreIpAddresses(String s) {
        this.path = new LinkedList<>();
        this.result = new ArrayList<>();
        this.s = s;

        backTrack(0);
        return result;
    }

    private void backTrack(int start) {
        if (start > s.length()-1 && path.size() == 4) {
            result.add(String.join(".", path));
            return;
        }

        // 剪枝优化
        int remainedLen = s.length() - start;
        int minumRequired = 4-path.size();
        int maxRequried = 3 * (4-path.size());

        if (remainedLen < minumRequired || remainedLen > maxRequried) {
            return;
        }

        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) {
                continue;
            }
            String cur = s.substring(start, start+len);
            if (isValid(cur)) {
                path.add(cur);
                backTrack(start+len);
                path.removeLast();
            }
        }
    }

    private boolean isValid(String cur) {
        if (cur.charAt(0) == '0' && cur.length() != 1) {
            return false;
        }

        if (Integer.parseInt(cur) > 255) {
            return false;
        }

        return true;
    }
}

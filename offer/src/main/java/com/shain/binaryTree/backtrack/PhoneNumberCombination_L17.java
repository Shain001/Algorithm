package com.shain.binaryTree.backtrack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneNumberCombination_L17 {
    List<String> res = new LinkedList<>();
    Map<Character, char[]> nums = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        nums.put('2', new char[]{'a', 'b', 'c'});
        nums.put('3', new char[]{'d', 'e', 'f'});
        nums.put('4', new char[]{'g', 'h', 'i'});
        nums.put('5', new char[]{'j', 'k', 'l'});
        nums.put('6', new char[]{'m', 'n', 'o'});
        nums.put('7', new char[]{'p', 'q', 'r', 's'});
        nums.put('8', new char[]{'t', 'u', 'v'});
        nums.put('9', new char[]{'w', 'x', 'y', 'z'});

        backTrack(digits, 0, new StringBuilder());
        return res;
    }

    public void backTrack(String digits, int level, StringBuilder path) {
        if (level == digits.length()) {
            if (path.length()!=0)
                res.add(path.toString());
            return;
        }

        char[] curLevelNodes = nums.get(digits.charAt(level));

        for (int i = 0; i < curLevelNodes.length; i++) {
            path.append(curLevelNodes[i]);
            backTrack(digits, level+1, path);
            path.deleteCharAt(path.length()-1);
        }
    }
}

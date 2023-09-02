package com.shain.array.limitedComplexity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindRepeat_L187 {
    private static final int L = 10;

    public static void main(String[] args) {
        var test = new FindRepeat_L187();
//        System.out.println(test.findRepeatedDnaSequences_v2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));

        System.out.println(~5);
    }
    /**
     * 基础方法， 时间复杂度，空间复杂度均为 O(NL) where N is length of String, L is length of window.
     * Why O(NL)?
     * for遍历N次， 每次遍历都要进行substring操作， substring的长度为L， 进而记为O(NL)， 实际上可以取为O(N)， 但是这里强调 NL 的目的为
     * 理解对其进行的优化
     *
     * 空间复杂度为O(NL)好理解， key为String， 共N个key
     *
     * 仅用滑动窗口优化？
     * 没用， 就算加入滑动窗口， 记录窗口内的字符串， 然后将其进行toString, toString 这个操作仍然是O(L)
     *
     * e.g.
     * String windowStr = window.toString();
     *
     * 同时这样的方案无法优化空间复杂度到真正的 O(N);
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        int n = s.length();
        for (int i = 0; i <= n - L; ++i) {
            String sub = s.substring(i, i + L);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }

    /**
     * 利用对字符串进行HASH 来优化。
     */
    private int R;
    public List<String> findRepeatedDnaSequences_v2(String s) {
        int[] converted = new int[s.length()];
        Map<Integer, Integer> occured = new HashMap<>();
        List<String> result = new ArrayList<>();

        // Since There is only 4 possible values ACGT, 所以是四进制
        // 注意理解这个hash的过程， 由于只有四种可能的核苷酸值， 所以每一位的值就只有四种可能性。
        // 也即， 将一个窗口中的字符串， 转换成了一个长度为L的四进制数
        // 然后又将这个四进制数转换成了十进制的值作为hash
        // 也即这种方法是将一个字符串， 先转换成一个 R 进制的数字， 再将其转换成10进制保存。
        R = 4;
        int i = 0;
        for (Character c : s.toCharArray()) {
            switch (c) {
                case 'A': converted[i++] = 0; break;
                case 'C': converted[i++] = 1; break;
                case 'G': converted[i++] = 2; break;
                case 'T': converted[i++] = 3; break;
                default: return null;
            }
        }

        int right = 0;
        int left = 0;
        int hash = 0;
        while (right < s.length()) {
            hash = addToRight(hash, converted[right]);
            right++;

            if (right - left == 10) {
                if (occured.getOrDefault(hash, 0) == 1) {
                    result.add(s.substring(left, right));
                }
                occured.put(hash, occured.getOrDefault(hash, 0) + 1);

                hash = removeFromLeft(hash, converted[left]);
                left++;
            }



        }

        return result;
    }

    private int addToRight(int hash, int n) {
        return hash * R + n;
    }

    private int removeFromLeft(int hash, int n) {
        return hash - n * (int) Math.pow(R, L-1);
    }
}

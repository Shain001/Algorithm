package com.shain.array.Rabin_Karp;

import java.util.*;

public class RepeatedDNA_L187 {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    // v2 and v1 都是活动窗口
    // 唯一区别在于， v2将string转换成了数字， 相当于hash， 这样可以不用一直substring， 能节约空间
    // Things to Note:
    // 1. 对于数字的 拼接操作， 即对于最高位，最低位的增减数字。 注意对于进制的处理。 if forgot see https://labuladong.github.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/hua-dong-c-88113/
    // 2. 对于将字符串转成数字， 以及其与滑动窗口的结合， 本质上没什么区别， 只是对于内存更友好。
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 10)
            return result;

        int[] converted = new int[s.length()];
        Map<Integer, Integer> occurred = new HashMap<>();

        // convert string to array
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            switch (curChar) {
                case 'A':
                    converted[i] = 0;
                    break;
                case 'G':
                    converted[i] = 1;
                    break;
                case 'C':
                    converted[i] = 2;
                    break;
                case 'T':
                    converted[i] = 3;
                    break;
                default: break;
            }
        }

        // sliding window, but this time we store integer to occurred, not string, which is more efficient
        int left = 0;
        int right = 0;

        // params used to concat hashvalue
        // 四进制， 因为只有四个不同字符
        int R = 4;
        // 拼接以后的hash值
        int hash = 0;

        while (right < converted.length) {
            hash = hash * R + converted[right];

            if (right - left == 9) {
                occurred.put(hash, occurred.getOrDefault(hash, 0) + 1);
                if (occurred.get(hash) == 2) {
                    result.add(s.substring(left, right+1));
                }

                // pop out
                hash = hash - converted[left] * (int) Math.pow(R, 9);
                left++;
            }

            // move window
            right++;
        }


        return result;
    }

    public List<String> findRepeatedDnaSequences_V1(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 10)
            return result;

        HashMap<String, Integer> occurred = new HashMap<>();
        int left = 0;
        int right = 10;

        // NOTE： 此处为 +1， right为10 是因为java substring 是左闭右开
        while (right < s.length()+1) {

            String cur = s.substring(left, right);
            occurred.put(cur, occurred.getOrDefault(cur,0)+1);

            if (occurred.getOrDefault(cur, 0) == 2){
                result.add(cur);
            }

            left++;
            right++;
        }

        return result;
    }
}

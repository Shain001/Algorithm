package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.*;

public class RepeatedDNA_L187 {
    public static void main(String[] args) {

    }

    public List<String> findRepeatedDnaSequences(String s) {
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

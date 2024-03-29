package com.shain.kmp;

public class Template_L28 {
    /**
     * KMP 整个过程， 重点即保证： "对于 指向 target的i指针， i 永远不会 回退"
     *
     * 要实现这一目标， 即通过 "next"， 找到 指向 pattern 的j 指针 下一个应该进行比较的 位置， 跳过不必要比较的位置。
     *
     * 看：
     * https://www.bilibili.com/video/BV1AY4y157yL/?spm_id_from=333.337.search-card.all.click&vd_source=f53217bf8acd143563c96bc7010c3795
     *
     * 模版背下来。
     *
     * 理解next数组， 是平移以后的 PMT 数组， PMT数组即 上面视频里面的 正常前后缀长度数组。
     * 平移的目的仅仅是为了 编程方便。
     * 但是要注意， 平移以后， next数组的含义变成了： pattern中， 以 i-1 所在字符结尾的 最长公共 前后缀。
     * @param target
     * @param pattern
     * @return
     */
    public int kmp(String target, String pattern) {
        int pLen = pattern.length();
        int tLen = target.length();

        int[] next = initializeNext(pattern);
        // i -> 目标字符串
        // j -> 模式字符串
        int i = 0;
        int j = 0;

        while (i < tLen && j < pLen) {
            // 为什么 j == -1 时， i 应该++？
            // 因为， 只有 next[0] = -1, 即代表着， 只有 当 pattern的第一个字符 与 当前匹配的 target字符不 相等时， j 才会得到 -1
            // 此时， 自然是需要 i， j 同时移动， 因为此时 j++=0， i++ 移动到下一个字符， 继续重新和 pattern的第一个字符比较。
            // 对于 pattern 比较到一半才发现 当前p ！= 当前t时， 不会出现 j 被移动到 -1 的情况， 此时会继续 检查 新的 p 是否等于当前 i， 不会出现 漏判 当前 i 与新的 p 比较的情况。
            if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                // 不想等时， i的位置不变， 只移动j到下一个要匹配的位置
                j = next[j];
            }
        }

        if (j == pLen) {
            return i - j;
        }
        return -1;
    }

    /**
     * next[i]表示在模式串pattern中，以i为结束位置的非整个子串（即，从pattern[0]到pattern[i-1]）的最长相等前后缀的长度
     *
     * 对于next的构建， 重点在于理解： "构建过程， 实际就是 把 pattern整个当成target， 把 当前最长前缀当成 pattern， 进行的KMP匹配过程"
     *
     * 注意一点：
     * 这个写法， next数组中， 是不包含 以 pattern 最后一个字符结尾的 最长前后缀信息的。
     * 但是并不会影响 KMP过程， 为什么？ 如果KMP过程中， 已经匹配到 pattern最后一位：
     * 1。 如果相等， 匹配成功， 直接return了
     * 2。 如果不想等， 我们关注的是 以 pattern倒数第二位字符结尾的子串的 前后缀长度信息。
     *
     * 看： https://www.zhihu.com/question/21923021/answer/281346746
     *
     */
    private int[] initializeNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;

        int i = 0;
        int j = -1;

        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        return next;
    }
}

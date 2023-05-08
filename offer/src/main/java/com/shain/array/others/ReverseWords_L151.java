package com.shain.array.others;

public class ReverseWords_L151 {
    private static StringBuilder sb;

    public static void main(String[] args) {
        System.out.println(reverseWords(" a  good d  "));
    }

    // reverse the whole string, then reverse single words
    public static String reverseWords(String s) {
        // trim space
        sb = trimSpace(s, 0, s.length() - 1);
        int left = 0;
        int right = sb.length() - 1;

        // reverse whole string
        doReverse(left, right);

        int p = 0;
        // 因为需要right < sb.length()作为循环条件
        // 所以无法再循环过程中删除sb中的char （循环条件会报错）
        // 有一个解决办法， 即从后向前遍历sb， 这样可以避免循环条件的问题
        // 进而可以做到再循环过程中删除多余的' '
        // 但是这样的坏处为， 由于sb的长度在变， reverse left right时， left的坐标也要变
        // 所以比较复杂， 没必要。
        // 但是好处是可以少循环一边sb
        while (right < sb.length()) {
            left = p;
            right = p;

            // find space;
            while (right + 1 < sb.length() && sb.charAt(right + 1) != ' ') {
                right++;
            }

            // update p to make it point to start of next word
            p = right + 1;
            while (p < sb.length() && sb.charAt(p) == ' ') {
                p++;
            }

            // reverse word
            doReverse(left, right);
        }
        // wrong:
        // remove ' '
        // 该方法remove'' 不好操作， 因为string不可变， 操作sb的话， 再删除过程中sb的长度又会变化
        // 所以还不如直接遍历 sb， 找出单词， 然后拼接成一个新的string。 但那样就失去了意义
        // 所以此题还是 stack做合适
        // 只需要遍历原字符串， 然后添加到sb即可
        return sb.toString();
    }

    private static void doReverse(int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    private static StringBuilder trimSpace(String s, int left, int right) {
        StringBuilder trimmed = new StringBuilder();
        // find first nonnull char from left
        while (left < right && (s.charAt(left) == ' ' || s.charAt(right) == ' ')) {
            if (s.charAt(left) == ' ')
                left++;
            if (s.charAt(right) == ' ')
                right--;
        }

        // NOTE: 此时right已经指向有效string的结尾
        while (left <= right) {
            if (left > 1 && s.charAt(left - 1) == ' ' && s.charAt(left) == ' ') {
                left++;
                continue;
            }
            trimmed.append(s.charAt(left));
            left++;
        }

        return trimmed;
    }
}

package com.shain.binaryTree.traverse.pureTraverse.bfs;

import java.util.*;

public class OpenTheLock_L752 {

    public static void main(String[] args) {
        var test = new OpenTheLock_L752();
        System.out.println(test.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
    }

    public int openLock_twoWayBFS(String[] deadends, String target) {
        // 改为用set， 更好判断两端遍历是否相遇。
        Set<String> queue1 = new HashSet<>();
        Set<String> queue2 = new HashSet<>();
        Set<String> deads = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Arrays.stream(deadends).forEach(deads::add);


        queue1.add("0000");
        queue2.add(target);

        int depth = 0;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            // temp 为新一层的节点， 所以后续交换以后， queue 被更新， 所以无需在从queue中移除元素。因为原本的queue已经被覆盖了。
            HashSet<String> temp = new HashSet<>();

            for (String cur: queue1) {

                if (deads.contains(cur))
                    continue;

                // 两个方向的遍历相遇
                if (queue2.contains(cur))
                    return depth;

                visited.add(cur);

                for (int i = 0; i < 4; i++) {
                    String up = doUp(cur, i);
                    String down = doDown(cur, i);

                    if (!visited.contains(up)) {
                        temp.add(up);

                    }

                    if (!visited.contains(down)) {
                        temp.add(down);
                    }
                }

            }

            // 交换q1 q2， 也即先从根结点往下遍历一层， 然后target底部根结点往上遍历一层。
            queue1 = queue2;
            // 注意， temp在每个while循环新开始时已经重新new了， 所以永远temp中记录的就是一层节点。
            queue2 = temp;

            depth++;
        }

        return -1;
    }

    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> deads = new HashSet<>();
        // 必须要用这个visited记录已经遍历过的字符串。 否则可能出现 -> 0000 - 1000 -> 到了1000 这一层，又把0000 加到了queue里。
        Set<String> visited = new HashSet<>();
        Arrays.stream(deadends).forEach(deads::add);


        queue.offer("0000");
        int curLevelSize = 1;
        int depth = 0;

        while (!queue.isEmpty()) {
            while (curLevelSize > 0) {
                curLevelSize--;

                String cur = queue.poll();

                // todo: check why add here will infinite loop, and in BFS it has to be added here
                // visited.add(cur);

                if (cur.equals(target)) {
                    return depth;
                }

                // 写在这比写在for中好， 不用做多余的输入判断是否0000 位deadlock。
                if (deads.contains(cur))
                    continue;



                for (int i = 0; i < 4; i++) {
                    String up = doUp(cur, i);
                    String down = doDown(cur, i);

                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }

                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }

            }
            curLevelSize = queue.size();
            depth++;
        }

        return -1;
    }

    String doUp(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    String doDown(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}

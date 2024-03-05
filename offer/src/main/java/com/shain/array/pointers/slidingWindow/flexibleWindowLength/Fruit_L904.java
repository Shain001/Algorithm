package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

import java.util.HashMap;
import java.util.Map;

/**
 * 也可以使用数组， 不用map， 因为元素取值范围是 0 - len-1
 * 应该也有空间复杂度 O(1) 的方法， 但是麻烦不写了。
 */
public class Fruit_L904 {
    public int totalFruit(int[] fruits) {
        int left = 0;
        int right = 0;
        int ans = 0;
        // value 存 最后一次出现的坐标会变复杂， 因为不知道上一个重复的是哪个数字。 存 出现次数更方便。
        Map<Integer, Integer> map = new HashMap<>();

        while (right < fruits.length) {
            // 扩展窗口, 加入 cur
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            // 如果 map size > 2
            // then 开始pop left， 直到 size = 2 为止， 注意删除 key when it reaches 0.

            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left])-1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            ans = Math.max(ans, right-left+1);
            right++;
        }
        return ans;
    }
}

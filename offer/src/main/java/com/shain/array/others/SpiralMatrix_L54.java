package com.shain.array.others;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_L54 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
    }

    // NOTE: 不可以 while (left <= right || up <= down)
    // 因为在while中的代码走到一半的时候就可能遍历了全部元素， 如果这样写， 会导致有元素被重复添加
    // 所以必须是 每结束遍历一行/一列 就做一次if判断
    // 只要left 超过 right， 或者up超过down，就一定已经遍历了全部元素。
    // update at 14/06/2023 -> while循环， 以及循环内判断result.size 与 元素总数更好理解。see code
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;

        int count = matrix.length * matrix[0].length;

        while (result.size() < count) {

            for (int i = left; i <= right && result.size() < count; i++)
                result.add(matrix[up][i]);
            up++;

            for (int i = up; i <= down && result.size() < count; i++)
                result.add(matrix[i][right]);
            right--;

            for (int i = right; i >= left && result.size() < count; i--)
                result.add(matrix[down][i]);
            down--;

            for (int i = down; i >= up && result.size() < count; i--)
                result.add(matrix[i][left]);
            left++;
        }

//
//
////        while (left <= right || up <= down) {
//        while (true) {
//            // 左到右
//            for (int i = left; i <= right; i++) {
//                result.add(matrix[up][i]);
//            }
//            up++;
//            if (up > down) break;
//
//            // 上到下
//            for (int i = up; i <= down; i++) {
//                result.add(matrix[i][right]);
//            }
//            right--;
//            if (left > right) break;
//
//            // 右到左
//            for (int i = right; i >= left; i--) {
//                result.add(matrix[down][i]);
//            }
//            down--;
//            if (up > down) break;
//
//            // 下到上
//            for (int i = down; i >= up; i--) {
//                result.add(matrix[i][left]);
//            }
//            left++;
//            if (left > right) break;
//        }

        return result;
    }
}

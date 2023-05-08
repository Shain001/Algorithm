package com.shain.array.others;

public class RotateImage_L48 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
        rotate(matrix);
    }

    // 对角线换位再反转
    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
                System.out.println(i + " " + j);
            }
        }

        for (int[] rows : matrix) {
            int left = 0;
            int right = matrix.length - 1;
            while (left <= right) {
                int temp = rows[left];
                rows[left] = rows[right];
                rows[right] = temp;
                left++;
                right--;
            }
        }
    }
}

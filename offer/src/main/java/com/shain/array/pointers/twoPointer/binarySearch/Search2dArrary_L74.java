package com.shain.array.pointers.twoPointer.binarySearch;

public class Search2dArrary_L74 {
    public boolean searchMatrix(int[][] matrix, int target) {

        int targetLen = getTargetLen(matrix, target);

        int left = 0;
        int right = matrix[0].length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (matrix[targetLen][mid] == target)
                return true;

            if (matrix[targetLen][mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        return false;
    }

    private int getTargetLen(int[][] matrix, int target) {
        int up = 0;
        int down = matrix.length - 1;

        while (up < down) {
            int mid = up + (down - up + 1) / 2;

            if (matrix[mid][0] == target) {
                up = mid;
                break;
            }

            if (matrix[mid][0] > target) {
                down = mid - 1;
            } else {
                up = mid;
            }
        }

        return up;
    }
}

package com.shain.array.binarySearch;

public class Search2dArray2_L240 {
    public boolean searchMatrix(int[][] matrix, int target) {

        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix[i].length -1;

            while (left < right) {
                int mid = left + (right-left) / 2 + 1;
                if (matrix[i][mid] <= target) {
                    left = mid;
                } else {
                    right = mid-1;
                }
            }
            if (matrix[i][left] == target) {
                return true;
            }
        }


        return false;
    }

    // 这个更好
    public boolean v2(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while(i >= 0 && j < matrix[0].length)
        {
            if(matrix[i][j] > target) i--;
            else if(matrix[i][j] < target) j++;
            else return true;
        }
        return false;
    }
}

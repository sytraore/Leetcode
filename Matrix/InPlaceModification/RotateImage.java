package Matrix.InPlaceModification;

public class RotateImage {
    public static void rotate(int[][] matrix) { // matrix is an n x n matrix
        /* 
            this is an in-place matrix modification problem
            rotate by 90 degree clockwise mean:
                => the first row must become the last column
                => the second row must become the column before the last column
                => the last row must become the first row
            we need to transpose the matrix first
                => this will make each row, a column
                   and each column, a row
            and finaly reverse each row of the newly transposed matrix

        */
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int n = matrix.length;

        // first transpose the matrix
        // this is how you transpose a square matrix (n x n)
        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                // store current cell value
                int temp = matrix[i][j];
                // 
                matrix[i][j] = matrix[j][i];
                // 
                matrix[j][i] = temp;
            }
        }

        // reverse each row
        for (int i = 0; i < n; i++){
            reverseRow(matrix[i]);
        }
    }

    // helper method to reverse the row of the transposed matrix
    private static void reverseRow(int[] nums){
        int l = 0, r = nums.length - 1;

        while (l < r){
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        rotate(matrix);

        // print the rotated matrix
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

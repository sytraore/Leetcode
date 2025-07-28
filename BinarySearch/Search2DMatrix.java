package BinarySearch;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        /**
            we need to know in what row could target be
            we know that the last value of each row is their biggest value
            and that the last element of row1 is less than the first value of row2, and so on
            so we can use this set up to locate the appropriate row
            once found, we can apply a binary search algorithm with a private helper method
        */

        for (int i = 0; i < matrix.length; i++){
            // last element of each row index
            int lastInd = matrix[i].length - 1;

            // to make the algorithm simpler.
            if (target == matrix[i][lastInd]){
                return true;
            }

            if (target < matrix[i][lastInd]){
                // target is defintively in this row
                return binarySearch(matrix[i], target);
            }
        }

        return false;
    }

    // helper method
    private boolean binarySearch(int[] row, int target){

        int l = 0, r = row.length - 1;

        while (l <= r){
            int mid = (l + r) / 2;

            if (row[mid] < target){
                l = mid + 1;
            }
            else if (row[mid] > target){
                r = mid - 1;
            }
            else{
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Example usage
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 50},
            {60, 61, 62, 63}
        };
        int target = 3;
        Search2DMatrix search2DMatrix = new Search2DMatrix();
        boolean result = search2DMatrix.searchMatrix(matrix, target);
        System.out.println("Target found: " + result); // Output: Target found: true
    }
}

package Matrix.Traversal;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        
        /*
            This is a matrix traversal problem
            we need to be able to track which column and row has been traversed
            the order of traversal is as this:
                => to start, traverse the first row from left to right
                => second, traverse the last column from top to bottom
                => third, traverse the last row from right to left
                => last, traverse the first column from bottom to top
            we will then proceed to move the pointers accordingly to visit the next rows and columns
            which have not been visited yet.
            the above order gives this pattern (# means it starts from here and + means it ends
            here, when traversing vertically top-bottom, bottom-top):
                =>  ---->
                    +   #
                    |   |
                    |   |
                    #   +
                    <----

            Time complexity: O(n * m)
            Space complexity: O(n) or O(1) if the output list does not count            
        */

        List<Integer> res = new ArrayList<>();

        // Add this check to handle null or empty matrices gracefully.
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        
        // we will need 4 pointers to track the column row and cell being visited
        // t stands for top and b stands for bottom
        // l and r stands for left and right
        // b represents the last row and r represents the last column
        // t represents the top row and l represents the left column
        int t = 0, l = 0, b = matrix.length - 1, r = matrix[0].length -1;

        while (t <= b && l <= r){
            // traverse the top row from left to right
            for(int i = l; i <= r; i++){
                // matrix[t][i] are values on the current row 
                res.add(matrix[t][i]);
            }
            // move the top pointer to indicate that a top row has been visited
            t++;

            // traverse the right column from top to bottom
            // r points at element in the last column
            // t points at a row that has not been visited yet
            // looping (b - t) times => we are going from top to bottom
            for (int i = t; i <= b; i++){
                // matrix[i][r] represents the values on a column
                res.add(matrix[i][r]);
            }
            // move the right pointer to indicate that a right column has been traversed
            r--;

            // traverse the bottom row from right to left
            // but first check, if we have not visited that row yet
            if (t <= b){
                // go from right to left
                for (int i = r; i >= l; i--){
                    res.add(matrix[b][i]);
                }
                // move the bottom pointer to indicate that a bottom row has been visited
                b--;
            }

            // traverse the left column from bottom to top
            // but before, we need to make sure the column has not been visited yet
            if (l <= r){
                // go from bottom to top
                for (int i = b; i >= t; i--){
                    res.add(matrix[i][l]);
                }
                // move the left pointer to indicate that a left column has been visited
                l++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        List<Integer> result = spiralOrder(matrix);
        System.out.println(result); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }
}

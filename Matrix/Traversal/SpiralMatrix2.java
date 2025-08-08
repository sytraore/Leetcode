package Matrix.Traversal;

public class SpiralMatrix2 {
    public static int[][] generateMatrix(int n) {
        /* 
            This is matrix traversal problem
            we will fill the matrix in this order (spiral order):
                => fill the first row from left to right
                => then fill the last column from top to bottom
                => then fill the last row from right to left
                => and finally fill the first column from bottom to top
            we will go to the next rows and columns that have not been filled
            until all rows and columns have been filled
            we need to be able to track the current cell value
            we also need to track the rows and columns that have been filled.
            
            Time complexity: O(n*n)
        */
        
        // check if n is valid
        if (n < 1){
            return new int[0][0];
        }

        int[][] res = new int[n][n]; // create the nxn matrix

        // t & b means top and bottoms
        // l & r means left and right
        // t tracks the current top row and b tracks the current bottom row
        // l tracks the current left column and b tracks the current right column
        int t = 0, l = 0, b = n - 1, r = n - 1, val = 1;

        while (l <= r && t <= b){
            // we will traverse the current top row from left to right
            // and fill it with values
            for (int i = l; i <= r; i++){
                res[t][i] = val;
                // increment the value for the next cell
                val++;
            }
            // move the top row pointer to indicate that we filled a top row
            t++;

            // tarverse the current right column from top to bottom
            // and fill it
            for (int i = t; i <= b; i++){
                res[i][r] = val;
                val++;
            }
            // move the right column pointer to indicate that we filled a right column
            r--;

            // traverse the current bottom row from right to left
            // but first, let's make sure we have not travrsed it yet
            if (t <= b){
                for (int i = r; i >= l; i--){
                    res[b][i] = val;
                    val++;
                }
                // move the bottom row pointer to indicate that we filled a bottom row
                b--;
            }

            // traverse the current left column from bottom to top
            // but first, let's make sure we have not traversed it yet
            if (l <= r){
                for (int i = b; i >= t; i--){
                    res[i][l] = val;
                    val++;
                }
                // move the left column pointer to indicate that we filled a left column
                l++;
            }

        }

        return res;
        
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = generateMatrix(n);
        
        // Print the generated matrix
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

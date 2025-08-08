package Matrix.Traversal;

public class DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] mat) {
        /* 
            This is matrix traversal problem
            
            This solution simulates the diagonal traversal path step-by-step.

            The core idea is to track our current position (row, col) and the
            current direction (goUp = true/false). In a loop that runs M*N times,
            we do two things:
            
            1.  Add the current element to our result.
            2.  Calculate the next (row, col) to visit.

            The trick is in calculating the next position. The direction flips only
            when we hit a boundary. The order of boundary checks is critical to
            handle the corners correctly (e.g., check for the right wall before the
            top wall when going up).

        */

        int m = mat.length, n = mat[0].length;

        if (m < 1 || n < 1){
            return new int[0];
        }

        int[] res = new int[m * n];
        int row = 0, col = 0;
        boolean goUp = true;

        for (int i = 0; i < res.length; i++){
            // visit current cell
            res[i] = mat[row][col];

            // we are going up
            if (goUp){
                // there are no columns on the right side of the current cell
                if (col == n - 1){
                    // change direction, go down
                    goUp = false;
                    // predict the row of the next cell to visit
                    row++;
                }
                // there are no rows above the current cell
                else if (row == 0){
                    // change direction, go down
                    goUp = false;
                    // predict the column of the next cell to visit
                    col++;
                }
                // there are rows above and there are columns on the right side of current cell 
                // now the next cell is the below diagonal neighbor of the current cell
                else{
                    row--;
                    col++;
                }
            }
            // we are going down
            else{
                // there are no rows below the current cell
                if (row == m - 1){
                    // change direction, go up
                    goUp = true;
                    // predict the column of the next cell to visit
                    col++;
                }

                // there are no columns on the left side of the current cell
                else if (col == 0){
                    // change direction, go up
                    goUp = true;
                    // predict the row of the next cell to visit
                    row++;
                }
                
                // there are rows above and there are columns on the right side of current cell
                // now the next cell is the above diagonal neighbor of the current cell 
                else{
                    row++;
                    col--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] result = findDiagonalOrder(mat);
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output: 1 2 4 7 5 3 6 8 9
    }
}

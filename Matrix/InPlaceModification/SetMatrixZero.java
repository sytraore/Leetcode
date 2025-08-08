package Matrix.InPlaceModification;

public class SetMatrixZero {
    public static void setZeroes(int[][] matrix) {
        /**
            track the rows and columns that contains zeroes
            locate them through a second iteration and zeroed them

            if (matrix == null || matrix.length < 1){
                return;
            }

            int m = matrix.length, n = matrix[0].length;
            int[] marquedRow = new int[m];
            int[] marquedCol = new int[n];

            // marque the rows and columns that contain zeroes
            for (int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if (matrix[i][j] == 0){
                        marquedRow[i] = 1;
                        marquedCol[j] = 1;
                    }
                }
            }

            // zeroed the marqued rows and columns
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    if (marquedRow[i] == 1 || marquedCol[j] == 1){
                        // how to zeroed the marqued row and column?
                        matrix[i][j] = 0;
                    }
                }
            }
        */

        /* Optimal solution 
            use the first row and first column as marker
            if we find a zero in a particular row, we will mark that row by zeroeing its first element
            and we will also mark the column of the current cell on the first row by zeroing it
        */

        if (matrix == null || matrix.length < 1){
                return;
            }

        int m = matrix.length, n = matrix[0].length;
        boolean zeroedRow1 = false, zeroedCol1 = false;

        // Step 1: Check if the first row and first column need to be zeroed.
        // ... your code to check the first column and set isFirstColZero ...
        for (int i = 0; i < m; i++){
            if (matrix[i][0] == 0){
                zeroedCol1 = true;
            }
        }

        // ... your code to check the first row and set isFirstRowZero ...
        for (int i = 0; i < n; i++){
            if (matrix[0][i] == 0){
                zeroedRow1 = true;
            }
        }

        // Step 2: Use the first row/col as markers for the rest of the matrix.
        // (Iterate from matrix[1][1] to the end)
        // ... your code here ...
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == 0){
                    // mark corresponding cell on first row
                    matrix[0][j] = 0;
                    // mark corresponding cell on first column
                    matrix[i][0] = 0;
                }
            }
        }

        // Step 3: Set the inner matrix to zero based on the markers.
        // (Iterate from matrix[1][1] to the end again)
        // ... your code here ...
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                // check the markers
                // if the corresponding cell on either the first row or first column is 0
                // we must change the current cell value to 0
                if (matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 4: Set the first row and first column to zero if needed,
        // using your boolean flags.
        // ... your code here ...
        if (zeroedRow1){
            for (int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }

        if (zeroedCol1){
            for (int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }    
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 8, 9}
        };

        setZeroes(matrix);

        // Print the modified matrix
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

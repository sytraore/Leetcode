package Matrix.InPlaceModification;

public class TransposeMatrix {
    public static int[][] transpose(int[][] matrix) {   // matrix is an m x n matrix

        /*
            a row of the tranposed matrix is a column in the input matrix
            we are dealing with m x n matrices
            so our transposed matrix should be an n x m matrix
        */
        if (matrix == null || matrix.length < 1){
            return new int[0][0];
        }

        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[n][m];    // set up n x m matrix

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                // the row of the transposed matrix becomes the column of the input matrix
                res[i][j] = matrix[j][i];
            }
        }
        return res;
    }
}

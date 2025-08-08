package Matrix.InPlaceModification;

public class RotateBox {
    public static char[][] rotateTheBox(char[][] boxGrid) {
        /* 
            we first need to do in-place modification on the boxGrid
                => we will visit each row from right to left
                    => at each cell, we will check if the cell is an obstacle, a stone or empty
                    if it is an obstacle,
                        => we will skip the cell and go the next cell
                    if it is empty
                        => we will leave it empty and
                            go to the next cell. This empty cell will eventually store a stone later
                    if it is a stone
                        => we will look at the empty cell on the right and move the stone there
            then, we will rotate the boxGrid by 90 degrees by:
                => first transposing the boxGrid
                => then reversing every row of the transposed matrix
        */

        if (boxGrid == null || boxGrid.length < 1){
            return new char[0][0];
        }

        int m = boxGrid.length, n = boxGrid[0].length;
        char[][] res = new char[n][m]; // since the box is a m x n matrix, its transpose will be an n x m matrix

        // move the content of the box when necessary
        for (int i = 0; i < m; i++){
            // set the pointers
            int l = n -1, r = n - 1;

            while (l >= 0){
                // // empty box
                if (boxGrid[i][l] == '.'){
                    l--;
                }
                // obstacle box
                else if (boxGrid[i][l] == '*'){
                    // skip it
                    l--;
                    r = l;

                }
                // how do I handle this?
                else{
                    // We found a stone. Move it to the floor.
                    boxGrid[i][r] = '#';
                    // Only set the original spot to '.' if it's not the same spot.
                    if (l != r) {
                        boxGrid[i][l] = '.';
                    }  
                    r--;
                    l--;
                }
                
            }
            
        }

        // transpose the box to an n x m matrix
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                res[i][j] = boxGrid[j][i];
            }
        }

        // reverse each row to make the 90 degree rotation
        for (int i = 0; i < n; i++){
            reverseRow(res[i]);
        }

        return res;
    }

    // helper method to reverse the rows
    private static void reverseRow(char[] nums){
        int l = 0, r = nums.length - 1;

        while (l < r){
            char temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        char[][] box = {
            {'#', '.', '#', '.'},
            {'#', '#', '*', '.'},
            {'.', '#', '.', '.'},
            {'*', '.', '.', '.'}
        };

        char[][] rotatedBox = rotateTheBox(box);

        // Print the rotated box
        for (char[] row : rotatedBox) {
            System.out.println(row);
        }
    }
}

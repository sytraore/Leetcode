package Arrays.Hashing;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    
    public static boolean isValidSudoku(char[][] board) {
        /*
            each row must contain unique digits from 1 to 9
            each column must contain unique digits from 1 to 9

            Uniq => hashset
            Use a hashset to determine if there are any duplicates in either the rows or columns

            First, traverse the rows and check for duplicates
            Second, traverse the columns and check for duplicates
            Third, traverse the 3x3 squares and check for duplicates
            if no duplicates found, return true
        */

        HashSet<Integer> set = new HashSet<>();

        // traverse rows
        for (char[] row: board){
            for (char c: row){
                if (Character.isDigit(c)){
                    int val = Character.getNumericValue(c);

                    if (!set.add(val) || !(val <= 9 && val >= 1)){
                        return false;
                    }
                    
                }
                
            }
            set.clear();
        }

        // traverse columns
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (Character.isDigit(board[j][i])){
                    int val = Character.getNumericValue(board[j][i]);

                    if (!set.add(val) || !(val <= 9 && val >= 1)){
                        return false;
                    }
                }
            }
            set.clear();
        }

        // traverse 3x3 squares
        // there are 9 squares in total, each square is 3x3
        // square 0: (0,0) to (2,2)
        // square 1: (0,3) to (2,5)
        // square 2: (0,6) to (2,8)
        // square 3: (3,0) to (5,2)
        // square 4: (3,3) to (5,5)
        // square 5: (3,6) to (5,8)
        // square 6: (6,0) to (8,2)
        // square 7: (6,3) to (8,5)
        // square 8: (6,6) to (8,8)
        // We can use a single loop to traverse the squares
        // The square number can be calculated as follows:
        // square = (row / 3) * 3 + (col / 3)
        // where row and col are the indices of the current element in the board
        // For each square, we will traverse the 3x3 grid and check for duplicates
        // We can use a nested loop to traverse the 3x3 grid
        for (int square = 0; square < 9; square++) {
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int row = (square / 3) * 3 + i;
                    int col = (square % 3) * 3 + j;

                    // int val = Character.getNumericValue(board[row][col]);
                    // if (!set.add(val) || !(val <= 9 && val >= 1)){
                    //     return false;
                    // }

                    if (board[row][col] == '.') continue;
                    if (seen.contains(board[row][col])) return false;
                    seen.add(board[row][col]);
                }
            }
        }

        return true;
    }
}

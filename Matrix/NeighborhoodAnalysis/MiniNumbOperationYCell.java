package Matrix.NeighborhoodAnalysis;

import java.util.HashMap;

public class MiniNumbOperationYCell {
    public static int minimumOperationsToWriteY(int[][] grid) {
        /* 
            we want the minimum number of operations to write the letter Y on the grid
            the letter Y can only be written iff:
                => values at all cells belonging to the Y are equal
                => values at other cells are not equal to the values at all Y cells
                => values at other cells are equal to each other.

            we need to first know what cell is the center of the grid
            then, we must save the initial set up of the grid by:
                => first saving the values at the Y cells.
                => then save the values at the non-Y cells.
            if each list contains identique numbers, no further need
            else, we need to compute the minimum number of operations to write Y by following the rules:
                => for each list, we will look for the value with the highest frequency
                => compare the cost of operations for each possibility
                   for example, calculate the cost if the value of Y-cells is 0 and 1 for non-Y cells
                   do the same with (1, 0); (0, 2), (2, 0); (1, 2); (2, 1)
                => since we need to track the values, we can use a hash map data structure
                    => 1 hashmap for the values of the Y cell and another for the values of the non-Y
                => we will then compute for each hashmap, the number of opeartions (finding the cost) 
                => we all sum the two costs and that will give us the minimum number of operations

            Time complexity: O(n^2) where n is the number of rows or columns in the grid
            Space complexity: O(n) for the hashmaps used to store the values and their frequencies
        */

        if (grid == null || grid.length < 3){
            return -1;
        }

        int n = grid.length, center = n / 2;
        HashMap<Integer, Integer> yMap = new HashMap<>();
        HashMap<Integer, Integer> nonYMap = new HashMap<>();

        // save initial grid set up
        for (int i = 0; i < n; i++){
            // save top left
            if (i == 0){
                yMap.put(grid[i][0], yMap.getOrDefault(grid[i][0], 0) + 1);
            }
            // save top right
            else if (i == n - 1){
                yMap.put(grid[0][i], yMap.getOrDefault(grid[0][i], 0) + 1); 
            }
            // save the non-Y cells of the first row
            else{
                nonYMap.put(grid[0][i], nonYMap.getOrDefault(grid[0][i], 0) + 1);
            }
            
        }

        // save the cells from center to bottom for Y-cells
        // save the non-Y cells from the center row to bottom row
        for (int row = center; row < n; row++){
            for (int col = 0; col < n; col++){
                if (col == center){
                    yMap.put(grid[row][center], yMap.getOrDefault(grid[row][center], 0) + 1);
                }
                else{
                    nonYMap.put(grid[row][col], nonYMap.getOrDefault(grid[row][col], 0) + 1);
                }
            }
        }

        // check if we haven't reached the center yet
        if (center != 1){
            for (int i = 1; i < center; i++){
                // save the diagonals on the left
                yMap.put(grid[i][i], yMap.getOrDefault(grid[i][i], 0) + 1);
                // save the diagonals on the right
                yMap.put(grid[i][n - (i + 1)], yMap.getOrDefault(grid[i][n - (i + 1)], 0) + 1);

                // save non-Y cells under the diagonal
                int col = 0;

                while (col < n){
                    if (col == i || col == n - (i + 1)){
                        col++;
                        continue;
                    }
                    else{
                        nonYMap.put(grid[i][col], nonYMap.getOrDefault(grid[i][col], 0) + 1);
                    }
                    col++;
                }
                
            }
        }

        // compute the cost for each value possibility
        // to get the minimum cost of opeartions for one hashmap
        // first find out how many elements are in the hashmap
        // then substract the number of values with value (0, or 1 or 2)
        int cost = 0; 
        Integer costY = 0, costNonY = 0, yNumbCells = 0, nonYNumbCells = 0, res = Integer.MAX_VALUE;
        
        // calculate how many elements are on the Y-cells
        // remember that the keys are only the uniq elements, the values are their frequencies
        // so adding the frequencies give exactly how many elements there are in the hashmap
        for (Integer val: yMap.values()){
            yNumbCells += val;
        }

        for (Integer val: nonYMap.values()){
            nonYNumbCells += val;
        }

        // compute the cost for each possibility and store the smallest cost
        for (Integer i = 0; i < 3; i++){
            for (Integer j = 0; j < 3; j++){
                if (i == j){
                    continue;
                }
                // for ex: Y-cell val is 0 and non-Y cell value is 1
                // Y-cell val is 0 and non-Y cell value is 2
                // Y-cell val is 1 and non-Y cell value is 0, and so on
                costY = yNumbCells - yMap.getOrDefault(i, 0);
                costNonY = nonYNumbCells - nonYMap.getOrDefault(j, 0);
                cost = (int)costY + costNonY;
                res = Math.min(res, cost);
                
            }
        }

        return res;
        
    }

    public static void main(String[] args) {
        // Example usage
        int[][] grid = {
            {1,2,2},
            {1,1,0},
            {0,1,0}
        };
        int[][] grid2 = {
            {0,1,0,1,0},
            {2,1,0,1,2},
            {2,2,2,0,1},
            {2,2,2,2,2},
            {2,1,2,2,2}
        };

        System.out.println(minimumOperationsToWriteY(grid)); // 3
        System.out.println(minimumOperationsToWriteY(grid2)); // 12
    }
}

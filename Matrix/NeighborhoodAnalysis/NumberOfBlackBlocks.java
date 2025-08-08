package Matrix.NeighborhoodAnalysis;

import java.util.HashMap;

public class NumberOfBlackBlocks {
    public static long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        /* 
            we must return an integer array of size 5 such that arr[i] is the
            number of blocks that contains exactly i black cells.
            which mean, we need to find the answer to these questions:
                how many blocks have 0 black cell?
                how many blocks have 1 black cell?
                how many blocks have 2 black cells?
                how many blocks have 3 black cell?
                how many blocks have 4 black cells?
            once we found them, that will be the output array

            we must know how many blocks are in the m x n grid first
            A 2x2 block is defined by its top-left corner. A top-left corner (r, c) can be at any
            position as long as the block fits in the grid. This means r can go from 0 to m-2 and c can
            go from 0 to n-2.
            To get the total number of possible blocks, we multiply the number of possible starting rows
            by the number of possible starting columns
                => we can mathematically compute the number of blcoks in the grid. It will be:
                    => (m - 1) * (n - 1)
                    
            we can use each [x, y] black cell coordinates to check if it falls into a valid block
            if it falls, we need to update our count
            we can use a hashmap to track the count. the pair will be block: numb of black cells
            => block ID: numb of black cells
               we use a block ID in order to be able to easily update the counting of black cells for 
               a block that is already in the map. 
            for each coordinate in the input array that falls in a valid block, we update the hashmap
            by adding the block and its number of black cells (increment or set).
            at the end, we will compute the difference between the number of blocks
            and the length of the hashmap
            this will give us the number of blocks that do not have black cells.
            we will use the values of the hashmap to fill the rest of our putput array

            Time complexity: O(k) k being the length of the coordinates 2D array
            Space complexity: O(k)
        */
        if (coordinates == null || m < 2 || n < 2){
            return new long[0];
        }

        long[] res = new long[5];
        HashMap<Long, Integer> map = new HashMap<>();
        long numbBlocks = (long)(m - 1) * (n - 1);

        for (int i = 0; i < coordinates.length; i++){
            /*
                look for the block that contains the current black cell
                this can be done by looking at the the coordinates that will make a block based
                on the current black cell coordinate
                => current black cell: coordinates[i] (let's shorten it to co[i])
                the coordinates: [co[i][0], co[i][1]], [co[i][0] - 1, co[i][1]], [co[i][0], co[i][1] - 1],
                [co[i][0] - 1, co[i][1] - 1] constitute a block based on the current black cell coord
                we will check if the x and y value of these coordinates are withing the bound of the grid
                if yes, we will add them in the hashmap because each one of these coordinates
                can represent the top-left corner of another block
                so having them in the map will be easy to track their number of black cells
            */

            // coordinates of the potential block
            int x = coordinates[i][0], y = coordinates[i][1];

            for (int j = x - 1; j <= x; j++){
                for (int k = y - 1; k <= y; k++){
                    // check if coordinates are within the grid bound
                    if (j >= 0 && j < m - 1 && k >= 0 && k < n - 1){
                        // create uniq identification for the blocks
                        long blockId = (long)j * n + k;
                        // update hashmap
                        map.put(blockId, map.getOrDefault(blockId, 0) + 1);
                    }
                }
            }

        }

        // compute how many blocks do not have black cells
        long noBlackCells = numbBlocks - (long)map.size();

        // update output array
        res[0] = noBlackCells;

        // fill rest of output array
        for (int numbBlackCells: map.values()){
            res[numbBlackCells]++;
        }

        return res;
    }

    public static void main(String[] args) {
        // Example usage
        int[][] coordinates = {{0, 0}, {1, 1}, {2, 2}};
        long[] result = countBlackBlocks(3, 3, coordinates);
        for (long count : result) {
            System.out.print(count + " ");
        }
    }
}

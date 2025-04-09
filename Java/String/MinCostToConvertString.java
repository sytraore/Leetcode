package Java.String;

import java.util.Arrays;
/*
 * You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters.
 * You are also given two 0-indexed character arrays original and changed, and an integer array cost, 
 * where cost[i] represents the cost of changing the character original[i] to the character changed[i].
 * You start with the string source. 
 * In one operation, you can pick a character x from the string and change it to the character y at a cost of z 
 * if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.
 * Return the minimum cost to convert the string source to the string target using any number of operations. 
 * If it is impossible to convert source to target, return -1.
 * Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
 */

 /*
  * Example 1:
  Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
  Output: 28
  Explanation: To convert the string "abcd" to string "acbe":
  - Change value at index 1 from 'b' to 'c' at a cost of 5.
  - Change value at index 2 from 'c' to 'e' at a cost of 1.
  - Change value at index 2 from 'e' to 'b' at a cost of 2.
  - Change value at index 3 from 'd' to 'e' at a cost of 20.
  The total cost incurred is 5 + 1 + 2 + 20 = 28.
 It can be shown that this is the minimum possible cost.


*/
public class MinCostToConvertString {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Initialize the cost matrix for all character conversions
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            // Fill the cost matrix rows with Long.MAX_VALUE
            Arrays.fill(minConversionCosts[i], Long.MAX_VALUE);
            // Set the diagonal to 0, as the cost to convert a character to itself is 0
            minConversionCosts[i][i] = 0;
        }

        // Populate the cost matrix with the given conversions
        for (int i = 0; i < original.length; i++) {
            // Convert characters to their respective indices (0-25) to represent 'a' to 'z' indices in the matrix
            // 'a' corresponds to 0, 'b' to 1, ..., 'z' to 25
            // ascii value of 'a' is 97, so we subtract 'a' to get the index
            // original[i] - 'a' gives the index of the character in the alphabet
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            // Update the cost matrix with the minimum cost for conversion
            // If there are multiple conversions, we take the minimum cost
            // as for now, since the cost is Long.MAX_VALUE, we can use Math.min
            // to replace the cost for conversion with the given cost
            minConversionCosts[from][to] = Math.min(minConversionCosts[from][to], cost[i]);
        }

        // Apply Floyd-Warshall to find the shortest paths between all pairs of characters
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (minConversionCosts[i][k] != Long.MAX_VALUE && minConversionCosts[k][j] != Long.MAX_VALUE) {
                        minConversionCosts[i][j] = Math.min(minConversionCosts[i][j], minConversionCosts[i][k] + minConversionCosts[k][j]);
                    }
                }
            }
        }

        // Calculate the total cost of converting source to target
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            char sourceChar = source.charAt(i);
            char targetChar = target.charAt(i);

            if (sourceChar != targetChar) {
                long conversionCost = minConversionCosts[sourceChar - 'a'][targetChar - 'a'];
                if (conversionCost == Long.MAX_VALUE) {
                    return -1; // Conversion not possible
                }
                totalCost += conversionCost;
            }
        }

        return totalCost;
    }
}
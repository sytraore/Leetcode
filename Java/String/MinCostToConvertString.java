package Java.String;

import java.util.Arrays;

public class MinCostToConvertString {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Initialize the cost matrix for all character conversions
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(minConversionCosts[i], Long.MAX_VALUE);
            minConversionCosts[i][i] = 0; // Cost to convert a character to itself is 0
        }

        // Populate the cost matrix with the given conversions
        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
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
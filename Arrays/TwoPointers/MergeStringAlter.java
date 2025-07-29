package Arrays.TwoPointers;

public class MergeStringAlter {
    public static String mergeAlternately(String word1, String word2) {
        /*
            use only one pointer
            go through each word
            add each character at the current pointer into a new string
            at the end of the iteration, add the remaining characters if one word was longer than the other.

            Time complexity: O(n)
            space complexity: O(n)
        */

        int p1 = 0;
        StringBuilder res = new StringBuilder();

        while (p1 < word1.length() && p1 < word2.length()){
            res.append(word1.charAt(p1));
            res.append(word2.charAt(p1));

            p1++;
        }

        res.append(word1.substring(p1));
        res.append(word2.substring(p1));
        
        return res.toString();
    }

    public static void main(String[] args) {
        String word1 = "abc";
        String word2 = "pqr";
        String result = mergeAlternately(word1, word2);
        System.out.println(result); // Expected output: "apbqcr"
    }
}

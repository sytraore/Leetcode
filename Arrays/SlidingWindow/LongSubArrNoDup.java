package Arrays.SlidingWindow;

import java.util.HashSet;

public class LongSubArrNoDup {
    public static int lengthOfLongestSubstring(String s) {

        /*
         * A brute force solution:
         * for each character in the string, we can check all possible substrings
         * and check if they contain duplicates
         * This approach has a time complexity of O(n^2) and space complexity of O(n)
         * 
         *  int res = 0;
            for (int i = 0; i < s.length(); i++) {
                Set<Character> charSet = new HashSet<>();
                for (int j = i; j < s.length(); j++) {
                    if (charSet.contains(s.charAt(j))) {
                        break;
                    }
                    charSet.add(s.charAt(j));
                }
                res = Math.max(res, charSet.size());
            }
            return res;
         * 
         */
        /**
         * Optimal solution
            Substring => sequence of characters: order matters
            We can start a window of length 0 with two pointers on each side of the window
            and use a hash set to track duplicates
            we expand the window as long as there is no duplicates
            and we calculate the size of the window and update the result if necessary
            if there are duplicates
                => then we shrink the window by moving the left pointer one step to the right
            
            Data structure: hashset
            Time complexity: O(n)
            Space complexity: O(n)
         */

        if (s.length() == 0){
            return 0;
        }

        int res = 0, l = 0, r = 0;
        HashSet<Character> set = new HashSet<>();

        while (r < s.length()){
            // duplicate found
            // shrink window and evaluate the length 
            while (set.contains(s.charAt(r))){
                set.remove(s.charAt(l));
                l++;
            }

            // as long as both characters are different, the window will keep expanding
            set.add(s.charAt(r));
            res = Math.max(res, r - l + 1); // evaluate the length of the window
            r++;        // expand the window
        }
        return res;
    }

    public static void main(String[] args) {
        // Example usage
        String s = "abcabcbb";
        int result = lengthOfLongestSubstring(s);
        System.out.println("Length of longest substring without repeating characters: " + result); // 3
    }
}

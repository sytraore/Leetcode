package Arrays.SlidingWindow;

import java.util.HashMap;

public class LongRepeatCharReplace {
    public static int characterReplacement(String s, int k) {
        /**
            in string "AABC", A is the character with the highest frequency.
            changing the other characters B or C to A will increase the number of ocurrences of A.
            we can calculate how many characters are different from a particular character if we know the frequency of the character in the string.
            In "AABC", the length is 4 and A has a frequency of 2.
            => 4 - 2 = 2 => there are 2 characters that are different from A
            4 - 1 = 3 => there are 3 characters different from B in "AABC".

            by going through the string
            we can use this formula to evaluate how many characters are different from the current character
            once that number is found, we can compare it to k

            we will set a window of size 1,
            for each character, update its frequency in the map
            compute the formula
            as long as the formula is not met, shrink the window by moving the left pointer to the right
            if formula is met,
            update result
            expand window
        */

        if (s.length() == 1) {
            return 1;
        }

        int res = 0, l = 0, maxFreq = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for(int r = 0; r < s.length(); r++){
            Character c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0) + 1);

            maxFreq = Math.max(maxFreq, map.get(c));

            while ((r - l + 1) - maxFreq > k){
                Character lc = s.charAt(l);
                map.put(lc, map.get(lc) - 1);
                l++;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
        
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        int result = characterReplacement(s, k);
        System.out.println("Max length of substring after replacements: " + result); // Output: 4
    }
}

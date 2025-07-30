package Arrays.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubStr {
    public static String minWindow(String s, String t) {
        /* 
            Brute force:
                the general idea is to find the characters of t in a substring of s
                the substring can be longer than t itself, but as long as it contains the characters
                of t, it will be a valid substring
                we will evaluate the length of each valid substring and return the valid substring with the 
                smallest length.
                to do so, we need 2 pointers that will be the limit of the substring
                those 2 pointers can be use later to evaluate the length of a valid substring 
                for each character in s, we must check if it occurs in t the same amount of time
                since we are trying to track both characters and their number of occurences,
                a hash map data structure will be useful
                we will store the characters of t and their frequencies in one hash map
                in another hashmap, we will add the character at the second pointer
                and check if the character exists in the first hashmap
                if it does exist, we must check its frequency.
                if the frequencies do not match, we continue looking for characters by moving the second pointer
                if they match, we have a valid substring and we must store the indices of the substring delimiters
                at the end of the iteration, we will simply return the smallest substring based on the indices
                stored above.


            if (t.isEmpty()) return "";

            Map<Character, Integer> countT = new HashMap<>();
            for (char c : t.toCharArray()) {
                countT.put(c, countT.getOrDefault(c, 0) + 1);
            }

            int[] res = {-1, -1};
            int resLen = Integer.MAX_VALUE;

            // i is first pointer
            for (int i = 0; i < s.length(); i++) {
                Map<Character, Integer> countS = new HashMap<>();
                // j is the second pointer
                for (int j = i; j < s.length(); j++) {
                    // add each character to the second hashmap and check for a match in the first hashmap
                    countS.put(s.charAt(j), countS.getOrDefault(s.charAt(j), 0) + 1);
                    
                    // this is set as long as the character at j is in countT with a frequency >= the 
                    // frequency in countT
                    boolean flag = true;    
                    for (char c : countT.keySet()) {
                        if (countS.getOrDefault(c, 0) < countT.get(c)) {
                            flag = false;
                            break;
                        }
                    }

                    // update the length of the substring if it got smaller
                    // and store the indices of the substring
                    if (flag && (j - i + 1) < resLen) {
                        resLen = j - i + 1;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }

            return resLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
        */

        /*
            Optimal solution: sliding window technic
            instead of nested loops, we can solve this problem in a simpler way.
            the general idea does not change
            but it is how we achieve it that changes.
            we will use a sliding window to slide through the string s
            we will also need a counter to use on the second hashmap
            the goal of the counter is to check if the number of characters in the first hashmap are in
            the second hashmap.
            everytime, we find a character in s that is also in t, we will update its frequency in the second map
            and increament the counter
            check if the counter is equal to the size of the first map
            if yes, 
                => we have a valid substring and we update the length of the substring if it got smaller
                => we save the limit pointers of the window as a reference for later
                => we move the window one step to the right
                    => by first reducing the frequency of the character at the left pointer in the second map
                    => then we check again if the character at the left pointer exists in both maps and if 
                        it has the same frequency in both maps
                            => no => decreament the counter
                    => we increment the left pointer
            if not, we continue expanding the window
            at the end of the iteration, we will return the smallest valid substring with the limit pointers of
            the window that we stored above

        */

        if (t.isEmpty()) return "";

        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int have = 0, need = countT.size();
        int[] res = {-1, -1};
        int resLen = Integer.MAX_VALUE;
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))) {
                have++;
            }

            while (have == need) {
                if ((r - l + 1) < resLen) {
                    resLen = r - l + 1;
                    res[0] = l;
                    res[1] = r;
                }

                char leftChar = s.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                if (countT.containsKey(leftChar) && window.get(leftChar) < countT.get(leftChar)) {
                    have--;
                }
                l++;
            }
        }

        return resLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
    
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t)); // Output: "BANC"
    }
}

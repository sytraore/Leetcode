package Arrays.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnagramsInStr {
    public static List<Integer> findAnagrams(String s, String p) {

        /*
            Brute force solution:
            For each substring of length p, check if it is an angarm with p
            use an helper method for anagram check
            anagram check will use a hashmap to compare the frequencies
            use a sliding window to go through s
            the window will be of size length of p
            if substring and p are anagrams, 
            add the left pointer of the window into resuting list

            Time complexity: O(n * m) where n is the length of s and m is the length of p
            Space complexity: O(m) for the hashmap used to check anagrams
            This is not optimal as it checks each substring of s against p, leading to a quadratic time complexity.
         */

        /*
            Optimal solution: sorting
            sort p
            for each substring of s of length p, sort it and check if it is equal to sorted p
            if it is, add the left pointer of the window into resuting list

            Time complexity: O(n * m log m) where n is the length of s and m is the length of p
            Space complexity: O(m) for the sorted substring of s and p
            This is still not optimal as it sorts each substring of s
         */

        /*
            Optimal solution: sliding window with 2 hashmaps
            For each substring of length p, check if it is an angarm with p
            use a sliding window to go through s
            use 2 hashmaps to check for anagram compatibility
            for each substring of s of length p, add the characters into a hashmap that count the frequency of each character
            if the two hashmaps are equals, then the substring and p are anagrams
            add the left pointer of the window into resuting list
            go to the next substring

            Time complexity: O(n) where n is the length of s
            Space complexity: O(m) for the hashmaps used to check anagrams
            This is the optimal solution as it only goes through s once and uses hashmaps to check
            anagram compatibility in constant time.
         */
        
        List<Integer> res = new ArrayList<>();

        // if p is longer than s, there won't be any anagrams
        if (p.length() > s.length()){
            return res;
        }

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> pMap = new HashMap<>();

        // count the frequencies of p and s characters
        // this is the first substring
        // it will set the correct size of the hash maps
        // once we know the correct size, it will just be a matter of adding or removing keys to track anagrams within a window
        for (int i = 0; i < p.length(); i++){
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        // if the first substring in s is an anagram of p, then the index is 0
        if (sMap.equals(pMap)){
            res.add(0);
        }

        // define window of size p
        int l = 0, r = p.length();

        // check for substrings in s that are anagrams to p
        while (r < s.length()){
            // add or update value of character at right pointer in sMap
            Character rc = s.charAt(r); // rc is character at right pointer
            sMap.put(rc, sMap.getOrDefault(rc, 0) + 1);

            // key point: this is where the window moves
            // we need to move the window to the next substring
            // if character at right pointer was a new character, sMap size is greater than pMap size
            // so we need to maintain sMap size so it can be effectively compared to pMap
            // if a character frequency is == 0, that mean there is no occurence of the character in the substring
            // hence, no reason to keep it in the sMap
            // by removing 0 occurence characters, we reduce the size of sMap in case it was bigger than the size of pMap
            Character lc = s.charAt(l); // lc is character at left pointer
            sMap.put(lc, sMap.get(lc) - 1);

            if (sMap.get(lc) == 0){
                sMap.remove(lc);
            }
            
            // move the window to the next substring beginning
            l++;

            // check for anagram compatibility
            if (sMap.equals(pMap)){
                res.add(l);
            }

            // update end of the window
            r++;
        }
        return res;

    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = findAnagrams(s, p);
        System.out.println("Anagram indices: " + result); // Output: Anagram indices: [0, 6]
    }
}

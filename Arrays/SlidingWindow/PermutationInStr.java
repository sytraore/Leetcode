package Arrays.SlidingWindow;

import java.util.HashMap;

public class PermutationInStr {
    public static boolean checkInclusion(String s1, String s2) {
        /**
            s1 = "abo", s2= "eidbaoo" => true
                => some permutations of s1: "oab", "boa"
            we are basically looking at anagrams of s1 in s2.

            we will use a sliding window technic on 2 hashmaps
            one hashmap will store the frequencies of s1 characters
            and the other will be for s2

            for each s2 substring of length s1, we will check if they are anagrams.
            if they are anagrams, we will return true
        */

        if (s1.length() > s2.length()){
            return false;
        }

        // determinate the size of the window
        int l = 0, r = s1.length();
        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> s2Map = new HashMap<>();

        // get the first substring and compare both maps
        for (int i = 0; i < s1.length(); i++){
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0) + 1);
        }

        // check for anagram
        if (s1Map.equals(s2Map)){
            return true;
        }

        while (r < s2.length()){
            Character c = s2.charAt(r);
            s2Map.put(c, s2Map.getOrDefault(c, 0) + 1);

            // decrement frequency at left pointer
            Character lc = s2.charAt(l);
            s2Map.put(lc, s2Map.get(lc) - 1);

            // if the key value is 0 => this character does not exist in the current window
            // we must remove it to maintain s2Map size at s1Map size
            if (s2Map.get(lc) == 0){
                s2Map.remove(lc);
            }

            // move window one step to the right
            l++;

            // check for anagram
            if (s1Map.equals(s2Map)){
                return true;
            }
            
            // since we already moved the left pointer one step to the right,
            // // moving the right pointer one step to the right will effectively move the window one position to the right
            r++;
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        boolean result = checkInclusion(s1, s2);
        System.out.println("Is s1 a permutation of any substring in s2? " + result); // Output: true
    }
}

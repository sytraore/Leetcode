package Arrays.Hashing;
import java.util.HashMap;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        /*
            special cases:
            s = "" and t = "" => true
            s = "v" and t = "v" => true

            A brute force approach is to sort both strings. If both strings are identique, they are anagrams.
            Appropriate data structure: array
            Appropriate algorithm: sorting
            Time complexity: O(nlogn)
            Space complexity: O(n)

            char[] c1 = s.toCharArray(), c2 = t.toCharArray();
            Arrays.sort(c1);
            Arrays.sort(c2);
            boolean result = Arrays.equals(c1, c2);
            return result;
        */

        /*
            Each letter in s must be found in t with the exact number of occurences.
            => we are not looking for duplicates
            => we are looking for each letter number of occurences
                => hash map data structure is useful to track number of occurences for each letter.
            => if one letter present in s and t but with different number of occurences, s and t are not anagrams
        */

        // if both strings have different length => not anagrams
        int lenS = s.length(), lenT = t.length();

        if (lenS != lenT){
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        // store each letter of s with their frequency in the hashmap
        for (int i = 0; i < lenS; i++){
            Character c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // check if each letter in t occurs the same amount of times as each letter in s
        for (int i = 0; i < lenT; i++){
            Character c = t.charAt(i);
            if (map.containsKey(c)){
                // decrease the frequency number as a way to know that the letter in t is also in s
                map.put(c, map.get(c) - 1);

                // this is where we compare the number of ocuurences
                // if the frequency for the current letter is less than 0
                // there is a difference in number of occurences => not anagrams
                if (map.get(c) < 0){
                    return false;
                }
            }
            else{
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car")); // false
        System.out.println(isAnagram("", "")); // true
        System.out.println(isAnagram("v", "v")); // true
        System.out.println(isAnagram("aabbcc", "abcabc")); // true
    }

}
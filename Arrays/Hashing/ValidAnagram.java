package Arrays.Hashing;
import java.util.HashMap;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        /*
            special cases:
            s = "" and t = "" => true
            s = "v" and t = "v" => true

            use one hashmap to store the characters of one string with their frequencies
            compare the frequency of each character of one string to the frequency of each character of the other string
            if 2 characters have different frequency => false
            if one character present in one string and not present in the other => false 
         */
        
        // check for length
        if (s.length() != t.length()){
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        // fill the maps
        for (int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            if (map.containsKey(c1)){
                map.put(c1, map.get(c1)+1);
            }
            else{
                map.put(c1, 1);
            }
        }

        //compare the frequencies of each character in t 
        // with the frequencies in map
        for (int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if (!map.containsKey(c)){
                return false;
            }
            // update frequeny
            map.put(c, map.get(c)-1);
            // check if some characters remain
            if (map.get(c) < 0){
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
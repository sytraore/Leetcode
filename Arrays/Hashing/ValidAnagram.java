package Arrays.Hashing;
import java.util.HashMap;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        /*
            special cases:
            s = "" and t = "" => true
            s = "v" and t = "v" => true

            use two hashmaps to store the characters of both strings with their frequencies
            compare the frequency of each character of one string to the frequency of each character of the other string
            if 2 characters have different frequency => false
            if one character present in one string and not present in the other => false 
         */
        
        // check for length
        if (s.length() != t.length()){
            return false;
        }

        HashMap<Character, Integer> map1 = new HashMap();

        // fill the maps
        for (int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            if (map1.containsKey(c1)){
                map1.put(c1, map1.get(c1)+1);
            }
            else{
                map1.put(c1, 1);
            }
        }

        //compare the frequencies
        for (int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if (!map1.containsKey(c)){
                return false;
            }
            // update frequeny
            map1.put(c, map1.get(c)-1);
            // check if some characters remain
            if (map1.get(c) < 0){
                return false;
            }
        }
        
        return true;
    }


}
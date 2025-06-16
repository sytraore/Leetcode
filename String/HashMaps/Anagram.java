package Java.String.HashMaps;

import java.util.HashMap;

public class Anagram{
    public static boolean isAnagram(String s, String t) {
        /* brute force

        if (s.length() != t.length()){
            return false;
        }

        //char[] charS = s.toCharArray();
        int count = 0;
        char[] charT = t.toCharArray();
        for (int i = 0; i < s.length(); i++){
            for (int j = 0; j < charT.length; j++){
                if (s.charAt(i) == charT[j]){
                    charT[j] = ' ';
                    count++;
                }
            }
        }

        if (count == s.length()){
            return true;
        }
        return false; */

        // hashmap approach

        if (s.length() != t.length()){
            return false;
        }
        HashMap<Character, Integer> s1 = new HashMap<>();
        HashMap<Character, Integer> s2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if (s1.containsKey(c)){
                int value = s1.get(c);
                s1.put(c, value + 1);
            }
            else{
                s1.put(c, 1);
            }
        }

        for (int i = 0; i < t.length(); i++){
            Character c = t.charAt(i);
            if (s2.containsKey(c)){
                int value = s2.get(c);
                s2.put(c, value + 1);
            }
            else{
                s2.put(c, 1);
            }
        }

        for (Character key: s1.keySet()){
            int valueS1Key = s1.get(key);
            if (s2.containsKey(key)){
                int valueS2Key = s2.get(key);
                if (valueS2Key != valueS1Key){
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
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t)); // true

        s = "rat";
        t = "car";
        System.out.println(isAnagram(s, t)); // false
    }

}
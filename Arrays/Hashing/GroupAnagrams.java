package Arrays.Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        
        List<List<String>> result = new ArrayList<>();

        /*
            for each string, sort it and add it to a hash map if not already there as key. its value would be the string that has been sorted. if the key was in hash map, add the string to the list of values of the key.
            add the various list of values to result

            Time complexity: O(m * n log n) where m is the number of strings and n is the length of the longest string
         */
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s: strs){
            char[] lstChar = s.toCharArray();
            Arrays.sort(lstChar);
            String sortedStr = new String(lstChar);

            if (map.containsKey(sortedStr)){
                List<String> lstValues = map.get(sortedStr);
                lstValues.add(s);
                map.put(sortedStr, lstValues);
            }
            else{
                List<String> lstValues = new ArrayList<>();
                lstValues.add(s);
                map.put(sortedStr, lstValues);
            }
        }

        for (String s: map.keySet()){
            result.add(map.get(s));
        }

        return result;

        /*
         * Optimal solution: Count the frequency of the characters in each string. Format the counting result to a string format, and use that format as a key to a hashmap. the value of the key will be a list of strings whose format match with the key. 

         * List<List<String>> result = new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str:  strs){
            // count the frequency of each character in the string
            int[] count = new int[26];
            for (char currentChar: str.toCharArray()){
                // this is how to count a character frequency in a string
                // we are using the ascii values of the characters to locate the character index in the string and then increase the value at of the number of occurences in the count array
                // for example, the ascii value of 'a' is 97 and the one for 'e' is 101
                // => 'e' - 'a' = 4
                // which means the character 'e' is at index 4 of the count array
                // incrementing the value at that location => count[4] = 1
                // if we go on with the string "eat", the count array will look like this
                // [1, 0, 0, 0, 1, 0, 0, 0, ..., 1, 0, 0, ..., 0]
                count[currentChar - 'a'] += 1;
            }
            // now we need to convert count from array of int to a string
            // the new string will serve as key for all other strings that have the same character frequencies.
            String newStr = Arrays.toString(count);
            // for "eat", newStr will be "1000100...1000..0"

            // now add newStr and the string associated to it to the hashmap
            if (map.containsKey(newStr)){
                List<String> lstValues = map.get(newStr);
                lstValues.add(str);
                map.put(newStr, lstValues);
            }
            else{
                List<String> lstValues = new ArrayList<>();
                lstValues.add(str);
                map.put(newStr, lstValues);
            }
        }

        for (String s: map.keySet()){
            result.add(map.get(s));
        }

        return result;
         * 
         */
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupedAnagrams = groupAnagrams(strs);
        System.out.println(groupedAnagrams); // Output: [[eat, tea, ate], [tan, nat], [bat]]
    }
}

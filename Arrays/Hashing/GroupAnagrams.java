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
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupedAnagrams = groupAnagrams(strs);
        System.out.println(groupedAnagrams); // Output: [[eat, tea, ate], [tan, nat], [bat]]
    }
}

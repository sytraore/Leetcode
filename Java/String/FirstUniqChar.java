package Java.String;
import java.util.HashMap;

public class FirstUniqChar{
    public static int firstUniqChar(String s) {
        // find the first unique character in a string
        // return the index of the first unique character
        // if there is no unique character, return -1

        // use a hashmap to store the frequency of each character
        // iterate through the string and check the frequency of each character
        // if the frequency is 1, return the index of that character

        // time complexity => O(n)
        // space complexity => O(n)

        HashMap<Character, Integer> map = new HashMap<>();
        // iterate through the string and store the frequency of each character
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // if the character is not in the map, add it with a frequency of 1
            // if the character is already in the map, increment its frequency
            map.put(c, map.getOrDefault(c, 0) + 1);

            /* the above line is equivalent to the following lines
             * if (map.containsKey(s.charAt(i))){
                int frequency = map.get(s.charAt(i));
                map.put(s.charAt(i), frequency + 1);
            }
            else{
                map.put(s.charAt(i), 1);
            }
             */
        }

        for (int i = 0; i < s.length(); i++){
            if (map.get(s.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }
}

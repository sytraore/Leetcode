package Java.String;
import java.util.HashMap;

public class FirstUniqChar{
    public int firstUniqChar(String s) {
        // find the first unique character in a string
        // return the index of the first unique character
        // if there is no unique character, return -1

        // use a hashmap to store the frequency of each character
        // iterate through the string and check the frequency of each character
        // if the frequency is 1, return the index of that character

        // time complexity => O(n)
        // space complexity => O(n)

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++){
            if (map.get(s.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }
}

     /*
        figure out why this approach is not working
         problem looks like a substring problem that we can increment the length as long as there is no repeating character for the current character
         Algorithm: sliding window

         * go through the string
         * one pointer will point to the first char
         * the second pointer will point to the char right next to the firts char
         * move the second pointer to the next char of its current char if the current char is different from the char of the first pointer
         * if they are the same, move both pointers forward

         if (s.length() == 0){
         return -1;
     }
     else if (s.length() == 1){
         return 0;
     }

     int p1 = 0;
     int p2 = 1;
     int result = -1;

     while (p2 < s.length()){
         result = -1;
         if (s.charAt(p1) != s.charAt(p2)){
             result = p1;
             p2++;
         }
         else{
             p1++;
         }
     }

     return result;
      */

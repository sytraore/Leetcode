package Java.String.SlidingWindow;

import java.util.HashSet;

public class LengthOfLongestUniqCharSubArray{
    public static int lengthOfLongestSubstring(String s) {
        /*
            some edge cases:
            s = "" => 0
            s = "a" => 1
            s = "abcbda" => 3
            s = "abcculkiw" => 6
            s= "abcculkiwa" => 7

            get the length of a substring => dealing with sequential elements => sliding window algorithm
            the size of the window is not fixed

            loop through the string
            try adding each character to the set
            if successfully added, update the maxLength if the size of the set is bigger
                then expand the window and repeat the process of trying to add the next  
                character
            if not successfully added, the set rejected a duplicate
                check the size of the set and update if necessary
                clear the set and add the char
                move the first pointer to the location of the second pointer
                repeat
         */

        if (s.length() == 0){
            return 0;
        }
        HashSet <Character> unique = new HashSet<>();
        int pointer1 = 0;
        int uniqueSize = 0;
        double maxLength = Double.NEGATIVE_INFINITY;

        for (int pointer2 = 0; pointer2 < s.length(); pointer2++){
            while (unique.contains(s.charAt(pointer2))) {
                unique.remove(s.charAt(pointer1));
                pointer1++;
            }

            unique.add(s.charAt(pointer2));
            uniqueSize = unique.size();
            if (uniqueSize >= maxLength){
                maxLength = uniqueSize;
            }
        }

        return (int)maxLength;     
    }
}
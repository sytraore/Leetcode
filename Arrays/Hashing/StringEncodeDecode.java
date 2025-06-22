package Arrays.Hashing;

import java.util.ArrayList;
import java.util.List;

public class StringEncodeDecode {
    public String encode(List<String> strs) {
        /*
            Encode with a uniq charater that can be used to decode
            the encoded string
            The uniq character must contain the length of each string in
            the list
            because the length of each string will be necessary to decode
            each string back to the list
        */

        StringBuilder encodedStr = new StringBuilder();

        for (String str: strs){
            int strLen = str.length();
            // encoded string example: ["neet","code","love","you"]
            // => "4*neet4*code4*love3*you"
            encodedStr.append(strLen).append("*").append(str);
        }

        return encodedStr.toString();
    }

    public List<String> decode(String str) {
        /*
            Use a key word to find out the string that needs
            to be added to the list of strings.
            Key word cannot be only special characters
            because the string itself might contain special characters

            We must know how long is the substring in order to split it
            from the encoded string
            Once the length of the substring is known, we can effectively
            split the substring independly of its content.
        */

        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < str.length()){
            int j = i;
            
            while (str.charAt(j) != '*'){
                j++;
            }
            int subStrLen = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            j = i + subStrLen;
            result.add(str.substring(i, j));
            i = j;
        }

        return result;
    }
}

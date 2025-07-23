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
            // add something to make it easier to decode
            int len = str.length();
            encodedStr = encodedStr.append(len).append("#");
            encodedStr = encodedStr.append(str);
        }
        // encoded string example: ["neet","code","love","you"]
        // => "4#neet4#code4#love3#you"
        
        return encodedStr.toString();
    }

    public List<String> decode(String str) {
        // the idea is to know from where to get the substring
        // based on the length of the string that has been encoded.
        // we need one pointer that will point to the start of the substring
        // and another pointer that will point to the end of the substring
        // we need to be able to update both pointers so that they correspond
        // to the next substring.

        List<String> result = new ArrayList<>();
        // pointer that points to the start of an encoded substring
        int start = 0;

        // go through the string
        while (start < str.length()){
            // pointer that points to the end of an encoded substring
            int end = start;

            // search for the special character used for encoding
            // that will tell us where to start looking for the substring
            while (str.charAt(end) != '#'){
                end++;
            }

            // we found the '#' position
            // the char before '#' is the length of the substring
            int len = Integer.parseInt(str.substring(start, end));

            // now that we now how many letters are in the substring
            // we can update the pointers so that their positions
            // corresponds to the encoded substring

            // this will make start points to where the substring
            // starts without encoded characters
            start = end + 1;
            // this will make end points to where the next encoded substring starts
            // it will be useful to know this position to later update the start pointer
            end = start + len;
            // get the substring with no encoded character and store it
            String theStr = str.substring(start, end);
            result.add(theStr);
            
            // update the start pointer to point to the beginning
            // of the next encoded substring
            start = end;
        }

        return result;
    }
}

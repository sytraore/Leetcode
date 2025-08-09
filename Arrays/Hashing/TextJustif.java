package Arrays.Hashing;

import java.util.ArrayList;
import java.util.List;

public class TextJustif {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        /*
            The algorithm works line-by-line in a two-phase process:
            1. Pack as many words as possible onto the current line.
            2. Format that line with the correct spacing.

            --- PHASE 1: PACKING A LINE ---
            - Greedily add words to the current line one by one until the next word cannot fit.
            - A word fits if the line's current length + the new word's length + minimum spaces <= maxWidth.

            --- PHASE 2: FORMATTING THE LINE ---
            Once a line is packed, determine its type and format it.

            // CASE A: Normal Justification (for any middle line with more than one word)
            1. Calculate total spaces needed:
                - totalSpaces = maxWidth - (length of all words on the line)
                - numGaps = (number of words) - 1
            2. Calculate space distribution:
                - spacesPerGap = totalSpaces / numGaps
                - extraSpaces = totalSpaces % numGaps
            3. Build the line: Add each word, followed by 'spacesPerGap'. The first 'extraSpaces' gaps get one
             additional space.

            // CASE B: Special Cases (Left-Justified)
            - This formatting applies if:
                a) The line contains only one word.
                b) It is the very last line of the text.
            - Build the line: Join the words with a single space, then add all remaining spaces to the end to
             reach maxWidth.
            
            Time complexity: O(n * m) where m is the maxWidth
            Space complexity: O(n * m)
        */

        List<String> res = new ArrayList<>();
        // line represent the words that have been grouped together to reach maxWidth
        List<String> line = new ArrayList<>();
        int totalNumbChar = 0; // this is to track the total number of characters in a line 
    
        int i = 0; // iterator to go through the array

        while (i < words.length){
            // maxWidth has not been reached yet
            // check if the total number of characters in a line in addition with 
            // the spaces (initially 1 for each word in the list line)
            // and in addition with the curent word length, is not >= maxWidth
            if (totalNumbChar + line.size() + words[i].length() <= maxWidth){
                // add the word to the list
                line.add(words[i]);
                // increament the total number of characters currently < maxWidth
                totalNumbChar += words[i].length();
                i++; // go to the next word
            }

            // maxWidth has been reached
            // distribute the spaces
            else{
                // number of spaces needed to reach maxWidth
                int numbSpaces = maxWidth - totalNumbChar;
                // numbOfSpaceLocations stores the value of how many locations, spaces must be added
                int numbOfSpaceLocations = (Math.max(1, line.size() - 1));
                // numbEvenSpaces stores the value of how many spaces need to be added at each space location
                int numbEvenSpaces = numbSpaces / numbOfSpaceLocations;
                // numbOddSpaces stores the value of how many spaces must be added at the leftmost space location
                int numbOddSpaces = numbSpaces % numbOfSpaceLocations;

                // this is how we know if we can evenly distribute the spaces
                for (int j = 0; j < numbOfSpaceLocations; j++){
                    // distribute evenly the spaces according to where they should be added
                    line.set(j, line.get(j) + " ".repeat(numbEvenSpaces));
                    // distribute evenly the spaces by favoring the leftmost space location
                    // if there was not an even number of spaces to be added.
                    if (numbOddSpaces != 0){
                        line.set(j, line.get(j) + " ");
                        numbOddSpaces--;
                    }
                }

                // the line now has a length of maxWidth
                // add it to the resulting list
                String correctWord =  String.join("",line);
                res.add(correctWord);
                // reset everything to restart the process
                line.clear();
                totalNumbChar = 0;
            }
        }
        
        String lastLine = String.join(" ", line);
        // compute the number of needed trailing spaces.
        int numbTrailSpaces = maxWidth - lastLine.length();
        // add the trailing spaces to the last word.
        lastLine += " ".repeat(numbTrailSpaces);
        // update returning list
        res.add(lastLine);

        return res;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        List<String> justifiedText = fullJustify(words, maxWidth);
        for (String line : justifiedText) {
            System.out.println("\"" + line + "\"");
        }
    }
}

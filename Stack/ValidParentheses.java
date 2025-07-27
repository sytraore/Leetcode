package Stack;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s) {

        /*
         * Brute force solution: check if the string contains any of the following substrings: (), {}, []
         * these substrings are valid parentheses
         * if the string contains any of these substrings, we can replace them by empty strings and check again
         * if the string is empty at the end, it means the parentheses are valid
         * Time complexity: O(n^2) because we are checking the string for each replacement
         * Space complexity: O(n) because we are storing the string in memory
         * 
         * while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
                s = s.replace("()", "");
                s = s.replace("{}", "");
                s = s.replace("[]", "");
            }
            return s.isEmpty();
         * 
         */

        /**
         * Optimal solution:
            we go through the string
            the closing parenthesis must be preceded by the same type opening parenthese
            if the closing parenthesis is not preceded by the same type opening parenthese, the parenthese is not valid
            we need a way to know the opening parenthese of each closing parenthese
            we can use a hash map, the key will be the closing parenthese and the value will be the opening correspondant
            we need to be able to track the order of the parentheses.
            we can use a stack data structure to do this since the closing parenthese must be preceded by its corresponding opening parenthese
            the stack will only store the opening parentheses.
            when we encounter a closing parenthese, the current parenthese at the top of the stack has to be its corresponding opening parenthese.

            at the end of the string, if the stack is not empty
                => it means there are still opening parentheses
                    => which will imply that there were less closing parentheses compared to the opening ones
                    which will give false as final result
            but if the stack is empty,
                => all opening parentheses have been matched with their closing correspondents
                which will give true as final result

            Data structure: HashMap and Stack
            Time complexity: O(n)
            Space complexity: O(n)
        */

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);

            // when current character is a closing parenthese
            // we either remove it from the stack => they match each other
            // or we return false because the preceding parenthese was not of the same type
            if (map.containsKey(c)){
                // check if the stack is not empty becuase if the stack is empty, there is no preceding parenthese
                // implying that the first character is a closing parenthese
                // and if the top of the stack is the opening correspondent of the current parenthese,
                // it means the string does not have the correct order of parentheses
                if (!stack.empty() && stack.peek() == map.get(c)){
                    stack.pop();
                }
                else{
                    return false;
                }
            }

            // when the current parenthese is an opening parenthese
            // we save it in the stack
            else{
                stack.push(c);
            }
        }

        return stack.empty();
        
    }

    public static void main(String[] args) {
        String s = "([{}])";
        boolean result = isValid(s);
        System.out.println(s + "\nIs the string valid? " + result); // true

        String s2 = "([)]";
        boolean result2 = isValid(s2);
        System.out.println(s2 + "\nIs the string valid? " + result2); // false
    }
    
}

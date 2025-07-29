package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    private void backtrack(int openN, int closedN, int n, List<String> res, StringBuilder stack) {
        // base case
        // this is how we know we reached the required number of pairs
        if (openN == closedN && openN == n) {
            res.add(stack.toString());
            return;
        }

        // 1st choice
        // we only add an opening parenthese if the number of opening parentheses has not reached the required number of pairs.
        if (openN < n) {
            stack.append('(');
            // continue the process to see whether we reach the required number of pairs or not
            backtrack(openN + 1, closedN, n, res, stack);
            // Backtrack: This crucial step undoes our last choice. By removing the character we just added
            // we return the stack to its previous state. This allows the function to proceed and explore
            // a different path from the same decision point (e.g., trying to add a ')' after exploring the '(' path
            // the reason we do this is because, we want to be able to try all possible combinations
            stack.deleteCharAt(stack.length() - 1);
        }

        // 2nd choice
        // we only add a closing parenthese if there are more opening parentheses than closings
        // same logic as above concerning the recursive call and stack popping
        if (closedN < openN) {
            stack.append(')');
            backtrack(openN, closedN + 1, n, res, stack);
            stack.deleteCharAt(stack.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        /**
            there are n pairs of parentheses => there will be n opening and n closing parentheses
            but how can we generate more than one combinations of valid parentheses?
            before any closing parentheses, there is need for opening ones.
            so we must always start with opening parentheses
            We can add a closing parenthesis at any point, as long as it has a corresponding open parenthesis to match with
            After exploring a path, we must "backtrack" by undoing our last decision. 
            This allows us to return to the previous fork in the road and explore a different set of choices.
            we will repeat the step untill we go back to the stack where it was only one open parenthese.

            it will look something similar to this: if n = 2:
                => (( => (()) checked
                => ()() checked
            those 2 will be added to the list to be returned.
        */
        List<String> res = new ArrayList<>();
        // this is not a typical stack data structure but it is used as such
        // the reason we used a string builder instead of a real stack, is because we want to be able
        // to easily get a string from the generated parentheses.
        StringBuilder stack = new StringBuilder();
        // core algorithm
        backtrack(0, 0, n, res, stack);
        return res;
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        List<String> result = gp.generateParenthesis(3);
        System.out.println(result); // Output: ["((()))","(()())","(())()","()(())","()()()"]
    }
}

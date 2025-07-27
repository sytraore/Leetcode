package Stack;

import java.util.Stack;

public class BaseballGame {
    public static int calPoints(String[] operations) {
        /*
            we need to be able to track the value of the most recent record in order to apply the current operation on it
            we can use a stack to do this
            the stack will store the record values
            we will go through the list of operations
            the result of each operation will be reflected on the stack
            when the opeartion is to save a score in the record,
                => we will push the integer value on the stack
            when the operation is "C"
                => we will pop the stack
            when the operation is "D"
                => we will get the top element of the stack, double it and push the doubled value on the stack
            when the operation is "+"
                => we will pop the stack to get the most recent value of the record
                    => we will store that value in a temporary variable
                => we will get the top element of the stack and add it to the temporary variable value
                => we will push the temporary variable back on the stack
                => we will push the sum obtained on the stack
            once we reached the end of the operations,
            we will compute the sum of the scores in the stack and return the result

            Time complexity: O(n)
            Space complexity: O(n)
        */

        Stack<Integer> stack = new Stack<>();
        int score = 0;
        int res = 0;

        for (String op: operations){
            if (op.equals("C")){
                stack.pop();
            }

            else if (op.equals("D")){
                int newScore = stack.peek() * 2;
                stack.push(newScore);
            }

            else if (op.equals("+")){
                int temp = stack.pop();
                int newScore = temp + stack.peek();
                stack.push(temp);
                stack.push(newScore);
            }

            // op is string with digit => we need to store a score
            else {
                score = Integer.parseInt(op);
                stack.push(score);
            }
        }

        // compute the sum of stack
        while(!stack.empty()){
            res += stack.pop();
        }

        return res;
    }

    public static void main(String[] args) {
        // Example usage
        String[] operations = {"5", "2", "C", "D", "+"};
        int result = calPoints(operations);
        System.out.println("Total score: " + result); // Output: Total score: 30
    }
}

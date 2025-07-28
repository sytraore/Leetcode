package Stack;

import java.util.Stack;

public class EvaluateReversePolish {
    public static int evalRPN(String[] tokens) {
        /*
            we will only add the operands on a stack data structure
            once we find an operator, we pop the two top elements from the stack
            and complete the operation with the appropriate operator.
            we then push the result of the operation back to the stack
            we store the result of the operation in a variable that will change as we move through the array.
            the final result will be located at the top of the stack as there will only remain one element on the stack
        */

        Stack<Integer> stack = new Stack<>();
        int res = 0, temp = 0;

        for (String op: tokens){
            switch (op) {
                case "*":
                    temp = stack.pop();
                    res = stack.pop() * temp;
                    stack.push(res);
                    break;

                case "+":
                    temp = stack.pop();
                    res = stack.pop() + temp;
                    stack.push(res);
                    break;

                case "-":
                    temp = stack.pop();
                    res = stack.pop() - temp;
                    stack.push(res);
                    break;
                
                case "/":
                    temp = stack.pop();
                    res = stack.pop() / temp;
                    stack.push(res);
                    break;
                
                default:
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        // Example usage
        String[] tokens = {"2", "1", "+", "3", "*"};
        int result = evalRPN(tokens);
        System.out.println("Result: " + result); // Output: Result: 9
    }
}

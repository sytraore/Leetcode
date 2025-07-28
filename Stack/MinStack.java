package Stack;

import java.util.ArrayList;
import java.util.Stack;

public class MinStack {
    /**
        we use a stack to store only minimum values in order to track them
        the stack will have a decreasing order
        so the smallest value will be the top of the stack
        if we ever need that value in the getMin method, we just need to get the top of the stack
        if we ever delete that value, we are assured that the next top value will be the smallest value in the 
        arraylist

        we use an arraylist to be the data structure used to construct the main stack because appending a new element is O(1). removing will also be O(1) as we will be removing the last element of the arraylist
        getting the top of the main stack will also be easier as it is O(1) to access the last element of the 
        arraylist.

        Overall time complexity: O(1)
        space complexity: O(n)
    */
    private ArrayList<Integer> lst; // main data structure that will store the data in our stack
    private Stack<Integer> stack;   // this stack will store only minimum values of the lst

    public MinStack() {
        lst = new ArrayList<>();
        stack = new Stack<>();
    }
    
    /**
        push a value to the main stack => appending a value to the end of the arraylist
    */
    public void push(int val) {
        // if the lst is empty, add the value to both the arraylist and stack
        // as the value will be the minimum value in the lst
        if (lst.size() == 0){
            lst.add(val);
            stack.push(val);
        }

        // if the arraylist is not empty => there is at least one minimum value in the stack
        // so we need to compare the current value to the value in the stack and update the stack if needed
        else {
            if (val <= stack.peek()){
                stack.push(val);
            }
            lst.add(val);
        }
    }
    
    /*
        remove the top value of the main stack => removing the last element of the arraylist
    */
    public void pop() {
        // when removing a value from the main stack, we also need to remove it from the minimum values stack
        // in case the value removed from the main stack is the current smallest value in the main stack
        // as long as the minimum values stack is not empty, we can remove values from it
        if (!stack.empty()){
            // check if the value to be removed is the smallest value
            if (lst.get(lst.size() - 1).equals(stack.peek())){
                stack.pop();
                lst.remove(lst.size() - 1);
                return;
            }
        }
        
        lst.remove(lst.size() - 1);
    }
    
    /*
        get the top of the main stack => get the last element of the arraylist
    */
    public int top() {
        return lst.get(lst.size() - 1);
    }
    
    /*
        get the smallest value in the main stack => get the top of our minimum values stack
    */
    public int getMin() {
        // as long as the minimum values stack is not empty, there will always be a smallest value in the main stack
        if (stack.empty()){
            return 0;
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        // Example usage
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Returns -3
        minStack.pop();
        System.out.println(minStack.top());    // Returns 0
        System.out.println(minStack.getMin()); // Returns -2
    }
}

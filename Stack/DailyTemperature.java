package Stack;

import java.util.Stack;

public class DailyTemperature {
    public static int[] dailyTemperatures(int[] temperatures) {
        /*
            value at answer[i] depends of value at temperature[i+1]
            if the temperature at i is less than the temperature at i+1, we can start counting the number of days
            as long as the temperature at i is greater or equal than the temperature at i+1, we keep looking
            for a warmer temperature.
            Time complexity: O(n^2)
            Space complexity: O(n)
            Issue: not efficient with large arrays

            int len = temperatures.length;
        int[] answer = new int[len];

        if (len == 1){
            answer[0] = temperatures[0];
        }
        for (int i = 0; i < len; i++){
            int count = 0;
            
            for(int j = i + 1; j < len; j++) {
                if (temperatures[i] < temperatures[j]){
                    count++;
                    answer[i] = count;
                    break;
                }
                count++;
            }            
        }

        return answer;

            Optimal solution: 
            we want to be able to track the index of the temperatures at i and at i - 1 (with i > 0)
            if we know the indices, we can compute their difference as it will give the number of days we want.
            we need to be able to know what temperature was at day i-1 when we are at day i
            a stack data structure can help us achieve that easily since it is easy to get the most recent element added to the stack
            the stack will store pairs of temperature & index
            before pushing a pair to the stack, we must first make sure that the pair to be pushed does not
            have a temperature lesser than the temperature before it
            if yes:
                => add such temperature and its index to the stack
            if no:
                => compute the difference between their indices and store the result in the final array
                => then remove the current pair from the stack since we found a warmer temperature
                => finaly, add the warmer temperature with its index to the stack.

            Time complexity: O(n)
            Space complexity: O(n)

        */

        int len = temperatures.length;
        int[] answer = new int[len];
        // we are storing pairs on the stack
        // stack.peek()[0] will be where we store temperature
        // stack.peek()[1] will be where we store the temperature's index in the input array
        Stack<int[]> stack = new Stack<>();     

        for (int i = 0; i < len; i++){
            // check if the current temperature is warmer than the previous temperature
            while (!stack.empty() && stack.peek()[0] < temperatures[i]){
                // remove the previous temperature which is at the top of the stack
                int[] prevPair = stack.pop();
                // prevPair[1] is the index of the previous temperature
                // we are updating the number of elements we traversed before we found a value > previous temperature
                answer[prevPair[1]] = i - prevPair[1];
            }
            // add a pair to the stack no matter what
            // if the stack is empty, either it is because this is the first iteration
            // or because there have not been any temperature lesser than the current temperature since lesser temperature are removed from the stack above
            int[] pair = {temperatures[i], i};
            stack.push(pair);
        }

        return answer;
        
    }

    public static void main(String[] args) {
        // Example usage
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);
        for (int days : result) {
            System.out.print(days + " ");
        }
        // Output: 1 1 4 2 1 1 0 0
    }
}

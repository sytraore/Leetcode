package Stack;

import java.util.Stack;

public class LargestRecHistogr {
    public static int largestRectangleArea(int[] heights) {
        /*
            Brute force: ...
        */

        // int maxArea = 0; ... (Your brute force implementation is here)

        /*
            Optimal Solution:
            the core idea is the same 
            => find the first smaller height on the left side and right side of the
            current height.
            this time we will try to avoid nested loops for optimization
            we need a way to track the order of the heights
            as we go through the array.
            we will specially look for values that are bigger than the current height

            Time complexity: O(n)
            Space complexity: O(n)
            
            // ----- MY ANSWERS TO YOUR QUESTIONS -----

            // what data structure should be used? why?
            // ANSWER: A Stack. This pattern is called a "monotonic stack." A stack is perfect here because we only care about
            // the most recent bars that are taller than the current bar. The LIFO (Last-In, First-Out) nature of a stack gives us
            // instant access to the previous bar that could be a boundary for a rectangle. It lets us find the "next smaller element" efficiently.

        */
        int maxArea = 0;
        // The stack will store pairs of: {index, height}
        Stack<int[]> stack = new Stack<>(); 

        for (int i = 0; i < heights.length; i++) {
            int start = i;
            
            // why? 
            // ANSWER: This loop triggers when the current bar (heights[i]) is SHORTER than the bar at the top of the stack.
            // This is a critical moment. It means the taller bar at the top of the stack has just found its "right boundary."
            // Its rectangle cannot extend any further to the right because heights[i] is shorter.
            // So, we pop that taller bar, calculate its maximum possible area now that we know its height, its left boundary (the 'start' index we stored with it),
            // and its right boundary (the current index 'i'). We repeat this for any other bars on the stack that are also taller than heights[i].
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] top = stack.pop();
                int index = top[0];
                int height = top[1];
                maxArea = Math.max(maxArea, height * (i - index));
                // We extend the potential start of the current, shorter bar to the left,
                // because we know it can at least form a rectangle that starts where the taller, popped bar started.
                start = index;
            }
            // The stack will always hold bars in ascending order of height.
            stack.push(new int[]{start, heights[i]});
        }

        // why this?
        // ANSWER: After the main loop finishes, some bars might still be on the stack. These are the bars that never found a
        // shorter bar to their right. This means their rectangles can extend all the way to the end of the entire histogram.
        // This final loop processes these remaining bars, using the total length of the array as their right boundary.
        for (int[] pair : stack) {
            int index = pair[0];
            int height = pair[1];
            maxArea = Math.max(maxArea, height * (heights.length - index));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(heights);
        System.out.println("Maximum Rectangle Area: " + maxArea); // Output: 10
    }

}

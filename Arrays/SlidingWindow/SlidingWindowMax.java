package Arrays.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMax {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        /*
            Brute force:
            for each window, we will look for the biggest value and store it in a resulting array
            time complexity: O(n * k) 
            space complexity: O(n)  
            
            // resulting array's size is the number of windows
            // which can be found by simply substracting the number of elements per window to the total 
            // number of elements in the array + 1 
            int[] res = new int[nums.length - k + 1];
            int maxVal = 0;

            // loop through the array
            for (int i = 0; i < res.length; i++){
                maxVal = nums[i];
                // loop k times from nums[i]
                for (int j = i; j < i + k; j++){
                    // look for the biggest value in the window
                    maxVal = Math.max(maxVal, nums[j]);
                }

                // update the resulting array
                res[i] = maxVal;
            }

            return res;

        */

        /* Optimal solution: sliding window technic + deque data structure
            The idea remains the same. we will look for the biggest value in window of size k
            but this time, we won't have to look for the biggest value at every single window
            this approach has been proven to not be efficient when the length of nums is too long
            this time, we need to be able to track the position of the bigest value of the previous window
            knowing its position will help us look for another bigger value throughout the array

            // what data structure do we need? why?
            // ANSWER: We need a Deque (Double-Ended Queue). A standard queue only lets you add to the back and remove from the front.
            // A stack only lets you use one end. For this algorithm, we need to be able to efficiently:
            // 1. Remove elements from the BACK (when a new, larger number makes older, smaller numbers irrelevant).
            // 2. Remove elements from the FRONT (when the window slides past the current maximum).
            // A Deque provides O(1) access to both ends, making it the perfect tool.

            // how is knowing the biggest value position helpful in this problem?
            // ANSWER: Storing the index (position) is the key. If we only stored the *values*, we wouldn't know when to remove an old maximum.
            // For example, if the max is 8 and we slide the window, how do we know if that 8 is now outside the window? 
            // By storing the *index* of the max value, we can simply check if that index is less than the window's left boundary (if l > max_index).
            // It lets us know when the max value has expired.

            // any other reasons why this solution is the one?
            // ANSWER: The main reason this works is how the deque is maintained. The deque only stores indices of "useful" numbers. 
            // It's kept in a "monotonic decreasing" state, meaning the values at the indices in the deque are always from largest to smallest.
            // When we add a new number, we first "clean" the deque by removing any smaller numbers from the back. Why? Because if the new number
            // is bigger, those older, smaller numbers can NEVER be the maximum again.
            // This clever cleanup ensures that the largest number for the current window is ALWAYS at the very front of the deque, ready to be accessed in O(1) time.
        */

        int n = nums.length;
        int[] output = new int[n - k + 1];
        // The deque will store indices of the numbers.
        Deque<Integer> q = new LinkedList<>();
        int l = 0, r = 0;

        while (r < n) {
            // "Clean" the deque: Before adding the new element's index, remove any indices from the
            // back that correspond to values smaller than the new element.
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            // Add the current element's index to the back.
            q.addLast(r);

            // Remove the max element from the front if it's no longer in the window.
            if (l > q.getFirst()) {
                q.removeFirst();
            }

            // Once the window is full (size k), we can start recording the results.
            if ((r + 1) >= k) {
                // The max element for this window is always at the front of the deque.
                output[l] = nums[q.getFirst()];
                // Slide the window forward.
                l++;
            }
            r++;
        }

        return output;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}

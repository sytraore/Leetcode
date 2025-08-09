package Arrays.SlidingWindow;

public class MinSubArraySize{

    public static int minSubArrayLen(int target, int[] nums) {
        /*
            we need to find the smallest subarray that sum up to >= target
            we are dealing with subarrays => a sliding window algorithm will be appropriate
            to track the subarray content.
            we will expand the window as long as the sum is less than target
            if the sum is greater or equal than target, we will (this is where we will look for the
            minimum size):
                => update the minimum size
                => shrink the window
                => compute the sum of the new window
                => check if that sum is still >= target
                => if it is still >= target => we will update the minimum size
                => it is < target, expand the window
            return the minimum size

            Time complexity: O(n)
            Space complexity: O(1)
        */

        int l = 0, r = 0, windowSum = 0, res = Integer.MAX_VALUE;

        while (r < nums.length){
            windowSum += nums[r];

            while(windowSum >= target){
                // save size
                int windowSize = r - l + 1;
                // save minimum size
                res = Math.min(res, windowSize);

                // compute the new window sum
                // by removing the value at the left limit of the window
                windowSum -= nums[l];
                // shrink window
                l++;
            }
            
            // expand window
            r++;
            
        }

        // we never found a subarray with elements sum up to >= target
        // hence res value will never change
        if (res == Integer.MAX_VALUE){
            return 0;
        }

        return res;
    }

    public static void main(String [] args){
        int [] nums1 = {2,3,1,2,4,3};
        int target1 = 7;

        System.out.println(minSubArrayLen(target1, nums1)); // expected output: 2
        // the subarray is [4,3] or [2,4,3]
        // the length of the subarray is 2
        // the sum of the subarray is 7
        // the minimal length of the subarray is 2

        int [] nums2 = {1,4,4};
        int target2 = 4;

        System.out.println(minSubArrayLen(target2, nums2)); // expected output: 1
        // the subarray is [4]
        // the length of the subarray is 1
        // the sum of the subarray is 4
        // the minimal length of the subarray is 1
        
    }
}
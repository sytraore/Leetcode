package Arrays.TwoPointers;

import java.util.Arrays;

public class TrapRainWater {

    public static int trap(int[] height) {
        /*
            in real life, for water to be trapped, there must be a hole 
            a hole can be represented here with 3 bars alligned in such a way that the 2nd bar is the hole
            the right and left bars are what make the depth of the hole
            the rain water will fall between the 3 bars => the rain will fall on the second bar
            which gives us 2 scenarios:
                => the second bar either does not exist => value is 0
                => or the second bar exist but is smaller than either the left or right bar
            
            we are tracking how many holes will contain water
            so at each height/bar, we need to find the highest height to its left and right sides.
            because those values will act like the dimension of the hole and will be useful to estimate the depth
            of the hole (the number of units water)
            once found, we need to find the smallest value between those 2 because the water unit will increase
            till it reaches the top of the smallest height.
            once the smallest height between the biggest heights to the left and right sides of the current height is found, substracting the current hight from that smallest height will give us exactly how many units
            of water can be trapped at the current height.
        
        // this is a good solution but its space complexity can be optimized
        // time complexity: O(n)
        // space complexity: O(n)

        int n = height.length;
        if (n == 0) {
            return 0;
        }

        // we will store the biggest height to the left for each height in this array
        int[] leftMax = new int[n];
        // we will store the biggest height to the right for each height in this array
        int[] rightMax = new int[n];

        leftMax[0] = height[0];     // the first height has no bigger height to the left
        for (int i = 1; i < n; i++) {
            // basically look for any values that are bigger than the most recent added height in leftMax array
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];    // the last height has no bigger height to the right
        for (int i = n - 2; i >= 0; i--) {
            // basically look for any values that are bigger than the most recent added height in rightMax array
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            // key formula
            // find the smallest value between the biggest height to the left and right of current height
            // and substract the current height value from it to find the number of water unit that can be trapped on that height.
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;

        */

        /*
            Optimal solution: 2 pointers technic

            The core algorithm has not changed. We need to find the biggest heights to both left and right of each
            height, choose the smallest value between them and substract the height value itself from that found
            small value. But this time, we will look for the biggest heights to both left and right at the same 
            time of finding the smallest value to substract from.
            If we know that the value at a left pointer is smaller than the value at a right pointer, we can assume
            that we have found the side that has the smallest value between the biggest heights 
            to both left and right of each height. 
            => so we only need to find the biggest height on that side and substract the current height value.

            time complexity: O(n)
            space complexity: O(1)
        */

        if (height.length == 0){
            return 0;
        }

        // initialize the 2 pointers
        int l = 0, r = height.length - 1;
        // initialize the biggest height values at the left and right of each height/bar
        int currMaxLeftSide = height[l], currMaxRightSide = height[r];

        int res = 0;

        while (l < r){
            // found smallest height between biggest heights to left and right of the current height at the left
            // side of the current height
            if (currMaxLeftSide < currMaxRightSide){
                // look for bigger height than currMaxLeftSide
                // if found, update currMaxLeftSide before computing the result
                l++;
                currMaxLeftSide = Math.max(currMaxLeftSide, height[l]);
                // compute how many unit water can be trapped at this height
                res += currMaxLeftSide - height[l];
            }
            // found smallest height between biggest heights to left and right of the current height at the right
            // side of the current height
            else {
                // look for bigger height than currMaxRightSide
                // if found, update currMaxRightSide before computing the result
                r--;
                currMaxRightSide = Math.max(currMaxRightSide, height[r]);
                // compute how many unit water can be trapped at this height
                res += currMaxRightSide - height[r];
            }
        }

        return res;
    }
    public static void main(String[] args){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trap(height);
        System.out.println("Trapped rainwater: " + result); // Output: 6
        // Explanation: The water can be trapped in the valleys formed by the heights.
    }
}

package Java.Arrays.SlidingWindow;

public class MinSubArraySize{

    public static int minSubArraySize(int target, int [] nums){
        /*
            subArray => sequential problems => sliding window technique
            loop through the array
            add each element from pointer1 to pointer2
            check if the sum is >= target
            no => expand the window by incrementing pointer2
            yes => store the current length of the window
                   chek if that is the minimal length
                   shrink the window and see if the sum is still >= target by:
                   simply removing the value of the element pointed by pointer1 
                   then shring window by incrementing pointer1
                   do this as long as the sum is >= target to find the minimal length of the subarray who's sum is >= target
                       
         */

         int pointer1 = 0;
         int theSum = 0;
         double theLength = Double.POSITIVE_INFINITY;
 
         for (int pointer2 = 0; pointer2 < nums.length; pointer2++){
             theSum += nums[pointer2];
 
             while (theSum >= target){
                 if ((pointer2 - pointer1 + 1) <= theLength){
                     theLength = pointer2 - pointer1 + 1;
                 }
 
                 theSum -= nums[pointer1];
                 pointer1++;
             }
         }
         if (theLength == Double.POSITIVE_INFINITY){
             return 0;
         }
        return (int)theLength;
    }

    public static void main(String [] args){
        int [] nums1 = {2,3,1,2,4,3};
        int target1 = 7;

        System.out.println(minSubArraySize(target1, nums1)); // expected output: 2
        // the subarray is [4,3] or [2,4,3]
        // the length of the subarray is 2
        // the sum of the subarray is 7
        // the minimal length of the subarray is 2

        int [] nums2 = {1,4,4};
        int target2 = 4;

        System.out.println(minSubArraySize(target2, nums2)); // expected output: 1
        // the subarray is [4]
        // the length of the subarray is 1
        // the sum of the subarray is 4
        // the minimal length of the subarray is 1
        
    }
}
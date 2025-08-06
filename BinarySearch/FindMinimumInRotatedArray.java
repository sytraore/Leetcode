package BinarySearch;

public class FindMinimumInRotatedArray {
    public static int findMin(int[] nums) {
        /**
        Brute force:
        Time complexity: O(n)

            for (int i = 0; i < nums.length; i++){
                for (int j = i; j < nums.length; j++){
                    if (nums[i] > nums[j]){
                        return nums[j]; 
                    }
                }
            }
            return nums[0];
        */

        /**
            Optimal solution:
            since nums is a rotated array, its first element is bigger than the smallest element
            and the first elemenet before the smallest element if the array has been rotated less
            than n times (n being the length of the array)
            we will apply a binary seach algorithm on the array
            and look for a value that is less than the first element
            once a value is found, we will need to store that value and continue looking for a smaller value

            Time complexity: O(log n)
            Space complexity: O(1)
        */

        int bigVal = nums[0], l = 0, r = nums.length - 1, res = nums[0];

        while (l <= r){
            int m = l + (r - l) / 2;

            // value found, but we need to check if there are not smaller than this value
            // since we just fell on the value at the mid index
            // perhaps, a smaller value is on the left side of the mid index
            if (nums[m] < bigVal){
                res = nums[m];
                // look on the left of the mid index
                r = m - 1;
            }

            // since the array is sorted in ascending order, if the current number at mid index is bigger
            // that implies that the smallest number is on the right of the mid index
            // hence we move the left pointer instead of the right pointer like in classic binary search
            if (nums[m] >= bigVal){
                l = m + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums)); // Output: 1
    }
}

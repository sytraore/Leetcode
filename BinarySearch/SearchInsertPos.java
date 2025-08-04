package BinarySearch;

public class SearchInsertPos {
    public static int searchInsert(int[] nums, int target) {
        /** Apply a binary search algorithm on the array
            if the value at the middle index of the current subarray
            is equal to target, return that index
            else, return the value of the left pointer
        */

        if (nums.length == 1){
            if (nums[0] < target){
                return 1;
            }
            return 0;
        }

        int l = 0, r = nums.length -1;

        while (l <= r){
            int m = (l + r) / 2;

            if (nums[m] < target){
                l = m + 1;
            }
            else if (nums[m] > target){
                r = m - 1;
            }
            else {
                return m;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(searchInsert(nums, target)); // Output: 2

        target = 2;
        System.out.println(searchInsert(nums, target)); // Output: 1

        target = 7;
        System.out.println(searchInsert(nums, target)); // Output: 4

        target = 0;
        System.out.println(searchInsert(nums, target)); // Output: 0
    }
}

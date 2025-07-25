package BinarySearch;

public class BinarySearch {
    public static int search(int[] nums, int target) {
        /**
            since the array is already sorted in ascending order,
            we first check if the target is not at the middle of the array
            if not, we check the value of the middle element of the array            
            if the value < target, 
                => the target is more on the right side of the middle element pointer
            if the value > target
                => the target is more on the left side of the right pointer
            we repeat this process until target is found
            at each iteration, the array is divided into two

            Time complexity: O(log n)
            Space complexity: O(1)
        */
        if (nums.length == 1 && nums[0] == target){
            return 0;
        }

        int l = 0, r = nums.length - 1;

        while (l <= r){
            int midInd = (l + r) / 2;

            if (nums[midInd] < target){
                l = midInd + 1; // move the left pointer to the right of the middle element
            }

            else if (nums[midInd] > target){
                r = midInd - 1; // move the right pointer to the left of the middle element
            }
            else{
                return midInd;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 3;
        int result = search(nums, target);
        System.out.println("Index of target " + target + ": " + result); // Output: Index of target 3: 2
    }
    
}

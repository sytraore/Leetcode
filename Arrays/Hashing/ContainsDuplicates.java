package Arrays.Hashing;
import java.util.HashSet;

public class ContainsDuplicates{
    public static boolean containsDuplicate(int[] nums) {
        /**
            brute force
            loop through the array and store the value at the current iterator
            loop a second time within the first loop and check if the other values in the array
            are equal to the value at the first iterator
            return true if true, else, continue loop
         

        if (nums.length <= 1) {
            return false;
        }
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int j = 0;
            while (j < size) {
                if (i == j) {
                    j++;
                    continue;
                }
                if (nums[j] == nums[i]){
                    return true;
                }
                j++;
            }
        }
        return false; 
        
        Issue with this method: does not do well with large arrays due to O(n^2) time complexity 

        Alternative/Optimal solution: hash set
        store each value in a hash set and if the hash set already contains a value, return true
        otherwise, return false
        
        Time complexity: O(n)
        Space complexity: O(n)
        */

        if (nums.length == 0){
            return false;
        }
        HashSet<Integer> uniq = new HashSet<Integer>();

        for (int i: nums){
            if (!uniq.add(i)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(containsDuplicate(nums)); // Output: true

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(containsDuplicate(nums2)); // Output: false

        int[] nums3 = {1, 2, 3, 1, 2, 3};
        System.out.println(containsDuplicate(nums3)); // Output: true
    }
}
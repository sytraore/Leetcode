package Java.Arrays.HashMapsAndHashSets;
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
            for (int j = i + 1; j < size; j ++){
                if (nums[i] == nums[j]){
                    return true;
                }
            }
        }

        return false; 
        
        Issue with this method: O(n^2) time complexity exceeds the time limit set by the testers when nums is a huge array.

        Alternative/Optimal solution: hash set

        store each value in a hash set and if the hash set already contains a value, return true
        otherwise, return false
        Time complexity: O(n)
        Space complexity: O(n)
        */

        HashSet<Integer> set = new HashSet<>();
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
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
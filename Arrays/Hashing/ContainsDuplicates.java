package Arrays.Hashing;
import java.util.HashSet;

public class ContainsDuplicates{
    public static boolean containsDuplicate(int[] nums) {
        /**
            brute force
            loop through the array and for each element, search for the same element in the rest of the array
            if the element is found, return true
            if the loop ends without finding a duplicate, return false
            Appropriate data structure: array
            Appropriate algorithm: Nested loops
            Time complexity: O(n^2)
            Space complexity: O(1)

        int len = nums.length;

        if (len == 1){
            return false;
        }
        
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if (nums[i] == nums[j]){
                    return true;
                }
            }
        }

        return false;

        */

        /*
        Issue with the above method: does not do well with large arrays due to O(n^2) time complexity 

        Alternative/Optimal solution: hash set
        store each value in a hash set and if the hash set already contains a value, return true
        otherwise, return false
        
        Time complexity: O(n)
        Space complexity: O(n)
        */

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

        int[] nums4 = {1};
        System.out.println(containsDuplicate(nums4)); // Output: false
    }
}
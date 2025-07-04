package Arrays.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {

        /*
            Optimal solution:
                The sum of two numbers must cancel the third number for the sum of the three numbers be equal to 0.
                Sort the array
                for each element in the array, search for the two elements that will result in 0 if they get added to the current element.

                Appropriate data structure: array
                Appropriate algorithm: Two pointers
         */
        
        /*
            Brute force:
                Sort the array to easily find the elements
                for each element in the array, search for 2 other elements that sum up to 0 when added to the current element
                use a hashSet to add the triplet of elements to avoid having duplicates in the solution

                Appropriate data structure: array & hashSet
                Appropriate algorithm: Nested loops
                Time complexity: O(n^3)
                Space complexity: O(n)
         */
         
        HashSet<List<Integer>> set = new HashSet<>();
        int numsLen = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < numsLen; i++){
            for (int j = i + 1; j < numsLen; j++){
                for (int k = j + 1; k < numsLen; k++){
                    if (nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> lst = new ArrayList<>();
                        lst.add(nums[i]);
                        lst.add(nums[j]);
                        lst.add(nums[k]);
                        set.add(lst);
                    }
                }
            }
        }
        
        return new ArrayList<>(set);
    }

    public static void main(String[] args){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result); // Output: [[-1, -1, 2], [-1, 0, 1]]
    }
}

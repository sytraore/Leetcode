package Arrays.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        /*
            Brute force:
                Sort the array to easily find the elements
                for each element in the array, search for 2 other elements that sum up to 0 when added to the current element
                use a hashSet to add the triplet of elements to avoid having duplicates in the solution

                Appropriate data structure: array & hashSet
                Appropriate algorithm: Nested loops
                Time complexity: O(n^3)
                Space complexity: O(n)

            
            Implementation:

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
         */

        /*
            Optimal solution:
                Sort the array
                for each element in the array, search for the two elements that will result in 0 if they get added to the current element.
                The sum of the two elements must be equal to the opposite value of the current element for the sum of the three numbers be equal to 0. (if currElmnt = -5, the two other elements must sum up to 5)

                Appropriate data structure: array
                Appropriate algorithm: Two pointers
                Time complexity: O(n^2)
                Space complexity: O(1)
         */
        
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int numsLen = nums.length;

        for (int i = 0; i < numsLen; i++){
            // the current elemnent must be negative or 0 to avoid duplicates
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            // find the two other element that sum up to 0 when added to nums[i]
            int left = i + 1, right = numsLen - 1;
            while (left < right){
                int theSum = nums[i] + nums[left] + nums[right];

                if (theSum < 0) {
                    left++;
                }

                else if (theSum > 0){
                    right --;
                }

                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    
                    // look for the next triplet
                    left++;
                    right--;

                    // we need to skip the duplicates
                    // we need to make sure that the left pointer is still less than the right pointer
                    // and that the element of the left pointer is not a duplicate of the previous element of the left pointer
                    // if it is, we increment the left pointer until we find a different element
                    while (left < right && nums[left] == nums[left-1]){
                        left++;
                    }
                }
            }
        }

        return result;
         
        
    }

    public static void main(String[] args){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result); // Output: [[-1, -1, 2], [-1, 0, 1]]
    }
}

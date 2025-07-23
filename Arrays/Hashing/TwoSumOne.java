package Arrays.Hashing;

import java.util.HashMap;

public class TwoSumOne {
    public int[] twoSum(int[] nums, int target) {

        /* 
         * Brute force solution: for each element in the array, we will look for another element such that their sum is equal to target.
         * This can be done using two nested loops.
            * Data structure: Array
            * Time complexity: O(n^2)
            * Space complexity: O(1)
            
            int len = nums.length;
            int[] result = new int[2];

            for (int i = 0; i < len; i++){
                for (int j = i+1; j < len; j++){
                    if (nums[i] + nums[j] == target){
                        result[0] = i;
                        result[1] = j;
                        break;
                    }
                }
            }
            return result;
         
        */

        /*
            nums[i] + x = target => x = target - nums[i]
            for each element in the array, we will look for x in the array
            => since we only want the indices of the nums[i] and x, we can use a hashmap to store value:index pairs
            we will compute target - nums[i] at each iteration and see if x is in the hashmap
            if x is in the hashmap, we have found our two sum
            if x is not in the hashmap, we will add nums[i] in the hashmap.

            Data structure: HashMap
            Time complexity: O(n)
            Space complexity: O(n)
        */
        int len = nums.length;
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++){
            
            int diff = target - nums[i];
            if (map.containsKey(diff)){
                result[0] = map.get(diff);
                result[1] = i;
            }
            else{
                map.put(nums[i],i);
            }
        }
        return result;
    }
}
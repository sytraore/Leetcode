package Arrays.Hashing;

import java.util.HashMap;
/*
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * 
 */
public class TwoSumOne {
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store the numbers and their indices
        // The key is the number and the value is its index
        // The time complexity is O(n) and the space complexity is O(n)
        // The HashMap allows us to check if the complement of the current number exists in O(1) time
        // The complement is the difference between the target and the current number
        // If the complement exists in the HashMap, we have found the two numbers that add up to the target
        // We return the indices of the two numbers
        HashMap<Integer, Integer> map = new HashMap<>();
        int theDiff = 0;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++){
            theDiff = target - nums[i];
            if (map.containsKey(theDiff)){
                result[0] = i;
                result[1] = map.get(theDiff);
                return result;
            }

            map.put(nums[i], i);
        }
        return result;
    }
}
package Arrays.Hashing;

public class MajorityElement {
    public static int majorityElement(int[] nums) {

        /**
         * Brute force solution:
         * use a hashmap to store the number of occurrences of each element
         * at each iteration, we will check if the number of occurrences
         * of the current element is greater than the current maximum count
         * if it is, we will update the maximum count and the result
         * Time complexity: O(n)
         * Space complexity: O(n)
            HashMap<Integer, Integer> map = new HashMap<>();
            int count = 0;
            int res = 0;

            for (int num: nums){
                map.put(num, map.getOrDefault(num, 0) + 1);

                if (map.get(num) > count){
                    res = num;
                    count = map.get(num);
                }
            }

            return res;
        */

        /**
            Optimal solution: Boyer's Voting Algorithm
            we will use a counter to keep track of the number of occurrences
            of the current element. We will also keep track of the current element.
            Every time we encounter an identical element, we will increment the counter
            if we encounter a different element, we will decrement the counter.
            if the counter reaches 0, we will update the current element to the new element

            Time complexity: O(n)
            Space complexity: O(1)
         */

        
        int count = 0, res = 0; // res is the value of the majority element

        for (int i = 0; i < nums.length; i++){
            // Initialize the result and count for the first element
            // This ensures that we start with a valid candidate
            if (i == 0) {
                res = nums[i];
                count++;
                continue;
            }

            // If the current element is the same as the res, increment count
            else if (res == nums[i]){
                count++;
            }

            // If the current element is different, decrement count
            else {
                // If count is zero, it means we need to update res
                // update res to the current element
                // and reset count to 1
                // This is the key part of Boyer's Voting Algorithm
                // It ensures that we always have a candidate for majority element
                if (count == 0){
                    count++;
                    res = nums[i];
                }
                // If count is greater than zero, we decrement it
                // This means we have seen more of the current candidate
                // than the new element, so we keep it as a candidate
                else if (count > 0){
                    count--;
                }
            }
        }

        return res;    
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        System.out.println(majorityElement(nums)); // Output: 3

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums)); // Output: 2

        nums = new int[]{1};
        System.out.println(majorityElement(nums)); // Output: 1

        nums = new int[]{5, 5, 5, 2, 2};
        System.out.println(majorityElement(nums)); // Output: 5
    }
}

package Arrays.Hashing;

import java.util.HashSet;

public class LongestSequence {
    // optimal solution
    public static int longestConsecutive(int[] nums) {
        /*
            Algorithm:
                how will my algorithm know the start of a sequence?
                    When a number does not have its preceding number in the array, this is the beginning of a sequence
                    example => 100 is in the array. if 99 is not in the array, then 100 is the beginning of a sequence

                how can my algorithm tracks the length of the longest sequence?
                    one variable will be used to count the lenhth of the sequence
                    another variable will be used to store the length of the longest sequence
                    by comparing its current value to the value of the first variable mentioned above
            
            Appropriate Data structure: Hashset because duplicates will not be counted
            => example: [1,0,1,2] longest consecutive sequence is of length 3 => 0, 1, 2. 
            The second 1 in the array did not count

            Time complexity: O(n)
            Space complexity: O(n)
         */

        HashSet<Integer> set = new HashSet<>();
        int result = 0;
        // store elements in nums into the set
        for (int num: nums){
            set.add(num);
        }

        for (int num: set){
            // variable to measure the length of the sequence
            int length = 0;
            // if the number that precede num is not in the set, then num is the start of a sequence
            // example => when num = 100, 99 is not in the set, hence 100 is the start of a sequence
            // but when num = 4, 3 is in the set, hence 4 is not the start of a sequence
            if (!set.contains(num - 1)){
                length++;
                // variable that holds the value of the number after num
                int nextVal = num + 1;
                
                // keep counting the number of values that follow num
                // example => when num is 100, if 101 is in the set, keep iterating until a number is not in the set
                // at that step, we will have an accurate length of the sequence
                while(set.contains(nextVal)){
                    length++;
                    nextVal++;
                }
            }

            // keep track of the longest sequence length
            if (length > result){
                result = length;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums)); // Output: 4
    }
}

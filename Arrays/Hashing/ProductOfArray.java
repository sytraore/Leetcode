package Arrays.Hashing;

public class ProductOfArray {
    public static int[] productExceptSelf(int[] nums) {
        /*
            My brute force solution:
            Loop through the array from left to right and for each element, 
            loop through the array again but from right to left 
            and calculate the product of all elements except the current one.

            Store the value in a new array at the same index as the current element.

            Time complexity: O(n^2) - because of the nested loop
            Space complexity: O(n) - because of the hashmap
            This solution is not optimal, but it works for small arrays
         */

        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            int j = nums.length -1;
            int val = 1;
            while (j >= 0){
                if (j == i) {
                    j--;
                    continue;
                }
                else {
                    val *= nums[j];
                }

                j--;
            }

            result[i] = val;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);

        // Print the result
        System.out.print("Product of array except self: ");
        for (int num : result) {
            System.out.print(num + " "); // Output: 24 12 8 6
        }
    }
}

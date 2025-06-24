package Arrays.Hashing;

public class ProductOfArrayOptimal {
    public static int[] productExceptSelfTimeOptimal(int[] nums) {
        /*
            Use a Prefix/Postfix approach
            First, traverse array from left to right and compute the product of each element
            and store the result in an array
            The prefix array must have 1 as the first element because there is no element before the first element
            Then, do the same thing from right to left
            The last element of the postfix array must have 1 as there is no element after the last element
            last, multiply the element of each array and store the result in the final array

            Time complexity: O(n)
            Space complexity: O(n)
         */

        int len = nums.length;
        int[] prefix = new int[len];
        int[] postfix = new int[len];
        int[] result = new int[len];

        prefix[0] = 1;
        postfix[len-1] = 1;

        // get a prefix array
        // example: [2, 3, 4, 5] => [1, 2, 6, 24]
        for (int i = 1; i < len; i++){
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        // get a postfix array
        // example: [2, 3, 4, 5] => [60, 20, 5, 1]
        for (int i = len - 2; i >= 0; i--){
            postfix[i] = nums[i + 1] * postfix[i + 1];
        }

        // get final result
        // example: [2, 3, 4, 5] => [60, 40, 30, 24]
        for (int i = 0; i < len; i++){
            result[i] = prefix[i] * postfix[i];
        }

        return result;
        
    }

    public static int[] productExceptSelfTimeSpaceOptimal(int[] nums) {
        /*
            Use a single array to store the result
            First, traverse array from left to right and compute the product of each element
            and store the result in the same array
            
            But this time, we will not use a separate postfix array
            Instead, we will use a variable to store the postfix product
            The first element of the result array will be 1 because there is no element before the first element
            Then, traverse the array from right to left and multiply the current element with the postfix product
            The last element of the result array will be 1 because there is no element after the last element
            Finally, return the result array

            Time complexity: O(n)
            Space complexity: O(1) - since we are using the same array
         */

        int len = nums.length;
        int[] result = new int[len];

        result[0] = 1;

        // get a prefix product
        for (int i = 1; i < len; i++){
            result[i] = nums[i - 1] * result[i - 1];
        }

        int postfix = 1;

        // get final result by multiplying prefix and postfix
        for (int i = len - 1; i >= 0; i--){
            result[i] *= postfix;
            postfix *= nums[i];
        }

        return result;
    }
    

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5};
        int[] result = productExceptSelfTimeOptimal(nums);
        // int[] result = productExceptSelfTimeSpaceOptimal(nums);

        // Print the result
        System.out.print("Product of array except self: ");
        for (int num : result) {
            System.out.print(num + " "); // Output: 60 40 30 24
        }
    }
}

package Arrays.TwoPointers;

public class TwoSum2 {
    public static int[] twoSum(int[] numbers, int target) {
        /*
            Array is already sorted in increasing order
            Two Pointers approach:
                => One pointer points to the first array element
                => The other points to the last array element

                if the sum at both pointers is > target
                    => move the second pointer 1 step toward the first pointer
                if the sum at both pointers < target
                    => move the first pointer 1 step toward the second pointer
                else => return both pointers values + 1 each (because the question asks for 1-based index)

            Time Complexity: O(n)
            Space Complexity: O(1)
         */

        int left = 0, right = numbers.length - 1;
        int[] result = new int[2];
        while (left < right){
            if (numbers[left] + numbers[right] > target){
                right--;
                continue;
            }

            if (numbers[left] + numbers[right] < target) {
                left++;
                continue;
            }

            if (numbers[left] + numbers[right] == target){
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(numbers, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]); // Output: Indices: 1, 2
    }
}

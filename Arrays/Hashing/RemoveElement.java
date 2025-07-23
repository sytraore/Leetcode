package Arrays.Hashing;

public class RemoveElement {
    public static int removeElement(int[] nums, int val) {
        /*
            The idea is to use two pointers:
            - one pointer will iterate through the array
            - the other pointer will keep track of the position where we can place the next non-val element
            
            Time complexity: O(n)
            Space complexity: O(1)
        */

        int k = 0; // pointer for the position of the next non-val element

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k; // return the new length of the array
    }
}

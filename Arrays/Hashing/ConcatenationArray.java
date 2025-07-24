package Arrays.Hashing;

public class ConcatenationArray {
    public static int[] getConcatenation(int[] nums) {
        /**
            use a pointer to track the end of nums
            if the pointer is equal to nums.length -1, reset it to 0

            Data structures: array
            Time Complexity: O(n)
            Space complexity: O(n)
         */

        int len = nums.length * 2;

        if (len == 0){
            return new int[0];
        }

        int[] ans = new int[len];
        int ptr = 0;

        for (int i = 0; i < len; i++){
            if (i == nums.length){
                ptr = 0;
            }
            ans[i] = nums[ptr];
            ptr++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] result = getConcatenation(nums);
        System.out.println(java.util.Arrays.toString(result)); // Output: [1, 2, 3, 1, 2, 3]
        System.out.println("68477744a0522036e112db80".equals("68477744a0522036e112db80"));
    }
}

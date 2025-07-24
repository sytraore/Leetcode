package Arrays.SlidingWindow;
import java.util.HashSet;
import java.util.Set;

public class ContainDuplicates2{

    /**
     * Brute force: for each element, check if there is a duplicate
     * when duplicates are found, check if the difference between their indices is less than or equal to k
     * This approach has a time complexity of O(n^2) and space complexity of O(1)
     * * This is not efficient for large arrays, so we can use a sliding window technique
     * 
     * for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] == nums[j]){
                    if (Math.abs(i - j) <= k){
                        return true;
                    }
                }
            }
        }

        return false;
     * 
     */

     /*
      * HashMap solution:
      Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        
        return false; 

    */


    /* hashset are useful for checking duplicates
        hashmap are useful for counting duplicates or tracking the frequency of duplicates
        use a hashset to check if the current element is already in the set
        if it is, we found a duplicate
        if it is not, we add it to the set
        if the size of the set exceeds k, we remove the oldest element from the set
        this is the sliding window technique
        time complexity => O(n)
        space complexity => O(k)
    */
    public static boolean containDuplicates(int [] nums, int k){
        // Use a HashSet to maintain the sliding window of size k
        Set<Integer> window = new HashSet<>();
        int L = 0;

        for (int R = 0; R < nums.length; R++) {
            if (R - L > k) {
                window.remove(nums[L]);
                L++;
            }
            if (window.contains(nums[R])) {
                return true;
            }
            window.add(nums[R]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println(containDuplicates(nums1, k1)); // true

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println(containDuplicates(nums2, k2)); // true

        int[] nums3 = {1, 2, 3, 4};
        int k3 = 2;
        System.out.println(containDuplicates(nums3, k3)); // false
    }
}
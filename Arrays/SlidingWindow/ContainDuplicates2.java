package Arrays.SlidingWindow;
import java.util.HashSet;
import java.util.Set;

public class ContainDuplicates2{
    // hashset are useful for checking duplicates
    // hashmap are useful for counting duplicates or tracking the frequency of duplicates
    // use a hashset to check if the current element is already in the set
    // if it is, we found a duplicate
    // if it is not, we add it to the set
    // if the size of the set exceeds k, we remove the oldest element from the set
    // this is the sliding window technique
    // time complexity => O(n)
    // space complexity => O(k)
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
}
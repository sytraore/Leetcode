package Java.Arrays.SlidingWindow;
import java.util.HashSet;

public class ContainDuplicates2{

    public static boolean containDuplicates(int [] nums, int k){
        // Use a HashSet to maintain the sliding window of size k
        HashSet<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // If the current element is already in the window, we found a duplicate
            if (window.contains(nums[i])) {
                return true;
            }

            // Add the current element to the window
            window.add(nums[i]);

            // If the window size exceeds k, remove the oldest element
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }

        return false;
    }
}
package Java.Arrays.SlidingWindow;

import java.util.HashMap;

public class ContainDuplicates2{

    public static boolean containDuplicates(int [] nums, int k){
        /*
            check if the array contains duplicates within k distance
            use a hashmap to store the index of each element
            iterate through the array and check if the element is already in the hashmap
            if it is, check if the difference between the current index and the index in the hashmap is <= k
            if it is, return true
            if it is not, update the index in the hashmap
            if it is not in the hashmap, add it to the hashmap
        */

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(nums[i])){
                if (i - map.get(nums[i]) <= k){
                    return true;
                }
            }
            map.put(nums[i], i);
        }

        return false;
    }
}
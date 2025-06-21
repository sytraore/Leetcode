package Arrays.Hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElement {
    public static int[] topKFrequent(int[] nums, int k) {
        /*
            Use a hash map to get each number frequncy. Compare each pair of key-value of the map
            Store k keys of the resulting comparison in an array
            Return the array
         */

        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();

        // fill the map with element:frequency
        for (int num: nums){
            if (map.containsKey(num)){
                int val = map.get(num);
                map.put(num, val + 1);
            }
            else{
                map.put(num, 1);
            }
        }

        // Compare key-value in the map
        // first, store each pair of key-value in a list
        List<Integer[]> keyValArray = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            Integer[] pair = {entry.getValue(), entry.getKey()};
            keyValArray.add(pair);
        }

        // compare key-value pairs => sort them in descending order
        // array1 represent the first array (which is the first pair) in the list of arrays
        // array2 represent the second pair of key-value in the list of arrays
        // array2[0] - array1[0] means that if the first element of array2 is > the first element of array1, move array2 in front of array1 (hence the list of arrays will be sorted from greatest to smallest)
        keyValArray.sort((array1, array2) -> array2[0] - array1[0]);

        // store the k elements that have the highest frequencies
        for (int i = 0; i < k; i++){
            result[i] = keyValArray.get(i)[1];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);
        
        // Print the result
        System.out.print("Top 2 frequent elements: ");
        for (int num : result) {
            System.out.print(num + " "); // Output: 1 2
        }
    }
}
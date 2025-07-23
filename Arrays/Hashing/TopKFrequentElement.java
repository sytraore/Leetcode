package Arrays.Hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElement {
    public static int[] topKFrequent(int[] nums, int k) {
        /**
        We need to track 2 things in order to get the top k frequent values.
            => we need to know the value and its number of occurences in the array
            => once that is known, we can sort the frequecies by decreasing order and return the top k values that match the frequencies.
            => we must use a hashmap in this case because we need to be able to get the values based on the frequencies at the end
            => the key will be the element in the array and the value will be the frequency
        
        Data structure: hashmap
        Time complexity: O(nlogn)
        Space complexity: O(n)
        */ 

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];

        // fill the map
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        // sort the map by frequencies in decreasing order
        // first, store all the pairs value-frequency of the hashmap into a list
        // in th hash map, the pairs are in an array of length 2
        //  => our list will be therefore a list of arrays, with each array being of length 2
        List<Integer[]> lst = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            Integer[] pair = {entry.getValue(), entry.getKey()};
            lst.add(pair);
        }

        // sort the list
        // this effectively sort the list in decreasing order based on the frequency
        // which is the first element in each array stored in lst
        lst.sort((array1, array2)-> array2[0] - array1[0]);

        // get k top elements
        for (int i = 0; i < k; i++){
            // lst.get(i)[1] => for the array at position i in lst, get the second value which is the key into the resulting array
            result[i] = lst.get(i)[1];
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
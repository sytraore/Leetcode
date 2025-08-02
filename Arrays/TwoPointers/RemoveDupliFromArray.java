package Arrays.TwoPointers;

public class RemoveDupliFromArray {
    public static int removeDuplicates(int[] nums) {
        /**
            use a hashmap to store pairs of value-index
            if the map does not contain the value, we will store the index
            otherwise, we won't
            the index is a variable that we will increment as we add values to the map
            after storing all the pairs, we will update the values of the array
            based on the hashmap pairs

            Time complexity: O(n)
            Space complexity: O(n)
        

        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;

        for(int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], index);
                index++;
            }
        }

        for (Integer key: map.keySet()){
            int theIndex = map.get(key);
            nums[theIndex] = key;
        }

        return index;

        */

        /*
            Optimal solution: 
            use two pointers, one pointer will look for the first element that has a 
            value different from the value of the other pointer.
            as long as the element at the second pointer has the same value as the 
            value of the element at the first pointer, we will continue moving
            the second pointer one step to the right
            once a different value is found, we will change the value of the element
            next to the elememt of the first pointer
            since the elements between the 2 pointers will be duplicates, the next element
            of the first pointer element is either a duplicate or a different value
            either case, it will be changed and the order will still be maintained

            Time complexity: O(n)
            Space complexity: O(1)
        */

        int l = 0, r = 1;

        while (r < nums.length){
            if (nums[l] != nums[r] && l < nums.length){
                nums[l + 1] = nums[r];
                l++;
            }
            r++;
        }

        return l + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 4, 4};
        int newLength = removeDuplicates(nums);
        System.out.println("New length: " + newLength); // Output: 4
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " "); // Output: 1 2 3 4
        }
    }
}

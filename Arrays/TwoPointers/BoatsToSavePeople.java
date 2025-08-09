package Arrays.TwoPointers;

import java.util.Arrays;

public class BoatsToSavePeople {
    public static int numRescueBoats(int[] people, int limit) {
        /*
            we will track the weights for each boat by going through each person weight
            we can use a two pointer algorithm to track two people weights since only
            2 people are allowed on a boat.
            we will first sort people weights in ascending order
            we will place two pointers at each limit of the array
            we will check if the sum of the weights at the 2 limit of the array
            sum up to the given boat weight limit or is less than that the weight limit
            if they do => we found one more boat
            if they don't => the weight at the right pointer needs to be the only weight on the boat
                => we found one more boat

            Time complexity: O(n log n) where n is the number of people
            Space complexity: O(1) since we are not using any extra space
        */

        int res = 0, l = 0, r = people.length - 1;
        Arrays.sort(people);

        while (l < r){
            // weight limit has not been reached yet and we have 2 people already
            // so we need a boat
            if (people[l] + people[r] <= limit ) {   
                // increaming numb of boats
                res++;
                // go to next people
                r--;
                l++;
            }

            // the weight limit has been passed, the person at the right pointer needs to be alone
            // on the boat
            else if (people[l] + people[r] > limit){
                r--;    // go to next person
                res++;  // increament numb of boats

            }
        }

        // one person is left
        // add him to the last boat
        if (l == r){
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] people = {1, 2, 3, 4, 5};
        int limit = 5;
        System.out.println(numRescueBoats(people, limit)); // Output: 3
    }
}

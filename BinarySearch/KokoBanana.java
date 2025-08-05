package BinarySearch;

public class KokoBanana {
    public static int minEatingSpeed(int[] piles, int h) {
        /**
            problem explanation: at each hour, koko chooses a pile and eats k bananas
                                => meaning that she can spend more than 1 hour to finish a pile
                                   if the number k of bananas she chose to ate is too small
                                   the only scenario where she eats all bananas in 1 hour
                                   is when she chose to eat k bananas where k is > the the number
                                   of bananas in the pile
            example explanation: piles = [3,6,7,11], h = 8
                                => if koko chose to eat 1 banana per pile:
                                    => at h = 1, she will eat 1 banana from the first pile (piles[0])
                                       at the end of the hour, the pile will contain 2 bananas (3 - 1)
                                    => at h = 2, she will eat 1 banana from the same pile
                                       at the end of the hour, the pile will contain 1 banana (2 - 1)
                                    => at h = 3, she will eat 1 banana from the same pile again
                                       at the end of the hour, no bananas remain
                                            => meaning at the next hour, she will start eating 1 banana
                                               in the next pile (piles[1])
                                    so to finish the first pile by eating 1 banana per hour, koko took 3 hours
                                    the same process repeats for the next pile
                                    we can calculate how long it will take her to finish the next pile which has
                                    6 bananas if she wants to eat 1 banana per hour
                                        => number of bananas in pile / number of bananas chosen to be eaten per hour
                                        => 6 / 1 = 6 => it will take her 6 hours to finish the pile if k is 1
                                    adding to the number of hours it took her to finish the previous pile, 
                                    3 + 6 = 9 => it will take her 9 hours to finish 2 piles
                                    9hours > h which is 8hours, so the choice of eating 1 banana per hour is not good
                                    if we incement k by 1 and repeat the process, we might find the right value
            
            Brute force:
            eventually, by finding the biggest pile, we can assume that koko wont be able to eat more bananas per
            hour than that number. so her choice k is between 1 and that biggest pile of bananas.
            we can calculate the number of hours it will take to finish each pile for a certain value of k
            and add up the hours. (k being between 1 and biggest pile number of bananas)
            if the sum of the hours is > h, we need to increment k because that mean the number of bananas eaten per
            hour is too small.
            if the sum is <= h, that mean, we found our smallest number of bananas to be eaten per hour (k)

            Time complexity: O(n * m) with m being the number of bananas of the biggest pile since we will be
                            iterating m times
            Space complexity: O(1)

            int res = 1, maxPile = piles[0];

            // find first the biggest pile
            for (int pile: piles){
                if (maxPile < pile){
                    maxPile = pile;
                }
            }

            for (int k = 1; k <= maxPile; k++){
                long numbHours = 0;
                for (int pile: piles){
                    numbHours += (int) Math.ceil((double) pile / k);
                }

                if (numbHours <= h){
                    return k;
                }
            }
            
            return res;
        */

        

        /**
            Optimal solution: binary search
            The core algorithm stays the same. But this time, instead of increamenting k at eat each iteration
            to find the smallest number, we can apply a classic binary search algorithm to find that number.

            Time complexity: O(n * log m)
            Space complexity: O(1)
        */

        int res = 1, maxPile = piles[0];

        // find first the biggest pile
        for (int pile: piles){
            if (maxPile < pile){
                maxPile = pile;
            }
        }

        int l = 1, r = maxPile;

        // binary search algorithm
        while (l <= r){
            int m = l + (r - l) / 2;

            long numbHours = 0;
            // compute the number of hours
            for (int pile: piles){
                numbHours += (int) Math.ceil((double) pile / m);
            }

            if (numbHours > h){
                l = m + 1;
            }

            // we found a value of k but we do not know if it is the minimum
            else if (numbHours <= h){
                res = m;
                r = m - 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(minEatingSpeed(piles, h)); // Output: 4
    }
}

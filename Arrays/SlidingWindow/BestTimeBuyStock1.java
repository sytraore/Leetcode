package Arrays.SlidingWindow;

public class BestTimeBuyStock1 {
    public static int maxProfit(int[] prices) {
        /**
         * We can use a sliding window/2pointers approach to solve this problem.
            l is the pointer that will point to the left side of the window
            it represents the minimum price we can buy the stock at
            r is the pointer that will point to the right side of the window
            it represents the maximum price we can sell the stock at

            when prices[r] < prices[l]:
                => move l to r because we have a new minimum price
                => restart the process
            when prices[l] < prices[r]:
                => calculate the profit and update if necessary
            move r one step to the right to keep the window moving forward
         */

        int p = 0;
        int l = 0, r = 1;

        while (r < prices.length){
            int diff = 0;

            if (prices[l] <= prices[r]){
                diff = prices[r] - prices[l];
                p = Math.max(diff, p);
            }
            else {
                l = r;
            }
            r++;
        }
        return p;
    }

    public static void main(String[] args){
        int [] prices = {2, 2, 7, 6, 1};
        System.out.println(maxProfit(prices)); // 5
        int [] prices2 = {1, 4, 6, 4, 0, 9};
        System.out.println(maxProfit(prices2)); // 9
        int [] prices3 = {2, 2, 2, 2};
        System.out.println(maxProfit(prices3)); // 0
        int [] prices4 = {2};
        System.out.println(maxProfit(prices4)); // 0
        int [] prices5 = {};
        System.out.println(maxProfit(prices5)); // 0
    }

}


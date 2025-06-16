package Arrays.SlidingWindow;

public class BestTimeBuyStock1 {
    public static int maxProfit(int [] prices){
/**
            edge cases:
                prices = [] => 0
                prices = [2] => 0
                prices = [2,2,2,2] => 0
                prices = [1, 4, 6, 4, 0, 9] => 9
                prices = [2, 2, 7, 6, 1] => 5
            choose a day to buy
            choose a day to sell
            return the highest profit
            
            we must be analyzing contigeous elements in the array
            => this can be solved with a sliding window algorithm
            the window does not have a fixed size
            when we buy stocks, slide the window by first shrinking it and increment it
            => increment both pointers
            when we sell stocks, only expand the window
            we sell only if the stock price is > than the bought price
            => if value at pointer2 > value at pointer1
         */
        
         if (prices.length <= 1){
            return 0;
        }

        int pointer1 = 0;
        int maxProfit = 0;
        int profit = 0;
        for (int pointer2 = 0; pointer2 < prices.length; pointer2++){

            if (prices[pointer1] <= prices[pointer2]){
                profit = prices[pointer2] - prices[pointer1];
                if (maxProfit < profit){
                    maxProfit = profit;
                }
            }
            else{
                pointer1 = pointer2;
            }
        }

        return maxProfit;
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


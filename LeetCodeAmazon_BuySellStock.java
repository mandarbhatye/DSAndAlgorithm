package Amazon;

import java.util.Arrays;

public class LeetCodeAmazon_BuySellStock {

    //Buy and Sell Stock - I:
    // int[] Arr = {7, 1, 5, 3, 6, 4};//The maximum profit by selling the stock is 5
    static int maximumProfit(int[] Arr) {
        int maxProfit = 0;
        int min = Arr[0];

        for (int i = 1; i < Arr.length; i++) {
            int curProfit = Arr[i] - min;
            maxProfit = Math.max(maxProfit, curProfit);
            min = Math.min(min, Arr[i]);
        }
        return maxProfit;
    }

    //Buy and Sell Stock - II
    static int maximumProfitII(int[] Arr) {
        int[][] dp = new int[Arr.length][2];
        for (int[] tmp : dp)
            Arrays.fill(tmp, -1);

        return maximumProfitIIRecursion(Arr, 0, 0, dp);
    }

    static int maximumProfitIIRecursion(int[] Arr, int index, int buy, int[][] dp) {
        if (index == Arr.length) {
            return 0;
        }

        if (dp[index][buy] != -1) return dp[index][buy];

        int profit = 0;

        if (buy == 0) {
            profit = Math.max(0 + maximumProfitIIRecursion(Arr, index + 1, 0, dp), -Arr[index] + maximumProfitIIRecursion(Arr, index + 1, 1, dp));
        }
        if (buy == 1) {
            profit = Math.max(0 + maximumProfitIIRecursion(Arr, index + 1, 1, dp), Arr[index] + maximumProfitIIRecursion(Arr, index + 1, 0, dp));
        }
        return dp[index][buy] = profit;
    }

    static int maximumProfitIIDP(int[] Arr) {
        int n = Arr.length;
        int dp[][] = new int[n + 1][2];

        int profit = 0;

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) {// We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                }
                if (buy == 1) {// We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }

    static long maximumProfitIIDPSpaceOptimized(int[] Arr) {
        int n = Arr.length;
        long[] prev = new long[2];
        long[] cur = new long[2];

        long profit = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                if (j == 0) {
                    profit = Math.max(0 + prev[0], -Arr[i] + prev[1]);
                }
                if (j == 1) {
                    profit = Math.max(0 + prev[1], Arr[i] + prev[0]);
                }
                cur[j] = profit;
            }
            prev = cur.clone();
        }
        return prev[0];
    }

    static long maximumProfitIII(int[] Arr, int tranCapacity) {
        long[][][] dp = new long[Arr.length][2][3];
        for (long[][] row : dp)
            for (long[] column : row)
                Arrays.fill(column, -1);

        return maximumProfitIIIRecursion(Arr, 0, 0, dp, tranCapacity);
    }

    //Buy/Sell Stock with Transaction capacity
    static long maximumProfitIIIRecursion(int[] Arr, int index, int buy, long[][][] dp, int tranCapacity) {
        if (index == Arr.length || tranCapacity == 0) {
            return 0;
        }
        if (dp[index][buy][tranCapacity] != -1) return dp[index][buy][tranCapacity];

        long profit = 0;
        if (buy == 0) {//Buy stock
            profit = Math.max(-Arr[index] + maximumProfitIIIRecursion(Arr, index + 1, 1, dp, tranCapacity), maximumProfitIIIRecursion(Arr, index + 1, 0, dp, tranCapacity));
        }
        if (buy == 1) {//Sell stock
            profit = Math.max(Arr[index] + maximumProfitIIIRecursion(Arr, index + 1, 0, dp, tranCapacity - 1), maximumProfitIIIRecursion(Arr, index + 1, 1, dp, tranCapacity));
        }

        return dp[index][buy][tranCapacity] = profit;
    }

    static long maximumProfitIIIDP(int[] Arr, int tranCapacity) {
        int n = Arr.length;
        long[][][] dp = new long[n + 1][2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= tranCapacity; cap++) {
                    if (buy == 0) {//Buy stock
                        dp[index][buy][cap] = Math.max(0 + dp[index + 1][0][cap],
                                -Arr[index] + dp[index + 1][1][cap]);
                    }
                    if (buy == 1) {//Sell stock
                        dp[index][buy][cap] = Math.max(0 + dp[index + 1][1][cap],
                                Arr[index] + dp[index + 1][0][cap - 1]);
                    }
                }
            }
        }
        return dp[0][0][2];
    }

    static int maximumProfitIIIDPSpaceOptimized(int[] Arr, int tranCapacity) {
        int n = Arr.length;
        int[][] prev = new int[2][3];
        int[][] cur = new int[2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= tranCapacity; cap++) {
                    if (buy == 0) {//Buy stock
                        cur[buy][cap] = Math.max(0 + prev[0][cap],
                                -Arr[index] + prev[1][cap]);
                    }
                    if (buy == 1) {//Sell stock
                        cur[buy][cap] = Math.max(0 + prev[1][cap],
                                Arr[index] + prev[0][cap - 1]);
                    }
                }
            }
            prev = cur.clone();
        }
        return prev[0][2];
    }

    static long maximumProfitIV(int[] Arr, int kTran) {
        long[][][] dp = new long[Arr.length][2][3];
        for (long[][] row : dp)
            for (long[] column : row)
                Arrays.fill(column, -1);

        return maximumProfitIVRecursion(Arr, 0, 0, dp, kTran);
    }

    //Buy/Sell Stock with K Transaction capacity
   static long maximumProfitIVRecursion(int[] Arr, int index, int buy, long[][][] dp, int kTran){
        if(index == Arr.length || kTran == 0){
            return 0;
        }

        if(dp[index][buy][kTran] != -1) return dp[index][buy][kTran];

        long profit =0;

        if(buy ==0){//Can Buy
            profit = Math.max(0+maximumProfitIVRecursion(Arr,index+1,0,dp,kTran),
                    -Arr[index] + maximumProfitIVRecursion(Arr,index+1,1,dp,kTran));
        }
        if(buy==1){//Can Sell
            profit = Math.max(0+maximumProfitIVRecursion(Arr,index+1,1,dp,kTran),
                    Arr[index] + maximumProfitIVRecursion(Arr,index+1,0,dp,kTran-1));
        }
        return dp[index][buy][kTran] = profit;
    }

    static long maximumProfitIVDP(int[] Arr, int kTran){
        int n = Arr.length;
        int[][][] dp = new int[n+1][2][kTran+1];

        for (int index = n-1; index >=0 ; index--) {
            for (int buy = 0; buy <=1 ; buy++) {
                for (int cap = 1; cap <=kTran ; cap++) {
                    if(buy ==0) {
                        dp[index][buy][cap] = Math.max(0+ dp[index+1][0][cap], -Arr[index] + dp[index+1][1][cap]);
                    }
                    if(buy ==1){
                        dp[index][buy][cap] = Math.max(0+ dp[index+1][1][cap], Arr[index] + dp[index+1][0][cap-1]);
                    }
                }
            }
        }
        return dp[0][0][2];
    }

    static long maximumProfitIVDPOptimal(int[] Arr, int kTran){
        int n = Arr.length;
        int[][] prev = new int[2][kTran+1];
        int[][] cur = new int[2][kTran+1];

        for (int index = n-1; index >=0 ; index--) {
            for (int buy = 0; buy <=1 ; buy++) {
                for (int cap = 1; cap <=kTran ; cap++) {
                    if(buy ==0) {
                        cur[buy][cap] = Math.max(0+ prev[0][cap], -Arr[index] + prev[1][cap]);
                    }
                    if(buy ==1){
                        cur[buy][cap] = Math.max(0+ prev[1][cap], Arr[index] + prev[0][cap-1]);
                    }
                }
            }
            prev = cur.clone();
        }
        return prev[0][2];
    }

    static long maximumProfitV(int[] Arr) {
        long[][] dp = new long[Arr.length][2];
        for (long[] row : dp)
                Arrays.fill(row, -1);

        return maximumProfitVRecursion(Arr, 0, 0, dp);
    }

    //Buy/Sell Stock with CoolDown( a day gap for Buu after previous sell) capacity
    static long maximumProfitVRecursion(int[] Arr, int index, int buy, long[][] dp){
        if(index == Arr.length || index > Arr.length) return 0;

        if(dp[index][buy] !=-1) return dp[index][buy];

        long profit =0;

        if(buy ==0){//can Buy
            profit = Math.max(0 +maximumProfitVRecursion(Arr,index+1,0,dp),
                    -Arr[index] + maximumProfitVRecursion(Arr,index+1,1,dp));
        }

        if(buy ==1){//can sell
            profit = Math.max(0+ maximumProfitVRecursion(Arr,index+1,1,dp),
                    Arr[index] + maximumProfitVRecursion(Arr,index +2,0,dp));
        }

        return dp[index][buy] = profit;
    }

    static long maximumProfitVDP(int[] Arr){
        int n = Arr.length;
        int[][] dp = new int[n+2][2];

        for (int index = n-1; index >=0; index--) {
            for (int buy = 0; buy <=1 ; buy++) {
                if(buy ==0){
                    dp[index][buy] = Math.max(0+ dp[index+1][0], -Arr[index] + dp[index+1][1]);
                }
                if(buy ==1){
                    dp[index][buy] = Math.max(0+ dp[index+1][1], Arr[index] + dp[index+2][0]);
                }
            }
        }
        return dp[0][0];
    }

    static long maximumProfitVI(int[] Arr,int fees) {
        long[][] dp = new long[Arr.length][2];
        for (long[] row : dp)
            Arrays.fill(row, -1);

        return maximumProfitVIRecursion(Arr, 0, 0,fees, dp);
    }

    //Buy/Sell Stock with Fees
    static long maximumProfitVIRecursion(int[] Arr, int index, int buy, int fees,long[][] dp){
        if(index == Arr.length || index > Arr.length) return 0;

        if(dp[index][buy] !=-1) return dp[index][buy];

        long profit =0;

        if(buy ==0){//can Buy
            profit = Math.max(0 +maximumProfitVIRecursion(Arr,index+1,0,fees,dp),
                    -Arr[index] + maximumProfitVIRecursion(Arr,index+1,1,fees,dp));
        }

        if(buy ==1){//can sell
            profit = Math.max(0+ maximumProfitVIRecursion(Arr,index+1,1,fees,dp),
                    (Arr[index] - fees ) + maximumProfitVIRecursion(Arr,index +1,0,fees,dp));
        }
        return dp[index][buy] = profit;
    }

    static long maximumProfitVIDP(int[] Arr,int fees){
        int n = Arr.length;
        int[][] dp = new int[n+1][2];

        for (int index = n-1; index >=0; index--) {
            for (int buy = 0; buy <=1 ; buy++) {
                if(buy ==0){
                    dp[index][buy] = Math.max(0+ dp[index+1][0], -Arr[index] + dp[index+1][1]);
                }
                if(buy ==1){
                    dp[index][buy] = Math.max(0+ dp[index+1][1], Arr[index] - fees + dp[index+1][0]);
                }
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        System.out.println("========================  Buy and Sell Stocks I/ 1 Transaction ===========================");
        int[] Arr = {7, 1, 5, 3, 6, 4};//The maximum profit by selling the stock is 5
        System.out.println("The maximum profit by selling the stock is " + maximumProfit(Arr));

        System.out.println("====================  Buy and Sell Stocks II/ Infinite Transaction ========================");
        Arr = new int[]{5,2,6,1,4,7,3,6}; //The maximum profit that can be generated is 7
        System.out.println("The maximum profit by selling the stock Recursion is " + maximumProfitII(Arr));

       // Arr = new int[]{17,20,11,9,12,6}; //The maximum profit that can be generated is 7
        System.out.println("The maximum profit by selling the stock with DP is: " + maximumProfitIIDP(Arr));

        System.out.println("The maximum profit by selling the stock with DP - Space Optimized is: " + maximumProfitIIDPSpaceOptimized(Arr));

        System.out.println("======================  Buy and Sell Stocks III/ 2 Transaction =========================");

        Arr = new int[]{5,11,3,50,60,90};//The maximum profit that can be generated is 6
        int tranCapacity = 2;
        System.out.println("The maximum profit that can be generated by Recursion is " + maximumProfitIII(Arr, tranCapacity));

        System.out.println("The maximum profit that can be generated by DP is " + maximumProfitIIIDP(Arr, tranCapacity));

        System.out.println("The maximum profit that can be generated by DP- Space Optimization is " + maximumProfitIIIDPSpaceOptimized(Arr, tranCapacity));

        System.out.println("======================  Buy and Sell Stocks IV/ K Transaction =========================");

        System.out.println("The maximum profit that can be generated by Recursion is " + maximumProfitIV(Arr, tranCapacity));

        System.out.println("The maximum profit that can be generated by DP is " + maximumProfitIVDP(Arr, tranCapacity));

        System.out.println("The maximum profit that can be generated by DP - Optimal is " + maximumProfitIVDPOptimal(Arr, tranCapacity));

        System.out.println("================= Buy and Sell Stocks V/  Transaction with CoolDown ======================");
        int[] prices = {4,9,0,4,10}; //The maximum profit that can be generated is 11
        System.out.println("The maximum profit that can be generated is "+ maximumProfitV(prices));
        System.out.println("The maximum profit that can be generated is "+ maximumProfitVDP(prices));

        prices = new int[] {1,3,2,8,4,9};//The maximum profit that can be generated is 8
        int fee = 2;
        System.out.println("================= Buy and Sell Stocks VI/  Transaction with Fees =========================");

        System.out.println("The maximum profit that can be generated is "+  maximumProfitVI(prices,fee));
        System.out.println("The maximum profit that can be generated is "+  maximumProfitVIDP(prices,fee));

    }
}
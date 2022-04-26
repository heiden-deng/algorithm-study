package org.example.stack.s3;


public class MinCoins {
    /**
     *  动态规划计算由n种硬币组成的和为aim，且硬币数最少
     *  例如2元，3元，5元三种硬币，现在给定需要10元，则最佳答案为两个5元，
     * @param arr  硬币种类
     * @param aim  目标和
     * @return
     */
    public int calcMinCoins(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim <= 0){
            return 0;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1]; //dp[i][j]表示从arr[0..i]的硬币中能组成总和为j的最少硬币数
        for(int j = 1; j <= aim; j++){    //dp[0-n][0]硬币种类
            dp[0][j] = max;
            if(j - arr[0] > 0 && dp[0][j-arr[0]] != max){
                dp[0][j] = dp[0][j-arr[0]] + 1;
            }
        }
        int left = max;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= aim; j++){
                left = max;
                if(j - arr[i] > 0 && dp[i][j - arr[i]] != max){
                    left = dp[i][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(left,dp[i-1][j]);
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }
}

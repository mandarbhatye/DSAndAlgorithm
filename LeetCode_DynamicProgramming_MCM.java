
package Amazon;

import java.util.Arrays;

public class LeetCode_DynamicProgramming_MCM {

    public static int matrixMultiplication(int[] arr , int N) {
        int[][] dp = new int[N][N];
        for(int[] row: dp){
            Arrays.fill(row,-1);
        }
        return matrixMultiplicationRecursion(1, N-1,arr,dp);
    }
    public static int matrixMultiplicationRecursion(int i, int j,int[] arr,int[][] dp) {
        if(i == j) return 0;

        int minCost = Integer.MAX_VALUE;

        if(dp[i][j] !=-1) return dp[i][j];

        for(int k = i;k<j;k++){
            int steps = arr[i-1] * arr[k] * arr[j] + matrixMultiplicationRecursion(i,k,arr,dp) + matrixMultiplicationRecursion(k+1,j,arr,dp);
            minCost = Math.min(minCost,steps);
        }
        return dp[i][j] = minCost;
    }

    public static int matrixMultiplicationDP(int[] arr, int N) {
        int[][] dp = new int[N][N];

        for(int i = N-1;i>=1;i--){
            for(int j = i+1; j<N; j++){
                int minCost = Integer.MAX_VALUE;
                for(int k = i;k<j;k++){
                    int steps = arr[i-1] * arr[k] * arr[j] + dp[i][k] + dp[k+1][j];
                    if(steps < minCost)
                        minCost = steps;
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][N-1];
    }

    public static int minimumCostToCutTheStick(int n, int c, int cuts[]) {
        int[] newCut = new int[c+2];
        newCut[0] = 0;
        int idx = 1;
        for(int i=0;i<cuts.length;i++){
            newCut[idx++] = cuts[i];
        }
        newCut[idx] = n;
        Arrays.sort(newCut);

        int[][] dp = new int[c+1][c+1];

        for(int[] tmp: dp){
            Arrays.fill(tmp,-1);
        }
        return minimumCostToCutTheStickDP(1,c,newCut);
    }

    public static int minimumCostToCutTheStickDP(int n,int c, int cuts[]){
        int[][] dp = new int[c+2][c+2];

        for(int i = c; i>=1;i--){
            for(int j = 1; j<=c; j++){
                int minCost = Integer.MAX_VALUE;
                if(i>j) continue;
                for(int ind =i; ind<=j; ind++){
                    int cost = (cuts[j+1] - cuts[i-1]) + dp[i][ind-1] + dp[ind+1][j];
                    minCost = Math.min(minCost,cost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][c];
    }
    public static int minimumCostToCutTheStickRecursion(int i, int j, int cuts[],int[][] dp){
        if(i>j) return 0;

        int minCost = Integer.MAX_VALUE;

        if(dp[i][j] != -1) return dp[i][j];

        for(int ind =i; ind<=j; ind++){
            int cost = (cuts[j+1] - cuts[i-1]) + minimumCostToCutTheStickRecursion(i,ind-1,cuts,dp)
                    + minimumCostToCutTheStickRecursion(ind+1,j,cuts,dp);
            minCost = Math.min(minCost,cost);
        }
        return dp[i][j] = minCost;
    }

    public static int countSquares(int n, int m, int[][] arr) {
        int[][] dp = new int[n][m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = arr[i][j];
                } else if (i == n - 1) {
                    dp[i][j] = arr[i][j];
                } else if (j == m - 1) {
                    dp[i][j] = arr[i][j];
                } else {
                    if (arr[i][j] == 1) {
                        int min = Math.min(dp[i + 1][j], Math.min(dp[i][j + 1], dp[i + 1][j + 1]));
                        dp[i][j] = min + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
    }
    public static void main(String[] args) {

        int[] matrix = {41, 23, 42, 27, 35, 46, 43, 49, 34, 31, 23, 34, 20, 20, 32, 42, 40, 27, 35, 46};
        int out = matrixMultiplication(matrix,matrix.length); //Output : 454562
        System.out.println("Minimum cost of MCM is : " + out);

        System.out.println("Minimum cost of MCM with DP is : " + matrixMultiplicationDP(matrix, matrix.length));


        int[] cuts = {1,8,3,5}; //Output: 21
        int n = 9;
        int c = 4;

        int res = minimumCostToCutTheStick(n,c,cuts);

        System.out.println("Minimum cost to cut the stick is: " + res);

        int[][] mat = {{0,1,1,0},
                       {1,1,1,0},
                       {0,0,1,0}};
        System.out.println("Count of square matrix is: " + countSquares(mat.length, mat[0].length, mat)); // Output: 7

    }
}

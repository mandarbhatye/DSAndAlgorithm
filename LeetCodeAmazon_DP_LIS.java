package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCodeAmazon_DP_LIS {

    private static int longestIncreasingSubsequenceRecursion(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n+1];
        for(int row[]: dp)
            Arrays.fill(row,-1);
        return longestIncreasingSubsequenceRecursionHelper(0,-1,arr,dp);
    }

    private static int longestIncreasingSubsequenceRecursionHelper(int index,int prevIndex,int[] arr, int[][] dp){
        if(index == arr.length ){
            return 0;
        }
        if(dp[index][prevIndex+1] !=-1) return dp[index][prevIndex+1];

        int notPick = 0 + longestIncreasingSubsequenceRecursionHelper(index+1, prevIndex,arr,dp);

        int pickUp =0;
        if(prevIndex == -1 || arr[index] > arr[prevIndex]) {
            pickUp = 1 + longestIncreasingSubsequenceRecursionHelper(index + 1, index, arr, dp);
        }

        return dp[index][prevIndex+1] = Math.max(notPick,pickUp);
    }

    private static int longestIncreasingSubsequenceDP(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n+1][n+1];

        for (int index = n-1; index >=0 ; index--) {
            for (int prevIndex = index-1; prevIndex >=-1 ; prevIndex--) {
                int notPick = 0 + dp[index+1][prevIndex+1];

                int pickUp =0;
                if(prevIndex == -1 || arr[index] > arr[prevIndex]) {
                    pickUp = 1 + dp[index + 1][index+1];
                }

                dp[index][prevIndex+1] = Math.max(notPick,pickUp);
            }
        }

        return dp[0][0];
    }

    public static int longestIncreasingSubsequenceBinarySearch(List<Integer> arr) {
        int n=arr.size();
        int dp[]=new int[n];
        int len=0;

        for(int i=0;i<n;i++){
            int k=arr.get(i);
            int index=Arrays.binarySearch(dp,0,len,k);
            if(index<0){
                index=-(index+1);
            }
            dp[index]=k;
            if(index==len){
                len++;
            }
        }
        return len;
    }
    static int longestIncreasingSubsequenceTabulation(int arr[], int n){
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for (int index = 0; index < n ; index++) {
            for (int prevIndex = 0; prevIndex <= index-1 ; prevIndex++) {
                if(arr[prevIndex] < arr[index] && 1 + dp[prevIndex] > dp[index]){
                    dp[index] = 1+ dp[prevIndex];
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    static int printLongestIncreasingSubsequenceTabulation(int arr[], int n){
        int[] dp=new int[n];
        Arrays.fill(dp,1);
        int[] hash=new int[n];
        Arrays.fill(hash,1);

        for(int i=0; i< n; i++){
            hash[i] = i; // initializing with current index
            for(int prev_index = 0; prev_index <=i-1; prev_index ++){
                if(arr[prev_index]<arr[i] && 1 + dp[prev_index] > dp[i]){
                    dp[i] = 1 + dp[prev_index];
                    hash[i] = prev_index;
                }
            }
        }

        int ans = -1;
        int lastIndex =-1;

        for(int i=0; i< n; i++){
            if(dp[i]> ans){
                ans = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(arr[lastIndex]);

        while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
            lastIndex = hash[lastIndex];
            temp.add(arr[lastIndex]);
        }
        // reverse the array

        System.out.print("The subsequence elements are ");

        for(int i=temp.size()-1; i>=0; i--){
            System.out.print(temp.get(i)+" ");
        }
        System.out.println();

        return ans;
    }

    private static int lowerBound(int[] a, int low, int high, int element){
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (element > a[middle])
                low = middle + 1;
            else
                high = middle;
        }
        return low;
    }
    public static int longestIncreasingSubsequenceBinarySearch(int arr[]){
        int n = arr.length;
        // dp[i] represents i+1'th length LIS ending at minimum integer dp[i]
        int dp[] = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++){
			/*
			    Since dp array stores elements in the sorted order therefore
			    we can use binary search to find the correct position for
			    arr[i] to be placed.
			    And elements are present in the dp array from 0 to ans-1 position
			    So we will be doing the binary search in this range.
			*/
            int position = lowerBound(dp, 0, ans, arr[i]);
            dp[position] = arr[i];

            if (position == ans)
                ans++;
        }
        return ans;
    }

    public static ArrayList<Integer> divisibleSet(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] hash = new int[n];
        Arrays.fill(hash,1);

        Arrays.sort(arr);

        for(int index = 0; index< n; index++){
            hash[index] = index;
            for(int prevIndex =0; prevIndex <= index-1; prevIndex++){
                if(arr[index] % arr[prevIndex] == 0 && 1+ dp[prevIndex] > dp[index]){
                    dp[index] = 1 + dp[prevIndex];
                    hash[index] = prevIndex;
                }
            }
        }

        int ans =-1;
        int lastIndex = -1;

        for(int i=0; i< n;i++){
            if(dp[i] > ans){
                ans = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[lastIndex]);

        while(hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            temp.add(arr[lastIndex]);
        }
        return temp;
    }
    public static boolean compareString(String s1, String s2){
        if(s1. length() != s2.length()+1) return false;
        int first  = 0;
        int second = 0;
        while(first < s1.length()){
            if(second < s2.length() && s1.charAt(first) == s2.charAt(second)){
                first++;
                second++;
            }else{
                first++;
            }
        }
        return (first == s1.length() && second == s2.length());
    }
    public static int longestStrChain(int n, String[] arr) {
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int maxlen =1;

        Arrays.sort(arr, Comparator.comparingInt(String::length));

        for(int index = 0; index< n; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(compareString(arr[index], arr[prevIndex]) && 1+ dp[prevIndex] > dp[index]){
                    dp[index] = 1+ dp[prevIndex];
                }
            }
            if(dp[index] > maxlen){
                maxlen = dp[index];
            }
        }
        return maxlen;
    }

    public static void main(String[] args) {

        System.out.println("=================== Longest Increasing Subsequences | Recursion ==========================");

        int[] arr = {2,4,3,7,4,5};
        //int arr[] = {1,3,5,4,7}; //The length of the longest increasing subsequence is 4
        System.out.println("The length of the longest increasing subsequence With Recursion is : "+ longestIncreasingSubsequenceRecursion(arr));

        System.out.println("The length of the longest increasing subsequence with DP is : "+ longestIncreasingSubsequenceDP(arr));
        System.out.println("The length of the longest increasing subsequence with Binary Search is : "+ longestIncreasingSubsequenceBinarySearch(arr));
        System.out.println("The length of the longest increasing subsequence with Tabulation is: " + longestIncreasingSubsequenceTabulation(arr, arr.length));
        System.out.println("Printing longest increasing subsequence is: " + printLongestIncreasingSubsequenceTabulation(arr, arr.length));
    }
}

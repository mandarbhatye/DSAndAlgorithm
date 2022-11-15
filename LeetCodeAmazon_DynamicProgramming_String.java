package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LeetCodeAmazon_DynamicProgramming_String {

    private static int longestCommonSubsequenceRecursion(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int n = s1.length();
        int m = s2.length();

        for (int[] tmp : dp)
            Arrays.fill(tmp, -1);

        return longestCommonSubsequenceRecursionHelper(s1, s2, n, m, dp);
    }

    private static int longestCommonSubsequenceRecursionHelper(String s1, String s2, int i, int j, int[][] dp) {
        if (i == 0 || j == 0)
            return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            return dp[i][j] = 1 + longestCommonSubsequenceRecursionHelper(s1, s2, i - 1, j - 1, dp);

        return dp[i][j] = Math.max(longestCommonSubsequenceRecursionHelper(s1, s2, i - 1, j, dp), longestCommonSubsequenceRecursionHelper(s1, s2, i, j - 1, dp));
    }

    private static int longestCommonSubsequenceDp(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < dp.length ; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[n][m];
    }

    public static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b) {
        int n = a.size(), m = b.size();
        List<Integer> dp[][] = new ArrayList[n+1][m+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                dp[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                if(i == 0 || j == 0) dp[i][j].add(0);
                else{
                    int x = a.get(i-1), y = b.get(j-1);
                    if(x == y){
                        dp[i][j].addAll(dp[i-1][j-1]);
                        dp[i][j].add(a.get(i-1));
                    }
                    else{
                        if(dp[i-1][j].size() >= dp[i][j-1].size()){
                            dp[i][j].addAll(dp[i-1][j]);
                        }
                        else{
                            dp[i][j].addAll(dp[i][j-1]);
                        }
                    }
                }
            }
        }
        dp[n][m].remove(0);
        return dp[n][m];
    }

    public static List<String> printLongestCommonSubsequenceII(String a, String b) {
       int n= a.length();
       int m = b.length();
       List<String>[][] dp = new List[n+1][m+1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <=m ; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i <=n ; i++) {
            for (int j = 0; j <=m ; j++) {
                if(i==0 || j==0){
                    dp[i][j].add("");
                }else{
                    char c1 = a.charAt(i-1);
                    char c2 = b.charAt(j-1);

                    if(c1== c2){
                        dp[i][j].addAll(dp[i-1][j-1]);
                        dp[i][j].add(a.charAt(i-1)+"");
                    }else{
                        int size1 = dp[i][j-1].size();
                        int size2 = dp[i-1][j].size();
                        if(size1 > size2){
                            dp[i][j].addAll(dp[i][j-1]);
                        }else{
                            dp[i][j].addAll(dp[i-1][j]);
                        }
                    }
                }
            }
        }
        dp[n][m].remove(0);
        return dp[n][m];
    }
    static void printLongestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        int i = n;
        int j = m;

        Stack<Character> st = new Stack<>();
        String ans = "";
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                st.push(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        while (!st.isEmpty()){
            ans+= st.pop();
        }
        System.out.println(ans);
    }

    private static int longestCommonSubstringDp(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int max = 0;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    private static int longestCommonSubstringDpSpaceOptimized(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int prev[] = new int[m + 1];
        int cur[] = new int[m + 1];

        int max = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cur[j] = 1 + prev[j - 1];
                    max = Math.max(max, cur[j]);
                } else {
                    cur[j] = 0;
                }
            }
            prev = cur;
        }
        return max;
    }

    private static int mod = (int) Math.pow(10,9) + 7;

    private static long countOfPallindromicSubsequenceRecursion(String str){
        int n = str.length();
        long[][] dp = new long[n][n];
        for(long[] row: dp)
            Arrays.fill(row,-1);

        long count = countOfPallindromicSubsequenceRecursionHelper(str,0, n-1,dp);
        return count;
    }
    private static long countOfPallindromicSubsequenceRecursionHelper(String str, int i, int j, long dp[][]){
        if(i > j) return 0;

        if(i == j) return dp[i][j]=1;

        if(dp[i][j] != -1) return dp[i][j];

        if(str.charAt(i) == str.charAt(j)){
            return dp[i][j] = (1 + countOfPallindromicSubsequenceRecursionHelper(str, i+1,j,dp) + countOfPallindromicSubsequenceRecursionHelper(str,i,j-1,dp)) % mod;
        }else{
            return dp[i][j] = (countOfPallindromicSubsequenceRecursionHelper(str, i+1,j,dp) + countOfPallindromicSubsequenceRecursionHelper(str,i,j-1,dp) - countOfPallindromicSubsequenceRecursionHelper(str, i+1,j-1,dp)) % mod;
        }
    }

    private static int countOfPallindromicSubsequence(String str) {
        int[][] dp = new int[str.length()][str.length()];

        for (int gap = 0; gap < dp.length; gap++) {
            for (int i = 0, j = gap; j < dp.length; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 3 : 2;
                else {
                    if (str.charAt(i) == str.charAt(j))
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
                    else
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
            }
        }
        return dp[0][str.length() - 1];
    }

    private static void countOfPallindromicSubstring(String str) {
        boolean[][] dp = new boolean[str.length()][str.length()];
        int count = 0;

        for (int gap = 0; gap < dp.length; gap++) {
            for (int i = 0, j = gap; j < dp.length; j++, i++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                else
                    dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] == true;

                if (dp[i][j])
                    count++;
            }
        }
        System.out.println("Total Pallindromic substring counts in string : " + str + " are : " + count);
    }

    static int longestPalindromeSubsequence(String s) {
        String t = s;
        String ss = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequenceDp(ss, t);
    }

    public static String longestPalindromeSubstring(String str) {
        int n = str.length();
        int maxLength = 1;
        int start = 0;
        boolean[][] dp = new boolean[str.length()+1][str.length()+1];

        // Single letter is always palindromic.
        for (int i = 0; i < n; ++i) {
            dp[i][i] = true;
        }

        // Substring of length 2.
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (maxLength < 2) {
                    start = i;
                    maxLength = 2;
                }
            }
        }

        // Check for lengths greater than 2. k is length of substring.
        for (int len = 3; len <= n; len++) {
            // Fix the starting index.
            for (int i = 0; i < n - len + 1; ++i) {
                // Ending index of length len.
                int j = i + len - 1;
                // Condition of str[i,j] to be palindromic.
                if (dp[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = true;
                    // Update the starting index and the length.
                    if (len > maxLength) {
                        start = i;
                        maxLength = len;
                    }
                }
            }
        }
        return str.substring(start, start + maxLength);

          //Below code is also fine but giving TLE due to O(n^2) nature.
//        boolean[][] dp = new boolean[s.length()][s.length()];
//        String pallindromicString = "";
//
//        for(int gap=0; gap < s.length();gap++){
//            for(int i=0,j=gap; j < s.length();i++,j++){
//                if(gap==0){
//                    dp[i][j] = true;
//                }else if(gap==1){
//                    dp[i][j] = s.charAt(i) == s.charAt(j);
//                }else{
//                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == true;
//                }
//                if(dp[i][j]){
//                    int len = s.length() - j;
//                    String str = s.substring(i,j+1);
//                    if(str.length() >= pallindromicString.length())
//                        pallindromicString = str;
//                }
//            }
//        }
//        return pallindromicString;
    }

    public static int minCharsforPalindrome(String str) {
        int len = str.length();
        String reversedStr = reverseString(str);
        String concat = str + "$" + reversedStr;
        int[] lps = calculatePrefix(concat);

        return (len - lps[lps.length -1]);
    }

    public static int[] calculatePrefix(String str){
        int len = str.length();
        int[] lps = new int[len];
        lps[0] =0;
        int i=1;
        int j=0;

        while(i< str.length()){
            if(str.charAt(i) == str.charAt(j)){
                j++;
                lps[i] =j;
                i++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static String reverseString(String s){
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    static int minInsertion(String s) {
        int n = s.length();
        int k = longestPalindromeSubsequence(s);
        return n - k;
    }

    public static String reverse(String s) {
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--)
            rev += s.charAt(i);

        return rev;
    }

    static int convertStringToAnotherString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int k = longestCommonSubsequenceDp(str1, str2);

        return (n - k) + (m - k);
    }

    //bgruoote
    static String shortestSupersequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 0 + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = n;
        int j = m;
        String ans = "";

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ans += s1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans += s1.charAt(i - 1);
                i--;
            } else {
                ans += s2.charAt(j - 1);
                j--;
            }
        }

        //Adding Remaing Characters - Only one of the below two while loops will run
        while (i > 0) {
            ans += s1.charAt(i - 1);
            i--;
        }
        while (j > 0) {
            ans += s2.charAt(j - 1);
            j--;
        }

        String ans2 = new StringBuilder(ans).reverse().toString();

        return ans2;
    }

    static final int prime = (int) (Math.pow(10, 9) + 7);

    static int distinctSubsequenceCountRecursionMemoizationHelper(String s1, String s2, int ind1, int ind2, int[][] dp) {
        if (ind2 < 0) return 1;
        if (ind1 < 0) return 0;

        if (dp[ind1][ind2] != -1) return dp[ind1][ind2];

        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            int leaveOne = distinctSubsequenceCountRecursionMemoizationHelper(s1, s2, ind1 - 1, ind2 - 1, dp);
            int stay = distinctSubsequenceCountRecursionMemoizationHelper(s1, s2, ind1 - 1, ind2, dp);

            return dp[ind1][ind2] = (leaveOne + stay) % prime;
        } else {
            return dp[ind1][ind2] = distinctSubsequenceCountRecursionMemoizationHelper(s1, s2, ind1 - 1, ind2, dp);
        }
    }

    static int distinctSubsequenceCountRecursionMemoization(String t, String s, int lt, int ls) {
        int dp[][] = new int[lt][ls];
        for (int rows[] : dp)
            Arrays.fill(rows, -1);
        return distinctSubsequenceCountRecursionMemoizationHelper(t, s, lt - 1, ls - 1, dp);
    }

    static int distinctSubsequenceCountDP(String s1, String s2, int n, int m) {
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % prime;
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][m];
    }

    static int distinctSubsequenceCountDPSpaceOptimization(String s1, String s2, int n, int m) {
        int[] prev = new int[m + 1];
        prev[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 1; j--) { // Reverse direction
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    prev[j] = (prev[j - 1] + prev[j]) % prime;
                else
                    prev[j] = prev[j]; //can omit this statemwnt
            }
        }
        return prev[m];
    }

    static int editDistanceRecursion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n][m];
        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }
        return editDistanceRecursionHelper(n - 1, m - 1, s1, s2, dp);
    }

    static int editDistanceRecursionHelper(int i, int j, String s1, String s2, int[][] dp) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = 0 + editDistanceRecursionHelper(i - 1, j - 1, s1, s2, dp);
        else {
                                //Hypothetical Insertion to str1 from j and hence and move j    //Hypothetical delete char from Str1 and keep j as is         //Hypothetical replace char from Str1 and str2  means move i and j both
            return dp[i][j] = 1 + Math.min(editDistanceRecursionHelper(i, j - 1, s1, s2, dp), Math.min(editDistanceRecursionHelper(i - 1, j, s1, s2, dp), editDistanceRecursionHelper(i - 1, j - 1, s1, s2, dp)));
        }
    }

    static int editDistanceDP(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n+1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m+1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 0 + dp[i - 1][j - 1];
                else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[n][m];
    }

    static int editDistanceDPSpaceOptimized(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];

        for (int j = 0; j < m+1; j++) {
            prev[j] = j;
        }

        for (int i = 1; i < n + 1; i++) {
            cur[0] = i;
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    cur[j] = 0 + prev[j - 1];
                else
                    cur[j] = 1 + Math.min(cur[j - 1], Math.min(prev[j], prev[j - 1]));
            }
            prev = cur.clone();
        }
        return prev[m];
    }

    static int
    wildcardMatchingRecursion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int dp[][] = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        return wildcardMatchingRecursionHelper(n - 1, m - 1, s1, s2, dp);
    }

    //p.chars().allMatch(c -> c == '*')
    static boolean isAllStars(String s1, int i) {
        for (int j = 0; j <= i; j++) {
            if (s1.charAt(j) != '*')
                return false;
        }
        return true;
    }

    static int wildcardMatchingRecursionHelper(int i, int j, String s1, String s2, int[][] dp) {
        if (i < 0 && j < 0)
            return 1;
        if (i < 0 && j >= 0)
            return 0;
        if (j < 0 && i >= 0)
            return isAllStars(s1, i) ? 1 : 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
            return wildcardMatchingRecursionHelper(i - 1, j - 1, s1, s2, dp);
        } else {
            if (s1.charAt(i) == '*') {
                return (wildcardMatchingRecursionHelper(i - 1, j, s1, s2, dp) == 1 || wildcardMatchingRecursionHelper(i, j - 1, s1, s2, dp) == 1) ? 1 : 0;
            } else
                return 0;
        }
    }

    static boolean wildcardMatchingDp(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int j = 1; j <= m; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 0; j <= i; j++) {
                if (s1.charAt(j) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (s1.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[n][m];
    }

    static boolean wildcardMatchingDpSpaceOptimized(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        boolean[] prev = new boolean[m+1];
        boolean[] cur  = new boolean[m+1];

        prev[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {
                    cur[j] = prev[j - 1];
                } else {
                    if (s1.charAt(i - 1) == '*') {
                        cur[j] = prev[j] || cur[j - 1];
                    } else {
                        cur[j] = false;
                    }
                }
            }
            prev= cur.clone();
        }
        return prev[m];
    }

    //Tushar Roy
    public static boolean isMatch_Tushar(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        //replace multiple consecutive * with single *
        boolean isFirst = true;
        int writeIndex = 0;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != '*') {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            } else {
                if (isFirst) {
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                }
            }
        }

        boolean[][] dp = new boolean[str.length + 1][writeIndex + 1];

        if (writeIndex > 0 && pattern[0] == '*') {
            dp[0][1] = true;
        }
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[str.length][writeIndex];
    }
    //From LeetCode
    public static boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        // base cases
        if (p.equals(s)) {
            return true;
        }

        if (pLen > 0 && p.chars().allMatch(c -> c == '*')) {
            return true;
        }

        if (p.isEmpty() || s.isEmpty()) {
            return false;
        }

        // init all matrix except [0][0] element as False
        boolean[][] d = new boolean[pLen + 1][sLen + 1];
        d[0][0] = true;

        // DP compute
        for (int pIdx = 1; pIdx < pLen + 1; pIdx++) {
            // the current character in the pattern is '*'
            if (p.charAt(pIdx - 1) == '*') {
                int sIdx = 1;

                // d[p_idx - 1][s_idx - 1] is a string-pattern match
                // on the previous step, i.e. one character before.
                // Find the first idx in string with the previous math.
                while ((!d[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) {
                    sIdx++;
                }

                // If (string) matches (pattern),
                // when (string) matches (pattern)* as well
                d[pIdx][sIdx - 1] = d[pIdx - 1][sIdx - 1];

                // If (string) matches (pattern),
                // when (string)(whatever_characters) matches (pattern)* as well
                while (sIdx < sLen + 1) {
                    d[pIdx][sIdx++] = true;
                }

                // the current character in the pattern is '?'
            } else if (p.charAt(pIdx - 1) == '?') {
                for (int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
                }

                // the current character in the pattern is not '*' or '?'
            } else {
                for (int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                    // Match is possible if there is a previous match
                    // and current characters are the same
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] &&
                            (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
                }
            }
        }

        return d[pLen][sLen];
    }

    public static boolean isMatchOptimized(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;

                // If pattern character = '*'
            } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;

                // If pattern character != string character
                // or pattern is used up
                // and there was no '*' character in pattern
            } else if (starIdx == -1) {
                return false;

                // If pattern character != string character
                // or pattern is used up
                // and there was '*' character in pattern before
            } else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }

        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("================= Longest Common Subsequence /  Recursion-Memoization ====================");

        String s1 = "abcde";
        String s2 = "bdgek";
        System.out.println("The Length of Longest Common Subsequence with Recursion/Memoization is: " + longestCommonSubsequenceRecursion(s1, s2));

        System.out.println("========================== Longest Common Subsequence /  DP ==============================");

        System.out.println("The Length of Longest Common Subsequence with Dp is: " + longestCommonSubsequenceDp(s1, s2));

        List<Integer> l1 = new ArrayList<>();
        l1.add(3);
        l1.add(9);
        l1.add(8);
        l1.add(3);
        l1.add(9);
        l1.add(7);
        l1.add(9);
        l1.add(7);
        l1.add(0);

        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(3);
        l2.add(9);
        l2.add(9);
        l2.add(9);
        l2.add(1);
        l2.add(7);
        l2.add(2);
        l2.add(0);
        l2.add(6);

        List<Integer> lcsList = longestCommonSubsequence(l1,l2); //LCS of Numbered array is: [3, 9, 9, 9, 7, 0]
        System.out.println("LCS of Numbered array is: " + lcsList); //3 3 9 9 7 0 HackerRank

        System.out.println("======================= Print Longest Common Subsequence /  DP ===========================");

        System.out.print("The Longest Common Subsequence is ");
        printLongestCommonSubsequence(s1, s2);
        List<String> ls = printLongestCommonSubsequenceII(s1,s2);
        System.out.println("The Longest Common SubsequenceII is : " + ls);
        System.out.println("========================== Longest Common Substring /  DP ==================================");
        s1 = "abcjklp";
        s2 = "acjkp";
        System.out.println("The Length of Longest Common Substring with Dp is: " + longestCommonSubstringDp(s1, s2));
        System.out.println("The Length of Longest Common Substring with Dp - Space Optimized is: " + longestCommonSubstringDpSpaceOptimized(s1, s2));

        System.out.println("============================ Count of Pallindromic Subsequence ===========================");
        s1 = "abccbc";
        System.out.println("Count of pallindromic subsequence with PepCoding is: " + countOfPallindromicSubsequence(s1));
        System.out.println("Count of pallindromic subsequence with recursion is: " + countOfPallindromicSubsequenceRecursion(s1));

        System.out.println("=============== Minimum Characters for Pallindrome ======================");
        String testString = "geeks";
        System.out.println("The Minimum characters required to add from front to make string palindrome is : " + minCharsforPalindrome(testString));

        System.out.println("================ Minimum insertions to make string palindrome | DP =======================");
        String s = "abccbc";//The Minimum insertions required to make string palindrome: 2
        System.out.println("The Minimum insertions required to make string palindrome: " + minInsertion(s));

        System.out.println("=============== Minimum Insertions/Deletions to Convert String | DP ======================");
        String str1 = "abcd";
        String str2 = "anc"; //The Minimum operations required to convert str1 to str2: 3
        System.out.println("The Minimum operations required to convert str1 to str2: " + convertStringToAnotherString(str1, str2));

        System.out.println("======================== Shortest Common Supersequence | DP ==============================");
        s1 = "brute";
        s2 = "groot"; //The Longest Common Supersequence is bgruoote

        System.out.println("The Longest Common Supersequence is " + shortestSupersequence(s1, s2));

        System.out.println("=================== Distinct Subsequences| recursion-Memoization ==========================");
        s1 = "babgbag";
        s2 = "bag"; //The Count of Distinct Subsequences is 5
        System.out.println("The Count of Distinct Subsequences with Recursion is: " + distinctSubsequenceCountRecursionMemoization(s1, s2, s1.length(), s2.length()));

        System.out.println("============================ Distinct Subsequences| DP ===================================");
        System.out.println("The Count of Distinct Subsequences with Dp is: " + distinctSubsequenceCountDP(s1, s2, s1.length(), s2.length()));

        System.out.println("==================== Distinct Subsequences| DP | Space Optimization 1D ===================");
        System.out.println("The Count of Distinct Subsequences with DP & Space Optimization is " + distinctSubsequenceCountDPSpaceOptimization(s1, s2, s1.length(), s2.length()));

        System.out.println("==================== Edit Distance | Recursionand Memoization ============================");
        s1 = "horse";
        s2 = "ros";//The minimum number of operations required is: 3

        System.out.println("The minimum number of operations required with Recursion is: " + editDistanceRecursion(s1, s2));

        System.out.println("================================= Edit Distance | DP =====================================");
        System.out.println("The minimum number of operations required with DP is: " + editDistanceDP(s1, s2));

        System.out.println("======================== Edit Distance | DP - Space Optimized ============================");
        System.out.println("The minimum number of operations required with DP-Optimized is: " + editDistanceDPSpaceOptimized(s1, s2));

        System.out.println("==================== Wild Card matching | Recursion and memoization ======================");
        s1 = "**";
        s2 = "abcdefhjo"; //String S1 and S2 do match

        if (wildcardMatchingRecursion(s1, s2) == 1)
            System.out.println("String S1 and S2 do match");
        else System.out.println("String S1 and S2 do not match");

        System.out.println("================================ Wild Card matching | DP =================================");

//        if (wildcardMatchingDp(s1, s2))
//            System.out.println("String S1 and S2 do match");
//        else System.out.println("String S1 and S2 do not match");

        System.out.println("====================== Wild Card matching | DP- Space Optimization =======================");

        if (wildcardMatchingDpSpaceOptimized(s1, s2))
            System.out.println("String S1 and S2 do match");
        else System.out.println("String S1 and S2 do not match");


        System.out.println("====================== Wild Card matching | LeetCode DP =======================");
        s2 = "bbbababbabbbbabbbbaabaaabbbbabbbababbbbababaabbbab";
        s1 = "a******b*"; //String S1 and S2 do match

        System.out.println("String S1 : " + s1 + " and S2: " + s2 + " do match? " + isMatch(s2,s1));
        System.out.println("====================== Wild Card matching | LeetCode DP Optimized=======================");
        //System.out.println("String S1 : " + s1 + " and S2: " + s2 + " do match? " + isMatch(s2,s1));

        System.out.println("Tushar Roy: " + isMatch_Tushar(s2,s1));
    }
}

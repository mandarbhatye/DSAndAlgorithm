package Amazon;

import java.util.*;
import java.util.stream.Collectors;

public class Practice_Recursion {

    public static int superDigit(String n, int k) {
        long total = 0;
        long sum = 0;

        if (n.length() == 1) {
            return Integer.valueOf(n);
        } else {
            for (int i = 0; i < n.length(); i++) {
                sum += n.charAt(i) - '0';
            }
        }
        total = sum * k;
        return superDigit(String.valueOf(total), 1);
    }
    static Map<Integer, String> cache = new HashMap<>();
    static String arithmeticExpressions(int[] arr, int idx, int val) {
        // to avoid overflow
        val %= 101;
        // use cache if present
        final int key = idx * 101 + val;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (idx < arr.length) {
            if (val != 0) { // if val == 0 keep multiplying
                if (val != arr[idx]) { // if val == arr[idx] skip adding
                    // try addition
                    final String add = arithmeticExpressions(arr, idx + 1, val + arr[idx]);
                    cache.put(key, add);
                    if (add != null) {
                        return "+" + arr[idx] + add;
                    }
                }
                // try subtraction
                final String sub = arithmeticExpressions(arr, idx + 1, val - arr[idx]);
                cache.put(key, sub);
                if (sub != null) {
                    return "-" + arr[idx] + sub;
                }
            }
            // try multiplication
            final String mul = arithmeticExpressions(arr, idx + 1, val * arr[idx]);
            cache.put(key, mul);
            if (mul != null) {
                return "*" + arr[idx] + mul;
            }
        } else if (val == 0) { // if last idx and val == 0 we found the solution
            return "";
        }
        return null;
    }


    public static List<List<Integer>> getPermutations(int[] arr) {
        if (arr.length  == 0) return new ArrayList<>();
        List<List<Integer>> perms = new ArrayList<>();
        generatePermutation(arr, 0, perms);
        return perms;
    }
    private static void generatePermutation(int[] arr, int idx, List<List<Integer>> perms){
        if(idx == arr.length){
            List<Integer> perm = Arrays.stream(arr).boxed().collect(Collectors.toList());
            perms.add(perm);
            return;
        }
        for (int i = idx; i < arr.length ; i++) {
            swap(arr, idx,i);
            generatePermutation(arr,idx+1,perms);
            swap(arr, idx,i);
        }
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i]= arr[j];
        arr[j] = temp;
    }

    public static List<List<Integer>> getSubsequences(int[] arr) {
        if (arr.length  == 0) return new ArrayList<>();
        List<List<Integer>> perms = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        generateSubsequences(arr, 0, perms,perm);
        return perms;
    }

    private static void generateSubsequences(int[] arr, int idx, List<List<Integer>> perms, List<Integer>perm){
        if(idx == arr.length){
           perms.add(perm);
           return;
        }
        perm.add(arr[idx]);
        generateSubsequences(arr,idx+1,perms,perm);
        perm.remove(perm.size()-1);
    }
    public static void main(String[] args) {
        //long sum = superDigit("593", 10);
        //System.out.println("Final sum is: " + sum);

        //int[] arr = new int[]{55,3,45,33,25};
        //String s = arr[0] + arithmeticExpressions(arr,1,arr[0]); //55+3-45*33-25
        //System.out.println("Result is: " + s);

        System.out.println("============================ Print Number Permutation ====================================");
        int[]arr = new int[]{3, 1, 2};
        List<List<Integer>> perms = getPermutations(arr);
        System.out.println(perms);

        System.out.println("============================ Print Number Subsequence ====================================");
        arr = new int[]{3, 1, 2};
        perms = getSubsequences(arr);
        System.out.println(perms);

    }
}

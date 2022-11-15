package Amazon;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import javax.print.attribute.IntegerSyntax;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Practice_StringMatchingAlgorithms {

    private static int[] generateLongestProperPrefixArray(String pattern){
        int n = pattern.length();
        int[] lps = new int[n];
        int i = 1;
        int j = 0;

        while(i < pattern.length()){
            if(pattern.charAt(i) == pattern.charAt(j)){
                j++;
                lps[i] = j;
                i++;
            }else{
                if(j !=0){
                    j = lps[j-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }
    private static List<Integer> kmpAlgorithmSearchPattern(String text, String pattern){
        List<Integer> occcurance = new ArrayList<>();
        int[] lps = generateLongestProperPrefixArray(pattern);
        int n = text.length();
        int i = 0;
        int j = 0;

        while(i < text.length()){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }

            if(j == pattern.length()){
                occcurance.add(i-j);
                j = lps[j-1];
            }
        }
        return occcurance;
    }

    private static List<Integer> stringMatchByRabinKarp(String text, String pattern){
        List<Integer> occurance = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int prime =13;
        int mod = 998242353;

        long[] primePower = new long[n];
        primePower[0] =1;
        for (int i = 1; i < n; i++) {
            primePower[i] = (primePower[i-1]*prime)% mod;
        }

        long[] hash = new long[n+1];
        for (int i = 0; i < n ; i++) {
            hash[i+1] = (hash[i] + (text.charAt(i) - 'A' +1) * primePower[i]) % mod;
        }
        long patternHash =0;
        for (int i = 0; i < m ; i++) {
            patternHash = (patternHash + (pattern.charAt(i) - 'A' +1) * primePower[i]) % mod;
        }

        for (int i = 0; i +m -1 < n ; i++) {
            long currrentHash = (hash[i+m] + mod -hash[i]) % mod;
            if(currrentHash == patternHash * primePower[i] % mod){
                occurance.add(i);
            }
        }
        return occurance;
    }

    private static int[] generateZArray(String str){
        int n = str.length();
        int[] zArray = new int[n];
        int left  = 0;
        int right = 0;
        for (int i = 0; i < n ; i++) {
            if(i <= right){
                zArray[i] = Math.min(right -i +1, zArray[i - left]);
            }
            while(i + zArray[i] < n && str.charAt(zArray[i]) == str.charAt(i+ zArray[i])){
                zArray[i]++;
            }
            if(i+zArray[i] -1 > right){
                left = i;
                right = i + zArray[i] -1;
            }
        }
        return zArray;
    }
    private static List<Integer> zFuncForStringMatching(String text, String pattern){
        List<Integer> occurance = new ArrayList<>();
        String temp = pattern + "$" + text;

        int[] zArray = generateZArray(temp);

        for (int i = 0; i < zArray.length ; i++) {
            if(zArray[i] == pattern.length()){
                occurance.add(i - pattern.length() -1);
            }
        }
        return occurance;
    }
    public static void main(String[] args) {
        System.out.println("========================= Pattern Matching - KMP (Knuth-Morris-Pratt)======================");
        String st = "hellowllen";
        String pattern = "ll"; // 0,5
        //String st = "ababa";
        //String pattern = "ab"; // 0,5
        List<Integer> l = kmpAlgorithmSearchPattern(st, pattern);
        System.out.println("Pattern match at indexes : " + l);

        System.out.println("=============== Pattern Matching - Rabin Karp ================");
        l = stringMatchByRabinKarp(st, pattern);
        System.out.println("Pattern match at indexes : " + l);

        System.out.println("=========================== Pattern Matching - Z Function =================================");
        l = zFuncForStringMatching(st, pattern);
        System.out.println("Pattern match at indexes : " + l);
    }
}

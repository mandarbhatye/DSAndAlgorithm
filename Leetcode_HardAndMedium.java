package Amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Leetcode_HardAndMedium {

    //A password is considered strong if the below conditions are all met:
        //It has at least 6 characters and at most 20 characters.
        //It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
        //It does not contain three repeating characters in a row (i.e., "...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
    //Given a string password, return the minimum number of steps required to make password strong. if password is already strong, return 0.
    //In one step, you can:
        //Insert one character to password,
        //Delete one character from password, or
        //Replace one character of password with another character.
    public static int strongPasswordChecker(String password) {
        int[] ok = new int[]{1, 1, 1};
        char[] A = password.toCharArray();

        ok[0] &= password.chars().anyMatch(c-> Character.isLowerCase(c)) ? 0 : 1;
        ok[1] &= password.chars().anyMatch(c-> Character.isUpperCase(c)) ? 0 : 1;
        ok[2] &= password.chars().anyMatch(c-> Character.isDigit(c)) ? 0 : 1;

        int ans = 0;
        int len = password.length();
        int cond = ok[0]+ok[1]+ok[2]; // cond -> how many cond not met yet.

        PriorityQueue<Integer> minheap = new PriorityQueue<>(Comparator.comparingInt(o -> o%3)); // sort by o%3

        for (int i = 0, cnt = 1; i < len; i++, cnt++){ // put consecutive chars with count >= 3 into the minheap
            if (i == len-1 || A[i] != A[i+1]){
                if (cnt >= 3){
                    minheap.offer(cnt);
                }
                cnt = 0;
            }
        }
        while(len > 20 && !minheap.isEmpty()){ // if len > 20, we try to delete from the chars groups.
            int cur = minheap.poll();
            if (--cur >= 3){
                minheap.offer(cur);
            }
            --len;
            ++ans;
        }
        while(!minheap.isEmpty()){ // if there are still 3 or more chars groups, we need to replace or insert
            int cur = minheap.poll();
            cond -= cur/3;
            ans += cur/3;
            if (len < 6){ // insert only for len < 6; otherwise, replace.
                len++;    // I do ++ here as cur can only be 3,4,5.
            }
        }
        while(len > 20){ // if len is still > 20, we need to delete.
            ans++;
            len--;
        }
        while(len < 6){ // if len < 6, we need to insert and per insertion will satisify 1 cond
            cond--;
            ans++;
            len++;
        }
        return ans + (cond < 0? 0 : cond); // remember to take the max of (cond, 0).
    }

    public static List<Integer> partitionLabel(String str){
        int[] freqArray = new int[26];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < str.length() ; i++) {
            freqArray[str.charAt(i) -'a'] =i;
        }

        int start = 0;
        int end   = 0;
        for (int i = 0; i <str.length() ; i++) {
            end = Math.max(end, freqArray[str.charAt(i)-'a']);
            if(i== end){
                result.add(end-start+1);
                start = end+1;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String password = "bbaaaaaaaaaaaaaaacccccc";
        int pass = strongPasswordChecker(password);

        System.out.println("Is Password strong? " + pass);

        String str = "ababcbacadefegdehijhklij";
        List<Integer> lst = partitionLabel(str);
        System.out.println("New partition would be: " + lst);
//        List<String> lst = new ArrayList<>();
//        int previousSplit =0;
//        for (int i = 1; i < password.length() ; i++) {
//            if(password.charAt(i) != password.charAt(i-1)){
//                lst.add(password.substring(previousSplit,i));
//                previousSplit =i;
//            }
//        }
//        lst.add(password.substring(previousSplit, password.length()));
//
//        List<String> l = lst.stream().filter(x-> x.length()>3).collect(Collectors.toList());       //max(Comparator.comparingInt(String::length)).get().length();
    }
}

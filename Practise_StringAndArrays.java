package Amazon;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.effect.Blend;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practise_StringAndArrays {

    private static String[] ones ={"", "One ","Two ", "Three ","Four ","Five ","Six ","Seven ","Eight ","Nine "};
    private static String[] one_ten ={"Ten ", "Eleven ","Twelve ","Thirteen ","Fourteen", "Fifteen ","Sixteen ","Seventeen ","Eighteen ","Nineteen "};
    private static String[] tens ={"","","Twenty ","Thirty ","Forty ","Fifty ","Sixty ","Seventy ","Eighty ","Ninety "};
    private static String[] hundreds ={"","Thousand ","Million ","Billion "};

    private static String numberToWord(int number){
        List<String> result = new ArrayList<>();

        for (int i = number; i !=0 ; i/=1000) {
            int digit = i%1000;
            result.add(word(digit));
        }

        String wo ="";
        String wordNumber ="";
        for (int i = 0; i < result.size() ; i++) {
            String s = result.get(i);
            if(s.equals("") || s.isEmpty())
                continue;
            else{
                wo = s + " " + hundreds[i];
            }
            wordNumber = wo + wordNumber;
            wo="";

        }
        return wordNumber.trim();
    }

    private static String word(int number){
        int digiCounter =1;
        int i = 0;
        int n = 0;
        String answer = "";
        for (int j = number; j !=0 ; j/=10) {
            i = j % 10;
            n =n*10+i;

            if(digiCounter ==1){
                answer += ones[i];
            }else if(digiCounter ==2){
                if(i!=0 && n % 10 ==1){
                    answer = one_ten[n/10];
                }else if( n>=2){
                    answer = tens[i] + answer;
                }
            }else{
                if(i ==0){
                    continue;
                }
                answer = ones[i] + "Hundred " + answer;
            }
            digiCounter++;
        }
        return answer.trim();
    }

    private static void longestSubstringWithoutRepeatingCharacters(String s){
        Map<Character,Integer> map = new HashMap<>();
        int len = Integer.MIN_VALUE;
        String answer = "";
        int j =0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(map.containsKey(ch))
                j = Math.max(j, map.get(ch));

            int newLen = i-j +1;
            if(newLen > len)
                len = newLen;

            String newStr = s.substring(j,i+1);
            if(newStr.length() > answer.length())
                answer = newStr;

            map.put(ch, i+1);
        }
        System.out.println("Longest substring without repeating character for String:" + s + " length is: " + len + " Substring is: " + answer);
    }

    private static void myAtoi(String str){
        int result =0;
        int sign =1;
        int index =0;

        while(index < str.length() && str.charAt(index) == ' '){
            index++;
        }

        if(index < str.length() && str.charAt(index) == '+'){
            sign =1;
            index++;
        }else if(index < str.length() && str.charAt(index) == '-'){
            sign =-1;
            index++;
        }

        while(index < str.length() && Character.isDigit(str.charAt(index))){
            int digit = str.charAt(index) -'0';

            if((result > Integer.MAX_VALUE/10) || (result == Integer.MAX_VALUE /10 && digit > Integer.MAX_VALUE %10)) {
                int num = sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
                System.out.println("Number Overflow for string : " + str + " is : " + num);
            }
            result = result * 10 + digit;
            index++;
        }
        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        System.out.println("Number representation for string : " + str + " is : " + sign * result);
    }


    private static String intToRoman(int num){
         String[] ones ={"","I","II","III","IV","V","VI","VII","VIII","IX"};
         String[] tens ={"","X","XX","XXX","XL","L","LC","LCC","LCC","XC"};
         String[] hundred ={"", "C","CC","CCC","CD","D","DC","DCC","DCC","CM"};
         String[] thousand ={"","M","MM","MMM"};

         System.out.println(num % 1000);
         return thousand[num/1000] + hundred[num % 1000 /100] + tens[num%100/10]+ ones[num%10];
    }

    private static int romanToInt2(String roman){
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result = 0;

        for (int i = 0; i < roman.length() ; i++) {
            char ch = roman.charAt(i);
            if(i != roman.length()-1){
                if(ch == 'I' && (roman.charAt(i+1)== 'V' || roman.charAt(i+1)== 'X')){
                    result--;
                    continue;
                }
                if(ch == 'X' && (roman.charAt(i+1)== 'L' || roman.charAt(i+1)== 'C')){
                    result -=10;
                    continue;
                }
                if(ch == 'C' && (roman.charAt(i+1)== 'D' || roman.charAt(i+1)== 'M')){
                    result -=100;
                    continue;
                }
            }
            result +=map.get(ch);
        }
        return result;
    }

    private static List<List<Integer>> twoSum(int[] arr, int start, int end, int target){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int left = start;
        int right = end;
        while(left < right){
            if(left!=0 && arr[left] == arr[left-1]) {
                left++;
                continue;
            }
            if(arr[left] == arr[right]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];

            if(sum == target){
                List<Integer> subList = new ArrayList<>();
                subList.add(arr[left]);
                subList.add(arr[right]);
                result.add(subList);
                left++;
                right--;
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }
        return result;
    }

    private static List<List<Integer>> threeSum(int[] arr, int target){
        Arrays.sort(arr);
        return kSum(arr, target, 0, 3);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public static List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if( start == nums.length)
            return result;

        long avg = target/k;

        if(nums[start]> avg || avg > nums[nums.length-1])
            return result;

        if(k==2){
            return twoSum(nums, target,start);
        }

        for (int i = start; i < nums.length-1 ; i++) {
            if(i!= start && nums[i] == nums[i-1]) continue;
            for(List<Integer> subList : kSum(nums,target - nums[i],i+1,k-1)){
                result.add(new ArrayList<>(Arrays.asList(nums[i])));
                result.get(result.size()-1).addAll(subList);
            }
        }
        return result;
    }

    public static List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        int left = start;
        int right = nums.length -1;

        while(left< right){
            int sum = nums[left] + nums[right];

            if(sum < target || (left > start && nums[left] == nums[left -1])){
                left++;
            }else if(sum > target || (right < nums.length-1 && nums[right] == nums[right+1])){
                right--;
            }else{
                result.add(Arrays.asList(nums[left++], nums[right--]));
            }
        }
        return result;
    }

    private static int threeSumClosest(int[] nums, int target){
        int difference = Integer.MAX_VALUE;
        int answer =0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length ; i++) {
            int left = i+1;
            int right = nums.length -1;

            while(left < right){
                int sum = nums[i] + nums[left]+ nums[right];
                int diff =Math.abs(sum - target);

                if(diff == target){
                    return sum;
                }else if(diff < difference){
                    difference = diff;
                    answer = diff;
                }

                if(sum < target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return answer;
    }

    private static int[] smallestDifference(int[] arr1, int[] arr2){
        int[] result = new int[2];
        int difference = Integer.MAX_VALUE;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int left = 0;
        int right =0;

        while(left < arr1.length && right < arr2.length){
            int diff = Math.abs(arr1[left] - arr2[right]);
            if(diff < difference){
                difference = diff;
                result[0] = arr1[left];
                result[1] = arr2[right];
            }

            if(arr1[left] < arr2[right]){
                left++;
            }else{
                right++;
            }
        }
        return result;
    }

    private static List<Integer> moveElementToEnd(List<Integer> list, int toMove){
        int j=0;

        for (int i = 0; i < list.size() ; i++) {
            if(list.get(i) != toMove){
                list.set(j++,list.get(i));
                list.set(i,toMove);
            }
        }
        return list;
    }

    private static boolean isMonotonic(int[] arr){
        if(arr.length == 0) return false;

        boolean isDecreasing = arr[0] > arr[arr.length-1];

        for (int i = 1; i < arr.length; i++) {
            if(isDecreasing && arr[i] >arr[i-1])
                return false;
            if(!isDecreasing && arr[i] < arr[i-1])
                return false;
        }
        return true;
    }

    private static int[] generateLPS(String needle){
        int len = needle.length();
        int[] lps = new int[len];
        int i = 1;
        int j = 0;

        while(i < needle.length()){
            if(needle.charAt(i) == needle.charAt(j)){
                j++;
                lps[i] = j;
                i++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    lps[i]=0;
                    i++;
                }
            }
        }
        return lps;
    }
    private static void strStr(String hayStack, String needle){
        List<Integer> occurance = new ArrayList<>();
        int[] lps = generateLPS(needle);
        int i=0;
        int j=0;
        while(i< hayStack.length()){
            if(hayStack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else{
                if(j!=0){
                    j= lps[j-1];
                }else{
                    i++;
                }
            }

            if(j == needle.length()){
                occurance.add(i-j);
                j = lps[j-1];
            }
        }
        System.out.println("Occurance of pattern : " + needle + " in the String: " + hayStack + " is at : " + occurance.toString());
    }
    private static void strStrByRabinKarp(String hayStack, String needle){
        List<Integer> occurance = new ArrayList<>();
        int prime =13;
        int mod = 998242353;
        int n = hayStack.length();
        int m = needle.length();

        long[] primePower = new long[n];
        primePower[0] = 1;
        for (int i = 1; i < n ; i++) {
            primePower[i] = (primePower[i-1] * prime)% mod;
        }

        long[] hash = new long[n+1];
        for (int i = 0; i < n ; i++) {
            hash[i+1] = ((hash[i] + (hayStack.charAt(i) -'A' +1)*primePower[i]))% mod;
        }

        long patternHash = 0;
        for (int i = 0; i < m ; i++) {
           patternHash = (patternHash + (needle.charAt(i) - 'A'+1)* primePower[i])% mod;
        }

        for (int i = 0; i + m - 1 < n ; i++) {
            long currentHash = (hash[i+m] + mod - hash[i]) % mod;

            if(currentHash == patternHash * primePower[i] % mod){
                occurance.add(i);
            }
        }
        System.out.println("Occurance of pattern : " + needle + " in the String: " + hayStack + " is at : " + occurance.toString());
    }

    private static int[] generateZArray(String text){
        int n = text.length();
        int[] zArray = new int[n];
        int left  = 0;
        int right = 0;
        for (int i = 1; i < n ; i++) {
            if(i<= right){
                zArray[i] = Math.min(right-i+1, zArray[i-left]);
            }
            while( i+ zArray[i] < n && text.charAt(zArray[i]) == text.charAt(i+ zArray[i])){
                zArray[i]++;
            }

            if(i+zArray[i]-1 > right){
                left =i;
                right = i+ zArray[i] -1;
            }
        }
        return zArray;
    }
    private static void zFuncForStringMatching(String hayStack, String needle){
        List<Integer> occurance = new ArrayList<>();
        String temp =needle + "$" + hayStack;
        int[] zArray = generateZArray(temp);

        for (int i = 0; i < zArray.length; i++) {
            if(zArray[i] == needle.length()){
                occurance.add(i - needle.length()-1);
            }
        }
        System.out.println("Occurance of pattern : " + needle + " in the String: " + hayStack + " is at : " + occurance.toString());
    }

    private static void rotate(int[][] matrix){
        transformMatrix(matrix);
        reverseMatrixRow(matrix);
    }

    private static void transformMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < i ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void reverseMatrixRow(int[][] matrix){
        for (int i = 0; i < matrix.length ; i++) {
            int left =0;
            int right = matrix[0].length -1;

            while(left < right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
    private static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j] +" ");
            }
            System.out.println();
        }
    }

    private static void printMatrix(ArrayList<ArrayList<Integer>>  matrix){
        for (int i = 0; i < matrix.size() ; i++) {
            for (int j = 0; j < matrix.get(0).size() ; j++) {
                System.out.print(matrix.get(i).get(j) +" ");
            }
            System.out.println();
        }
    }

    private static void rotateMatrix(ArrayList<ArrayList<Integer>> matrix){
        rotateMatrixrecursion(matrix,0,0, matrix.size(),matrix.get(0).size());
    }

    private static void rotateMatrixrecursion(ArrayList<ArrayList<Integer>> matrix, int startRow, int startCol, int endRow, int endCol){
        if(startRow >= endRow || startCol >= endCol) return;
        //if(startRow >= endRow -1 || startCol >= endCol-1) return;

        int previous = matrix.get(startRow+1).get(startCol);

        for (int i = startCol; i < endCol ; i++) {
            int current = matrix.get(startRow).get(i);
            matrix.get(startRow).set(i, previous);
            previous = current;
        }

        startRow++;

        for (int i = startRow; i < endRow ; i++) {
            int current = matrix.get(i).get(endCol-1);
            matrix.get(i).set(endCol-1,previous);
            previous = current;
        }
        endCol--;

        if(startRow < endRow){
            for (int i = endCol -1 ; i >= startCol ; i--) {
                int current = matrix.get(endRow-1).get(i);
                matrix.get(endRow-1).set(i, previous);
                previous = current;
            }
        }

        endRow--;

        if(startCol < endCol){
            for (int i = endRow -1; i >= startRow ; i--) {
                int current = matrix.get(i).get(startCol);
                matrix.get(i).set(startCol, previous);
                previous = current;
            }
        }

        startCol++;
        rotateMatrixrecursion(matrix,startRow,startCol,endRow,endCol);
    }
    private static List<List<String>> groupAnagrams(String[] wordList){
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();

        for(String word: wordList){
            char[] chArray = word.toCharArray();
            Arrays.sort(chArray);
            String newWord = String.valueOf(chArray);

            if(map.containsKey(newWord)){
                map.get(newWord).add(word);
            }else{
                List<String> lst = new ArrayList<>();
                lst.add(word);
                map.put(newWord,lst);
            }
        }

        for(List<String> ls: map.values())
            result.add(ls);

        return result;
    }

    private static int compareVersion(String v1, String v2){
        int result =0;

        String[] s1 = v1.split("\\.");
        String[] s2 = v2.split("\\.");
        int n1= s1.length;
        int n2 = s2.length;

        int i1,i2 =0;
        for (int i = 0; i < Math.min(n1,n2) ; i++) {
            i1 = i< n1 ? Integer.parseInt(s1[i]) :0;
            i2 = i< n2 ? Integer.parseInt(s2[i]) :0;

            if(i1 != i2){
               return i1 > i2 ? 1:-1;
            }
        }

        return 0;
    }

    private static int missingNumber(int[] arr){
        int[] arrTemp = arr.clone();
        boolean containsZero = false;
        int n = arr.length;

        for (int i = 0; i < arr.length ; i++) {
            if(arr[i]== 1)
                containsZero = true;
            else if(arr[i] <=0 || arr[i] > n)
                arr[i] =1;
        }

        if(!containsZero) return 1;

        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]);
            arr[index-1] = -Math.abs(arr[index-1]);
        }

        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] > 0)
                return i+1;
        }
        return n+1;
    }
    private static String mostCommonWord(String paragraph, String[] banned){
        HashSet<String> bannWord = new HashSet<>();
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        for(String word: bannWord)
            bannWord.add(word);

        String[] wordList = paragraph.toLowerCase().split("\\W+");
        for(String word: wordList){
            if(!bannWord.contains(word)){
                wordCountMap.put(word, wordCountMap.getOrDefault(word,0) +1);
            }
        }

        int max = Collections.max(wordCountMap.values());

        Optional<String> str = wordCountMap.entrySet().stream().filter(e-> e.getValue() == max).map(Map.Entry::getKey).collect(Collectors.toList()).stream().findFirst();

        return str.orElse("NA");
    }

    //String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
    private static String[] reorderLogFiles(String[] logs){
       Map<Boolean,List<String>> logMap =  Arrays.stream(logs).collect(Collectors.partitioningBy(c-> Character.isDigit(c.split(" ")[1].charAt(0))));

       List<String> letterLog = logMap.get(false);
       List<String> digitLog = logMap.get(true);

       letterLog.sort((log1, log2)->{
           int idx1 = log1.indexOf(" ");
           String id1 = log1.substring(0,idx1);
           String mainStr1 = log1.substring(idx1+1);

           int idx2 = log2.indexOf(" ");
           String id2 = log2.substring(0,idx2);
           String mainStr2 = log2.substring(idx2+1);

           int val = mainStr1.compareTo(mainStr2);
           if(val ==0){
               return id1.compareTo(id2);
           }
           return val;
       });

       letterLog.addAll(digitLog);

       return letterLog.toArray(new String[0]);
    }

    private static int[] productExceptSelf(int[] arr){
        int n = arr.length;
        int[] rightProduct = new int[n];
        int product =1;
        for(int i= n-1; i>=0;i--){
            rightProduct[i] = product;
            product *= arr[i];
        }

        product =1;
        for (int i = 0; i < arr.length ; i++) {
            rightProduct[i] = rightProduct[i] * product;
            product *= arr[i];
        }
        return rightProduct;
    }

    private static void firstUniqChar(String str){
        int[] chArray = new int[26];
        for (int i = 0; i < str.length() ; i++) {
            chArray[str.charAt(i)-'a']++;
        }

        for (int i = 0; i < str.length() ; i++) {
            if(chArray[str.charAt(i)-'a'] == 1) {
                System.out.println("First Unique Character in a String: " + str + " is: " + i);
                return;
            }
        }
        System.out.println("No Unique Character in a String: " + str);
    }

    private static void trap(int[] arr){
        int lmax=0;
        int rmax =0;
        int left =0;
        int right = arr.length-1;
        int answer =0;

        while(left < right){
            if(arr[left] > lmax)
                lmax = arr[left];
            if(arr[right] > rmax)
                rmax = arr[right];

            if(lmax < rmax){
                answer += Math.max(0, lmax - arr[left]);
                left++;
            }else{
                answer += Math.max(0, rmax - arr[right]);
                right--;
            }
        }
        System.out.println("Accumulated water quantity is: " + answer);
    }

    private static List<Integer> zigzagTraverse(List<List<Integer>> array){
        List<Integer> result = new ArrayList<>();
        boolean isDown = true;
        int row =0;
        int col =0;
        int endRow = array.size() - 1;
        int endCol = array.get(0).size() - 1;

        while(row <= endRow && col <= endCol){
            result.add(array.get(row).get(col));

            if(isDown){
                if(row == endRow){
                    col++;
                    isDown = false;
                }else if(col == 0){
                    row++;
                    isDown = false;
                }else{
                    row++;
                    col--;
                }
            }else{
                if(col == endCol){
                    row++;
                    isDown = true;
                }else if(row == 0){
                    col++;
                    isDown = true;
                }else{
                    row--;
                    col++;
                }
            }
        }
        return result;
    }

    private static int longestPeak(int[] arr){
        int longestPeak =0;
        int i = 1;

        while( i < arr.length-1){
            boolean isPeak = arr[i-1] < arr[i] && arr[i] > arr[i+1];
            if(!isPeak){
                i++;
                continue;
            }

            int previous = i - 2;
            while(previous >=0 && arr[previous] < arr[previous+1]){
                previous--;
            }

            int next = i + 2;
            while(next < arr.length && arr[next] < arr[next-1]){
                next++;
            }

            int len = next - previous -1;
            if(len > longestPeak){
                longestPeak = len;
            }
            i = next;
        }
        return longestPeak;
    }

    //Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    private static void mergeOverlappingIntervals(int[][] intervals){
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        result.add(intervals[0]);

        int index = 0;
        for (int i = 1; i < intervals.length ; i++) {
            if(result.get(index)[1] > intervals[i][0]){
                result.get(index)[1] = Math.max(result.get(index)[1],intervals[i][1]);
            }else{
                index++;
                result.add(index,intervals[i]);
            }
        }
        int[][] newInt = result.toArray(new int[result.size()][]);

        result.stream().forEach(a -> System.out.print(Arrays.toString(a) + " "));
        System.out.println();
    }
    private static void SearchIn2DMATRIXOptimal(int[][] matrix, int elementToSearch){
        int n = matrix.length;
        int m = matrix[0].length;

        int low  = 0;
        int high = (n * m) - 1;

        while(low <= high){
            int mid = (low + high) /2;

            int currentVal = matrix[mid/m][mid%m];

            if(currentVal == elementToSearch){
                System.out.println("Element found at : " + mid / m + " and : " + mid % m);
                return;
            }else if(currentVal < elementToSearch){
                low = mid+1;
            }else{
               high = mid -1;
            }
        }
        System.out.println("Element doesn't exists in Matrix");
    }

    private static int[] sortAnArrayOf0_1_2(int[] arr){
        int low =0;
        int mid =0;
        int high = arr.length -1;

        while(mid <= high){
            if(arr[mid] == 0){
                swap(arr, low, mid);
                low++;
                mid++;
            }else if(arr[mid] == 1){
                mid++;
            }else{
                swap(arr,mid, high);
                high--;
            }
        }
        return arr;
    }

    private static void nextPermutation(int[] arr){
        int i = arr.length -2;

        while(i>=0 && arr[i] >= arr[i+1])
            i--;

        if(i>=0){
            int j = arr.length -1;

            while (arr[j] <= arr[i])
                j--;

            swap(arr, i,j);

            reverseArray(arr, i+1,arr.length-1);
        }
    }

    private static int majorityElement(int[] arr){
        int candidate = 0;
        int count = 0;
        int n = arr.length;

        for(int num: arr){
            if(num == candidate){
                count++;
            }else if(count ==0){
                candidate = num;
                count =1;
            }else{
                count--;
            }
        }
        count =0;

        for (int i = 0; i <n ; i++) {
            if(arr[i] == candidate)
                count++;
        }

        if(count > n/2){
            return candidate;
        }
        return -1;
    }

    public static List<Integer> majorityElementII(int[] nums){
        List<Integer>result = new ArrayList<>();

        int candidate1 = -1;
        int candidate2 = -1;
        int count1 = 0;
        int count2 = 0;
        int n = nums.length;

        for (int num: nums){
            if(num == candidate1){
                count1++;
            }else if(num == candidate2){
                count2++;
            }else if(count1 == 0){
                candidate1 = num;
                count1 = 1;
            }else if(count2 == 0){
                candidate2 = num;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }
        }

        count1 = count2 =0;
        for (int i = 0; i < n ; i++) {
            if(nums[i] == candidate1)
                count1++;
            else if(nums[i] == candidate2)
                count2++;
        }

        if(count1 > n/3)
            result.add(candidate1);
        if(count2 > n/3)
            result.add(candidate2);

        return result;
    }
    private static void reverseArray(int[] arr, int start, int end){
        while(start < end){
            swap(arr,start++, end--);
        }
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static double pow(Double x, int n){
        long nn = n;
        if(nn < 0) nn = nn *-1;
        double ans = 1.0;

        while(nn > 0){
            if(nn % 2 ==1){
                ans = ans * x;
                nn = nn - 1;
            }else{
                x  = x * x;
                nn /= 2;
            }
        }
        if (n < 0)
            ans = (double) 1.0 / (double) ans;

        return ans;
    }
    private static void longestConsecutiveSequence(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        int maxElement = 0;
        int maxLen = 0;

        for(int num : nums)
            set.add(num);

        for (int i = 0; i < nums.length ; i++) {
            int element = nums[i];
            int prevElement = element -1;

            if(!set.contains(prevElement)){
                int j = element;
                while(set.contains(j)){
                    j++;
                }
                if(j-element > maxLen){
                    maxLen = j - element;
                    maxElement = element;
                }
            }
        }

        System.out.print("Longest Consecutive Sequence length for given array is: " + maxLen + " And Sequence is: ");
        for (int i = 0; i < maxLen; i++) {
            System.out.print(maxElement + i + " ");
        }
        System.out.println();
    }

    private static void MergeTwoSortedArrays(int[] arr1, int[] arr2, int m, int n){
        int p1 = m-1;
        int p2 = n-1;

        for (int p = m+n-1; p >=0 ; p--) {
            if(p2 <0) break;

            if(p1>=0 && arr1[p1] > arr2[p2]){
                arr1[p] = arr1[p1--];
            }else{
                arr1[p] = arr2[p2--];
            }
        }
    }
    private static void setMatrixElementToZeros(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[] zeroRow = new boolean[n];
        Arrays.fill(zeroRow, false);
        boolean[] zeroCol = new boolean[m];
        Arrays.fill(zeroCol, false);

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                if(matrix[i][j] == 0){
                    zeroRow[i] = zeroCol[j] = true;
                }
            }
        }

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                if(zeroRow[i] == true || zeroCol[j] == true)
                    matrix[i][j] = 0;
            }
        }
    }

    public static int[] missingAndRepeating(List<Integer> arr, int n){
        int[] result = new int[2];

        int arrSum = arr.stream().mapToInt(Integer::intValue).sum();
        int sum = IntStream.rangeClosed(1,n).sum();

        int arrSquareSum = arr.stream().mapToInt(Integer::intValue).map(m -> m * m).sum();
        int squareSum = IntStream.rangeClosed(1,n).map(m-> m * m).sum();

        return result;
    }
    public static void main(String[] args) {
        int number = 123421; //One Million Two Hundred Thirty Four Thousand Five Hundred Twelve
        String englishWord = numberToWord(number);
        System.out.println("Word representation for integer : " + number + " is: " + englishWord);

        System.out.println("============= Longest Substring Without Repeating Characters =============================");
        String s1 = "codingninjas";
        longestSubstringWithoutRepeatingCharacters(s1); //Longest substring without repeating character for String:codingninjas is :coding and length is: 6

        System.out.println("========================== String to Integer (atoi) ======================================");
        String num = "   4193 with words"; //Number representation for string : 4193 with words is : 4193
        //num = "-91283472332";
        myAtoi(num);

        System.out.println("============================== Integer to Roman ==========================================");
        number = 2343; //Roman representation for number : 2343 is : MMCCCXLIII
        //Output: "III"
        //Input: num = 58
        //Output: "LVIII"
        //Input: num = 1994
        //Output: "MCMXCIV"
        String answer = intToRoman(number);
        System.out.println("Roman representation for number : " + number + " is : " + answer);

        System.out.println("============================== Roman to Integer ==========================================");
        //String s = "LVIII";
        //Output: 58
        String s = "MCMXCIV";
        //Output: 1994
        //romanToInt(s);

        //String romanStr = "IV";
        System.out.print(romanToInt2(s) + "\n");

        System.out.println("========================== Two sum in Array Unique pair ==================================");
        //Elements would be repeating
        int[] arr = new int[]{2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};
        int target = 4;
        List<List<Integer>> lst = twoSum(arr, 0, arr.length - 1, target); //[[1, 3]]
        System.out.println(lst);

        System.out.println("========================== Three sum in Array Unique pair ==================================");
        arr = new int[]{2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};
        target = 14;
        List<List<Integer>> threeSumLst = threeSum(arr,  target);
        System.out.println(threeSumLst); //[[1, 5, 8], [1, 6, 7], [2, 4, 8], [2, 5, 7], [2, 6, 6], [3, 3, 8], [3, 4, 7], [3, 5, 6]]

        System.out.println("========================== Four sum in Array Unique pair ==================================");
        arr = new int[]{2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};
        target = 15;
        List<List<Integer>> fourSumLst = fourSum(arr, target);
        System.out.println(fourSumLst); //[[1, 1, 5, 8], [1, 1, 6, 7], [1, 2, 4, 8], [1, 2, 5, 7], [1, 2, 6, 6], [1, 3, 3, 8], [1, 3, 4, 7], [1, 3, 5, 6], [2, 2, 3, 8], [2, 2, 4, 7], [2, 2, 5, 6], [2, 3, 3, 7], [2, 3, 4, 6], [3, 3, 4, 5]]

        System.out.println("=================================== 3 Sum Closest ========================================");
        arr = new int[]{-1, 2, 1, -4};
        target = 1;
        int ans = threeSumClosest(arr, target);
        System.out.println("Closest 3 some for target: " + target + " is : " + ans); //Closest 3 some for target: 1 is : 2

        System.out.println("================================ Smallest Difference ======================================");
        int[] arr1 = new int[]{-1, 5, 10, 20, 28, 3};
        int[] arr2 = new int[]{26, 134, 135, 15, 17};
        int[] diff = smallestDifference(arr1, arr2); //smallest difference close to 0 is from element: [28, 26]
        System.out.println("smallest difference close to 0 is from element: " + Arrays.toString(diff));

        System.out.println("================================ Move element to end =====================================");
        List<Integer> ls = Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2);
        int toMove = 2;
        List<Integer> moveLst = moveElementToEnd(ls, toMove);
        System.out.println("After moving elements to end, Arrays would be : " + moveLst); //After moving elements to end, Arrays would be : [1, 3, 4, 2, 2, 2, 2, 2]

        System.out.println("================================ Monotonic Array =====================================");
        arr = new int[] {-1, -5, -10, -1100, -1100, -1101, -1102, -9001}; //True
        boolean isArrayMonotonic = isMonotonic(arr); //Array with elements : [1, 2, 3, 3, 2, 1] is Monotonic : false
        System.out.println("Array with elements : " + Arrays.toString(arr) + " is Monotonic : " + isArrayMonotonic);

        System.out.println("========================== strStr - KMP (Knuth-Morris-Pratt)======================");
        String hayStack = "hellowllen";
        String needle = "ll";
        strStr(hayStack, needle); //Occurance of pattern : ll in the String: hello is at : [2]

        System.out.println("=============================== strStr - Rabin Karp ======================================");
        hayStack = "hellowllen";
        needle = "ll";
        strStrByRabinKarp(hayStack, needle); //Occurance of pattern : ll in the String: hello is at : [2]


        System.out.println("=========================== Pattern Matching - Z Function =================================");
        zFuncForStringMatching(hayStack,needle);

        System.out.println("================================== Rotate Image ==========================================");
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        //Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("-------------------------------------------");
        printMatrix(matrix);

        System.out.println("============================== Rotate Matrix clockwise ===================================");
        //output
        //-7 -12 14 5 1 -7 18 -4
        //19 16 12 -4 12 -14 15 13
        //9 -8 1 -4 16 -20 -11 16
        //2 7 6 0 -17 -15 -19 -19
        //-14 6 20 12 13 12 -9 20
        //-12 -9 -11 18 11 -15 -4 8
        //-8 6 7 -8 -5 8 0 14
        //9 -19 12 -7 -15 11 -14 14

        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();

        int[] tmp1 = new int[]{-12, 14, 5, 1, -7, 18, -4, 13};
        List<Integer> matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);

        tmp1 = new int[]{-7, 12, -4, 12, -14, 15, -11, 16};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);

        tmp1 = new int[]{19, 16, -4, 16, -20, -15, -19, -19};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);
        tmp1 = new int[]{9, -8, 1, -17, 13, 12, -9, 20};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);
        tmp1 = new int[]{2, 7, 6, 0, 12, -15, -4, 8};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);
        tmp1 = new int[]{-14, 6, 20, -11, 18, 11, 0, 14};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);
        tmp1 = new int[]{-12, -9, 6, 7, -8, -5, 8, 14};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);
        tmp1 = new int[]{-8, 9, -19, 12, -7, -15, 11, -14};
        matLst = Arrays.stream(tmp1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        mat.add((ArrayList<Integer>) matLst);

        printMatrix(mat);
        rotateMatrix(mat);
        System.out.println();
        printMatrix(mat);

        System.out.println("================================== Group Anangram ========================================");
        //Input: strs = ["eat","tea","tan","ate","nat","bat"]
        //Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        String[] anagramInput = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = groupAnagrams(anagramInput);
        System.out.println(res);

        System.out.println("============================= Compare Version Numbers ====================================");
        //Input: version1 = "1.01", version2 = "1.001"
        //Output: 0
        //Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
        //Example 2:
        //
        //Input: version1 = "1.0", version2 = "1.0.0"
        //Output: 0
        //Explanation: version1 does not specify revision 2, which means it is treated as "0".
        //Example 3:
        //
        //Input: version1 = "0.1", version2 = "1.1"
        //Output: -1
        //Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
        String version1 = "1.21";
        String version2 = "1.2";
        int versions = compareVersion(version1, version2); // 1
        System.out.println("Comparing version v1 : " + version1 + " and V2: " + version2 + ". Comparison value is: " + versions);

        System.out.println("=================================== Missing Number =======================================");
        //Input: nums = [3,0,1]
        //Output: 2
        //Input: nums = [0,1]
        //Output: 2
        //Input: nums = [9,6,4,2,3,5,7,0,1]
        //Output: 8
        int[] nums = {9,0,4,2,3,5,7,8,1}; //Missing 6
        int missingNum = missingNumber(nums);
        System.out.println("Missing number in array is: " + missingNum);

//        nums = new int[]{9,6,4,2,3,5,7,1};
//        findMissingElement(nums);

        System.out.println("================================= Most Common Word =======================================");
        //Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
        //"Bob. hIt, baLl"
        //["bob", "hit"]
        String para = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        System.out.println(mostCommonWord(para, banned));

        System.out.println("================================= Reorder Log Files =======================================");

        //Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
        //Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        String[] orderedLogs = reorderLogFiles(logs);
        System.out.println(Arrays.toString(orderedLogs));

        System.out.println("============================== Product Except Self =======================================");
        //Input: nums = [1,2,3,4]
        //Output: [24,12,8,6]
        //Input: nums = [-1,1,0,-3,3]
        //Output: [0,0,9,0,0]
        int[] input = {1, 2, 3, 4};
        int[] product = productExceptSelf(input);
        System.out.println(Arrays.toString(product));

        System.out.println("============================== First Unique Character ====================================");
        //Input: s = "leetcode"
        //Output: 0
        //Example 2:
        //
        //Input: s = "loveleetcode"
        //Output: 2
        //Example 3:
        //
        //Input: s = "aabb"
        //Output: -1
        String inputChar = "loveleetcode";
        firstUniqChar(inputChar);

        System.out.println("================================= Trapping Rain Water ====================================");
        //Input: height = [4,2,0,3,2,5]
        //Output: 9
        int[] height = {4,2,0,3,2,5};
        trap(height);

        System.out.println("================================== Array ZigZag traversal ================================");
        System.out.println("Input array : [ ");
        System.out.println("[1, 3, 4, 10]");
        System.out.println("[2, 5, 9, 11]");
        System.out.println("[6, 8, 12, 15]");
        System.out.println("[7, 13, 14, 16]");
        System.out.println("]");

        List<List<Integer>> zigZagList = new ArrayList<>();
        List<Integer> l;
        l = Arrays.asList(1, 3, 4, 10);
        zigZagList.add(l);
        l = Arrays.asList(2, 5, 9, 11);
        zigZagList.add(l);
        l = Arrays.asList(6, 8, 12, 15);
        zigZagList.add(l);
        l = Arrays.asList(7, 13, 14, 16);
        zigZagList.add(l);
        List<Integer> newList = zigzagTraverse(zigZagList);
        System.out.println("Output is: " + newList.toString());

        System.out.println("================================== Longest Peak in Array ================================");
        arr = new int[]{5, 4, 3, 2, 1, 2, 10, 12, -3, 5, 6, 7, 10};
        //Output: 5
        // [1, 2, 3, 4, 5, 6, 10, 100, 1000]
        //Output 0
        int peak = longestPeak(arr);
        System.out.println("Longest Peak length in Array: " + Arrays.toString(arr) + " is :" + peak);
//
//        System.out.println("=============================== First Duplicate Value ====================================");
//
//        arr = new int[]{2, 1, 5, 2, 3, 3, 4}; //Output 2
//        //arr = new int[] {2, 1, 5, 3, 3, 2, 4}; //Output 3
//        int duplicate = firstDuplicateValue(arr);
//        System.out.println("First duplicate value from array : " + Arrays.toString(arr) + " is : " + duplicate);
//
//        //arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}; //Output {2, 1, 5, 2, 3, 3, 4}
//        arr = new int[]{-1, -1, -1, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 5, 6};
//        duplicate = removeAllDuplicates(arr);
//        System.out.println("Duplicate value from array : " + Arrays.toString(arr) + " is : " + duplicate);

        System.out.println("================================== Merge Intervals =======================================");
        //Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        //Output: [[1,6],[8,10],[15,18]]
        //Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
        //Example 2:
        //
        //Input: intervals = [[1,4],[4,5]]
        //Output: [[1,5]]
        //Explanation: Intervals [1,4] and [4,5] are considered overlapping.
        //int[][] intervals = new int[][]{{1,4},{4,5}};
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        mergeOverlappingIntervals(intervals);

        System.out.println("=============================== Search in 2D Matrix ======================================");

        matrix = new int[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        SearchIn2DMATRIXOptimal(matrix, 8);

        System.out.println("================================= Sort Array  for 0/1/2 ==================================");
        int[] ar = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}; //Output : 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2
        sortAnArrayOf0_1_2(ar);
        System.out.println("After sorted array is : " + Arrays.toString(ar));

        System.out.println("================================= Next permutation =======================================");
        int[] numForPerm = {1, 3, 5, 4, 2}; //14235
        nextPermutation(numForPerm);
        System.out.println("Next permutation for given number is : " + Arrays.toString(numForPerm));

        System.out.println("============================ Majority of element in Array ================================");
        int[] numsArr = {2, 11, 11, 4, 4}; //-1
        System.out.println("Majority element from given Array is: " + majorityElement(numsArr));

        numsArr = new int[]{-1, -1, -1}; //[-1]
        List<Integer> majorElements = majorityElementII(numsArr);
        System.out.println("Majority element II from given Array is: " + majorElements.toString());

        System.out.println("======================================== POW =============================================");
        System.out.println("Power of 2 at 10 is  " + (int) pow(2.0, 10)); //Power of 2 at 10 is  1024
//
        System.out.println("============================= Longest Consecutive Sequence ===============================");
        int[] seq = {100, 4, 200, 1, 3, 2};
        longestConsecutiveSequence(seq);

        System.out.println("================================ Merge two sorted Array ==================================");
        int[] Arr1 = {3, 6, 9, 0, 0,};
        int[] Arr2 = {4, 10,};
        MergeTwoSortedArrays(Arr1, Arr2, 3, 2);
        System.out.println("Main Arry after merging is: " + Arrays.toString(Arr1));

        System.out.println("============================== Set matrix element to Zero ================================");
        int[][] zeroMat = {{0, 2, 3},
                {1, 0, 3},
                {1, 2, 0}};
        printMatrix(zeroMat);
        setMatrixElementToZeros(zeroMat);
        System.out.println();
        printMatrix(zeroMat);

        System.out.println("============================ Missing and duplicate element ===============================");
        int n = 15;
        //int[] missingArray = {4, 3, 2, 5, 6, 7, 8, 8, 9, 10, 11, 14, 13, 15, 12}; //1 8
        int[] missingArray = {4, 3, 6, 2, 1, 1}; //1 5
        n=6;
        int[] an = missingAndRepeating(Arrays.stream(missingArray).boxed().collect(Collectors.toList()), n);
        System.out.println("Missing number from given Array is: " + an[0] + " and Duplicate number is : " + an[1]);

//        System.out.println("=================================== Max Product SubArray =================================");
//        //int Arr[] = {2, 3, 4, 5, -1, 0};// Output: 120
//        int Arr[] = {6, -3, -10, 0, 2}; //Output: 180
//        System.out.println("Maximum product subArray from given Array: " + Arrays.toString(Arr) + " is: " + maxProductSubArray(Arr, Arr.length));
//
//        String[] justifyWords = {"This", "is", "an", "example", "of", "text", "justification."};
//        int masWidth = 16;
//        List<String> justifiedWords = textJustify(justifyWords, 16);
//        System.out.println("Justified words are : " + justifiedWords);
//
//        System.out.println("================================ String Abbrecviation check ===============================");
//        //You can perform the following operations on the string, :        //
//        //Capitalize zero or more of 's lowercase letters.
//        //Delete all of the remaining lowercase letters in .
//        //Given two strings,  and , determine if it's possible to make  equal to  as described. If so, print YES on a new line. Otherwise, print NO.
//        //For example, given a= AbcDE and b=ABDE, in a we can convert b and delete  to match . If a= AbcDE and b= AFDE, matching is not possible because letters may only be capitalized or discarded, not changed.
//        String str1 = "daBcd";
//        String str2 = "ABC";
//        System.out.println("String abbreviation check: " + abbreviation(str1,str2));
//
//        System.out.println("================================== String rotation check ==================================");
//        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
//        for (String[] pair : pairs) {
//            String word1 = pair[0];
//            String word2 = pair[1];
//            boolean is_rotation = isRotation(word1, word2);
//            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
//        }
//
//        System.out.println("================================== Encode/Decode String ==================================");
//        String[] string = {"lintcode","codelove","loveyou","youalot"};
//        String r = encode(string);
//        System.out.println("Encoded String is: "+ r);
//        String[] decodedStr = deCode(r);
//        System.out.println("Decoded String Array is: "+ Arrays.toString(decodedStr));
//
//        System.out.println("================================= Search in Rotated Array ================================");
//        nums = new int[]{4,5,6,7,0,1,2};
//        target = 0;
//        int result = searchInRotatedArray(nums,target);
//        System.out.println(result == -1 ? "Element: " + target + " not found in Array: " + Arrays.toString(nums) : "Element: " + target + " found at: " + result + " in Rotated Array : "+ Arrays.toString(nums));
//
//        System.out.println("============================== Find minimum in Rotated Array =============================");
//        nums = new int[]{3,4,5,1,2};
//        result = findMinimumInRotatedArray(nums);
//        System.out.println("Minimum Element in rotated array: " + Arrays.toString(nums) + " is : " + result);
//
//        System.out.println("==================================== String shifting =====================================");
//        s ="abcdefg";
//        int[][] shift = { {1, 1}, {1, 1}, {0, 2}, {1, 3} };
//        System.out.println("String before shift operation is :" + s);
//        stringShift(s,shift); //efgabcd
//
//        System.out.println("======== Circularly shifting each character to the right by respective frequencies =========");
//        String S = "geeksforgeeksyyyy";
//        System.out.println("String before Circular shift operation is :" + S);
//        addFrequencyToCharacter(S);

    }
}

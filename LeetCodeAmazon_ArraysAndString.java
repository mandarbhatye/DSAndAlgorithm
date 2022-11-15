
package Amazon;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeAmazon_ArraysAndString {

    private static void longestSubstringWithoutRepeatingCharacters(String str) {
        int i = -1;
        int j = -1;
        int len = 0;
        Map<Character, Integer> charToFreqMap = new HashMap<>();

        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;

            //Acquiring Char and put them in Map for duplicate check.
            while (i < str.length() - 1) {
                flag1 = true;
                i++;

                Character ch = str.charAt(i);
                charToFreqMap.put(ch, charToFreqMap.getOrDefault(ch, 0) + 1);

                if (charToFreqMap.get(ch) == 2) {
                    break;
                } else {
                    int newLen = i - j;
                    if (newLen > len) {
                        len = newLen;
                    }
                }
            }
            //Releasing Char and decrease count of map
            while (j < i) {
                flag2 = true;
                j++;

                Character ch = str.charAt(j);
                charToFreqMap.put(ch, charToFreqMap.getOrDefault(ch, 0) - 1);
                if (charToFreqMap.get(ch) == 1) {
                    break;
                }
            }
            if (flag1 == false && flag2 == false) {
                break;
            }
        }
        System.out.println("Longest substring without repeating character for String:" + str + " length is: " + len);
    }

    private static void longestSubstringWithoutRepeatingCharactersOptimized(String str) {
        Map<Character, Integer> charToFreqMap = new HashMap<>();
        int len = 0;
        String answer = "";

        for (int j = 0, i = 0; j < str.length(); j++) {
            Character ch = str.charAt(j);
            if (charToFreqMap.containsKey(ch))
                i = Math.max(charToFreqMap.get(ch), i);

            int newLen = j - i + 1;
            len = Math.max(len, newLen);

            String ans = str.substring(i, j + 1);
            if (ans.length() > answer.length())
                answer = ans;

            charToFreqMap.put(ch, j + 1);
        }
        System.out.println("Longest substring without repeating character for String:" + str + " is :" + answer + " and length is: " + len);
    }

    private static void myAtoi(String str) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = str.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && str.charAt(index) == ' ') {
            index++;
        }
        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && str.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && str.charAt(index) == '-') {
            sign = -1;
            index++;
        }


        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(str.charAt(index))) {
            int digit = str.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if ((result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                int newNum = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                System.out.println("Number Overflow for string : " + str + " is : " + newNum);
            }
            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        System.out.println("Number representation for string : " + str + " is : " + sign * result);
    }

    public static String intToRoman(int num) {
        final String[] thousands = {"", "M", "MM", "MMM"};
        final String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        final String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        final String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }

    public static int romanToInt2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1) {
                if (s.charAt(i) == 'I' && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')) {
                    result--;
                    continue;
                }
                if (s.charAt(i) == 'X' && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                    result -= 10;
                    continue;
                }
                if (s.charAt(i) == 'C' && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')) {
                    result -= 100;
                    continue;
                }
            }
            result += map.get(s.charAt(i));
        }
        return result;
    }

    public static void romanToInt(String s) {
        Map<String, Integer> values = new HashMap<>();
        values.put("I", 1);
        values.put("V", 5);
        values.put("X", 10);
        values.put("L", 50);
        values.put("C", 100);
        values.put("D", 500);
        values.put("M", 1000);
        values.put("IV", 4);
        values.put("IX", 9);
        values.put("XL", 40);
        values.put("XC", 90);
        values.put("CD", 400);
        values.put("CM", 900);

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                String doubleSymbol = s.substring(i, i + 2);
                // Check if this is the length-2 symbol case.
                if (values.containsKey(doubleSymbol)) {
                    sum += values.get(doubleSymbol);
                    i += 2;
                    continue;
                }
            }
            String singleSymbol = s.substring(i, i + 1);
            sum += values.get(singleSymbol);
        }
        System.out.println("Number representation for Roman letter : " + s + " is : " + sum);
    }

    private static List<List<Integer>> twoSum(int[] arr, int startIdx, int endIndex, int target) {
        List<List<Integer>> finalAnswer = new ArrayList<>();
        Arrays.sort(arr);
        int left = startIdx;
        int right = endIndex;

        while (left < right) {
            if (left != 0 && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }
            if (arr[left] == arr[right]) {
                left++;
                continue;
            }
            int sum = arr[left] + arr[right];
            if (sum == target) {
                List<Integer> lst = new ArrayList<>();
                lst.add(arr[left++]);
                lst.add(arr[right--]);
                finalAnswer.add(lst);
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return finalAnswer;
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
        List<List<Integer>> res = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return res;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        long average_value = target / k;

        // We cannot obtain a sum of target if the smallest value
        // in nums is greater than target / k or if the largest
        // value in nums is smaller than target / k.
        if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return res;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        //[[1, 5, 8], [1, 6, 7], [2, 4, 8], [2, 5, 7], [2, 6, 6], [3, 3, 8], [3, 4, 7], [3, 5, 6]]
        for (int i = start; i < nums.length; ++i) {
            if (i != start && nums[i] == nums[i - 1]) continue;

            for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                res.add(new ArrayList<>(Arrays.asList(nums[i])));
                res.get(res.size() - 1).addAll(subset);
            }
        }
        return res;
    }

    public static List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;

        while (lo < hi) {
            int currSum = nums[lo] + nums[hi];
            if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                ++lo;
            } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }

        return res;
    }

    private static int threeSumClosest(int[] arr, int target) {
        Arrays.sort(arr);
        int diff = Integer.MAX_VALUE;
        int answer = 0;

        for (int i = 0; i < arr.length; i++){
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right){
                int sum = arr[i] + arr[left] + arr[right];
                int difference = Math.abs(sum - target);
                if (sum == target) {
                    return target;
                } else if (difference < diff) {
                    diff = difference;
                    answer = sum;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return answer;
    }

    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        int[] result = new int[1];

        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int left = 0;
        int right = 0;

        int smallest = Integer.MAX_VALUE;

        while (left < arrayOne.length && right < arrayTwo.length) {
            int diff = Math.abs(arrayOne[left] - arrayTwo[right]);

            if (diff < smallest) {
                smallest = diff;
                result = new int[]{arrayOne[left], arrayTwo[right]};
            }

            if (arrayOne[left] < arrayTwo[right]) {
                left++;
            } else {
                right++;
            }
        }
        return result;
    }

    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int j = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != toMove) {
                array.set(j++, array.get(i));
                array.set(i, toMove);
            }
        }
        return array;
    }

    public static boolean isMonotonic(int[] array) {
        if (array.length <= 1) return true;

        boolean isDecreasing = array[0] > array[array.length - 1];

        for (int i = 1; i < array.length; i++) {
            //If decreasing order then second element should be smaller than first. If not then return false as non-Monotonic
            if (isDecreasing && array[i] > array[i - 1]) {
                return false;
            }
            //If non-decreasing(increasing) order then second element should be greater than first. If not then return false as non-Monotonic
            if (!isDecreasing && array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //string Match By Rabin Karp. We can also use KMP/Z algorithm.
    private static void strStr(String haystack, String needle) {
        ArrayList<Integer> occurance = new ArrayList<>();
        int prime = 13;
        int mod = 998242353;
        int m = needle.length();
        int n = haystack.length();

        long[] primePower = new long[n];
        primePower[0] = 1;
        for (int i = 1; i < n; i++) {
            primePower[i] = (primePower[i - 1] * prime) % mod;
        }

        long[] hash = new long[n + 1];
        for (int i = 0; i < n; i++) {
            hash[i + 1] = (hash[i] + (haystack.charAt(i) - 'A' + 1) * primePower[i]) % mod;
        }

        long patternHash = 0;
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash + (needle.charAt(i) - 'A' + 1) * primePower[i]) % mod;
        }

        for (int i = 0; i + m - 1 < n; i++) {
            long currentHash = (hash[i + m] + mod - hash[i]) % mod;

            if (currentHash == patternHash * primePower[i] % mod) {
                occurance.add(i);
            }
        }
        System.out.println("Occurance of pattern : " + needle + " in the String: " + haystack + " is at : " + occurance.toString());
    }

    //Calculate proper prefix array and subtract length of str from last element of LPS array.
    static int minimumCharactersForPalindrome(String str) {
        String revStr = reverse(str);
        String concat = str + "$" + revStr;
        int[] lps = calculatePrefix(concat);
        return (str.length() - lps[lps.length - 1]);
    }


    static int[] calculatePrefix(String str) {
        int len = str.length();
        int[] lps = new int[len];
        int i = 1;
        int j = 0;
        lps[0] = 0;

        while (i < str.length()) {
            if (str.charAt(i) == str.charAt(j)) {
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    private static void rotate(int[][] matrix) {
        //Transpose Matrix
        transposeMatrix(matrix);
        //Reverse rows
        reverseMatrix(matrix);

//        transposeMatrix(matrix);
//        reverseMatrix(matrix);
    }

    private static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void reverseMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix.length - 1;

            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    //-7 -12 14 5 1 -7 18 -4
    //19 16 12 -4 12 -14 15 13
    //9 -8 1 -4 16 -20 -11 16
    //2 7 6 0 -17 -15 -19 -19
    //-14 6 20 12 13 12 -9 20
    //-12 -9 -11 18 11 -15 -4 8
    //-8 6 7 -8 -5 8 0 14
    //9 -19 12 -7 -15 11 -14 14
    public static void rotateMatrixHelper(ArrayList<ArrayList<Integer>> mat, int rowStart, int colStart, int rowEnd, int colEnd) {
        // Base Condition
        if (rowStart >= rowEnd || colStart >= colEnd) return;

        if (rowStart >= rowEnd - 1 || colStart >= colEnd - 1) return;

        int previous = mat.get(rowStart + 1).get(colStart);
        int current;

        // Move elements of first row from the remaining rows
        for (int i = colStart; i < colEnd; i++) {
            current = mat.get(rowStart).get(i);
            mat.get(rowStart).set(i, previous);
            previous = current;
        }

        rowStart++;
        // Move elements of last column from the remaining columns
        for (int i = rowStart; i < rowEnd; i++) {
            current = mat.get(i).get(colEnd-1);
            mat.get(i).set(colEnd - 1, previous);
            previous = current;
        }

        colEnd--;
        // Move elements of last row from the remaining rows
        if (rowStart < rowEnd) {
            for (int i = colEnd - 1; i >= colStart; i--) {
                current = mat.get(rowEnd - 1).get(i);
                mat.get(rowEnd - 1).set(i, previous);
                previous = current;
            }
        }

        rowEnd--;
        // Move elements of first column from the remaining rows
        if (colStart < colEnd) {
            for (int i = rowEnd - 1; i >= rowStart; i--) {
                current = mat.get(i).get(colStart);
                mat.get(i).set(colStart, previous);
                previous = current;
            }
        }

        colStart++;
        // Recursively rotate inner rings
        rotateMatrixHelper(mat, rowStart, colStart, rowEnd, colEnd);

    }

    public static void rotateMatrix(ArrayList<ArrayList<Integer>> mat, int n, int m) {
        rotateMatrixHelper(mat, 0, 0, n, m);
    }
    private static List<List<String>> groupAnagrams_v2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> bigMap = new HashMap<>();

        for (String word : strs) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String sortedLetters = String.valueOf(letters);

            if (bigMap.containsKey(sortedLetters)) {
                List lst = bigMap.get(sortedLetters);
                lst.add(word);
            } else {
                List<String> l = new ArrayList<>();
                l.add(word);
                bigMap.put(sortedLetters, l);
            }
        }
        for (List<String> ls : bigMap.values()) {
            result.add(ls);
        }
        return result;
    }

    private static int compareVersion(String version1, String version2) {
        String[] VersionStr1 = version1.split("\\.");
        String[] versionStr2 = version2.split("\\.");
        int n1 = VersionStr1.length, n2 = versionStr2.length;
        // compare versions
        int i1, i2;
        for (int i = 0; i < Math.max(n1, n2); ++i) {
            i1 = i < n1 ? Integer.parseInt(VersionStr1[i]) : 0;
            i2 = i < n2 ? Integer.parseInt(versionStr2[i]) : 0;
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        // the versions are equal
        return 0;
    }

    private static int missingNumber_GaussFormula(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    //3,0,1
    //Missing number by changing Array values
    private static int missingNumber(int[] nums) {
        boolean containsOne = false;
        int n = nums.length;
        //Remove numbers which are negative and which are beyond ranges. Replace them with one. Also check existance of One
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                containsOne = true;
            } else if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }
        //If one is not present then return itself
        if (containsOne == false) return 1;

        //Mark index with negative
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]);
            nums[index - 1] = -Math.abs(nums[index - 1]);
        }
        //Find out first positive number present and return it's next index.
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private static String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> bannedWord = new HashSet<>();
        for (String word : banned) {
            bannedWord.add(word);
        }

        HashMap<String, Integer> wordCountMap = new HashMap<>();
        //String[] st = paragraph.replaceAll("\\W+", " ").trim().toLowerCase().split(" ");
        //String[] temp = paragraph.replaceAll("[^a-zA-Z]", " ").toLowerCase().split(" ");

        for (String word : paragraph.replaceAll("[^a-zA-Z]", " ").toLowerCase().split(" ")) {
            if (!bannedWord.contains(word) && !word.isEmpty()) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }
        int max = Collections.max(wordCountMap.values());
        Optional<String> data = wordCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == max)//
                .map(Map.Entry::getKey)//entry -> entry.getKey()
                .collect(Collectors.toList()).stream().findFirst();

        return data.orElse("");
    }


    private static String[] reorderLogFiles(String[] logs) {
        Map<Boolean, List<String>> patritionLog = Arrays.stream(logs)
                .collect(Collectors.partitioningBy(entry -> Character.isDigit(entry.split(" ")[1].charAt(0))));

        List<String> digitLog = patritionLog.get(true);
        List<String> letterLog = patritionLog.get(false);

        letterLog.sort((log1, log2) -> {
            int idx1 = log1.indexOf(" ");
            String id1 = log1.substring(0, idx1);
            String mainStr1 = log1.substring(idx1 + 1);

            int idx2 = log2.indexOf(" ");
            String id2 = log2.substring(0, idx2);
            String mainStr2 = log2.substring(idx2 + 1);

            int value = mainStr1.compareTo(mainStr2);
            if (value == 0) return id1.compareTo(id2);
            return value;
        });

        letterLog.addAll(digitLog);

        return letterLog.toArray(new String[0]);
    }

    //Array: [1,2,3,4]
    //[1,1,2,6] leftProduct - not need to generate it as we will use it dynamically while looping.
    //[24,12,4,1] rightProduct
    //Output: [24,12,8,6]
    private static int[] productExceptSelf(int[] array) {
        int product = 1;
        int[] rightProduct = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            rightProduct[i] = product;
            product *= array[i];
        }

        //int[] result = new int[array.length];
        product = 1;
        for (int i = 0; i < array.length; i++) {
            rightProduct[i] = rightProduct[i] * product;
            product *= array[i];
        }

        return rightProduct;
    }

    static String[] numbers = {"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine "};
    static String[] one_ten = {"Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    static String[] tens = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    static String[] hundreds = {"", "Thousand ", "Million ", "Billion ", "Trillion "};

    private static String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        List<String> number = new ArrayList();

        for (int i = num; i != 0; i /= 1000) {
            int d = i % 1000;
            number.add(word(d));
        }

        String wordNumber = "", wo = "";

        for (int i = 0; i < number.size(); i++) {
            String s = number.get(i);
            if (s.equals("") || s.isEmpty())
                continue;
            else
                wo = number.get(i) + " " + hundreds[i];
            wordNumber = wo + wordNumber;
            wo = "";
        }
        return wordNumber.trim();
    }

    private static String word(int number) {
        String ans = "";
        int digitCounter = 1;
        int n = 0;
        for (int j = number; j != 0; j /= 10) {
            int i = j % 10;
            n = n * 10 + i;
            if (digitCounter == 1)
                ans += numbers[i];
            else if (digitCounter == 2) {
                if (n != 0 && n % 10 == 1)
                    ans = one_ten[n / 10];
                else if (i >= 2) {
                    ans = tens[i] + ans;
                }
            } else {
                if (i == 0)
                    continue;
                else {
                    ans = numbers[i] + "Hundred " + ans;
                }
            }
            digitCounter++;
        }
        return ans.trim();
    }

    public static void firstUniqChar(String s) {
        int[] count = new int[26];
        int result;
        // build char count bucket : <charIndex, count>
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            count[index]++;
        }
        // find the index
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (count[index] == 1) {
                System.out.println("First Unique Character in a String: " + s + " is: " + i);
                return;
            }
        }
        System.out.println("No Unique Character in a String: " + s);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //19-07-2022
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Trapping Rain Water
    //Input: height = [4,2,0,3,2,5]
    //Output: 9
    public static void trap(int[] height) {
        // time : O(n)
        // space : O(1)
        if (height.length == 0) {
            System.out.println("Invalid height");
            return;
        }
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left < right) {
            if (height[left] > leftMax) {
                leftMax = height[left];
            }
            if (height[right] > rightMax) {
                rightMax = height[right];
            }
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - height[right]);
                right--;
            }
        }
        System.out.println(ans);
    }

    public static long getTrappedWater(long[] arr, int n) {

        if (arr.length == 0) {
            System.out.println("Invalid height");
            return 0;
        }
        int left = 0, right = arr.length - 1;
        long leftMax = 0, rightMax = 0;
        long ans = 0;
        while (left < right) {
            if (arr[left] > leftMax) {
                leftMax = arr[left];
            }
            if (arr[right] > rightMax) {
                rightMax = arr[right];
            }
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - arr[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - arr[right]);
                right--;
            }
        }
        return ans;
        // Base case.
//        if (n == 0) {
//            return 0;
//        }
//
//        // Result.
//        long totalWaterStored = 0L;
//
//        // Storing leftMax and rightMAx.
//        long[] leftMax = new long[n];
//        long[] rightMax = new long[n];
//
//        leftMax[0] = arr[0];
//
//        // Filling leftMax.
//        for (int i = 1; i < n; i++) {
//            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
//        }
//
//        rightMax[n - 1] = arr[n - 1];
//
//        // Filling rightMax.
//        for (int i = n - 2; i >= 0; i--) {
//            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
//        }
//
//        // Calculate result.
//        for (int i = 1; i < n - 1; i++) {
//            totalWaterStored += Math.min(rightMax[i], leftMax[i]) - arr[i];
//        }
//
//        return totalWaterStored;
    }

    //Input array : [
    //[1, 3, 4, 10]
    //[2, 5, 9, 11]
    //[6, 8, 12, 15]
    //[7, 13, 14, 16]
    //]
    public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
        List<Integer> result = new ArrayList<>();

        int lastRow = array.size() - 1;
        int lastColumn = array.get(0).size() - 1;

        int row = 0;
        int col = 0;
        boolean down = true;

        while (row <= lastRow && col <= lastColumn) {
            result.add(array.get(row).get(col));

            if (down) {
                if (row == lastRow) {
                    col += 1;
                    down = false;
                } else if (col == 0) {
                    row += 1;
                    down = false;
                } else {
                    row += 1;
                    col -= 1;
                }

            } else {
                if (col == lastColumn) {
                    row += 1;
                    down = true;
                } else if (row == 0) {
                    col += 1;
                    down = true;
                } else {
                    row -= 1;
                    col += 1;
                }
            }
        }
        return result;
    }

    public static int longestPeak(int[] array) {
        int longestPeak = 0;
        int i = 1;
        while (i < array.length - 1) {
            boolean isPeak = array[i - 1] < array[i] && array[i + 1] < array[i];
            if (!isPeak) {
                i++;
                continue;
            }

            int leftIndex = i - 2;
            while (leftIndex >= 0 && array[leftIndex] < array[leftIndex + 1]) {
                leftIndex--;
            }

            int rightIndex = i + 2;
            while (rightIndex < array.length && array[rightIndex] < array[rightIndex - 1]) {
                rightIndex++;
            }

            int peakLength = rightIndex - leftIndex - 1;
            longestPeak = Math.max(peakLength, longestPeak);
            i = rightIndex;
        }
        return longestPeak;
    }

    public static int firstDuplicateValue(int[] array) {
        //Brut Force: O(n^2) Time /O(1) space
//        int minimumIndex = Integer.MAX_VALUE;
//
//        for(int i=0 ; i< array.length; i++){
//            for(int j = i+1; j< array.length; j++){
//                if(array[i] == array[j]){
//                    minimumIndex = Math.min(minimumIndex, j);
//                }
//            }
//        }
//        if(minimumIndex == Integer.MAX_VALUE)
//            return -1;
//        else
//            return array[minimumIndex];

        //O(n) Time / O(n) space
//        HashSet<Integer> set= new HashSet<>();
//        for(int i =0 ; i< array.length; i++){
//            if(set.contains(array[i]))
//                return array[i];
//            set.add(array[i]);
//        }
//        return -1;

        //{2, 1, 5, 2, 3, 3, 4}; //Output 2
        //O(n) Time/ o(1) Space - Most Optimal solution but modifies Array. If given Array is immutable then we can't use this approach and better approach would be XOR.
        for (int i = 0; i < array.length; i++) {
            int absValue = Math.abs(array[i]);
            if (array[absValue - 1] < 0)
                return absValue;

            array[absValue - 1] *= -1;
        }
        return -1;
    }

    //Output : -1, 0, 2, 3, 4, 5, 6, 2, 2, 3, 3, 3, 4, 5, 6
    public static int removeAllDuplicates(int[] nums) {
        int i = nums.length > 0 ? 1 : 0;
        for (int n : nums)
            if (n > nums[i - 1])
                nums[i++] = n;
        return i;
    }

    //Remove Duplicate and return length of new Array.
    // arr = new int[]{-1, -1, -1, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 5, 6};
    //Output : -1, 0, 2, 3, 4, 5, 6, 2, 2, 3, 3, 3, 4, 5, 6
    public static int removeDuplicates(List<Integer> arr, int n) {
        int i = 0;

        for (int j = 1; j < n; j++) {
            if (arr.get(j) != arr.get(i)) {
                i++;
                arr.set(i, arr.get(j));
            }
        }
        return i + 1;
    }

    //Input: {{1,3},{2,6},{8,10},{15,18}};
    //[1, 6] [8, 10] [15, 18]
    public static void mergeOverlappingIntervals(int[][] intervals) {
        if (intervals.length < 2) return;
        List<int[]> result = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        result.add(intervals[0]);

        int idx = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (result.get(idx)[1] >= intervals[i][0]) {
                result.set(idx, new int[]{result.get(idx)[0], Math.max(result.get(idx)[1], intervals[i][1])});
            } else {
                idx++;
                //result.add(new int[]{intervals[i][0], intervals[i][1]});
                result.add(intervals[i]);
            }
        }

        int[][] newInt = result.toArray(new int[result.size()][]);

        result.stream().forEach(a -> System.out.print(Arrays.toString(a) + " "));
        System.out.println();
    }

    //arr = new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
    //Output [4, 9]
    public static int[] subarraySort(int[] array) {
        int[] result = new int[2];
        int len = array.length;
        int minElement = Integer.MAX_VALUE;
        int maxElement = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                if (array[i] > array[i + 1]) {
                    minElement = Math.min(minElement, array[i]);
                    maxElement = Math.max(maxElement, array[i]);
                }
            } else if (i == len - 1) {
                if (array[i] < array[i - 1]) {
                    minElement = Math.min(minElement, array[i]);
                    maxElement = Math.max(maxElement, array[i]);
                }
            } else {
                if (array[i] > array[i + 1] || array[i] < array[i - 1]) {
                    minElement = Math.min(minElement, array[i]);
                    maxElement = Math.max(maxElement, array[i]);
                }
            }
        }

        if (minElement == Integer.MAX_VALUE || maxElement == Integer.MIN_VALUE) {
            return new int[]{-1, -1};
        }

        int i = 0;
        int j = 0;
        for (i = 0; i < array.length && array[i] <= minElement; i++) {
        }

        for (j = len - 1; j >= 0 && array[j] >= maxElement; j--) {
            System.out.println("");
        }

        result[0] = i;
        result[1] = j;

        return result;
    }

    public static int lineThroughPoints(int[][] points) {
        int maxLinePoints = 1;

        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> slopeToCountMap = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                //y2-y1
                double deltaY = ((double) (points[j][1] - points[i][1]));
                //x2-x1
                double deltaX = ((double) (points[j][0] - points[i][0]));
                double slope = (double) deltaY / deltaX;

                if (slopeToCountMap.containsKey(slope)) {
                    slopeToCountMap.put(slope, slopeToCountMap.getOrDefault(slope, 0) + 1);
                } else {
                    slopeToCountMap.put(slope, 2);
                }
            }
            //int newMax = Collections.max(slopeToCountMap.values(), Comparator.comparingDouble(v -> v));
            int maxCount = slopeToCountMap.values().stream().max(Comparator.comparingDouble(v -> v)).orElse(0);

            maxLinePoints = Math.max(maxLinePoints, maxCount);
        }
        return maxLinePoints;
    }

    public static int[] largestRange(int[] array) {
        // Write your code here.
        Map<Integer, Boolean> numMap = new HashMap<>();
        int[] ans = new int[2];
        int longestLength = 0;

        for (int val : array) {
            numMap.put(val, true);
        }

        for (int i = 0; i < array.length; i++) {
            if (!numMap.containsKey(array[i])) continue;

            numMap.put(array[i], false);

            int currentLen = 0;
            int left = array[i];
            int right = array[i];

            while (numMap.containsKey(left)) {
                numMap.put(left, false);
                currentLen += 1;
                left--;
            }

            while (numMap.containsKey(right)) {
                numMap.put(right, false);
                currentLen += 1;
                right++;
            }

            if (currentLen > longestLength) {
                longestLength = currentLen;
                ans[0] = left + 1;
                ans[1] = right - 1;
            }
        }
        return ans;
    }

    public static String reverseWords(String s) {
        String res = "";
        String[] words = s.split(" ");
        for (String st : words) {
            res += reverse(st) + " ";
        }
        return res.trim();
    }

    public static String reverse(String s) {
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--)
            rev += s.charAt(i);

        return rev;
    }

    //O(n)/ O(1)
    static void     SearchIn2DMATRIX(int[][] matrix, int elemntToSearch) {
        int n = matrix.length;
        int i = 0;
        int j = matrix[0].length - 1;

        while (i < n && j >= 0) {
            if (matrix[i][j] == elemntToSearch) {
                System.out.println("Element found at : " + i + " and : " + j);
                return;
            }
            if (matrix[i][j] > elemntToSearch)
                j--;
            else
                i++;
        }
        System.out.println("Element doesn't exists in Matrix");
    }

    //O(logN)/O(1)
    static void SearchIn2DMATRIXOptimal(int[][] matrix, int elemntToSearch) {
        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0;
        int high = (n * m) - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int current = matrix[mid / m][mid % m];
            if (current == elemntToSearch) {
                System.out.println("Element found at : " + mid / m + " and : " + mid % m);
                return;
            } else if (current < elemntToSearch)
                low = mid + 1;
            else
                high = mid - 1;
        }
        System.out.println("Element doesn't exists in Matrix");
    }

    public static int findDuplicate(int[] array) {
        int slow = array[0];
        int fast = array[0];

        do {
            slow = array[slow];
            fast = array[array[fast]];
        } while (slow != fast);

        fast = array[0];

        while (slow != fast) {
            slow = array[slow];
            fast = array[fast];
        }
        return slow;
    }

    //Dutch national Flag
    //ar = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}; Output : 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2
    static void sortAnArrayOf0_1_2(int[] array) {
        int low = 0;
        int mid = 0;
        int high = array.length - 1;

        while (mid <= high) {
            if (array[mid] == 0) {
                swap(array, low, mid);
                low++;
                mid++;
            } else if (array[mid] == 1) {
                mid++;
            } else if (array[mid] == 2) {
                swap(array, mid, high);
                high--;
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    static void reverseNum(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    //int[] numForPerm = {1, 3, 5, 4, 2}; //14235
    static void nextPermutation(int[] arr) {
        int i = arr.length - 2;

        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = arr.length - 1;

            while (arr[j] <= arr[i]) {
                j--;
            }
            swap(arr, i, j);
        }
        reverseNum(arr, i + 1, arr.length - 1);
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            int inv_count = 0;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
            //return inv_count;
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }

        for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1)
            arr[k++] = leftArr[i++];
        while (j < n2)
            arr[k++] = rightArr[j++];
    }

    //Moore Algorithm
    public static int majorityElement(int[] nums) {
        int n = nums.length;
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else if (count == 0) {
                candidate = num;
                count = 1;
            } else
                count--;
        }
        count = 0;
        // Checking if majority element occurs more than 'n' / 2 times.
        for (int i = 0; i < n; i++) {
            if (nums[i] == candidate)
                count++;
        }
        // If the count of the majority element is greater than 'n' / 2, then return it.
        if (count > n / 2) {
            return candidate;
        }
        // If no majority element found, return -1.
        return -1;
    }

    public static List<Integer> majorityElementII(int[] nums) {
        int candidate1 = -1, candidate2 = -1, count1 = 0, count2 = 0, len = nums.length;
        for (int num : nums) {
            if (num == candidate1)
                count1++;
            else if (num == candidate2)
                count2++;
            else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        List<Integer> ans = new ArrayList<>();
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1)
                count1++;
            else if (nums[i] == candidate2)
                count2++;
        }
        if (count1 > len / 3)
            ans.add(candidate1);
        else if (count2 > len / 3)
            ans.add(candidate2);
        return ans;
    }

    static double pow(double x, int n) {
        double ans = 1.0;
        long nn = n;
        if (nn < 0) nn = -1 * nn;

        while (nn > 0) {
            if (nn % 2 == 1) {
                ans = ans * x;
                nn = nn - 1;
            } else {
                x = x * x;
                nn = nn / 2;
            }
        }
        if (n < 0)
            ans = (double) 1.0 / (double) ans;

        return ans;
    }

    public static void longestConsecutiveSequence(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int maxLen = 0;
        int maxElement = 0;

        for (Integer element : nums) {
            set.add(element);
        }

        for (int element : nums) {
            int prevElement = element - 1;
            if (!set.contains(prevElement)) {
                int j = element;
                while (set.contains(j)) {
                    j++;
                }
                if (j - element > maxLen) {
                    maxLen = j - element;
                    maxElement = element;
                }
                //maxLen = Math.max(maxLen, j - element);
            }
        }


        //Below solution is having TLE exception as it's using O(N^2)
//        Map<Integer, Boolean> map = new HashMap<>();
//
//        for(int num: nums){
//            map.put(num,true);
//        }
//
//        for(int num: nums){
//            if(map.containsKey(num-1))
//                map.put(num,false);
//        }
//
//        int maxLen =0;
//        int maxElement =0;
//
//        for(int num: nums){
//            if(map.get(num) == true){
//                int tempLen = 1;
//                int tempElement = 0;
//
//                while(map.containsKey(num + tempLen))
//                    tempLen++;
//
//                if(tempLen > maxLen){
//                    maxLen = tempLen;
//                    maxElement = num;
//                }
//            }
//        }

        System.out.print("Longest Consecutive Sequence length for given array is: " + maxLen + " And Sequence is: ");
        for (int i = 0; i < maxLen; i++) {
            System.out.print(maxElement + i + " ");
        }
        System.out.println();
    }

    public int uniquePaths(int m, int n) {
        // if (m == 1 || n == 1) {
        //     return 1;
        // }
        // return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);

        return uniquePathsDP(m, n);
    }

    public int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];

        for (int[] tmp : dp) {
            Arrays.fill(tmp, 1);
        }

        for (int col = 1; col < m; col++) {
            for (int row = 1; row < n; row++) {
                dp[col][row] = dp[col - 1][row] + dp[col][row - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //Input: s = "the sky is blue"
    //Output: "blue is sky the"
    public static String reverseOrderOfWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = s.split("\\W+");

        for (int i = strArr.length - 1; i >= 0; i--) {
            if (!strArr[i].isEmpty())
                sb.append(strArr[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static ArrayList<ArrayList<Long>> printPascal(int n) {
        ArrayList<ArrayList<Long>> triangle = new ArrayList<ArrayList<Long>>();
        for (int i = 0; i < n; i++) {
            ArrayList<Long> add = new ArrayList<Long>();
            for (int j = 0; j <= i; j++) {
                add.add(calPascal((long) i, (long) j));
            }
            triangle.add(add);
        }
        return triangle;
    }

    public static long calPascal(long i, long j) {
        if (j == 0 || j == i) {
            return 1;
        } else {
            return calPascal(i - 1, j - 1) + calPascal(i - 1, j);
        }
    }

    public static ArrayList<ArrayList<Long>> pascalTriangle1(int rows) {
        ArrayList<ArrayList<Long>> triangle = new ArrayList<ArrayList<Long>>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Long> l = new ArrayList<>();
            long icj = 1;
            for (int j = 0; j <= i; j++) {
                l.add(icj);
                long icjp1 = icj * (i - j) / (j + 1);
                icj = icjp1;
            }
            triangle.add(l);
        }
        return triangle;
    }

    public static void pascalTriangleHill() {
        int rows = 5;
        for (int i = 0; i < rows; i++) {
            for (int j = i; j <= rows; j++) {
                System.out.print("-");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void pascalTriangleHill2() {
        int rows = 10;
        for (int i = 0; i < rows; i++) {
            int icj = 1;
            for (int j = rows; j >= i; j--) {
                System.out.print("-");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(icj + " ");
                icj = icj * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }

    public static void pascalTriangleHillByRow() {
        int row = 10;
        int icj = 1;
        String icjStr = "1";

        for (int i = 0; i < row - 1; i++) {
            icj = icj * (9 - i) / (i + 1);
            icjStr = icjStr + "," + icj;
        }

        System.out.println(icjStr);
    }

    // int[] Arr1 = {3, 6, 9, 0, 0,};
    // int[] Arr2 = {4, 10,};
    // [3, 4, 6, 9, 10]
    public static int[] MergeTwoSortedArrays(int arr1[], int arr2[], int m, int n) {
//        for (int i = 0; i < n; i++) {
//            arr1[i + m] = arr2[i];
//        }
//
//        Arrays.sort(arr1);
//        return arr1;

        int p1 = m - 1;
        int p2 = n - 1;

        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) break;

            if (p1 >= 0 && arr1[p1] > arr2[p2]) {
                arr1[p] = arr1[p1--];
            } else {
                arr1[p] = arr2[p2--];
            }
        }
        return arr1;
    }

    public static void setMatrixElementToZeros(int matrix[][]) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[] zeroRow = new boolean[rows];
        Arrays.fill(zeroRow, false);
        boolean[] zeroCol = new boolean[cols];
        Arrays.fill(zeroCol, false);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0)
                    zeroCol[j] = zeroRow[i] = true;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (zeroRow[i] || zeroCol[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static ArrayList<Integer> duplicates(int arr[], int n) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] tempArray = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int idx = arr[i];
            tempArray[idx]++;
        }

        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i] >= 2) {
                answer.add(i);
            }
        }

        return answer;
    }

    public static int[] missingAndRepeating(List<Integer> arr, int n) {
        int xor1;
        int set_bit_no;

        int i;
        int x = 0;
        int y = 0;

        xor1 = arr.get(0);

        /* Get the xor of all array elements  */
        for (i = 1; i < n; i++)
            xor1 = xor1 ^ arr.get(i);

       /* XOR the previous result with numbers from 1 to n*/
        for (i = 1; i <= n; i++)
            xor1 = xor1 ^ i;

        /* Get the rightmost set bit in set_bit_no */
        set_bit_no = xor1 & ~(xor1 - 1);

        /* Now divide elements into two sets by comparing
        rightmost set bit of xor1 with the bit at the same
        position in each element. Also, get XORs of two
        sets. The two XORs are the output elements. The
        following two for loops serve the purpose */
        for (i = 0; i < n; i++) {
            if ((arr.get(i) & set_bit_no) != 0)
                /* arr[i] belongs to first set */
                x = x ^ arr.get(i);

            else
                /* arr[i] belongs to second set*/
                y = y ^ arr.get(i);
        }
        for (i = 1; i <= n; i++) {
            if ((i & set_bit_no) != 0)
                /* i belongs to first set */
                x = x ^ i;

            else
                /* i belongs to second set*/
                y = y ^ i;
        }
        return new int[]{x, y};
    }

//    public static int[] missingAndRepeating(List<Integer> arr, int n) {
//        int[] tempArray = new int[n + 1];
//
//        for (int i = 0; i < n; i++) {
//            int idx = arr.get(i);
//            tempArray[idx]++;
//        }
//        int duplicate = 0;
//        int missing = 0;
//
//        for (int i = 0; i <= n; i++) {
//            if (tempArray[i] > 1) {
//                duplicate = i;
//            } else if (i != 0 && tempArray[i] == 0) {
//                missing = i;
//            }
//        }
//        return new int[]{missing, duplicate};
//    }

    //Q=1 then return sum of all integers from n to 1. Q=2, then return product of integers from 1 to n. Return modulo
    // 10 ^ 9+7
    public static long sumOrProduct(int n, int q) {
        long answer = 0;
        long mod = (long) 1e9 + 7;

        if (q == 1) {
            answer = (n * (n + 1)) / 2;
        } else {
            answer = 1;
            for (int i = 1; i <= n; i++) {
                i %= mod;
                answer %= mod;
                answer = (((answer * i) % mod) + mod) % mod;
            }
        }
        return answer;
    }

    static long maxProductSubArray(int[] arr, int n) {
        int minVal = arr[0];
        int maxVal = arr[0];
        int maxProduct = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < 0) {
                int temp = maxVal;
                maxVal = minVal;
                minVal = temp;
            }

            maxVal = Math.max(arr[i], maxVal * arr[i]);
            minVal = Math.min(arr[i], minVal * arr[i]);

            maxProduct = Math.max(maxProduct, maxVal);
        }
        return maxProduct;
    }

    public static List<String> textJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();

        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }

        return lines;
    }

    public static boolean abbreviation(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = false;
        }

        for (int i = 1; i < dp.length; i++) {
            if (Character.isLowerCase(s1.charAt(i - 1))) {
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (Character.isUpperCase(s1.charAt(i - 1))) {
                    if (dp[i - 1][j - 1]) {
                        dp[i][j] = Character.toUpperCase(s1.charAt(i - 1)) == s2.charAt(j - 1);
                    }
                } else {
                    if (Character.toUpperCase(s1.charAt(i - 1)) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[n][m];
    }

    //We define subsequence as any subset of an array. We define a subarray as a contiguous subsequence in an array.
    //Given an array, find the maximum possible sum among:
        //all nonempty subarrays.
        //all nonempty subsequences.
        //Print the two values as space-separated integers on one line.
    //Note that empty subarrays/subsequences should not be considered.
    //arr = [-1,2,3,-4,5,10]
    //The maximum subarray sum is comprised of elements at inidices[1,5]. Their sum is 2+3+-4+5+10 =16.
    // The maximum subsequence sum is comprised of elements at indices[1,2,4,5]  and their sum is 2+3+5+10.
    public static List<Integer> maxSubarray(List<Integer> arr) {
        ArrayList<Integer> al = new ArrayList<>();
        int sum = 0;
        int j = 0;
        int maxi = Integer.MIN_VALUE;

        for(int i=0;i<arr.size();i++)
        {
            sum+=arr.get(i);

            if(sum<0)
                sum=0;

            maxi=Math.max(maxi, sum);

            if(arr.get(i)>0)
                j+=arr.get(i);
        }
        if(j==0)
        {
            j=Collections.max(arr);
            maxi=j;
        }
        al.add(maxi);
        al.add(j);
        return al;
    }

    //Bill Gates is on one of his philanthropic journeys to a village in Utopia. He has brought a box of packets of candies and
    // would like to distribute one packet to each of the children. Each of the packets contains a number of candies.
    // He wants to minimize the cumulative difference in the number of candies in the packets he hands out. This is called the unfairness sum.
    // Determine the minimum unfairness sum achievable.
    //For example, he brings n=7  packets where the number of candies is packets =[3,3,4,5,7,9,10]. There are k=3  children. The minimum difference between all packets can be had with 3,3,4
    // from indices  and . We must get the difference in the following pairs:{(0,1),{0,2),(0,3)} . We calculate the unfairness sum as:
    public static long angryChildren(int k, List<Integer> packets) {
        Collections.sort(packets);
        int n = packets.size();

        long result = Long.MAX_VALUE, sum, d[] = new long[k];
        for (int i=0; i<=n-k; i++){
            sum=0;
            for (int j=1; j<k; j++){
                d[j] = d[j-1] + j * (packets.get(i+j) - packets.get(i+j-1));
                sum += d[j];
            }
            result = result < sum ? result : sum;
        }
        return result;
    }

    public static boolean isSubstring(String big, String small) {
        if (big.indexOf(small) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        /* check that s1 and s2 are equal length and not empty */
        if (len == s2.length() && len > 0) {
            /* concatenate s1 and s1 within new buffer */
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }
        return false;
    }

    private static String encode(String[] string){
       return Arrays.stream(string)
                .map(arr -> arr.length() + "#" + arr)
                .collect(Collectors.joining());
    }

    private static String[] deCode(String string){
        List<String> result = new ArrayList<>();

        for (int i = 0; i < string.length() ; i++) {
            if(string.charAt(i) >=48 && string.charAt(i) <= 57 && string.charAt(i+1)=='#'){
                int wordlen = string.charAt(i) - 48;
                String sub = string.substring(i+2,i+2+wordlen);
                result.add(sub);
            }
        }
        return result.toArray(new String[0]);
    }

    public static int searchInRotatedArray(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] >= nums[low]) {
                if (target >= nums[low] && target < nums[mid]){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }else {
                if (target <= nums[high] && target > nums[mid]){
                    low = mid + 1;
                }else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int findMinimumInRotatedArray(int[] nums){
        int low =0;
        int high = nums.length -1;

        if(nums[low] < nums[high])
            return nums[0];

        while(low <= high){
          int mid = low + (high-low)/2;

          if(nums[mid] > nums[mid+1])
              return nums[mid+1];
          else if(nums[mid] < nums[mid -1])
              return nums[mid];
          else if(nums[low] <= nums[high])
              low = mid +1;
          else if(nums[mid] <= nums[high])
              high = mid -1;
        }
        return -1;
    }
    //String before shift operation is :abcdefg
    //String after shift operation is  :efgabcd
    static void stringShift(String s, int[][] shift) {
        int val = 0;
        for (int i = 0; i < shift.length; ++i) {
            // If shift[i][0] = 0, then left shift, Otherwise, right shift
            if (shift[i][0] == 0)
                val -= shift[i][1];
            else
                val += shift[i][1];
        }
        // Stores length of the string
        int len = s.length();
        // Effective shift calculation
        val = val % len;
        // Stores modified string
        String result = "";
        // Right rotation
        if (val > 0)
            result = s.substring(len - val, (len - val) + val) + s.substring(0, len - val);
        // Left rotation
        else
            result = s.substring(-val, len + val) + s.substring(0, -val);

        System.out.println("String after shift operation is  :" + result);
    }

    //Modify a string by circularly shifting each character to the right by respective frequencies
    //Circular shifting of characters refers to shifting character ‘z’ to ‘a’, as its next character.
    //Input: S = “geeksforgeeks”
    //Output: iiimugpsiiimu
    //Frequency of ‘g’ is 2. Therefore, shifting the character ‘g’ by 2 becomes ‘i’.
    //Frequency of ‘e’ is 4. Therefore, shifting the character ‘e’ by 4 becomes ‘i’.
    //Frequency of ‘k’ is 2. Therefore, shifting the character ‘k’ by 2 becomes ‘m’.
    //Frequency of ‘s’ is 2. Therefore, shifting the character ‘s’ by 2 becomes ‘u’.
    //Frequency of ‘f’ is 1. Therefore, shifting the character ‘f’ by 1 becomes ‘g’.
    //Frequency of ‘o’ is 1. Therefore, shifting the character ‘o’ by 1 becomes ‘p’.
    //Frequency of ‘r’ is 1. Therefore, shifting the character ‘r’ by 1 becomes ‘s’.
    static void addFrequencyToCharacter(String Str){
        int frequency[] = new int[26];
        int N = Str.length();
        char S[] = Str.toCharArray();
        // Traverse the string S
        for (int i = 0; i < N; i++) {
            frequency[S[i] - 'a'] += 1;
        }
        // Traverse the string S
        for (int i = 0; i < N; i++) {
            // Find the frequency of
            // the current character
            int add = frequency[S[i] - 'a'] % 26;
            // Update the character
            if ((int)(S[i]) + add <= (int)('z'))
                S[i] = (char)((int)(S[i]) + add);
            else {
                add = ((int)(S[i]) + add) - ((int)('z'));
                S[i] = (char)((int)('a') + add - 1);
            }
        }
        // Print the resultant string
        System.out.println("String before Circular shift operation is :" + new String(S));
    }

    private static int findPivot(int[] nums){
        int totalSum = Arrays.stream(nums).sum();
        int leftSum  = 0;
        int rightSum = totalSum;

        for (int i = 0; i < nums.length; i++) {
            rightSum -=nums[i];

            if(leftSum == rightSum){
                return nums[i];
            }
            leftSum += nums[i];
        }
        return -1;
    }

    static int count=0;
    private static void testSum(int[] nums){
        int sum = Arrays.stream(nums).map(m-> m* count++).sum();

    }

    public static void main(String[] args) {

        System.out.println("============= Longest Substring Without Repeating Characters =============================");
        String s1 = "codingninjas";
        longestSubstringWithoutRepeatingCharacters(s1); //4

        System.out.println("=============== Longest Substring Without Repeating Characters Optimized =================");
        longestSubstringWithoutRepeatingCharactersOptimized(s1);

        System.out.println("========================== String to Integer (atoi) ======================================");
        String num = "4193 with words";
        //num = "-91283472332";
        myAtoi(num);

        System.out.println("============================== Integer to Roman ==========================================");
        int number = 2343;
        //Output: "III"
        //Input: num = 58
        //Output: "LVIII"
        //Input: num = 1994
        //Output: "MCMXCIV"
        String answer = intToRoman(number);
        System.out.println("Roman representation for number : " + number + " is : " + answer);

        System.out.println("============================== Roman to Integer ==========================================");
        String s = "LVIII";
        //Output: 3
        //Input: s = "LVIII"
        //Output: 58
        //Input: s = "MCMXCIV"
        //Output: 1994
        romanToInt(s);

        String romanStr = "IV";
        System.out.print(romanToInt2(romanStr) + "\n");

        System.out.println("========================== Two sum in Array Unique pair ==================================");
        //Elements would be repeating
        int[] arr = new int[]{2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};
        int target = 4;
        List<List<Integer>> lst = twoSum(arr, 0, arr.length - 1, target);
        System.out.println(lst);

        System.out.println("========================== Three sum in Array Unique pair ==================================");
        arr = new int[]{2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};
        target = 14;
        List<List<Integer>> threeSumLst = threeSum(arr, target);
        System.out.println(threeSumLst);

        System.out.println("========================== Four sum in Array Unique pair ==================================");
        arr = new int[]{2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};
        target = 15;
        List<List<Integer>> fourSumLst = fourSum(arr, target);
        System.out.println(fourSumLst);

        System.out.println("=================================== 3 Sum Closest ========================================");
        arr = new int[]{-1, 2, 1, -4};
        target = 1;
        int ans = threeSumClosest(arr, target);
        System.out.println("Closest 3 some for target: " + target + " is : " + ans);

        System.out.println("================================ Smallest Difference ======================================");
        int[] arr1 = new int[]{-1, 5, 10, 20, 28, 3};
        int[] arr2 = new int[]{26, 134, 135, 15, 17};
        int[] diff = smallestDifference(arr1, arr2); //smallest difference close to 0 is from element: [28, 26]
        System.out.println("smallest difference close to 0 is from element: " + Arrays.toString(diff));

        System.out.println("================================ Move element to end =====================================");
        List<Integer> ls = Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2);
        int toMove = 2;
        List<Integer> moveLst = moveElementToEnd(ls, toMove);
        System.out.println("After moving elements to end, Arrays would be : " + moveLst);

        System.out.println("================================ Monotonic Array =====================================");
        arr = new int[]{1, 2, 3, 3, 2, 1}; //{-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
        boolean isArrayMonotonic = isMonotonic(arr);
        System.out.println("Array with elements : " + Arrays.toString(arr) + " is Monotonic : " + isArrayMonotonic);

        System.out.println("========================== strStr - KMP (Knuth-Morris-Pratt)======================");
        String hayStack = "hellowllen";
        String needle = "ll";
        strStr(hayStack, needle); //Occurance of pattern : ll in the String: hello is at : [2]

        System.out.println("========================== Minimum Characters For Palindrome =============================");
        String pallindromCheckStr = "abcd";
        int cnt = minimumCharactersForPalindrome(pallindromCheckStr);
        System.out.println("Minimum characters to be added at front to make it a palindrome are : " + cnt);

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
        rotateMatrix(mat, 8, 8);
        System.out.println();
        printMatrix(mat);


        System.out.println("================================== Group Anangram ========================================");
        //Input: strs = ["eat","tea","tan","ate","nat","bat"]
        //Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        String[] anagramInput = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = groupAnagrams_v2(anagramInput);
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
        String version1 = "1.1";
        String version2 = "1.0000000001";
        int versions = compareVersion(version1, version2);
        System.out.println("Comparing version v1 : " + version1 + " and V2: " + version2 + ". Comparison value is: " + versions);

        System.out.println("=================================== Missing Number =======================================");
        //Input: nums = [3,0,1]
        //Output: 2
        //Input: nums = [0,1]
        //Output: 2
        //Input: nums = [9,6,4,2,3,5,7,0,1]
        //Output: 8
        int[] nums = {1};
        int missingNum = missingNumber(nums);
        System.out.println("Missing number in array is: " + missingNum);

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

        System.out.println("============================ Integer to English Words ====================================");
        //Input: num = 123
        //Output: "One Hundred Twenty Three"
        //Example 2:        //
        //Input: num = 12345
        //Output: "Twelve Thousand Three Hundred Forty Five"
        //Example 3:        //
        //Input: num = 1234567
        //Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        number = 1234512;
        String englishWord = numberToWords(number);
        System.out.println("Word representation for integer : " + number + " is: " + englishWord);

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


        System.out.println("=============================== First Duplicate Value ====================================");

        arr = new int[]{2, 1, 5, 2, 3, 3, 4}; //Output 2
        // arr = new int[] {2, 1, 5, 3, 3, 2, 4}; //Output 3
        int duplicate = firstDuplicateValue(arr);
        System.out.println("First duplicate value from array : " + Arrays.toString(arr) + " is : " + duplicate);

        //arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}; //Output {2, 1, 5, 2, 3, 3, 4}
        arr = new int[]{-1, -1, -1, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 5, 6};
        duplicate = removeAllDuplicates(arr);
        System.out.println("Duplicate value from array : " + Arrays.toString(arr) + " is : " + duplicate);

        arr = new int[]{-1, -1, -1, 0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 5, 6};
        List<Integer> aList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int len = removeDuplicates(aList, aList.size());
        System.out.println("After removing duplicate Array length is: " + len + " and Arrray is : " + aList.toString());

        System.out.println("================================== Merge Intervals =======================================");
        //Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        //Output: [[1,6],[8,10],[15,18]]
        //Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
        //Example 2:
        //
        //Input: intervals = [[1,4],[4,5]]
        //Output: [[1,5]]
        //Explanation: Intervals [1,4] and [4,5] are considered overlapping.
        //intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        mergeOverlappingIntervals(intervals);

        System.out.println("================================== Subarray Sort =========================================");
        //arr = new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        //Output [3, 9]
        arr = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 7, 7, 16, 18, 19};
        //[4, 9]
        int subArray[] = subarraySort(arr);
        System.out.println("SubArray require to be sorted is : " + Arrays.toString(subArray));

        System.out.println("=============================== Line Through Points ======================================");

        int[][] points = {{1, 1}, {2, 2}, {3, 3}, {0, 4}, {-2, 6}, {4, 0}, {2, 1}};
        int linePointCount = lineThroughPoints(points);
        System.out.println("Max counts of element lying on same line are: " + linePointCount);

        System.out.println("================================== Largest range =========================================");
        arr = new int[]{1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6}; //1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6
        int[] range = largestRange(arr);
        System.out.println(Arrays.toString(range));

        System.out.println("=============================== Reverse words in String ==================================");

        String str = "Let's take LeetCode contest";
        // Output: "s'teL ekat edoCteeL tsetnoc"
        String reversedStr = reverseWords(str);
        System.out.println("Original string is: " + str + " Reversed String is : " + reversedStr);

        System.out.println("=============================== Search in 2D Matrix ======================================");

        matrix = new int[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        SearchIn2DMATRIX(matrix, 8);
        SearchIn2DMATRIXOptimal(matrix, 8);

        System.out.println("================================= Find Duplicate in Array ================================");
        int[] ar = {1, 3, 4, 2, 2};
        System.out.println("Duplicate elment from given array is : " + findDuplicate(ar));

        System.out.println("================================= Sort Array  for 0/1/2 ==================================");
        ar = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}; //Output : 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2
        sortAnArrayOf0_1_2(ar);
        System.out.println("After sorted array is : " + Arrays.toString(ar));

        System.out.println("================================= Next permutation =======================================");
        int[] numForPerm = {1, 3, 5, 4, 2}; //14235
        nextPermutation(numForPerm);
        System.out.println("Next permutation for given number is : " + Arrays.toString(numForPerm));

        System.out.println("================================= Inversion of Array =====================================");
        int[] arrSort = new int[]{22, 33, 6, 90, 56, 78, 1, 7}; //1 6 7 22 33 56 78 90
        mergeSort(arrSort, 0, arrSort.length - 1);
        System.out.println("Array after mergr sort would be : " + Arrays.toString(arrSort));

        System.out.println("============================ Majority of element in Array ================================");
        int[] numsArr = {2, 11, 11, 4, 4}; //-1
        System.out.println("Majority element from given Array is: " + majorityElement(numsArr));

        numsArr = new int[]{-1, -1, -1}; //[-1]
        List<Integer> majorElements = majorityElementII(numsArr);
        System.out.println("Majority element II from given Array is: " + majorElements.toString());

        System.out.println("======================================== POW =============================================");
        System.out.println("Power of 2 at 10 is  " + (int) pow(2.0, 10)); //Power of 2 at 10 is  1024

        System.out.println("============================= Longest Consecutive Sequence ===============================");
        int[] seq = {100, 4, 200, 1, 3, 2};
        longestConsecutiveSequence(seq);

        System.out.println("================================ Reverse order of words ==================================");
        String strWord = "  hello world  ";
        String finalStr = reverseOrderOfWords(strWord);
        System.out.println("Reversed String is: " + finalStr);

        System.out.println("================================ Pascal Triangle ==================================");
        ArrayList<ArrayList<Long>> tirangle = pascalTriangle1(5);

        for (ArrayList<Long> triangleLst : tirangle) {
            System.out.print(triangleLst.toString() + " ");
            System.out.println();
        }

        pascalTriangleHill();

        pascalTriangleHill2();

        System.out.println();

        pascalTriangleHillByRow();

        System.out.println("================================ Merge two sorted Array ==================================");
        int[] Arr1 = {3, 6, 9, 0, 0,};
        int[] Arr2 = {4, 10,};
        MergeTwoSortedArrays(Arr1, Arr2, 3, 2); // // [3, 4, 6, 9, 10]
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
        int[] missingArray = {4, 3, 6,2,1,1}; //1 5
        n=6;
        int[] an = missingAndRepeating(Arrays.stream(missingArray).boxed().collect(Collectors.toList()), n);
        System.out.println("Missing number from given Array is: " + an[0] + " and Duplicate number is : " + an[1]);

        System.out.println("=================================== SUM or Product =======================================");
        System.out.println("SUM or Product of N numbers is : " + sumOrProduct(4,2)); //24

        System.out.println("=================================== Max Product SubArray =================================");
        //int Arr[] = {2, 3, 4, 5, -1, 0};// Output: 120
        int Arr[] = {6, -3, -10, 0, 2}; //Output: 180
        System.out.println("Maximum product subArray from given Array: " + Arrays.toString(Arr) + " is: " + maxProductSubArray(Arr, Arr.length));

        String[] justifyWords = {"This", "is", "an", "example", "of", "text", "justification."};
        int masWidth = 16;
        List<String> justifiedWords = textJustify(justifyWords, 16);
        System.out.println("Justified words are : " + justifiedWords);

        System.out.println("================================ String Abbrecviation check ===============================");
        //You can perform the following operations on the string, :        //
        //Capitalize zero or more of 's lowercase letters.
        //Delete all of the remaining lowercase letters in .
        //Given two strings,  and , determine if it's possible to make  equal to  as described. If so, print YES on a new line. Otherwise, print NO.
        //For example, given a= AbcDE and b=ABDE, in a we can convert b and delete  to match . If a= AbcDE and b= AFDE, matching is not possible because letters may only be capitalized or discarded, not changed.
        String str1 = "daBcd";
        String str2 = "ABC";
        System.out.println("String abbreviation check: " + abbreviation(str1,str2));

        System.out.println("================================== String rotation check ==================================");
        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean is_rotation = isRotation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
        }

        System.out.println("================================== Encode/Decode String ==================================");
        String[] string = {"lintcode","codelove","loveyou","youalot"};
        String r = encode(string);
        System.out.println("Encoded String is: "+ r);
        String[] decodedStr = deCode(r);
        System.out.println("Decoded String Array is: "+ Arrays.toString(decodedStr));

        System.out.println("================================= Search in Rotated Array ================================");
        nums = new int[]{4,5,6,7,0,1,2};
        target = 0;
        int result = searchInRotatedArray(nums,target);
        System.out.println(result == -1 ? "Element: " + target + " not found in Array: " + Arrays.toString(nums) : "Element: " + target + " found at: " + result + " in Rotated Array : "+ Arrays.toString(nums));

        System.out.println("============================== Find minimum in Rotated Array =============================");
        nums = new int[]{3,4,5,1,2};
        result = findMinimumInRotatedArray(nums);
        System.out.println("Minimum Element in rotated array: " + Arrays.toString(nums) + " is : " + result);

        System.out.println("==================================== String shifting =====================================");
        s ="abcdefg";
        int[][] shift = { {1, 1}, {1, 1}, {0, 2}, {1, 3} };
        System.out.println("String before shift operation is :" + s);
        stringShift(s,shift); //efgabcd

        System.out.println("======== Circularly shifting each character to the right by respective frequencies =========");
        String S = "geeksforgeeksyyyy";
        System.out.println("String before Circular shift operation is :" + S);
        addFrequencyToCharacter(S);

        nums = new int[]{1,7,3,6,5,6};
        int pivot = findPivot(nums);

        System.out.println("Pivot from give array: " + Arrays.toString(nums) + " is: " + pivot);

        nums = new int[]{1,2,3,4,5};
        testSum(nums);
        System.out.println("==================================== Sample Test =========================================");
        char[][] box = new char[][]{{'#', '#', '*', '.', '*', '.'}, {'#', '#', '#', '*', '.', '.'}, {'#', '#', '#', '.', '#', '.'}};

        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("---------------------------------");

        for (int col = 0; col < box[0].length; col++) {
            for (int row = 0; row < box.length; row++) {
                int empty = row + 1;
                while (empty < box.length && box[row][empty] == '.') {
                    empty++;
                }
                if (empty < box.length && box[row][empty] == '.') {
                    box[row][empty] = '#';
                    box[row][col] = '.';
                } else if (empty - 1 < box.length && box[row][empty - 1] == '.') {
                    box[row][empty - 1] = '#';
                    box[row][col] = '.';
                }
            }
        }

        System.out.println("---------------------------------");

        for (int col = 0; col < box[0].length; col++) {
            for (int row = 0; row < box.length; row++) {
                System.out.print(box[row][col]);
            }
            System.out.println();
        }

    }

}

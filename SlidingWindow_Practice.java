package Amazon;

import java.lang.reflect.Array;
import java.util.*;

public class SlidingWindow_Practice {

    public static void maximumSumSubarray(int[] array, int k){
        int maxSum = Integer.MIN_VALUE;
        int start =0;
        int end =0;
        int sum =0;
        while(end < array.length){
            sum += array[end];
            if(end-start+1 < k){
                end++;
            }else if(end- start+1 ==k){
                maxSum = Math.max(maxSum,sum);
                sum = sum - array[start];
                start++;
                end++;
            }
        }
        System.out.println("Maximum sum sub array of size k is: " + maxSum);
    }

    public static void maximumSumSubarrayII(int[] nums, int k){
        int n = nums.length;
        ArrayList<Integer> result = new ArrayList<>();
        int i=0;
        int sum =0;
        for (; i < k; i++) {
           sum+= nums[i];
        }
        //sum -= nums[0];
        for(; i < n;i++){
            sum += nums[i];
            sum -=nums[i-k];
        }

        System.out.println("Maximum sum sub array of size k is: " + sum);
    }

    public static void longestContinuousIncreasingSubsequence(int[] arr){
        int result = 0;
        int anchor = 0;

        for (int i = 0; i < arr.length ; i++) {
            if( i>0 && arr[i-1] >= arr[i])
                anchor = i;
            result = Math.max(result, i - anchor +1);
        }
        System.out.println("Longest Continuous Increasing Subsequence length is : " + result);
        //return result;
    }

    public static void longestRepeatingCharacterReplacement(String s, int k){
        int n= s.length();
        int j =0;
        int[] charArray = new int[26];
        int result =0;
        int max_count =0;
        for (int i = 0; i < n ; i++) {
            charArray[s.charAt(i) - 'A']++;
            int char_count = charArray[s.charAt(i) - 'A'];
            max_count = Math.max(max_count,char_count);

            while(i-j +1 - max_count + 1 > k){
                charArray[s.charAt(i) - 'A']--;
                j++;
            }
            result = Math.max(result, i-j +1);
        }
        System.out.println("Length of longest substring containing same letter is: "+ result);
    }

    public static void dynamicSlidingWindowForJPMC(String word, String wordSearch){
       int n = wordSearch.length();
       int m = word.length();


       Map<Character,Integer> wordSearchMap = new HashMap<>();
        Map<Character,Integer> wordMap = new HashMap<>();
        for (int i = 0; i < n ; i++) {
            char ch = wordSearch.charAt(i);
            wordSearchMap.put(ch, wordSearchMap.getOrDefault(ch, 0) + 1);
        }

        int i = 0;
        int j = 0;
        int matchCount =0;
        int expectedCount = wordSearch.length();
        int firstStartIndex  = 0;
        int firstEndIndex    = 0;
        int SecondStartIndex = 0;
        while(i < m) {
            char ch = word.charAt(i);
            if (!wordSearchMap.containsKey(ch)){
                i++;
                continue;
            }
            wordMap.put(ch, wordMap.getOrDefault(ch, 0) + 1);
            if (wordMap.getOrDefault(ch, 0) <= wordSearchMap.getOrDefault(ch, 0)) {
                matchCount++;
            }
            if (matchCount == expectedCount) {
                firstEndIndex = i;
                wordMap.clear();
                break;
            }

            i++;
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0){
                left[i] = nums[i];  // block_start
            }
            else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0){
                right[j] = nums[j];  // block_end
            }
            else{
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

    //Time : O(n*K)
    //Space: O(1)
    public static void maxSlidingWindowNaive(int[] nums, int k){
        int n = nums.length;
        int j, max;

        System.out.print("Maximum Sliding window naive: [");
        for (int i = 0; i <= n-k ; i++) {
            max = nums[i];
            for (j = 1; j < k ; j++) {
                if(nums[i+j] > max)
                    max = nums[i+j];
            }
            System.out.print(max + ", ");
        }
        System.out.println("]");
        System.out.println();
    }

    //Time : O(nLogK)
    //Space: O(k)
    public static void maxSlidingWindowPriorityQ(int[] nums, int k){
        int n = nums.length;
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        ArrayList<Integer> result = new ArrayList<>();
        int i=0;

        for (; i < k; i++) {
            q.add(nums[i]);
        }

        result.add(q.peek());
        q.remove(nums[0]);

        for(; i < n;i++){
            q.add(nums[i]);
            result.add((q.peek()));
            q.remove(nums[i-k+1]);
        }
        System.out.println("Maximum Sliding window PriorityQ: " + result);
    }

    //The most Optimal solution
    //Time : O(n)
    //Space: O(k)
    public static void  maxSlidingWindowDeque(int[] nums, int k){
        int n = nums.length;
        Deque<Integer> q = new ArrayDeque<>();

        int i=0;
        for (; i < k; i++) {
            while(!q.isEmpty() && nums[i] >= nums[q.peek()]){
                q.removeLast();
            }
            q.addLast(i);
        }

        System.out.print("Maximum Sliding window Deque: [");
        for(; i < n;i++){
            System.out.print(nums[q.peek()] + ", ");

            while(!q.isEmpty() && q.peek() <= i-k)
                q.removeFirst();

            while(!q.isEmpty() && nums[i] >= nums[q.peek()])
                q.removeLast();

            q.addLast(i);
        }
        System.out.print(nums[q.peek()]);
        System.out.println("]");
        System.out.println();
    }

    public static List<Integer> findAllAnagram(String s, String p){
        List<Integer> result = new ArrayList<>();

        int[] char_Counts = new int[26];
        for (int i = 0; i < p.length() ; i++) {
            char_Counts[p.charAt(i) -'a']++;
        }

        int left =0;
        int right =0;
        int count = p.length();

        while(right < s.length()){
            if(char_Counts[s.charAt(right++) -'a']-- >=1) count--;
            if(count == 0) result.add(left);

            if(right -left == p.length() && char_Counts[s.charAt(left++) -'a']++ >=0) count++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("======================= Maximum Sum Subarray of size K/ Sliding window ==================");
        int[] arr = {1,3,5,4,7,9,8,5};
        int k =4;
        maximumSumSubarray(arr,k);

        maximumSumSubarrayII(arr,k);

        longestContinuousIncreasingSubsequence(arr);

        dynamicSlidingWindowForJPMC("xprogxrmaxemrppprmmograeiruu","programmer");
        longestRepeatingCharacterReplacement("AABABBA",2);

        arr = new int[]{1,3,-1,-3,5,3,6,7};
        k=3;
        int[] ans = maxSlidingWindow(arr,k);
        System.out.println("Maximum sliding window array is: " + Arrays.toString(ans));

        System.out.println("============================ maximum Sliding Window Naive ================================");
        maxSlidingWindowNaive(arr,k);

        System.out.println("=============================== maximum Sliding Window PQ ================================");
        maxSlidingWindowPriorityQ(arr,k);

        System.out.println("============================== maximum Sliding Deque =====================================");
        maxSlidingWindowDeque(arr,k);

        System.out.println("======================================= Find All Anagram =================================");
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> anagram = findAllAnagram(s,p); //[0,6]
        System.out.println("Anagram found at Indexes: " + anagram);
    }
}

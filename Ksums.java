package Amazon;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class Ksums {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, results);
            }
        return results;
    }

    public void twoSum(int[] nums, int i,List<List<Integer>> results){
        int left = i+1;
        int right = nums.length-1;

        while(left < right){
            int sum = nums[i] + nums[left] + nums[right];

            if(sum == 0){
                results.add(Arrays.asList(nums[i],nums[left++],nums[right--]));
                while (left < right && nums[left] == nums[left - 1])
                    ++left;
            }else if(sum > 0){
                right--;
            }else{
                left ++;
            }
        }
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

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
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

    public static void main(String[] args) {
        System.out.println("========================================");
        int[] arr = new int[] {2, 2, 1, 1, 5, 3, 3, 4, 6, 6, 8, 8, 7};//{2,2,2,2,2}; //[2,2,2,2]
        int target =15;
        List<List<Integer>> res = fourSum(arr,target);
        System.out.println(res);

    }
}

package Leetcode.everyday.day20201231;

import java.util.*;

/**
 * @ClassName Solution
 * @Description
 * @Author 王品峰
 * @Date 2020/12/31 10:34
 * @Version 1.1.
 */
public class Solution {

    public static void main(String[] args) {
        String[] strs = {"dog","doge","dogh"};
        int[] nums = {1,2,5,10,11};
        System.out.println(new Solution().letterCombinations("234").size()+"{"+new Solution().letterCombinations("234")+"}");
    }
    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length < 3){
            return 0;
        }
        Arrays.sort(nums);
        int res = nums[0]+nums[1]+nums[2];
        for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i+1;
            int k = nums.length-1;
            while (j < k){
                if (Math.abs(nums[j]+nums[k]+nums[i]-target) < Math.abs(res-target)){
                    res = nums[j]+nums[k]+nums[i];
                }
                if (nums[j]+nums[k]+nums[i]-target<0){
                    j++;
                }else if (nums[j]+nums[k]+nums[i]-target>0){
                    k--;
                }else {
                    return target;
                }
            }
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs ==null || strs.length == 0){
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == nums || nums.length < 3){
            return ans;
        }
        Arrays.sort(nums);
        if (nums[nums.length-1]<0){
            return ans;
        }
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i]>0){
                return ans;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i+1;
            int k = nums.length-1;
            while (j < k){
                if (nums[j]+nums[k]+nums[i]<0){
                    j++;
                }else if (nums[j]+nums[k]+nums[i]>0){
                    k--;
                }else {
                    ans.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return ans;
    }

    public List<String> letterCombinations(String digits) {
        if (null == digits || digits.length() == 0){
            return new ArrayList<>();
        }
        Map<Character,List<String>> map = new HashMap<>(8);
        map.put('2',Arrays.asList("a","b","c"));
        map.put('3',Arrays.asList("d","e","f"));
        map.put('4',Arrays.asList("g","h","i"));
        map.put('5',Arrays.asList("j","k","l"));
        map.put('6',Arrays.asList("m","n","o"));
        map.put('7',Arrays.asList("p","q","r","s"));
        map.put('8',Arrays.asList("t","u","v"));
        map.put('9',Arrays.asList("w","x","y","z"));
        return letter(map,digits.toCharArray(),0);



    }
    public static List<String> letter(Map<Character,List<String>> map,char[] digits,Integer index){
        List<String> res = new ArrayList<>();
        if (index < digits.length-1){
            List<String> min = letter(map,digits,index+1);
            map.get(digits[index]).forEach(i->{
                min.forEach(j->{
                    res.add(i+j);
                });
            });
            return res;
        }
        if (index == digits.length-1){
            res.addAll(map.get(digits[index]));
        }
        return res;
    }

}

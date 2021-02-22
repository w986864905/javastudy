package Leetcode.day20210111;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        int[] nums = {0,1,2,2,3,0,4,2};
//        System.out.println(removeElement(nums,2));
//        System.out.println(Arrays.toString(nums));

        System.out.println(strStr("ababcabcacbab","abcac"));

    }
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 1){
            return 1;
        }
        int index = 1;
        for (int j = 1; j < nums.length; j++) {
           if (nums[j] != nums[j-1]){
               nums[index] = nums[j];
               index++;
           }
        }
        return index;
    }
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
//    public static int removeElement(int[] nums, int val) {
//        if (null == nums || nums.length == 0){
//            return 0;
//        }
//        if(nums.length == 1 && val == nums[0]){
//            return 0;
//        }
//        int i = 0,j=nums.length-1;
//        a:while (i < j){
//            if (nums[i] == val){
//                while (nums[j] == val){
//                    j--;
//                    if (j<=i){
//                        break a;
//                    }
//                }
//                swap(nums,i,j);
//            }
//            i++;
//        }
//        if (nums[0]!=val && nums[nums.length-1]!=val){
//            return nums.length;
//        }else if (nums[0]==val && nums[nums.length-1]==val){
//            return 0;
//        }
//        return i;
//    }
    public static void swap(int[] nums, int i, int j){
        int a = nums[i];
        nums[i] = nums[j];
        nums[j] = a;
    }
    public static int strStr(String haystack, String needle) {
        if (needle.length()==0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int j = 0;
        int index = -1;
        int k = 0;
        Boolean isIndex = true;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0) && isIndex && k <= 1 && index != i){
                if (k < 1){
                    k++;
                }else {
                    index = i;
                    isIndex = false;
                }
            }
            if (haystack.charAt(i) == needle.charAt(j)){
                if (j == needle.length()-1){
                    return i-needle.length()+1;
                }
                j++;
            }else {
                if (index <= i && j>0 && !isIndex){
                   i = index - 1;
                   isIndex = true;
                }
                j = 0;
            }
        }
        return -1;
    }
    class Boyer_Moore{
        int[] right;    //该数组用于记录主字符串中的字符c出现在模式字符串中的最右边的位置
        public Boyer_Moore(String needle){
            right=new int[26];
            for(int i=0;i<26;i++){
                right[i]=-1;
            }
            for(int i=0;i<needle.length();i++){
                right[needle.charAt(i)-'a']=i;
            }
        }
        public int search(String main_str,String pattern_str){
            //BM算法的技巧就在于从后向前搜索，匹配不成功时，将字符串向右移
            int jump=0;
            //i是主串中与模式串匹配的起始位置
            for(int i=0;i<=main_str.length()-pattern_str.length();i+=jump){
                jump=0;
                for(int j=pattern_str.length()-1;j>=0;j--){ //从后向前匹配
                    if(pattern_str.charAt(j)!=main_str.charAt(i+j)){
                        //当出现不匹配时，需要将i向右移动j-right[main_str.charAt(j)-'a']个位置
                        jump=j-right[main_str.charAt(i+j)-'a'];
                        if(jump<1) jump=1;
                        break;
                    }
                }
                if(jump==0) return i;
            }
            return -1;
        }
    }
}
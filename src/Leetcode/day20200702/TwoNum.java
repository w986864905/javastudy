package Leetcode.day20200702;

import java.util.Arrays;

/**
 * 两数之和
 */
public class TwoNum {


    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] res = twoSum(nums,target);
        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] nums,int target){
        int[] res = new int[2];//存放结果
        for(int i = 0;i < nums.length;i++){
            res[0] = nums[i];
            for(int j = nums.length-1;j > i;j--){
                if(res[0]+nums[j] == target){
                    res[1] = nums[j];
                    return res;
                }
            }
        }
        return res;
    }
}

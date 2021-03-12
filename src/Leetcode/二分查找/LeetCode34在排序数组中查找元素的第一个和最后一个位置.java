package Leetcode.二分查找;

/**
 * @ClassName LeetCode34在排序数组中查找元素的第一个和最后一个位置
 * @Description
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * @Author 王品峰
 * @Date 2021/3/1 17:07
 * @Version 1.1.
 */
public class LeetCode34在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums,target);
        if (index == -1){
            return new int[]{-1,-1};
        }
        int i = index;
        int j = index;
        while (i >= 0 && nums[i] == target){
            i--;
        }
        while (j < nums.length && nums[j] == target){
            j++;
        }
        return new int[]{i+1,j-1};
    }
    private static int binarySearch(int[] a,int x){
        int low=0,high=a.length-1;
        while(low<=high){
            int mid =(low+high) >> 1;
            if(a[mid]<x){
                low=mid+1;
            }else if(a[mid]>x){
                high=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}

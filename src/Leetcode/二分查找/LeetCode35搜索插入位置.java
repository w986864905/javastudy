package Leetcode.二分查找;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 */
public class LeetCode35搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length -1,mid = (left+right) >> 1;
        while (left <= right){
            mid = (left+right) >> 1;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return left;
    }
}

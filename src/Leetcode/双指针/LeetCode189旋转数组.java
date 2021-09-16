package Leetcode.双指针;

/**
 * @ClassName LeetCode189旋转数组
 * @Description 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * @Author 王品峰
 * @Date 2021/9/16 14:26
 * @Version 1.0
 */
public class LeetCode189旋转数组 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        reverse(nums,0,n-1);
        reverse(nums,0,(k % n) - 1);
        reverse(nums,(k % n),n-1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}

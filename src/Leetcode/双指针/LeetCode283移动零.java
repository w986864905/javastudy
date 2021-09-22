package Leetcode.双指针;

import java.util.Arrays;

/**
 * @ClassName LeetCode283移动零
 * @Description
 * @Author 王品峰
 * @Date 2021/9/17 16:56
 * @Version 1.0
 */
public class LeetCode283移动零 {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        new LeetCode283移动零().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

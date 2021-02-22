package Leetcode.贪心法;

/**
 * @ClassName LeetCode665非递减数列
 * @Description 总满足 nums[i] <= nums[i + 1]。
 * 输入: nums = [4,2,3]
 * 输出: true
 * @Author 王品峰
 * @Date 2021/2/3 15:35
 * @Version 1.1.
 */
public class LeetCode665非递减数列 {
    public static boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int cnt = 0;
        for (int i = 1; i < nums.length && cnt < 2; i++) {
            if (nums[i-1] <= nums[i]) {
                continue;
            }
            cnt++;
            if (i-2>=0 && nums[i-2] > nums[i]) {
                nums[i] = nums[i-1];
            }else {
                nums[i-1] = nums[i];
            }
        }
        return cnt <= 1;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,1,8};
        System.out.println(checkPossibility(nums));
    }
}

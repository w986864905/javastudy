package Leetcode.动态规划;

import org.checkerframework.checker.units.qual.A;

/**
 * @ClassName LeetCode413等差数列划分
 * @Description
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * @Author 王品峰
 * @Date 2021/7/23 17:28
 * @Version 1.0
 */
public class LeetCode413等差数列划分 {
    public int numberOfArithmeticSlices(int[] nums) {
        int dp = 0;
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }
    public int numberOfArithmeticSlices2(int[] nums) {
        int count = 0;
        int sum = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                count++;
            } else {
                sum += (count + 1) * (count) / 2;
                count = 0;
            }
        }
        return sum + count * (count + 1) / 2;
    }

    public static void main(String[] args) {
        System.out.println(2 >> 1);
    }
}

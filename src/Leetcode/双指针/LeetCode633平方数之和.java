package Leetcode.双指针;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.sqrt;

/**
 * @ClassName LeetCode633平方数之和
 * @Description
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * @Author 王品峰
 * @Date 2021/2/26 14:23
 * @Version 1.1.
 */
public class LeetCode633平方数之和 {
    public static boolean judgeSquareSum(int c) {
        if (c < 0){
            return false;
        }
        int right = (int) Math.sqrt(c),left = 0;
        while (left <= right){
            int sum = (int) (Math.pow(left,2)+ Math.pow(right,2));
            if (sum == c){
                return true;
            }else if (sum > c){
                right--;
            }else {
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(6));
    }
}

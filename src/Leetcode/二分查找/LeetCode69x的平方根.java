package Leetcode.二分查找;

/**
 * @ClassName LeetCode69x的平方根
 * @Description
 * 实现int sqrt(int x)函数。
 * 计算并返回x的平方根，其中x是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,由于返回类型是整数，小数部分将被舍去。
 * @Author 王品峰
 * @Date 2021/3/1 16:29
 * @Version 1.1.
 */
public class LeetCode69x的平方根 {
    public static int mySqrt(int x) {
        long a = x; while (a * a > x) { a = (a + x / a) / 2; } return (int) a;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}

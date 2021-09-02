package Leetcode.动态规划;

/**
 * @ClassName LeetCode70爬楼梯
 * @Description
 * @Author 王品峰
 * @Date 2021/7/23 15:46
 * @Version 1.0
 */
public class LeetCode70爬楼梯 {
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int a = 1,b = 2,result = 3;
        for (int i = 3; i <= n; ++i) {
            result = a+b;
            a = b;
            b = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(7));
    }
}

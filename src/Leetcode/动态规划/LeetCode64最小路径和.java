package Leetcode.动态规划;

/**
 * @ClassName LeetCode64最小路径和
 * @Description 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * @Author 王品峰
 * @Date 2021/7/26 11:09
 * @Version 1.0
 */
public class LeetCode64最小路径和 {
    public static int minPathSum(int[][] grid) {
        if (grid.length == 0){
            return 0;
        }
        int m = grid.length,n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0){
                    if (j == 0) {
                        dp[i][j] = grid[i][j];
                    }else {
                        dp[i][j] = dp[i][j-1]+grid[i][j];
                    }
                }else if (j == 0){
                    dp[i][j] = dp[i-1][j]+grid[i][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{
                {1,3,1}, {1,5,1},{4,2,1}}));
    }
}

package Leetcode.动态规划;

/**
 * @ClassName LeetCode542零一矩阵
 * @Description 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * 示例 1：
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * @Author 王品峰
 * @Date 2021/7/26 17:18
 * @Version 1.0
 */
public class LeetCode542零一矩阵 {
    private int[][] dp;
    private boolean[][] flag;
    public int[][] updateMatrix(int[][] mat) {
        int x = mat.length - 1;
        int y = mat[0].length - 1;
        int up = 0, down = mat.length - 1;
        int left = 0, right = mat[0].length - 1;
        dp = new int[mat.length][mat[0].length];
        flag = new boolean[mat.length][mat[0].length];
        while (true) {
            //往右
            for (int i = left; i <= right; i++) {

                System.out.println(mat[up][i]);
            }
            if (++up > down) {
                break;
            }
            for (int i = up; i <= down; i++) {
                //下
                System.out.println(mat[i][right]);
            }
            if (--right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                System.out.println(mat[down][i]);
            }
            if (--down < up) {
                break;
            }
            for (int i = down; i >= up; i--) {
                System.out.println(mat[i][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return dp;
    }
    private void setDpValue(int m,int n,int x,int y){
        if (flag[m][n] == true){
            return;
        }
        if (m == 0 ){

        }
    }

    public static void main(String[] args) {
        Long a = 123L;
        System.out.println(a.toString());
    }
}

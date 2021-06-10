package Leetcode.搜索算法;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode417太平洋大西洋水流问题
 * @Description
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * 提示：
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 * 示例：
 * 给定下面的 5x5 矩阵:
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 * 返回:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 * @Author 王品峰
 * @Date 2021/5/13 14:11
 * @Version 1.1.
 */
public class LeetCode417太平洋大西洋水流问题 {
    private static boolean pacific = false;
    private static boolean atlantic = false;
    /** 大西洋和太平洋共享的访问数组*/
    private boolean[][] visited = null;
    /** 方向转换的数组*/
    private int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0){
            return null;
        }
        int n = heights.length, m = heights[0].length;
        visited = new boolean[n][m];
        // temp 是用来记录当前深度优先搜索访问过的点
        boolean[][] temp = new boolean[n][m];
        //太平洋
        for (int i = 0; i < Math.max(n,m); i++) {
            if (i < n && !temp[i][0]){
                dfs(heights,i,0,temp,n,m,false);
            }
            if (i < m && !temp[0][i]){
                dfs(heights,0,i,temp,n,m,false);
            }
        }
        temp = new boolean[n][m];
        //大西洋
        for (int i = 0; i < Math.max(n,m); i++) {
            if (i < n && !temp[i][m-1]){
                dfs(heights,i,m-1,temp,n,m,true);
            }
            if (i < m && !temp[n-1][i]){
                dfs(heights,n-1,i,temp,n,m,true);
            }
        }
        return result;
    }

    private void dfs(int[][] heights, int curI, int curJ, boolean[][] temp, int n, int m, boolean flag) {
        // 如果是大西洋来的，而且 太平洋已经访问过 {x, y} 了，就放到返回值中
        if (flag && visited[curI][curJ]) {
            result.add(Arrays.asList(curI,curJ));
            // 顺便把该点置为 false，防止重复记录
            visited[curI][curJ] = false;
        }
        // 如果是从太平洋来的，需要将 {x, y} 标记为已来过
        if (!flag) {
            visited[curI][curJ] = true;
        }
        // 然后在当前深度优先搜索中标记为已来过
        temp[curI][curJ] = true;
        // 然后切换四个方向，逐个检查
        for (int i = 0; i != 4; ++i) {
            int nextI = curI + dirs[i][0];
            int nextJ = curJ + dirs[i][1];
            // 检查新的坐标是否合法，以及当前深度优先搜索是否来过，最后还要满足 逆向 条件
            if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < m && !temp[nextI][nextJ] && heights[nextI][nextJ] >= heights[curI][curJ]) {
                // 继续深度优先搜索
                dfs(heights, nextI, nextJ, temp, n, m, flag);
            }
        }
    }

//    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
//        if (heights == null || heights.length == 0){
//            return null;
//        }
//        List<List<Integer>> result = new ArrayList<>();
//        for (int i = 0; i != heights.length; ++i) {
//            for (int j = 0; j != heights[i].length; ++j) {
//                // temp 是用来记录当前深度优先搜索访问过的点
//                boolean[][] temp = new boolean[heights.length][heights[0].length];
//                dfs(heights,i,j,temp);
//                if (pacific && atlantic) {
//                    result.add(Arrays.asList(i, j));
//                }
//                pacific = false;
//                atlantic = false;
//            }
//        }
//        return result;
//    }
//    private static void dfs(int[][] heights, int curI, int curJ,boolean[][] temp){
//        if (curI == 0 || curJ == 0){
//            pacific = true;
//        }
//        if (curI == heights.length - 1 || curJ == heights[0].length - 1){
//            atlantic = true;
//        }
//        if (pacific && atlantic){
//            return;
//        }
//        int[] di = {0, 0, 1, -1};
//        int[] dj = {1, -1, 0, 0};
//        temp[curI][curJ] = true;
//        for (int i = 0;i != 4; i++){
//            int nextI = curI+di[i];
//            int nextJ = curJ+dj[i];
//            //出界
//            if (nextI < 0 || nextI == heights.length || nextJ < 0 || nextJ == heights[0].length){
//                continue;
//            }
//            if (!temp[nextI][nextJ] && heights[nextI][nextJ] <= heights[curI][curJ]){
//                dfs(heights,nextI,nextJ,temp);
//            }
//        }
//    }

}

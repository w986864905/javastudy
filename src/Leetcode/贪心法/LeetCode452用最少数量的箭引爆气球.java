package Leetcode.贪心法;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName LeetCode452用最少数量的箭引爆气球
 * @Description
 * @Author 王品峰
 * @Date 2021/1/22 15:06
 * @Version 1.1.
 */
public class LeetCode452用最少数量的箭引爆气球 {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE - (-10));
    }

    public int findMinArrowShots(int[][] points) {
        if (points.length <= 1){
            return points.length;
        }
        //以尾部升序排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[1], o1[1]);
            }
        });
        int tail = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > tail){
                count++;
                tail = points[i][1];
            }
        }
        return count;
    }
}

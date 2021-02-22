package Leetcode.贪心法;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName LeetCode435无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * @Description
 * @Author 王品峰
 * @Date 2021/1/21 17:25
 * @Version 1.1.
 */
public class LeetCode435无重叠区间 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (null == intervals || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparing(a -> a[1]));
        int total = 0, prev = intervals[0][1];
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] < prev) {
                ++total;
            } else {
                prev = intervals[i][1];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {2, 4}, {1, 3}};
        Arrays.sort(arr, Comparator.comparing(a -> a[1]));
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }

    }
}

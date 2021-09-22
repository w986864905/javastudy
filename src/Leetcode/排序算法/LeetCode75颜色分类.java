package Leetcode.排序算法;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.of;

/**
 * @ClassName LeetCode75颜色分类
 * @Description
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * @Author 王品峰
 * @Date 2021/4/13 19:04
 * @Version 1.1.
 */
public class LeetCode75颜色分类 {
    public static void sortColors(int[] nums) {
        int[] countNum = new int[3];
        for (int i:nums) {
            countNum[i]++;
        }
        int index = 0;
        for (int i = 0; i < countNum.length; i++) {
            for (int j = 0; j < countNum[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}

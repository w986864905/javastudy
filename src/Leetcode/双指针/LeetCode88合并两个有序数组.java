package Leetcode.双指针;

import Leetcode.common.ArraysUtil;

import java.util.Arrays;

/**
 * @ClassName LeetCode88合并两个有序数组
 * @Description
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * @Author 王品峰
 * @Date 2021/2/23 16:50
 * @Version 1.1.
 */
public class LeetCode88合并两个有序数组 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0){
            return;
        }
        int index = m-- + n-- -1;
        while (index >= 0 && n >=0){
            if (m >= 0){
                nums1[index--] = nums1[m] > nums2[n] ? nums1[m--]:nums2[n--];
            }else {
                nums1[index--] = nums2[n--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1,0,nums2,1);
        System.out.println(Arrays.toString(nums1));
    }
}

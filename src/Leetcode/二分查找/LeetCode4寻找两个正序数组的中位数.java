package Leetcode.二分查找;

/**
 * @ClassName LeetCode4寻找两个正序数组的中位数
 * @Description
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的中位数 。
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * @Author 王品峰
 * @Date 2021/3/10 15:26
 * @Version 1.1.
 */
public class LeetCode4寻找两个正序数组的中位数 {
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] mergeNums = mergeTwoNums(nums1,nums2,nums1.length-1,nums2.length-1);
        int mid = (mergeNums.length-1)>>1;
        //偶数
        if (mergeNums.length%2==0){
            return (mergeNums[mid]+mergeNums[mid+1])/2.0;
        }
        return mergeNums[mid];
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        int mid1 = 0, mid2 = 0;

        for(int k = 0, i = 0, j = 0; k <= n >> 1; k++){
            mid1 = mid2;
            if(i == nums1.length) {
                mid2 = nums2[j++];
            } else if(j == nums2.length) {
                mid2 = nums1[i++];
            } else if(nums1[i] < nums2[j]) {
                mid2 = nums1[i++];
            } else {
                mid2 = nums2[j++];
            }
        }
        return (n%2 == 1 ? mid2/1.0 : (mid1 + mid2)/2.0);
    }
    private static int[] mergeTwoNums(int[] nums1, int[] nums2,int m,int n){
        int[] result = new int[m+n+2];
        int current = result.length-1;
        while (m >= 0 || n >= 0){
            if (m >= 0 && n >= 0){
                if (nums1[m] >= nums2[n]){
                    result[current] = nums1[m];
                    current--;
                    m--;
                }else {
                    result[current] = nums2[n];
                    current--;
                    n--;
                }
            }else if (m == -1){
                result[current] = nums2[n];
                current--;
                n--;
            }else {
                result[current] = nums1[m];
                current--;
                m--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {5,6,7,8};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
}

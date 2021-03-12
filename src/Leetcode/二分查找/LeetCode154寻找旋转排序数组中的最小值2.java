package Leetcode.二分查找;

/**
 * @ClassName LeetCode154寻找旋转排序数组中的最小值2
 * @Description
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 * @Author 王品峰
 * @Date 2021/3/5 9:55
 * @Version 1.1.
 */
public class LeetCode154寻找旋转排序数组中的最小值2 {
    public static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = (low+high)>>1;
            //表明后面有序，旋转点 <= pivot
            if (nums[pivot] < nums[high]) {
                high = pivot;

            } else if (nums[pivot] > nums[high]) {
                //表明后面发生了旋转，旋转点 > pivot
                low = pivot + 1;
            } else {
                //可能发生后面都是重复数字的情况
                high -= 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] arr = {4,5,6,7,0,1,2};
        System.out.println(findMin(arr));
    }
}

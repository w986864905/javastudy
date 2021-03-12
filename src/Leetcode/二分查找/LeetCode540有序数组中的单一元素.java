package Leetcode.二分查找;

/**
 * @ClassName LeetCode540有序数组中的单一元素
 * @Description
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * @Author 王品峰
 * @Date 2021/3/10 14:30
 * @Version 1.1.
 */
public class LeetCode540有序数组中的单一元素 {
    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length -1;
        while (low < high){
            int mid = (low+high)>>1;
            if (nums[mid] == nums[mid+1]){
                if ((high-mid+1)%2==0){
                    high = mid-1;
                }else {
                    low = mid;
                }
            }else if(nums[mid] == nums[mid-1]){
                if ((mid-low+1)%2==0){
                    low = mid+1;
                }else {
                    high = mid;
                }
            }else {
                return nums[mid];
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(nums));
    }
}

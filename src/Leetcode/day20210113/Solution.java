package Leetcode.day20210113;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        //int[] nums = {1,3,5,6};
        //char[][] bored = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        //System.out.println(isValidSudoku(bored));
        //int i = Integer.MAX_VALUE;
        //System.out.println(divide(16,3));
        //System.out.println(divide(Integer.MIN_VALUE,-1));

    }
    public int arrayPairSum(int[] nums) {
        nums = Arrays.stream(nums).sorted().toArray();
        int result = 0;
        for (int i = 0; i <= nums.length/2 -1 ; i++) {
            result += nums[2*i];
        }
        return result;
    }
//    29. 两数相除给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
//    返回被除数 dividend 除以除数 divisor 得到的商。
    public static int divide(int dividend, int divisor) {
        if(divisor == -1 && dividend == Integer.MIN_VALUE) {
            // 溢出
            return Integer.MAX_VALUE;
        }
        int a = dividend>0 ? -dividend : dividend;
        int b = divisor>0 ? -divisor : divisor;
        // 都改为负号是因为int 的范围是[2^31, 2^31-1]，如果a是-2^32，转为正数时将会溢出
        if(a > b) {
            return 0;
        }
        int ans = div(a,b);
        return ((dividend > 0) ^ (divisor > 0)) ? -ans : ans;
    }
    private static int div(int a, int b) {
        if(a > b) {
            return 0;
        }
        int count = 1;
        int tb = b;
        while(tb<<1 >= a && tb<<1 < 0){
            // 溢出之后不再小于0
            tb = tb << 1;
            count = count << 1;
        }
        return count+div(a-tb,b);
    }
    public void nextPermutation(int[] nums) {
        if (nums.length <= 2){
            return;
        }
    }
    public static int search(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }
        boolean isContain = nums[0] == target;
        if (nums.length == 1){
            return isContain?0:-1;
        }
        int min = 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == target){
                isContain = true;
                index = i;
            }
            if(nums[i]<nums[i-1]){
                min++;
            }
            if (min > 1){
                return -1;
            }
        }
        return isContain?index:-1;
    }
    public static int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums,target);
        if (index == -1){
            return new int[]{-1,-1};
        }
        int i = index;
        int j = index;
        while (i >= 0 && nums[i] == target){
            i--;
        }
        while (j < nums.length && nums[j] == target){
            j++;
        }
        return new int[]{i+1,j-1};
    }
    private static int binarySearch(int[] a,int x){
        int low=0,high=a.length-1;
        while(low<=high){
            int mid =(low+high) >> 1;
            if(a[mid]<x){
                low=mid+1;
            }else if(a[mid]>x){
                high=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static int searchInsert(int[] nums, int target) {
        int low=0,high=nums.length-1;
        while(low<=high){
            int mid =(low+high) >> 1;
            if(nums[mid]<target){
                low=mid+1;
            }else if(nums[mid]>target){
                high=mid-1;
            }else{
                return mid;
            }
        }
        return low;
    }

    public static boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

}
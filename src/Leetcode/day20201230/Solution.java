package Leetcode.day20201230;

/**
 * @ClassName Solution
 * @Description
 * @Author 王品峰
 * @Date 2020/12/30 14:31
 * @Version 1.1.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(14));
    }
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public int romanToInt(String s) {
        return 0;
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num >= 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public int maxArea(int[] height) {
        if (height.length <= 1){
            return 0;
        }
        int l = 0;
        int r = height.length-1;
        int result = (r-l)*Math.min(height[l],height[r]);
        while (l != r){
            if (height[l] < height[r]){
                l++;
            }else {
                r--;
            }
            result = Math.max(result, (r - l) * Math.min(height[l], height[r]));
        }
        return result;
    }
}

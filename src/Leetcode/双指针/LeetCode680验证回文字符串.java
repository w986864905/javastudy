package Leetcode.双指针;

/**
 * @ClassName LeetCode680验证回文字符串
 * @Description
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * @Author 王品峰
 * @Date 2021/2/26 14:54
 * @Version 1.1.
 */
public class LeetCode680验证回文字符串 {
    public static boolean validPalindrome(String s) {
        if (s.length() == 1){
            return true;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while (left <= right){
            if (chars[left] == chars[right]){
                left++;
                right--;
            }else {
                return validPalindrome(chars,left,right-1) || validPalindrome(chars,left+1,right);
            }
        }
        return true;
    }

    public static boolean validPalindrome(char[] s, int low, int high) {
        for (;low <= high; ++low, --high) {
            char c1 = s[low], c2 = s[high];
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
    }
}

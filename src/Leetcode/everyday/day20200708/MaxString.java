package Leetcode.everyday.day20200708;

/**
 * 找出不重复字符的最长子串的长度
 */
public class MaxString {
    public static void main(String[] args) {
        String s = "abcdabc";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s){
        int count = 0;
        int start = 0;
        int end = 0;
        for (int i = 1;i < s.length();i++){
            while (s.substring(start,end+1).indexOf(s.charAt(i)) != -1){
                start++;
            }
            end++;
            count = count > (end - start +1)? count:(end - start +1 );
        }
        return count;
    }
}

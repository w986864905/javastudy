package Leetcode.everyday.day20200712;

public class PalinDrome {
    public static void main(String[] args) {
        String s = "";
        System.out.println(longestPalindrome(s));

    }
    private static String longestPalindrome(String s) {

        if (s == null || "".equals(s)){
            return "";
        }
        int start = 0;//回文串开始位置
        int end = 0;//回文串结束位置
        for (int i = 0;i < s.length();i++){
            int odd = expendCenter(s,i,i);
            int even = expendCenter(s,i,i+1);
            int length = Math.max(odd,even);
            if (length > end - start){
                start = i - (length - 1)/2;
                end = i + length/2;
            }
        }
        return s.substring(start,end + 1);

    }

    private static int expendCenter(String s,int left,int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left --;
            right ++;
        }
        return  right - left -1;
    }
}

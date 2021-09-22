package Leetcode.双指针;

public class LeetCode344反转字符串 {
    public void reverseString(char[] s) {
        int a = 0,b = s.length-1;
        while (a < b){
            swap(s,a,b);
            a++;
            b--;
        }
    }
    private void swap(char[] s, int a,int b){
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }

}

package Leetcode.双指针;

public class LeetCode557反转字符串中的单词3 {
    public static String reverseWords(String s) {
        int a = 0;
        char[] words = s.toCharArray();
        for (int i = 0; i < words.length; i++){
            if ( i == words.length-1 || words[i+1] == ' '){
                reverseString(words,a,i);
                a = i+2;
            }
        }
        return String.valueOf(words);

    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public static void reverseString(char[] s,int a,int b) {
        while (a < b){
            swap(s,a,b);
            a++;
            b--;
        }
    }
    private static void swap(char[] s, int a,int b){
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }
}

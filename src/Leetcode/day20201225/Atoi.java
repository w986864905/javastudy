package Leetcode.day20201225;

/**
 * @ClassName Atoi
 * @Description
 * @Author 王品峰
 * @Date 2020/12/25 10:40
 * @Version 1.1.
 */
public class Atoi {
    public static void main(String[] args) {

        System.out.println(isMatch("aa","a*"));
    }
    public static int myAtoi(String s) {
        //判断字符串是否为空
        if (null == s || "".equals(s.replace(" ","")) || s.length() == 0){
            return 0;
        }
        //第一个非空字符
        int start = 0;
        while (s.charAt(start) == 32){
            start++;
        }
        s = s.substring(start);
        boolean flag = true;
        String rs;
        //判断首字符是否为数字
        if (Character.isDigit(s.charAt(0))){
            rs = s;
        }else if (s.charAt(0) == '+' || s.charAt(0) == '-'){
            if (s.length() == 1 || !Character.isDigit(s.charAt(1))){
                return 0;
            }
            flag = s.charAt(0) == '+';
            rs = s.substring(1);
        }else {
            return 0;
        }
        //字符串
        StringBuilder sbb = new StringBuilder();
        int i =0;
        while (i < rs.length() && Character.isDigit(rs.charAt(i))){
            sbb.append(rs.charAt(i));
            i++;
        }
        try{
            return Integer.parseInt(flag?sbb.toString():"-"+sbb.toString());
        }catch (Exception e){
            return flag?Integer.MAX_VALUE:Integer.MIN_VALUE;
        }

    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
//        int s = 0;
//        while (x > s) {
//            s = s * 10 + x % 10;
//            x /= 10;
//        }
//        return x == s || x == s / 10;
        char[] chars = (x+"").toCharArray();
        int m = 0 ;
        int n = chars.length-1;
        while (chars[m] == chars[n] && m < chars.length/2){
            m++;
            n--;
        }
        return m == chars.length/2;
    }

//    public static boolean isMatch(String s, String p) {
//        char[] chars = s.toCharArray();
//        char[] relax = p.toCharArray();
//        int m = 0;
//        int n = 0;
//        while (m < chars.length) {
//            if (n == relax.length){
//                return false;
//            }
//            if (relax[n] == '.' || relax[n] == chars[m]){
//                if (n < relax.length-1 && relax[n] == '.' && relax[n+1] == '*'){
//                    return true;
//                }
//                n++;
//                m++;
//            }else if (relax[n] == '*'){
//                if (chars[m] == chars[m-1] && chars[m] == relax[n-1]){
//                    m++;
//                    if (m == chars.length){
//                        n++;
//                    }
//                }else {
//                    n++;
//                }
//            }else {
//                if(n < relax.length-1 && relax[n+1] == '*'){
//                    m++;
//                    n+=2;
//                }else {
//                    return false;
//                }
//            }
//        }
//        if (n == relax.length){
//            return true;
//        }
//        return false;
//    }
    public static boolean isMatch(String s, String p){
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }


    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}

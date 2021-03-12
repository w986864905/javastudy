package Leetcode.双指针;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode76最小覆盖子串
 * @Description
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * @Author 王品峰
 * @Date 2021/2/24 10:34
 * @Version 1.1.
 */
public class LeetCode76最小覆盖子串 {
    public static String minWindow2(String s, String t) {
        Map<Character, Integer> ori = new HashMap<>(t.length());
        Map<Character, Integer> num = new HashMap<>(t.length());
        if (s.equals(t)){
            return s;
        }
        if (s.length() < t.length()){
            return "";
        }
        int j = 0,k = s.length()-1;
        char[] chars = s.toCharArray();
        //初始化ori\num
        for (char c:t.toCharArray()) {
            ori.put(c,ori.getOrDefault(c,0)+1);
        }
        for (char c : chars) {
            if (ori.containsKey(c)){
                num.put(c,num.getOrDefault(c,0)+1);
            }
        }
        //如果count不等于t.length()说明无解
        if (!check(ori,num)){
            return "";
        }else {
            while (j <= k-t.length()+1){
                if (ori.containsKey(chars[j])){
                    if (ori.get(chars[j]).equals(num.get(chars[j]))){
                        //表明j只能到此
                        while (j <= k-t.length()+1){
                            if (ori.containsKey(chars[k])){
                                if (ori.get(chars[k]).equals(num.get(chars[k]))){
                                    //表明k只能到此
                                    return s.substring(j,k+1);
                                }
                                num.replace(chars[k],num.get(chars[k])-1);
                            }
                            k--;
                        }
                    }
                    num.replace(chars[j],num.get(chars[j])-1);
                }
                j++;
            }
        }
        return "";
    }
    public static String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>(t.length());
        Map<Character, Integer> window = new HashMap<>(t.length());
        int tLen = t.length();//目标字符串
        int sLen = s.length();
        if(tLen == 0 || sLen==0) return "";

        //先把t中的字符放到need表中，计数
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int len = Integer.MAX_VALUE, start = 0;
        int valid = 0; //已经匹配成功的字符种类数（非字符个数）

        //当右指针去到字符串末尾前
        while (right < sLen) {
            char c = s.charAt(right);
            right++;//右指针向右滑

            //如果右指针现在滑到的字符是目标字符串的一个，那么更新窗口中的数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if(window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            //窗口开始从左边收缩
            while (valid == need.size() ) {
                if (right - left  < len) {
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    private static boolean check(Map<Character, Integer> ori,Map<Character, Integer> num){
        for (Character key : ori.keySet()) {
            if (num.getOrDefault(key,0) < ori.get(key)){
                return false;
            }
        }
        return true;
    }




    public static void main(String[] args) {
        String s = "cabwefgewcwaefgcf", t = "cae";
        System.out.println(minWindow(s,t));
    }
}

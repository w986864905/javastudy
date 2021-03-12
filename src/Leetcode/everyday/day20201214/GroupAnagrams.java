package Leetcode.everyday.day20201214;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName groupanagrams
 * @Description
 * @Author 王品峰
 * @Date 2020/12/14 15:41
 * @Version 1.0.7
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> result = new HashMap<>(16);
        
        return null;
    }
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }

    public int reverse(int x) {
        //判断x大于0 true，小于0 false
        boolean flag = x > 0;
        if (x<-Integer.MAX_VALUE){
            return  0;
        }
        //将x转为字符串
        String s = new StringBuilder(Math.abs(x)+"").reverse().toString();
        if (Long.parseLong(s) > Integer.MAX_VALUE){
            return 0;
        }
        return flag ? Integer.parseInt(s):-Integer.parseInt(s);
    }
    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().reverse(-2147483648));
    }
}

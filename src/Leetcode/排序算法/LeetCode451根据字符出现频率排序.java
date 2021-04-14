package Leetcode.排序算法;

import com.google.common.collect.Lists;

import javax.xml.stream.events.Characters;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ClassName LeetCode451根据字符出现频率排序
 * @Description 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *  桶排序按数量多少
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * @Author 王品峰
 * @Date 2021/4/13 9:51
 * @Version 1.0
 */
public class LeetCode451根据字符出现频率排序 {
    public static String frequencySort(String s) {
        Map<Integer,Integer> charMap = new HashMap<>();
        for(char c:s.toCharArray()){
            charMap.put(c-'A',charMap.getOrDefault(c-'A',0)+1);
        }
        int size = charMap.size();
        int[][] charArray = new int[size][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : charMap.entrySet()) {
            charArray[index][0] = entry.getKey();
            charArray[index][1] = entry.getValue();
            index++;
        }
        Arrays.sort(charArray, new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return n[1]-m[1];
            }
        });
        char[] result = new char[s.length()];
        index = 0;
        for (int[] num:charArray){
            for (int i = 0;i < num[1];i++){
                result[index++] = (char) ('A'+num[0]);
            }
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }
}

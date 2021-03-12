package Leetcode.双指针;

import org.springframework.util.comparator.Comparators;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode524通过删除字母匹配到字典里最长单词
 * @Description
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 输出:
 * "apple"
 * @Author 王品峰
 * @Date 2021/2/26 15:56
 * @Version 1.1.
 */
public class LeetCode524通过删除字母匹配到字典里最长单词 {

    public static String findLongestWord(String s, List<String> dictionary) {
        int[] nums = new int[dictionary.size()];
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < dictionary.size(); j++) {
                if (nums[j] < dictionary.get(j).length() && s.charAt(i) == dictionary.get(j).charAt(nums[j])  ){
                    nums[j]++;
                }
                if (nums[j] == dictionary.get(j).length()){
                    if (nums[j] > result.length() || (nums[j] == result.length() && result.compareTo(dictionary.get(j))>0)){
                        result = dictionary.get(j);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] dic = {"abe","abc"};
        String s = "abce";
        System.out.println(findLongestWord(s, Arrays.asList(dic)));
    }
}

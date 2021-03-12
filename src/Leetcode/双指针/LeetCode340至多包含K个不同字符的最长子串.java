package Leetcode.双指针;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode340至多包含K个不同字符的最长子串
 * @Description
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * @Author 王品峰
 * @Date 2021/2/26 17:26
 * @Version 1.1.
 */
public class LeetCode340至多包含K个不同字符的最长子串 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        int j = 0;
        int maxLength = 0;
        Map<Character, Integer> hash = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && hash.size() <= k) {
                if (hash.size() == k && !hash.containsKey(s.charAt(j))) {
                    break;
                }
                hash.put(s.charAt(j), hash.getOrDefault(s.charAt(j), 0) + 1);
                j++;
            }

            maxLength = Math.max(maxLength, j - i);

            int currentCount = hash.get(s.charAt(i)) - 1;
            if (currentCount == 0) {
                hash.remove(s.charAt(i));
            } else {
                hash.put(s.charAt(i), currentCount);
            }
        }

        return maxLength;
    }
}

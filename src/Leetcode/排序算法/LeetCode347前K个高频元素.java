package Leetcode.排序算法;

import Leetcode.common.ArraysUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @ClassName LeetCode347前K个高频元素
 * @Description 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * @Author 王品峰
 * @Date 2021/3/30 16:10
 * @Version 1.1.
 */
public class LeetCode347前K个高频元素 {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> numCount = new HashMap<>();
        for (int i:nums){
            numCount.put(i,numCount.getOrDefault(i,0)+1);
        }
        int[][] num = new int[numCount.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            num[index][0] = entry.getKey();
            num[index][1] = entry.getValue();
            index++;
        }
        index--;
        Arrays.sort(num, new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = num[index--][0];
            if (index < 0){
                break;
            }
        }
        return result;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    public static void main(String[] args) {
//        String defaultUrl = "http://10.19.151.142:9999/group1/M00/0B/39/ChOXkGBlkyuAKoUhAABJf86JuaA923.png";
//        String url = "http://10.19.151.142:9999/group1/M00/0B/39/ChOXkGBlkyuAKoUhAABJf86Jua.png";
//
//        base64(url);

    }
    private static Integer gerThreeSplitIndex(String url){
        int k = 0;
        for(int i =0;i < url.length();i++){
            if (url.charAt(i) == '/'){
                k++;
                if (k == 3){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void base64(String str) {
        byte[] bytes = str.getBytes();

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Base 64 加密后：" + encoded);

        //Base64 解密
        String decodeStr = new String(Base64.getDecoder().decode("aHR0cDovLzEwLjE5LjE1MS4xNDI6OTk5OS9ncm91cDEvTTAwLzBCLzM5LT1hrR0Jsa3l1QUtvVWhBQUJKZjg2SnVhLnBuZw=="), StandardCharsets.UTF_8);
        System.out.println("Base 64 解密后：" + decodeStr);

        System.out.println();


    }
}

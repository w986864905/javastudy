package Leetcode.贪心法;

import study1.list;

import java.util.*;

/**
 * @ClassName LeetCode763划分字母区间
 * @Description
 * @Author 王品峰
 * @Date 2021/1/25 16:11
 * @Version 1.1.
 */
public class LeetCode763划分字母区间 {
    public static List<Integer> partitionLabels3(String S) {
        Long starttime = System.currentTimeMillis();
        Map<Character,Integer> last = new HashMap<>(16);
        char[] arr = S.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            last.put(arr[i],i);
        }
        if (last.size() == 1){
            return Collections.nCopies(1, arr.length);
        }
        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i ++) {
            end = Math.max(end, last.get(arr[i]));
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        Long endtime = System.currentTimeMillis();
        System.out.println("map cost:"+(endtime-starttime)+"  ms");
        return result;
    }
    public static List<Integer> partitionLabels2(String S) {
        Long starttime = System.currentTimeMillis();
        int[] last = new int[2000];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        Long endtime = System.currentTimeMillis();
        System.out.println("array cost:"+(endtime-starttime)+"  ms");
        return partition;
    }
    public static List<Integer> partitionLabels(String S) {
        Map<Character,Integer> first = new HashMap<>(16);
        Map<Character,Integer> last = new HashMap<>(16);
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (first.containsKey(c)){
                last.put(c,i+1);
            }else {
                first.put(c,i+1);
                last.put(c,i+1);
            }
        }
        char[] arr = S.toCharArray();
        if (first.size() == 1){
            return Collections.nCopies(1, S.length());
        }
        List<int[]> list = new ArrayList<>(first.size());
        first.forEach((key,value)->{
            list.add(new int[]{value,last.get(key)});
        });
        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]){
                    return 1;
                }else if (o1[1] < o2[1]){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        return mergeErase(list,S.length()+1);
    }

    private static List<Integer> mergeErase(List<int[]> list,int end){
        List<Integer> result = new ArrayList<>();
        int pre = list.get(list.size()-1)[0];
        for (int i = list.size()-2; i >= 0; i--) {
            int[] arr = list.get(i);
            if (arr[1] < pre){
                result.add(end - pre);
                end = pre;
            }
            pre = Math.min(pre,arr[0]);
        }
        result.add(end-1);
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            char c = (char) (new Random().nextInt(2000)+'a');
            sbb.append(c);
        }
        String s = sbb.toString();
        System.out.println(partitionLabels3(s));
        System.out.println(partitionLabels2(s));

    }
}

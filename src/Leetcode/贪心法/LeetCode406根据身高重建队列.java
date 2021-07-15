package Leetcode.贪心法;

import Leetcode.common.ArraysUtil;

import java.util.*;

/**
 * @ClassName LeetCode406根据身高重建队列
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * @Description
 * @Author 王品峰
 * @Date 2021/1/26 15:42
 * @Version 1.1.
 */
public class LeetCode406根据身高重建队列 {
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4},{7,1},{5,2},{6,1},{5,0}};
        int[][] people2 = people;
        int[][] people3 = people;
        int[][] people4 = people;
        long start = System.currentTimeMillis();
        Arrays.sort(people2, new Comparator<int[]>() {
            @Override
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        start = System.currentTimeMillis();
        Arrays.sort(people3, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person2[0] - person1[0];
            } else {
                return person1[1] - person2[1];
            }
        });
        end = System.currentTimeMillis();
        System.out.println(end-start);

        start = System.currentTimeMillis();
        Comparator<int[]> comp = Comparator.comparingInt(a -> a[1]);
        comp.reversed();
        comp.thenComparing(b -> b[0]);
        Arrays.sort(people4,comp);
        end = System.currentTimeMillis();
        System.out.println(end-start);


        System.out.println(ArraysUtil.toString(people4));
    }

}

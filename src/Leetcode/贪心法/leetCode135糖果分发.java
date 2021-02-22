package Leetcode.贪心法;

import Leetcode.common.ArraysUtil;

import java.util.Arrays;

/**
 * @ClassName leetCode135
 * @Description
 * @Author 王品峰
 * @Date 2021/1/21 16:32
 * @Version 1.1.
 */
public class leetCode135糖果分发 {
    /**
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     *
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。
     * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int[] num = new int[ratings.length];
        num[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]){
                num[i] = num[i-1]+1;
            }else {
                num[i] = 1;
            }
        }
        for(int i = ratings.length -2 ; i >= 0; i--){
            if(ratings[i] > ratings[i+1] && num[i] <= num[i+1]){
                num[i] = num[i+1] +1;
            }
        }
        return Arrays.stream(num).sum();
    }

    public static void main(String[] args) {
        int[] ratings = {1,3,2,2,1};
        System.out.println(candy(ratings));
    }

}

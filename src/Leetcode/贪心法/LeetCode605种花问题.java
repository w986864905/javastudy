package Leetcode.贪心法;

/**
 * @ClassName LeetCode605种花问题
 * @Description
 * @Author 王品峰
 * @Date 2021/1/22 14:21
 * @Version 1.1.
 */
public class LeetCode605种花问题 {
    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1){
            return (flowerbed[0] == 0 && n <= 1) ||  (flowerbed[0] == 1 && n == 0);
        }
        int num = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0){
                if (i == 0){
                    if (flowerbed[i+1] == 0){
                        flowerbed[i] = 1;
                        num++;
                    }
                }else if (i == flowerbed.length-1){
                    if (flowerbed[i-1] == 0){
                        flowerbed[i] = 1;
                        num++;
                    }
                }else{
                    if (flowerbed[i-1] == 0 && flowerbed[i+1] == 0){
                        flowerbed[i] = 1;
                        num++;
                    }
                }
            }
            if(num >= n) {
                return true;
            }
        }
        return false;
    }
}

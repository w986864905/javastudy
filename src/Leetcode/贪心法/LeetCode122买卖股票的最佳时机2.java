package Leetcode.贪心法;

/**
 * @ClassName LeetCode122买卖股票的最佳时机 II
 * @Description
 * @Author 王品峰
 * @Date 2021/1/26 10:41
 * @Version 1.1.
 */
public class LeetCode122买卖股票的最佳时机2 {
    public static int maxProfit(int[] prices) {
        int total = 0;
        for (int i = 1; i< prices.length; i++){
            if (prices[i]-prices[i-1] > 0){
                total += prices[i]-prices[i-1];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[] price = {7,6,4,3,1};
        System.out.println(maxProfit(price));
    }
}

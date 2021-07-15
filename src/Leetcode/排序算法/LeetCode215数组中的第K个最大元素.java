package Leetcode.排序算法;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

import java.util.*;

/**
 * @ClassName LeetCode215数组中的第K个最大元素
 * @Description
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * @Author 王品峰
 * @Date 2021/3/22 15:00
 * @Version 1.1.
 */
public class LeetCode215数组中的第K个最大元素 {
    public static int findKthLargest(int[] nums, int k) {
        //1.构建大顶堆
        for(int i=nums.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(nums,i,nums.length);
        }
        k--;
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=nums.length-1;j>0;j--){
            if (k-- == 0){
                break;
            }
            //将堆顶元素与末尾元素进行交换
            swap(nums,0,j);
            //重新对堆进行调整
            adjustHeap(nums,0,j);
        }
        return nums[0];
    }

    public static void main(String[] args) {
        //System.out.println(calculateEarlier(0,0,0));
    }

    /**
     * 计算同环比
     *
     * @Author : 王品峰
     * @Date : 2020/10/10 14:13
     * @return :Map<String, String>
     */
    private static Map<String, String> calculateEarlier(Integer currentNumber, Integer monthEarlierNumber,
                                                 Integer yearEarlierNumber) {
        Map<String, String> earlierMap = new HashMap<>(2);
        double monthEarlier = 0.0;
        double yearEarlier = 0.0;
        // 组装环比
        if (monthEarlierNumber != 0) {
            monthEarlier = (currentNumber - monthEarlierNumber) / (double)monthEarlierNumber;
        }
        if (currentNumber > 0 && monthEarlierNumber == 0) {
            monthEarlier = 1.0;
        }
        // 组装同比
        if (yearEarlierNumber != 0) {
            yearEarlier = (currentNumber - yearEarlierNumber) / (double)yearEarlierNumber;
        }
        if (currentNumber > 0 && yearEarlierNumber == 0) {
            yearEarlier = 1.0;
        }
        earlierMap.put("monthEarlier",
                String.format("%.2f", (monthEarlier * 100)));
        earlierMap.put("yearEarlier",
                String.format("%.2f", (yearEarlier * 100)));
        return earlierMap;
    }

    public void buhuo2(){
        new LeetCode215数组中的第K个最大元素().buhuo();
        System.out.println("true");
    }

    public JSONArray buhuo(){
        try{
            JSONArray arr = JSON.parseArray("{11");
            return arr;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }




    private static void swap(int[] nums, int a, int b) {
        int temp;
        temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    private static void adjustHeap(int []nums,int i,int length){
        //先取出当前元素i
        int temp = nums[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for(int k=i*2+1;k<length;k=k*2+1){
            //如果左子结点小于右子结点，k指向右子结点
            if(k+1<length && nums[k]<nums[k+1]){
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if(nums[k] >temp){
                nums[i] = nums[k];
                i = k;
            }else{
                break;
            }
        }
        //将temp值放到最终的位置
        nums[i] = temp;
    }

}

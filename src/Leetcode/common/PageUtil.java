package Leetcode.common;

/**
 * @ClassName PageUtil
 * @Description
 * @Author 王品峰
 * @Date 2021/3/23 13:43
 * @Version 1.1.
 */
public class PageUtil {
    private static Integer DEFAULT_PAGE_NUM = 1;
    private static Integer DEFAULT_PAGE_SIZE = 10;
    private static Integer checkPageNum(Integer num){
        return checkPageNum(num,DEFAULT_PAGE_NUM);
    }

    private static Integer checkPageSize(Integer size){
        return checkPageSize(size,DEFAULT_PAGE_SIZE);
    }

    private static Integer checkPageNum(Integer num,Integer defaultNum){
        if (null == num || 0 == num) {
            return defaultNum;
        }
        return num;
    }

    private static Integer checkPageSize(Integer size,Integer defaultSize){
        if (null == size || 0 == size) {
            return defaultSize;
        }
        return size;
    }
}

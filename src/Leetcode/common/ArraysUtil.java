package Leetcode.common;

import com.sun.istack.internal.NotNull;
import org.springframework.util.StringUtils;
import study1.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ArraysUtil
 * @Description
 * @Author 王品峰
 * @Date 2021/1/21 16:36
 * @Version 1.1.
 */
public class ArraysUtil {
    /**
     * 寻找最小的数组下标
     * @Author : 王品峰
     * @Date : 2021/1/21 16:43
     * @param arr
     * @return int
     */
    public static int findMin(int[] arr){
        if (null == arr || arr.length <= 1){
            return 0;
        }
        int min = arr[0];
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min){
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * 输出一维数组
     * @Author : 王品峰
     * @Date : 2021/1/21 16:43
     * @param a
     * @return String
     */
    @NotNull
    public static String toString(@NotNull int[] a){
        if (a == null) {
            return "null";
        }
        int iMax = a.length - 1;
        if (iMax == -1) {
            return "{}";
        }
        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }

    /**
     * 输出二维数组
     * @Author : 王品峰
     * @Date : 2021/1/21 16:43
     * @param array
     * @return String
     */
    public static String toString(int[][] array){
        if (null == array || array.length <= 1){
            return null;
        }
        return Arrays.stream(array).map(ArraysUtil::toString).collect(Collectors.joining(",","{","}"));
    }
}

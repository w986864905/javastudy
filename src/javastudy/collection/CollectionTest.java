package javastudy.collection;

import java.util.*;

/**
 * @ClassName CollectionTest
 * @Description
 * @Author 王品峰
 * @Date 2021/7/7 17:18
 * @Version 1.0
 */
public class CollectionTest {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
//        HashMap map = new HashMap(5);
//        map.put("1","1");
//        HashMap map1 = new HashMap(2,1);
//        for (int i = 0; i < 100; i++) {
//            System.out.println((4 - 1) & (i+"").hashCode());
//        }

//        map1.put("4","1");
//        map1.put("1","1");
//        map1.put("0","1");
//        map1.put("5","1");
//        System.out.println(tableSizeFor(5));
//        int[] array1 = {1,2,3,4,5};
//        int[] array2 = array1;
//        array2[0] = 2;
//        System.out.println(Arrays.toString(array1));
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(4,"wh");
        treeMap.put(3,"wh");
        treeMap.put(2,"zx");
        treeMap.put(5,"xx");
        System.out.println(treeMap);
    }
    private static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

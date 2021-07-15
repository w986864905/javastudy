package javastudy.collection;

import java.util.*;

public class list {
    public static void main(String[] args) {

        List<String> lists = new ArrayList<>();
        lists.add("a");
        lists.add("b");
        lists.add(null);
        lists.add("c");
        System.out.println(lists.size());
        //foreach循环输出
        for (String i: lists) {
            System.out.print(i);
        }
        System.out.println();
        //for循环输出效率低
        for (int i = 0;i < lists.size();i++){
            System.out.print(lists.get(i));
        }
        System.out.println();
        //迭代器输出
        Iterator<String> iter = lists.iterator();
        while (iter.hasNext()){
            System.out.print(iter.next());
        }
        System.out.println();
        //list转数组Object
        Object[] array = lists.toArray();
        for (Object arr:array
             ) {
            System.out.print(arr);
        }
        //list转数组带类型
        String[] array1 = lists.toArray(new String[lists.size()]);
        Arrays.toString(array1);

    }
}

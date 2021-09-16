package chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王品峰
 * @DateTime 2020/7/22 9:24
 * @Description
 */
public class streamStudy {

    public static void main(String[] args) {

//        System.out.println(userList.stream().filter(user -> user.getSex().equals(0))
//                .count());
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(1);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(5);
        list2.add(7);
        list2.add(6);
        list2.add(8);
        list2.add(0);
//        Stream.of(list1).flatMap(Collection::stream).distinct()
//                .collect(Collectors.toList())
//                .stream().forEach(i -> System.out.println(i));
//        Stream.of(list1,list2).flatMap(Collection::stream)
//                .sorted().forEach(i -> System.out.println(i));
//        System.out.println(Stream.of(list1,list2).skip(1).flatMap(Collection::stream)
//                .max(Integer::compareTo).get().toString());
//        list1.removeAll(list1.stream().filter(l->l.equals(1)).collect(Collectors.toList()));
//        list1.stream().forEach(System.out::println);
        Integer i = 1;
        System.out.println(i.toString());
    }
}

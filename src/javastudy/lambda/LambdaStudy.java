package javastudy.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangpinfeng on 2020/7/17.
 */
public class LambdaStudy {
    public static void main(String[] args) {
        List<String> list= new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");
        list.add("a");
        list.add("c");
        //list.stream().forEach(i-> System.out.println(i));
        StringBuilder sb = new StringBuilder("");
        sb.append("a").append("b").append("c");
        System.out.println(sb.toString().substring(1));
    }
}

package test;

import com.alibaba.fastjson.JSONArray;

/**
 * @ClassName JsonTest
 * @Description
 * @Author 王品峰
 * @Date 2021/9/7 17:47
 * @Version 1.0
 */
public class JsonTest {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.get(10);
        System.out.println(jsonArray.toJSONString());
    }
}

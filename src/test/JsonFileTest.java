package test;

import com.alibaba.fastjson.JSONObject;
import utils.file.FileUtils;

import java.util.*;

/**
 * @ClassName JsonFileTest
 * @Description
 * @Author 王品峰
 * @Date 2021/4/28 18:57
 * @Version 1.1.
 */
public class JsonFileTest {

    public static void main(String[] args) {
        List<String> list = FileUtils.fileToList("C:\\Users\\wangpinfeng\\Desktop\\redis\\33-7001\\appendonly.aof");
        Map<String,String> result = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i);
            if (key.contains("dataFish") && !key.contains("dataFisher") && list.get(i-2).equals("HSET")){
                result.put(key,list.get(i+4));
            }
        }
        String s = JSONObject.toJSONString(result);
        List<String> output = new ArrayList<>();
        output.add(s);
        FileUtils.ListToFile(output,"C:\\Users\\wangpinfeng\\Desktop\\redis\\33-7001\\33-7001.json");
    }

//    public static void main(String[] args) {
//        String regex = "[^\\u4e00-\\u9fa5]+";
//        Pattern pattern = Pattern.compile(regex);
//        String json = FileUtils.ReadFile("C:\\Users\\wangpinfeng\\Desktop\\rediscsv\\337001.json");
//        JSONObject jsonObject = JSON.parseObject(json);
//        Map<String,Object> map = new HashMap<>();
//        Iterator it =jsonObject.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
//            String key = entry.getKey();
//            if (key.contains("dataFish") && pattern.matcher(key).matches()){
//                map.put(entry.getKey(), entry.getValue());
//            }
//        }
//        String s = JSONObject.toJSONString(map);
//        List<String> list = new ArrayList<>();
//        list.add(s);
//        FileUtils.ListToFile(list,"C:\\Users\\wangpinfeng\\Desktop\\rediscsv\\337001dataFisher.json");
//    }
}
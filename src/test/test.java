package test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;


/**
 * @author 王品峰
 * @DateTime 2020/8/4 10:56
 * @Description
 */
public class test {
    public static List<String> txt2String(String files){
        File file = new File(files);
        List<String> resList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] list =  s.split("\\s{1,}");
                for (int i = 0;i < list.length; i++){
                    resList.add(list[i]);
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resList;
    }
    public String timeFormat(String rool,Date date){
        return null;
    }
    public static void fiveNumber(int length,int count){
        Set<String> set = new HashSet<String>();
        Random random = new Random();
        while(set.size() < count){
            String str = "";
            for (int i = 0; i < length; i++) {
                boolean b = random.nextBoolean();
                if(b){
                    int choice = 97;//随机到65：大写字母  97：小写字母
                    str += (char)(choice+random.nextInt(26));
                }else{
                    str += String.valueOf(random.nextInt(10));
                }
            }
            set.add(str);
        }
        System.out.println(set.toString());
    }
    public static void fiveNumber3(int length,int count){
        Set<String> set = new HashSet<String>();
        Random random = new Random();
        while(set.size() < count){
            String str = "";
            for (int i = 0; i < length; i++) {
                str += String.valueOf(random.nextInt(10));
            }
            set.add(str);
        }
        System.out.println(set.toString());
    }
    public static void fiveNumber2(){
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();
        while(set.size() < 5){
            int ind = new Random().nextInt(10);
            set.add(ind);
        }
        Integer[] inds = set.toArray(new Integer[set.size()]);
        System.out.println(set.toString());
        for (Integer item:inds) {
            System.out.println(item);
        }
    }
    private static String randomNumberAndCase(Integer length,Integer falg){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        if (falg == 0){//falg=0小写
            for (int i = 0; i < length; i++) {
                boolean b = random.nextBoolean();
                if(b){
                    int choice = 97;
                    sb.append((char)(choice+random.nextInt(26)));
                }else{
                    sb.append(random.nextInt(10));
                }
            }
        }else if (falg == 1){//falg=1大写
            for (int i = 0; i < length; i++) {
                boolean b = random.nextBoolean();
                if(b){
                    int choice = 65;
                    sb.append((char)(choice+random.nextInt(26)));
                }else{
                    sb.append(random.nextInt(10));
                }
            }
        }else {//falg=2大小写
            for (int i = 0; i < length; i++) {
                boolean b = random.nextBoolean();
                if(b){
                    int choice = random.nextBoolean() ? 65 : 97;
                    sb.append((char)(choice+random.nextInt(26)));
                }else{
                    sb.append(random.nextInt(10));
                }
            }
        }
        return sb.toString();
    }
    private static boolean isEmpty(Object obj){
        if (null == obj){
            return true;
        }
        if (obj instanceof List){
            if (((List<Object>)obj).isEmpty()){
                return true;
            }
            return ((List<Object>)obj).stream().allMatch(test::isEmpty);
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try{
                if (null != f.get(obj)){
                    return false;
                }
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String[] res = "11111".split(",");
        List<String> newList = new ArrayList<>();
        List<String> oldList = new ArrayList<>(Arrays.asList(res));
        oldList.add("1");
        oldList.add("2");
        oldList.add("3");
        newList.add("1");
        newList.add("2");
        newList.add("4");
        oldList.retainAll(newList);
        if (oldList.size() > 0){
            System.out.println(oldList);
        }else {
            System.out.println("无");
        }

    }


}

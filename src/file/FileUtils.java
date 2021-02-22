package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王品峰
 * @DateTime 2020/8/28 10:33
 * @Description
 */
public class FileUtils {
    /**
     * @Method: fileToArray
     * @Description: 将文件内容转成list
     * @param path 文件路径
     * @Return:
     * @Author: chenlu
     * @Date 2020/8/27 15:08
     * @Version:  1.0
     */
    public static List<String> fileToList(String path){
        File file = new File(path);
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

    /**
     * @Method: fileToArray
     * @Description: 将list写入文件
     * @param list 写入的数据
     * @param path 文件路径
     * @Return:
     * @Author: chenlu
     * @Date 2020/8/27 15:08
     * @Version:  1.0
     */
    public static void ListToFile(List<String> list,String path){
        File file = new File(path);
        List<String> resList = new ArrayList<>();
        try{
            FileWriter fw =  new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);//构造一个BufferedReader类来读取文件
            for (String item : list) {
                bw.write(item+"\n");
            }
            bw.close();
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
//            try {
//        text.forEach(t -> {
//            try {
//                writer.write(t);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }catch (Exception e) {
//        throw new IOException("写入文件错误");
//    } finally {
//        writer.close();
//    }

    /**
     * @Method: fileFormattingChinese
     * @Description: 将文件格式化成仅汉字无符号
     * @param path 文件路径
     * @Return: void
     * @Author: wangdehonga
     * @Date 2020/8/28 11:25
     * @Version:  1.0
     */
    public static void fileFormattingChinese(String path) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> text = new ArrayList<>();
            try {
                String contentLine = reader.readLine();
                while( contentLine != null) {
                    contentLine = contentLine.replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×\\s]","");
                    if (!"".equals(contentLine)) {
                        text.add(contentLine+"\n");
                    }
                    contentLine = reader.readLine();
                }
            }catch (IOException ioe) {
                throw new IOException("读取文件错误");
            }finally {
                reader.close();
            }
            ListToFile(text,path);
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

    /**
     * @Method: fileChineseSpaceTabulation
     * @Description: 将初始化后的纯汉字文本用reglx格式化制表
     * @param path 文件路径
     * @param reglx 分隔符
     * @Return: void
     * @Author: wangdehonga
     * @Date 2020/8/28 14:13
     * @Version:  1.0
     */
    public static void fileChineseSpaceTabulation(String path, String reglx) {
        fileFormattingChinese(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> text = new ArrayList<>();
            try {
                String contentLine = reader.readLine();
                while( contentLine != null) {
                    StringBuffer tb = new StringBuffer();
                    for (int i = 0; i < contentLine.length(); i++) {
                        tb.append(contentLine.charAt(i));
                        if (i < contentLine.length() - 1) {
                            tb.append(reglx);
                        }
                    }
                    if (!"".equals(tb.toString())) {
                        text.add(tb.toString()+"\n");
                    }
                    contentLine = reader.readLine();
                }
            }catch (IOException ioe) {
                throw new IOException("读取文件错误");
            }finally {
                reader.close();
            }
            ListToFile(text,path);
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

}

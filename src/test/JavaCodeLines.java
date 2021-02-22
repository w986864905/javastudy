package test;


import java.io.*;

/**
 * @author 王品峰
 * @DateTime 2020/7/23 14:28
 * @Description
 */
public class JavaCodeLines {
    private static final String PROJECT_DIR = "E:\\工作项目\\if-activity";
    private static int fileNum = 0;
    private static int lineNum = 0;
    private static void listNext(File dir){
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory()){
                listNext(files[i]);
            }else {
                try{
                    if (files[i].getName().endsWith(".java")){
                        fileNum++;
                        BufferedReader br = new BufferedReader(new FileReader(files[i]));
                        while (br.readLine() != null){
                            lineNum++;
                        }
                    }
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        File root = new File(PROJECT_DIR);
//        listNext(root);
//        System.out.println("Java files number"+fileNum);
//        System.out.println("Java code lines"+lineNum);
//    }
    private static int num1 = 0;
    private static int num2 = 0;
    private static int num3 = 0;
    public static void main(String[] args) {
        new JavaCodeLines ().showFile("E:\\工作项目\\if-activity");//匿名du对象 }
        //读取所有文件和zhi文件夹
        System.out.println("文件数" + num1);
        System.out.println("行数" + num3);
        System.out.println("目录数" + num2);
    }

    public void showFile (String dir){
        File f = new File(dir);
        File fs[] = f.listFiles();//系统文件返回null
        if (fs != null) {
            for (int i = 0; i < fs.length; i++) {
                File currentFile = fs[i];
                if (currentFile.isFile()) {//文件，直接显示
                    num1 += 1;
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(currentFile));
                        while (br.readLine() != null){
                            num3++;
                        }
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                } else {
                    num2 += 1;
                    showFile(currentFile.getAbsolutePath());
                }
            }
        }
    }
}

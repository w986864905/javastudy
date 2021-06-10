package test;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import com.spire.xls.*;
import com.spire.xls.collections.WorksheetsCollection;
import file.FileUtils;
import utils.file.ExcelUtil;

import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * @ClassName ExcelTest
 * @Description
 * @Author 王品峰
 * @Date 2021/4/9 9:19
 * @Version 1.1.
 */
public class ExcelTest {

    private static final Map<String,String> TYPE_MAP = new HashMap<String,String>(){{
        put("01","人（各类人员信息）");
        put("02","案（各类案件信息）");
        put("03","物（各类物件信息）");
        put("04","警（微警务+警内）");
        put("05","单位（单位信息+地址信息）");
        put("06","轨迹（各类行为轨迹）");
        put("07","社会资源数据（其他部门数据）");
    }};

    public static void main(String[] args) {

        addLicense();
        // String[] paths = {"E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0409_NEW"};
        //copyExcelToSheets(dirFilePaths("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0409_3\\分系统表字段_接入"));
        //addHyperlinkReturn(dirFilePaths("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0409_3\\分系统表字段_标准"));
        //cellStyle(dirFilePaths("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0409_3\\分系统表字段_标准"));
        //matchColumn();
        //mergeExcelToSheets();
        exportSql();
//        List<String> result = ExcelUtil.readColumn("C:\\Users\\wangpinfeng\\Desktop\\接入.xlsx",3,1,569);
//        System.out.println(result);
    }

    private static void exportSql(){
        //创建一个新的Excel文档
        Workbook indexBook = new Workbook();
        //载入文档
        indexBook.loadFromFile("C:\\Users\\wangpinfeng\\Desktop\\表相关sql\\新表.xlsx");

        for (int j = 0; j < indexBook.getWorksheets().size();j++){
            Worksheet targetSheet = indexBook.getWorksheets().get(j);
            List<String> list1  = new ArrayList<>();
            list1.add("set parquet_file_size = 64M;");
            list1.add(StrUtil.format("CREATE TABLE if not exists standard.std_{} (",targetSheet.getName().toLowerCase()));
            String format = "  {} {} COMMENT '{}',";
            String lastFormat = "  {} {} COMMENT '{}'";
            String list2format = ",t.{}";
            String list2FirstFormat = "t.{}";
            List<String> list2  = new ArrayList<>();
            list2.add(StrUtil.format("insert overwrite standard.std_{}",targetSheet.getName().toLowerCase()));
            list2.add("select");
            for (int k = 0; k < targetSheet.getLastRow(); k++){

                String columnEn = StrUtil.trim(targetSheet.getRange().get(k + 1, 1).getText());
                String columnCn = StrUtil.trim(targetSheet.getRange().get(k + 1, 3).getText());
                String columnType = StrUtil.trim(targetSheet.getRange().get(k + 1, 2).getText().toUpperCase());
                if (k == 0){
                    list2.add(StrUtil.format(list2FirstFormat,columnEn.toLowerCase()));
                }else {
                    list2.add(StrUtil.format(list2format,columnEn.toLowerCase()));
                }
                if (k == targetSheet.getLastRow()-1){
                    list1.add(StrUtil.format(lastFormat,columnEn.toLowerCase(),columnType,columnCn));
                }else {
                    list1.add(StrUtil.format(format,columnEn.toLowerCase(),columnType,columnCn));
                }
            }
            list1.add(")");
            list2.add(StrUtil.format("from  originald.{}  t",targetSheet.getName().toLowerCase()));
            list1.addAll(list2);
            FileUtils.ListToFile(list1,StrUtil.format("E:\\人相关表sql\\wpf（新）\\\\{}.sql",targetSheet.getName().toLowerCase()));
        }

    }

    /**这里是仅仅查询当前路径下的所有文件夹、文件并且存放其路径到文件数组
    *由于遇到文件夹不查询其包含所有子文件夹、文件，因此没必要用到递归*/
    public static ArrayList<String> dirFilePaths(String filePath){
        try{
            File dirFile = new File(filePath);
            ArrayList<String> dirStrArr = new ArrayList<>();
            if (dirFile.exists()) {
                //直接取出利用listFiles()把当前路径下的所有文件夹、文件存放到一个文件数组
                File files[] = dirFile.listFiles();
                for (File file : files) {
                    //如果传递过来的参数dirFile是以文件分隔符，也就是/或者\结尾，则如此构造
                    if (dirFile.getPath().endsWith(File.separator)) {
                        dirStrArr.add(dirFile.getPath() + file.getName());
                    } else {
                        //否则，如果没有文件分隔符，则补上一个文件分隔符，再加上文件名，才是路径
                        dirStrArr.add(dirFile.getPath() + File.separator
                                + file.getName());
                    }
                }
            }
            return dirStrArr;
        }catch (Exception e){
            System.out.println("读取文件失败");
            return new ArrayList<>();
        }

    }

    private static void matchColumn(){
        //创建一个新的Excel文档
        Workbook indexBook = new Workbook();
        //载入文档
        indexBook.loadFromFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0413_标准库\\接入源表.xlsx");
        Worksheet indexSheet = indexBook.getWorksheets().get(1);

        //创建一个新的Excel文档
        Workbook targetBook = new Workbook();
        //载入文档
        targetBook.loadFromFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0413_标准库\\接入源到原始库.xlsx");
        Worksheet targetSheet = targetBook.getWorksheets().get(0);

//        //创建一个新的Excel文档
//        Workbook ysBook = new Workbook();
//        //载入文档
//        ysBook.loadFromFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0409_3\\接入源到原始库.xlsx");
//        Worksheet ysSheet = ysBook.getWorksheets().get(0);


        Map<String,String> oriNameMap = new HashMap<>();

        for (int k = 0; k < targetSheet.getLastRow(); k++){
            String jrName = targetSheet.getRange().get(k + 1, 2).getText();
            String oriName = targetSheet.getRange().get(k + 1, 4).getText();

            if (oriNameMap.containsKey(jrName)){
                System.out.println(jrName+"："+oriName+"：原始重复");
            }
            String type = TYPE_MAP.get(targetSheet.getRange().get(k + 1, 6).getText());
            if (type == null){
                System.out.println(jrName+"："+oriName+"：类型缺失");
            }
            oriNameMap.put(jrName,type==null?"":type);
        }


//        for (int k = 1; k < targetSheet.getLastRow(); k++){
//            String bzName = targetSheet.getRange().get(k + 1, 7).getText();
//            String jrName = targetSheet.getRange().get(k + 1, 2).getText();
//            String ysName = targetSheet.getRange().get(k + 1, 4).getText();
//            if (oriNameMap.containsKey(jrName)){
//                if (!oriNameMap.get(jrName).equals(ysName)){
//                    System.out.println(jrName+"："+oriNameMap.get(jrName)+"："+ysName);
//                }
//                oriNameMap.put(jrName,ysName);
//            }else {
//                oriNameMap.put(jrName,ysName);
//            }
//            if (bzNameMap.containsKey(jrName)){
//                System.out.println(jrName+"标准重复");
//            }
//            bzNameMap.put(jrName,bzName);
//        }
        String text = "";
        for (int k = 1; k < indexSheet.getLastRow(); k++){
            text = indexSheet.getRange().get(k + 1, 3).getText();
            indexSheet.getRange().get(k + 1, 7).setText(oriNameMap.getOrDefault(text,""));
        }
        indexBook.saveToFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表_0413_标准库\\接入源表.xlsx",ExcelVersion.Version2013);
    }

    private static void addLicense(){
        com.spire.license.LicenseProvider.setLicenseKey("/cQkscgY+QEA3Gyh2jA9ZH3bgagf61OCvAIIy7g0f4pc948lzl/oSP2BUJr5O2UBUhAN1V92SZ+zufDHk07rSSMN3J/wv2+ETT669w2nJVB7cVEusI7Dro7JjFCYkAj3zjay1y4wYVRdFM8wenn8V4gP0Np9LQmhA21TVIP36azc57KAk/oyAeNNjT0n5tCDiApfKRYfsIGkUIvCmF3MdEg0PzPIvNADTaRFUci2O+Sl6NYwx38J+krfRuemjNk9rc8x4Hb6Qs/4fSo/Y4QD0Ncg8pjlBoq3ueorne0q3PFPDbjoht/yxIF1qKwCKbMso8mY212kUSil4VLe26IikEBCTCV6i32oUVRIWQHzBwj0Kn6sHtzpRD5hKW6UwUNBNztqhDVbna7dQVH+uUZmNpUIrLcq1DQCJTuq3dP5V7wH7reEWOUxTqvHWpJjUBTiZXxZORDX462jtyErWdwizgX1XcxT68qSaOjHO8B/2gSXGLHlH+yE5hAS1wH1qfjKISZ1PB5G+5XLxgJ4kASsn6ac7XA26T1gqE59gzfqTXuSN4ImBIX/Wdp2iBxBuzTVgiz6xv5tooXApOou6IX5xAl1Rf4XvJCIWbSiY+3MOZXFRLKTTO851rqpI6QZ1po1aDJOa3Xvtm7836d3C04rMPsT9i1kkVkyDvjA0XDD1r37mmV6Lj6x9SNjfiT6q45bg/2PmPWbcyoh1Q7LXK+RyWQ5PGN8/zvPTfQJaPZf3pj599ODYjm7DxPAkrrcMPQOA5SCj+cvBjEVH1uV8kjtiSUdd2gznKuuLV9W55ZMfdgNn6a57p69+Oh3hzf4QOp2bAMhfo7iwL59ke/fdHWkh1vvBnsyZpGDtGoBt5GUnfmz5ClyewLSdDEoWa1Pacx7M97okb6BAeu8Z9NwBxiZ+OIJ18fB4kLooPv7SaT1e9gAaYAjR3DX7h20GRd9Y8WCqFmzukuRyvOlLmoAoi20zX2b8BNGqK6j/mizct4RdCPXp5/618ZlA4bh+O0ssiyNN6wBJmO/pWL0CMqAsT81hGl+M7SzGQHRMBtpFndT7v9/1HNT65yC/DnluXSXNvf5T/Mmo/2yC623ECAyFmWEfLwIQ+Dl6bP02Ls1fHZ3NFbE3VORBkSrU2V6cUHEo8yvV7u0d3BzDKt+xu23gVJGUuIega3y4ZavE6J0ZhLC+MrvhFyKvPampmyNAA4sFVHbAYJNPNPzlWmhHZ7sPepcvecoIk0E0uTC2+luK4MpKjE/oJ56WONVaArhrp8HwYfJR/uwhUgL+P6yoocz3X8ktuaEUfCHJMb3FlU1lOKsMgaJkdHerR9vM3AUPtSEHplA1u7ryIGJD1l5fxMh0RAzXk/fokgy4Y6Sxc8qaM24zQ/xZ3qW5otsbr6UnzXeeBGp");
    }

    /**添加超链接*/
    private static void cellStyle(List<String> filePaths){
        filePaths.forEach(i->{
            //创建一个新的Excel文档
            Workbook indexBook = new Workbook();
            //清除默认的3张工作表
            indexBook.getWorksheets().clear();
            //载入文档
            indexBook.loadFromFile(i);
            WorksheetsCollection sheets = indexBook.getWorksheets();
            for (int k = 1; k < sheets.size();k++){
                Worksheet sheet =  sheets.get(k);
//                //水平居中+垂直居中
//                sheet.getRange().get("A1:G1").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
//                sheet.getRange().get("A1:G1").getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
//                //水平居中+垂直居中
//                sheet.getRange().get("I1:O1").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
//                sheet.getRange().get("I1:O1").getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
//                //水平居中+垂直居中
//                sheet.getRange().get("Q1:W1").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
//                sheet.getRange().get("Q1:W1").getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
//
//
//                //设置列宽
//                sheet.getRange().get("A1:B1").setColumnWidth(18);
//                sheet.setColumnWidth(3,12);
//
//                //设置列宽
//                sheet.getRange().get("I1:J1").setColumnWidth(18);
//                sheet.setColumnWidth(11,12);
//
//                //设置列宽
//                sheet.getRange().get("Q1:R1").setColumnWidth(18);
//                sheet.setColumnWidth(19,12);


                sheet.getRange().get("A1:G1").getCellStyle().setKnownColor(ExcelColors.PaleBlue);
                sheet.getRange().get("I1:O1").getCellStyle().setKnownColor(ExcelColors.PaleBlue);
                sheet.getRange().get("Q1:W1").getCellStyle().setKnownColor(ExcelColors.PaleBlue);

//                //设置列宽
//                sheet.getRange().get("D1:F1").setColumnWidth(5);
//
//                //设置列宽
//                sheet.getRange().get("L1:N1").setColumnWidth(5);
//
//                //设置列宽
//                sheet.getRange().get("T1:V1").setColumnWidth(5);
            }
            //保存文档
            indexBook.saveToFile(i,ExcelVersion.Version2013);
            indexBook.dispose();
        });

    }

    /**添加返回目录超链接*/
    private static void addHyperlinkReturn(List<String> filePaths){
        filePaths.forEach(i->{
            //创建一个新的Excel文档
            Workbook indexBook = new Workbook();
            //清除默认的3张工作表
            indexBook.getWorksheets().clear();
            //载入文档
            indexBook.loadFromFile(i);
            WorksheetsCollection sheets = indexBook.getWorksheets();
            for (int k = 1; k < sheets.size();k++){
                Worksheet sheet =  sheets.get(k);
                //添加Workbook文档链接，链接到工作簿文档（这里链接到测试文档中的指定sheet中的指定单元格）
                HyperLink wbLink = sheet.getHyperLinks().add(sheet.getCellRange("H1"));
                wbLink.getType().compareTo(HyperLinkType.Workbook);
                wbLink.setTextToDisplay("返回目录");
                wbLink.setAddress("目录!A1");
            }
            //保存文档
            indexBook.saveToFile(i,ExcelVersion.Version2013);
            indexBook.dispose();
        });

    }

    /**复制工作簿*/
    public static void copyExcelToSheets(ArrayList<String> inputFiles) {
        //创建一个新的Excel文档
        Workbook sourceBook = new Workbook();
        //清除默认的3张工作表
        sourceBook.getWorksheets().clear();

        //创建另一个Excel文档
        Workbook tempBook = new Workbook();
        //清除默认的3张工作表
        tempBook.getWorksheets().clear();

        //遍历数组，依次加载每个Excel文档并将文档中的所有工作表复制到新建的Excel文档中
        for (String sourceFile : inputFiles)
        {
            String tempFile = sourceFile.replace("分系统表字段_接入","分系统表字段_标准");
            sourceBook.loadFromFile(sourceFile);
            tempBook.loadFromFile(tempFile);
            Worksheet sourceSheet = sourceBook.getWorksheets().get(0);

            String name = tempBook.getWorksheets().get(0).getName();
            Worksheet newSheet = tempBook.getWorksheets().addCopy(tempBook.getWorksheets().get(0));
            tempBook.getWorksheets().get(0).clear();
            tempBook.getWorksheets().get(0).copyFrom(sourceSheet);
            tempBook.getWorksheets().get(0).setName("目录");
            newSheet.setName(name);
            //保存
            tempBook.saveToFile(tempFile, ExcelVersion.Version2013);
        }

    }

    /**合并多个工作簿*/
    public static void mergeExcelToSheets(){
        //将待合并Excel文档的名称保存至字符串数组
        ArrayList<String> inputFiles = dirFilePaths("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\数仓结构\\主题表");
        //创建一个新的Excel文档
        Workbook newBook = new Workbook();
        //清除默认的3张工作表
        newBook.getWorksheets().clear();

        //创建另一个Excel文档
        Workbook tempBook = new Workbook();
        //遍历数组，依次加载每个Excel文档并将文档中的所有工作表复制到新建的Excel文档中
        for (String file : inputFiles)
        {
            tempBook.loadFromFile(file);
            WorksheetsCollection sheets = tempBook.getWorksheets();
            newBook.getWorksheets().addCopy(sheets.get(0), WorksheetCopyType.CopyAll);
        }
        //保存
        newBook.saveToFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\数仓结构\\主题表.xlsx", ExcelVersion.Version2013);
    }

    /**拆分单元格*/
    public static void splitCell() {
        //加载示例文档
        Workbook workbook = new Workbook();
        workbook.loadFromFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表\\社会资源总库系统.xlsx");

        //获取第一个工作表
        Worksheet sheet = workbook.getWorksheets().get(0);

        //拆分按空格作为分隔符的数据列
        String splitTextPre;
        String splitTextSuf;
        String text;
        for (int i = 1; i < sheet.getLastRow(); i++)
        {
            text = sheet.getRange().get(i + 1, 5).getText();
            splitTextPre = text.substring(0,text.indexOf('('));
            splitTextSuf = text.substring(text.indexOf('(')+1,text.length()-1);
            sheet.getRange().get(i + 1, 3).setText(splitTextPre);
            sheet.getRange().get(i + 1, 4).setText(splitTextSuf);
        }
        workbook.saveToFile("E:\\项目相关文档\\enc-pap-wuhu\\接入源表\\接入源表\\Result.xlsx", ExcelVersion.Version2013);
    }

}

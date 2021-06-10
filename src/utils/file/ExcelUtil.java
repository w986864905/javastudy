package utils.file;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.util.ArrayList;

/**
 * @ClassName ExcelUtil
 * @Description
 * @Author 王品峰
 * @Date 2021/4/29 13:04
 * @Version 1.1.
 */
public class ExcelUtil {
    private static void addLicense(){
        com.spire.license.LicenseProvider.setLicenseKey("/cQkscgY+QEA3Gyh2jA9ZH3bgagf61OCvAIIy7g0f4pc948lzl/oSP2BUJr5O2UBUhAN1V92SZ+zufDHk07rSSMN3J/wv2+ETT669w2nJVB7cVEusI7Dro7JjFCYkAj3zjay1y4wYVRdFM8wenn8V4gP0Np9LQmhA21TVIP36azc57KAk/oyAeNNjT0n5tCDiApfKRYfsIGkUIvCmF3MdEg0PzPIvNADTaRFUci2O+Sl6NYwx38J+krfRuemjNk9rc8x4Hb6Qs/4fSo/Y4QD0Ncg8pjlBoq3ueorne0q3PFPDbjoht/yxIF1qKwCKbMso8mY212kUSil4VLe26IikEBCTCV6i32oUVRIWQHzBwj0Kn6sHtzpRD5hKW6UwUNBNztqhDVbna7dQVH+uUZmNpUIrLcq1DQCJTuq3dP5V7wH7reEWOUxTqvHWpJjUBTiZXxZORDX462jtyErWdwizgX1XcxT68qSaOjHO8B/2gSXGLHlH+yE5hAS1wH1qfjKISZ1PB5G+5XLxgJ4kASsn6ac7XA26T1gqE59gzfqTXuSN4ImBIX/Wdp2iBxBuzTVgiz6xv5tooXApOou6IX5xAl1Rf4XvJCIWbSiY+3MOZXFRLKTTO851rqpI6QZ1po1aDJOa3Xvtm7836d3C04rMPsT9i1kkVkyDvjA0XDD1r37mmV6Lj6x9SNjfiT6q45bg/2PmPWbcyoh1Q7LXK+RyWQ5PGN8/zvPTfQJaPZf3pj599ODYjm7DxPAkrrcMPQOA5SCj+cvBjEVH1uV8kjtiSUdd2gznKuuLV9W55ZMfdgNn6a57p69+Oh3hzf4QOp2bAMhfo7iwL59ke/fdHWkh1vvBnsyZpGDtGoBt5GUnfmz5ClyewLSdDEoWa1Pacx7M97okb6BAeu8Z9NwBxiZ+OIJ18fB4kLooPv7SaT1e9gAaYAjR3DX7h20GRd9Y8WCqFmzukuRyvOlLmoAoi20zX2b8BNGqK6j/mizct4RdCPXp5/618ZlA4bh+O0ssiyNN6wBJmO/pWL0CMqAsT81hGl+M7SzGQHRMBtpFndT7v9/1HNT65yC/DnluXSXNvf5T/Mmo/2yC623ECAyFmWEfLwIQ+Dl6bP02Ls1fHZ3NFbE3VORBkSrU2V6cUHEo8yvV7u0d3BzDKt+xu23gVJGUuIega3y4ZavE6J0ZhLC+MrvhFyKvPampmyNAA4sFVHbAYJNPNPzlWmhHZ7sPepcvecoIk0E0uTC2+luK4MpKjE/oJ56WONVaArhrp8HwYfJR/uwhUgL+P6yoocz3X8ktuaEUfCHJMb3FlU1lOKsMgaJkdHerR9vM3AUPtSEHplA1u7ryIGJD1l5fxMh0RAzXk/fokgy4Y6Sxc8qaM24zQ/xZ3qW5otsbr6UnzXeeBGp");
    }

    public static ArrayList<String> readColumn(String path,Integer sheetIndex,Integer column,Integer lines){
        addLicense();
        //创建一个新的Excel文档
        Workbook indexBook = new Workbook();
        //载入文档
        indexBook.loadFromFile(path);
        //获取第一个sheet页
        Worksheet sheet = indexBook.getWorksheets().get(sheetIndex-1);
        ArrayList<String> result = new ArrayList<>();
        for (int k = 1; k <= Math.min(lines,sheet.getLastRow()); k++){
            result.add(sheet.getRange().get(k, column).getText());
        }
        return result;
    }
}

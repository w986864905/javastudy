package test;

import org.springframework.util.AntPathMatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data
     *            byte[]
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data
     *            byte[]
     * @param toLowerCase
     *            <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data
     *            byte[]
     * @param toDigits
     *            用于控制输出的char[]
     * @return 十六进制char[]
     */
    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data
     *            byte[]
     * @return 十六进制String
     */
    public static String encodeHexStr(byte[] data) {
        return encodeHexStr(data, true);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data
     *            byte[]
     * @param toLowerCase
     *            <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制String
     */
    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data
     *            byte[]
     * @param toDigits
     *            用于控制输出的char[]
     * @return 十六进制String
     */
    protected static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param data
     *            十六进制char[]
     * @return byte[]
     * @throws RuntimeException
     *             如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] decodeHex(char[] data) {

        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch
     *            十六进制char
     * @param index
     *            十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException
     *             当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    private Integer test(){
        try{
            return 1/0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public Integer test2(){
        return test();
    }
    public static void main(String[] args) {

        System.out.println(new ListTest().test2());


    }
    private static ArrayList<String> splitYearsFrame(String current){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat yearFormat =  new SimpleDateFormat("yyyy");
        //1.当前时间初始化
        Date cur = new Date();
        //2.存放结果初始化
        ArrayList<String> datelists = new ArrayList<String>();
        //3.当前时间赋值
        try{
            cur = format.parse(current);//开始时间
        }catch (ParseException e){
            e.printStackTrace();
        }
        //4.初始化时间Calendar
        Calendar calendar = Calendar.getInstance();
        //5获取上年时间
        //5.1设置起始时间
        calendar.setTime(cur);
        //5.2当前年份减一
        calendar.add(Calendar.YEAR,-1);
        //5.3存入结果
        datelists.add(yearFormat.format(calendar.getTime()));
        //5.4当前年份加一
        calendar.add(Calendar.YEAR,1);
        //6.存入当年
        datelists.add(yearFormat.format(calendar.getTime()));
        return datelists;
    }
    private static List<String> splitMonth(String start, String end){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        Date pre = new Date();
        Date fin = new Date();
        try{
            pre = format.parse(start);//开始时间
            fin = format.parse(end);//结束时间
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pre);//设置起始时间
        ArrayList<String> datelists = new ArrayList<String>();//存放结果
        calendar.add(Calendar.MONTH,-1);//当前月份减一
        Calendar calendarLastYear = Calendar.getInstance();
        while (calendar.getTime().before(fin)){
            calendarLastYear.setTime(calendar.getTime());
            calendarLastYear.add(Calendar.YEAR,-1);
            datelists.add(format.format(calendar.getTime()));//将月份存入数组
            datelists.add(format.format(calendarLastYear.getTime()));
            calendar.add(Calendar.MONTH,1);//对当前日期加1
        }
        datelists.add(format.format(calendar.getTime()));//将结束时间放入List
        calendarLastYear.setTime(calendar.getTime());
        calendarLastYear.add(Calendar.YEAR,-1);
        datelists.add(format.format(calendarLastYear.getTime()));
        List<String> list = datelists.stream().distinct().collect(Collectors.toList());
        return list;
    }
    private static String lastYearTime(String time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        try{
            date = format.parse(time);
        } catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);//设置起始时间
        calendar.add(Calendar.MONTH,-1);//当前月份减一
        calendar.add(Calendar.YEAR,-1);//当前年份减一
        return format.format(calendar.getTime());
    }

    private static String getNextYear(String currentYear){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try{
            calendar.setTime(format.parse(currentYear));
        }catch (Exception e){
            e.printStackTrace();
        }
        calendar.add(Calendar.YEAR,1);
        return format.format(calendar.getTime());
    }

    private static List<String> splitMonth2(String start, String end){
        SimpleDateFormat format2 =  new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pre = new Date();
        Date fin = new Date();
        try{
            pre = format.parse(start);//开始时间
            fin = format.parse(end);//结束时间
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pre);//设置起始时间
        ArrayList<String> datelists = new ArrayList<String>();//存放结果
        while (calendar.getTime().before(fin)){
            datelists.add(format2.format(calendar.getTime()));//将月份存入数组
            calendar.add(Calendar.MONTH,1);//对当前日期加1
        }
        return datelists;
    }

    // 获取当前时间所在年的周数
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    // 获取某年的第几周的开始日期
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return cal.getTime();
    }


    // 获取某年的第几周的结束日期
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return cal.getTime();
    }
}

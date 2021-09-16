package chain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 王品峰
 * @DateTime 2020/7/28 11:04
 * @Description
 */
public class testData {

    public static DateTimeFormatter dateTimeFormatterUntilDay = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static DateTimeFormatter dateTimeFormatterUntilHour = DateTimeFormatter.ofPattern("yyyyMMddHH");
    public static SimpleDateFormat dateFormatGas = new SimpleDateFormat("yyyyMMddHH");


    private static final SimpleDateFormat DEFAULT_SIMPLE_DATE_FORMAT =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws ParseException {
        HashMap<String,String> xm = null;
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int hours = hour - 6;
        System.out.println(getCurrentTimeYYYYMMddHH());
        //System.out.println(formatTime(Long.valueOf("1605423627550")));
        //System.out.println(formatTime(Long.parseLong("1605423627550")));
    }

    public static String getCurrentTimeYYYYMMddHH(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR,-10);
        String time=dateFormatGas.format(cal.getTime());
        return time;
    }

    public static String formatTime(long timeMillis) {
        long day = timeMillis / 86400000L;
        long hour = timeMillis / 3600000L - day * 24L;
        long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
        long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        long sss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
        return (day > 0L ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }
    private static void add(Integer a){
        a = a+1;
    }
    private static ArrayList<String> splitMonth(String start,String end){
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
        return datelists;
    }
    private static ArrayList<String> splitMonthToCurrent(String start, String end){
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
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
            datelists.add(format.format(calendar.getTime()));//将月份存入数组
            calendar.add(Calendar.MONTH,1);//对当前日期加1
        }
        datelists.add(format.format(calendar.getTime()));//将结束时间放入List
        return datelists;
    }
    private static ArrayList<String> splitTimeFrame(String current){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
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
        datelists.add(format.format(calendar.getTime()));
        //5.4当前年份加一
        calendar.add(Calendar.YEAR,1);
        //6获取上月时间
        //6.1当前月份减一
        calendar.add(Calendar.MONTH,-1);
        //6.2存入结果
        datelists.add(format.format(calendar.getTime()));
        //6.3当前月份加一
        calendar.add(Calendar.MONTH,1);
        //7存入当前月份
        datelists.add(format.format(calendar.getTime()));
        return datelists;
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

    private static ArrayList<String> splitYear(String startTime, String endTime){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy");
        Date pre = new Date();
        Date fin = new Date();
        try{
            pre = format.parse(startTime);//开始时间
            fin = format.parse(endTime);//结束时间
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pre);//设置起始时间
        ArrayList<String> datelists = new ArrayList<String>();//存放结果
        while (calendar.getTime().before(fin)){
            datelists.add(format.format(calendar.getTime()));//将月份存入数组
            calendar.add(Calendar.YEAR,1);//对当前日期加1
        }
        datelists.add(format.format(calendar.getTime()));//将结束时间放入List
        return datelists;
    }
    public static ArrayList<String> splitTime(String start, String end,String timeFormat,int flag){

        SimpleDateFormat format =  new SimpleDateFormat(timeFormat);
        Date pre = new Date();
        Date fin = new Date();
        try{
            Date startTime = DEFAULT_SIMPLE_DATE_FORMAT.parse(start);
            Date endTime = DEFAULT_SIMPLE_DATE_FORMAT.parse(end);
            //开始时间
            pre = format.parse(format.format(startTime));
            //结束时间
            fin = format.parse(format.format(endTime));
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        //设置起始时间
        calendar.setTime(pre);
        //存放结果
        ArrayList<String> datelists = new ArrayList<>();
        while (calendar.getTime().before(fin)){
            //将月份存入数组
            datelists.add(format.format(calendar.getTime()));
            //对当前日期加1
            calendar.add(flag,1);
        }
        //将结束时间放入List
        datelists.add(format.format(calendar.getTime()));
        return datelists;
    }

    public static String getFormatterDate(int minusDays,int minusHours,int type){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime =  now.minusDays(minusDays).minusHours(minusHours);
        if(type==1){
            return localDateTime.format(dateTimeFormatterUntilDay);
        }else{
            return localDateTime.format(dateTimeFormatterUntilHour);
        }
    }
}

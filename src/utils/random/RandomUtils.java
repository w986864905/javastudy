package utils.random;

import utils.random.name.AreaCode;
import utils.random.name.FirstName;
import utils.random.name.SecondName;
import utils.random.name.ThirdName;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: wdkf-spring-boot-project
 * @Package: com.wdkf.wdkfspringbootutils.date
 * @ClassName: RandomAccessDate
 * @Description: 获取随机时间
 * @Author: wangdehonga
 * @Date: 2020/8/27 13:55
 * @Version: 1.0
 */
public class RandomUtils {

    /**
     * @Author: 王品峰
     * @Description: 随机大小写+数字
     * @DateTime: 2020/8/28 10:12
     * @param length 长度
     * @param flag 0小写，1大写，2大小写
     * @Return: java.lang.String
     */
    private static String randomNumberAndCase(Integer length,Integer flag){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        if (flag == 0){//flag=0小写
            for (int i = 0; i < length; i++) {
                boolean b = random.nextBoolean();
                if(b){
                    int choice = 97;
                    sb.append((char)(choice+random.nextInt(26)));
                }else{
                    sb.append(random.nextInt(10));
                }
            }
        }else if (flag == 1){//flag=1大写
            for (int i = 0; i < length; i++) {
                boolean b = random.nextBoolean();
                if(b){
                    int choice = 65;
                    sb.append((char)(choice+random.nextInt(26)));
                }else{
                    sb.append(random.nextInt(10));
                }
            }
        }else {//flag=2大小写
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

    /**
     * @Method: randomNumbersAndLowercase
     * @Description: 数字和小写字母随机组合（指定长度）
     * @param length 长度
     * @Return: java.lang.String
     * @Author: 王品峰
     * @Date 2020/8/27 14:34
     * @Version:  1.0
     */
    public static String randomNumbersAndLowercase(Integer length) {
        return randomNumberAndCase(length,0);
    }

    /**
     * @Author: 王品峰
     * @Description: 数字和小写字母随机组合（指定长度）
     * @DateTime: 2020/8/28 10:15
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbersAndLowercase(Integer length, Integer size) {
        return randomNumbersAndLowercase(length,size,true);
    }

    /**
     * @Author: 王品峰
     * @Description: 数字和小写字母随机组合（指定长度）
     * @DateTime: 2020/8/28 10:15
     * @param flag 是否允许重复；true允许重复
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbersAndLowercase(Integer length, Integer size, boolean flag) {
        Set<String> set = new HashSet<String>();
        List<String> list = new ArrayList<String>();
        if (flag){
            while(list.size() < size){
                list.add(randomNumbersAndLowercase(length));
            }
            return list.toArray(new String[list.size()]);
        }
        while(set.size() < size){
            set.add(randomNumbersAndLowercase(length));
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * @Method: randomNumbersAndUpcase
     * @Description: 数字和大写字母随机组合（指定长度）
     * flag：是否允许重复；true允许重复
     * @param length
     * @Return: java.lang.String
     * @Author: 王品峰
     * @Date 2020/8/27 14:35
     * @Version:  1.0
     */
    public static String randomNumbersAndUpcase(Integer length) {
        return randomNumberAndCase(length,1);
    }

    /**
     * @Author: 王品峰
     * @Description: 数字和大写字母随机组合（指定长度）
     * @DateTime: 2020/8/28 10:18
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbersAndUpcase(Integer length, Integer size) {
        return randomNumbersAndUpcase(length,size,true);
    }

    /**
     * @Author: 王品峰
     * @Description: 数字和大写字母随机组合（指定长度）
     * @DateTime: 2020/8/28 10:18
     * @param flag 是否允许重复；true允许重复
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbersAndUpcase(Integer length, Integer size, boolean flag) {
        Set<String> set = new HashSet<String>();
        List<String> list = new ArrayList<>();
        if (flag){
            while(list.size() < size){
                list.add(randomNumbersAndUpcase(length));
            }
            return list.toArray(new String[list.size()]);
        }
        while(set.size() < size){
            set.add(randomNumbersAndUpcase(length));
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * @Method: randomNumbersAndLowerUpcase
     * @Description: 数字和大小写字母随机组合（指定长度）
     * flag：是否允许重复；true允许重复
     * @param length
     * @Return: java.lang.String
     * @Author: 王品峰
     * @Date 2020/8/27 14:36
     * @Version:  1.0
     */
    public static String randomNumbersAndLowerUpcase(Integer length) {
        return randomNumberAndCase(length,2);
    }

    /**
     * @Author: 王品峰
     * @Description: 数字和大小写字母随机组合（指定长度）
     * @DateTime: 2020/8/28 10:19
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbersAndLowerUpcase(Integer length, Integer size) {
        return randomNumbersAndLowerUpcase(length,size,true);
    }

    /**
     * @Author: 王品峰
     * @Description: 数字和大小写字母随机组合（指定长度）
     * @DateTime: 2020/8/28 10:19
     * @param flag 是否允许重复；true允许重复
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbersAndLowerUpcase(Integer length, Integer size, boolean flag) {
        Set<String> set = new HashSet<String>();
        List<String> list = new ArrayList<>();
        if (flag){
            while(list.size() < size){
                list.add(randomNumbersAndLowerUpcase(length));
            }
            return list.toArray(new String[list.size()]);
        }
        while(set.size() < size){
            set.add(randomNumbersAndLowerUpcase(length));
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * @Method: randomNumbers
     * @Description: 随机生成纯数字（指定长度）
     * flag：是否允许重复；true允许重复
     * @param length
     * @Return: java.lang.String
     * @Author: 王品峰
     * @Date 2020/8/27 14:40
     * @Version:  1.0
     */
    public static String randomNumbers(Integer length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * @Author: 王品峰
     * @Description: 随机生成纯数字（指定长度）
     * @DateTime: 2020/8/28 10:23
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbers(Integer length, Integer size) {
        return randomNumbers(length,size,true);
    }

    /**
     * @Author: 王品峰
     * @Description: 随机生成纯数字（指定长度）
     * @DateTime: 2020/8/28 10:23
     * @param flag 是否允许重复；true允许重复
     * @param size 大小
     * @param length 长度
     * @Return: java.lang.String[]
     */
    public static String[] randomNumbers(Integer length, Integer size, boolean flag) {
        Set<String> set = new HashSet<String>();
        List<String> list = new ArrayList<String>();
        if (flag){
            while(list.size() < size){
                list.add(randomNumbers(length));
            }
            return list.toArray(new String[list.size()]);
        }
        while(set.size() < size){
            set.add(randomNumbers(length));
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * @Method: randomDate
     * @Description: 随机生成日期
     * @param
     * @Return: java.lang.String
     * @Author: 王品峰
     * @Date 2020/8/27 14:42
     * @Version:  1.0
     */
    public static String randomDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return randomDate("1970-01-01",format.format(new Date()));
    }

    /**
     * @Author: 王品峰
     * @Description: 随机生成日期
     * @DateTime: 2020/8/28 10:25
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @Return: java.lang.String
     */
    public static String randomDate(String startDate, String endDate) {
        try {
            Random random = new Random();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            if(start.getTime() >= end.getTime()){
                throw new Exception("开始时间需要小于结束时间");
            }
            long date = start.getTime() + (long)(Math.random() * (end.getTime() - start.getTime()));
            return format2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: 王品峰
     * @Description: 随机生成日期
     * @DateTime: 2020/8/28 10:25
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param size 大小
     * @Return: java.lang.String
     */
    public static String[] randomDate(String startDate, String endDate, Integer size) {
        return randomDate(startDate, endDate, size, true);
    }

    /**
     * @Author: 王品峰
     * @Description: 随机生成日期
     * @DateTime: 2020/8/28 10:25
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param size 大小
     * @param flag true为可重复,false为不可重复
     * @Return: java.lang.String
     */
    public static String[] randomDate(String startDate, String endDate, Integer size, boolean flag) {
        Set<String> set = new HashSet<String>();
        List<String> list = new ArrayList<>();
        if (flag){
            while(list.size() < size){
                list.add(randomDate(startDate,endDate));
            }
            return list.toArray(new String[list.size()]);
        }
        while(set.size() < size){
            set.add(randomDate(startDate,endDate));
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 随机地区
     *
     * @return
     */
    private static int randomAreaCode() {
        int index = (int) (Math.random() * AreaCode.areaCode.size());
        Collection<Integer> values = AreaCode.areaCode.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int code = 0;
        while (i < index && it.hasNext()) {
            i++;
            code = it.next();
        }
        return code;
    }

    /**
     * 随机出生日期
     *
     * @return
     */
    private static String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /*
     * <p>18位身份证验证</p> 根据〖中华人民共和国国家标准 GB
     * 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。 第十八位数字(校验码)的计算方法为：
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8
     * 4 2 2.将这17位数字和系数相乘的结果相加。 3.用加出来和除以11，看余数是多少？ 4.余数只可能有0 1 2 3 4 5 6 7 8 9
     * 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     */
    private static char calcTrailingNumber(char[] chars) {
        if (chars.length < 17) {
            return ' ';
        }
        int[] c = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] r = {'1', '0', 'X', '9', '8', '7', '6','5', '4', '3', '2' };
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    /**
     * 随机产生3位数
     *
     * @return
     */
    private static String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }

    /**
     * @Method: randomIDNumber
     * @Description: 随机生成身份证号码
     * @param
     * @Return: java.lang.String
     * @Author: chenlu
     * @Date 2020/8/27 15:08
     * @Version:  1.0
     */
    public static String randomIDNumber() {
        StringBuilder generater = new StringBuilder();
        generater.append(randomAreaCode());
        generater.append(randomBirthday());
        generater.append(randomCode());
        generater.append(calcTrailingNumber(generater.toString().toCharArray()));
        return generater.toString();
    }

    /**
     * @Method: randomIDNumber
     * @Description: 随机生成身份证号码（不允许重复）
     * @param size 随机生成身份证号个数
     * @Return: java.lang.String
     * @Author: chenlu
     * @Date 2020/8/27 15:08
     * @Version:  1.0
     */
    public static String[] randomIDNumber(Integer size) {
        HashSet<String> hashSet = new HashSet<>();
        while(hashSet.size()<size){
            hashSet.add(randomIDNumber());
        }
        return hashSet.toArray(new String[hashSet.size()]);
    }

    /**
     * @Method: randomUserName
     * @Description: 随机生成中文名字（不允许重复）
     * @param
     * @Return: java.lang.String
     * @Author: chenlu
     * @Date 2020/8/27 15:13
     * @Version:  1.0
     */
    public static String randomUserName() {
        int firstRandom = new Random().nextInt(FirstName.firstname.length);
        int secondRandom = new Random().nextInt(SecondName.secondname.length);
        int thirdRandom = new Random().nextInt(ThirdName.thirdname.length);
        StringBuilder sb = new StringBuilder();
        return sb.append(FirstName.firstname[firstRandom]).append(SecondName.secondname[secondRandom]).append(ThirdName.thirdname[thirdRandom]).toString();
    }
    /**
     * @Method: randomUserName
     * @Description: 随机生成中文名字（不允许重复）
     * @param size 随机生成中文名字个数
     * @Return: java.lang.String
     * @Author: chenlu
     * @Date 2020/8/27 15:13
     * @Version:  1.0
     */
    public static String[] randomUserName(Integer size) {
        HashSet<String> hashSet = new HashSet<>();
        while(hashSet.size()<size){
            hashSet.add(randomUserName());
        }
        return hashSet.toArray(new String[hashSet.size()]);
    }

    /**
     * @Method: randomUserName
     * @Description: 随机生成邮箱地址（不允许重复）
     * @param
     * @Return: java.lang.String
     * @Author: chenlu
     * @Date 2020/8/28 15:13
     * @Version:  1.0
     */
    public static String randomEmail(){
        StringBuilder result = new StringBuilder();
        int num = new Random().nextInt(12)+6;
        result.append(randomNumbersAndLowercase(num));
        String[] suffix = {"@gmail.com","@yahoo.com","@msn.com","@hotmail.com","@aol.com","@ask.com",
        "@live.com","@qq.com","@0355.net","@163.com","@163.net","@263.net","@3721.net","@yeah.net","@googlemail.com","@mail.com"};
        int random = new Random().nextInt(suffix.length);
        result.append(suffix[random]);
        return result.toString().toLowerCase();
    }

    /**
     * @Method: randomUserName
     * @Description: 随机生成邮箱地址（不允许重复）
     * @param size 随机生成邮箱地址个数
     * @Return: java.lang.String
     * @Author: chenlu
     * @Date 2020/8/28 15:13
     * @Version:  1.0
     */
    public static String[] randomEmail(Integer size){
        HashSet<String> hashSet = new HashSet<>();
        while(hashSet.size()<size){
            hashSet.add(randomEmail());
        }
        return hashSet.toArray(new String[hashSet.size()]);
    }

}

package test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegexTest
 * @Description
 * @Author 王品峰
 * @Date 2021/4/21 19:56
 * @Version 1.1.
 */
public class RegexTest {
    public static void main(String[] args) throws ClassNotFoundException {
//        String str = "·";
//        String pattern = "[^a-zA-Z0-9\\u4e00-\\u9fa5.·？?（）()]+";
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(trimb("·.......wdaw.·······.",".·"));
//        System.out.println(StrUtil.trim("0553－6390666"));
//        System.out.println(Pattern.compile("[+-]*\\d+\\.?\\d*[Ee]*[+-]*\\d+").matcher("1.822116087E10").matches());
//        System.out.println(new BigDecimal("1.822116087E10").toPlainString());
//        String regex = "[^\\u4e00-\\u9fa5]+";
//        Pattern pattern = Pattern.compile(regex);
//        System.out.println(pattern.matcher("encdata.serialsource.dataAccess_1279_base-unified-access1279").matches());
        //System.out.println("皖12313".replaceAll("[^A-Z0-9]+",""));


        Class.forName("test.Super");
        System.out.println(Super.i);

    }
    private static String trimb(String target,String replace){
        Set<Character> charSet = new HashSet<>(Arrays.asList(Convert.toCharArray(replace)));
        int len = target.length();
        int st = 0;
        char[] val = target.toCharArray();
        while ((st < len) && (charSet.contains(val[st]))) {
            st++;
        }
        while ((st < len) && (charSet.contains(val[len - 1]))) {
            len--;
        }
        return ((st > 0) || (len < val.length)) ? target.substring(st, len) : target;
    }
}

package utils;

/**
 * @ClassName StringUtil
 * @Description
 * @Author 王品峰
 * @Date 2021/6/24 16:54
 * @Version 1.0
 */
public class StringUtil {
    public static String replaceAll(String input, String forReplace, String replaceWith) {
        if (input == null) {
            return "null";
        }
        //return null;
        StringBuffer result = new StringBuffer();
        boolean hasMore = true;
        while (hasMore) {
            int start = input.indexOf(forReplace);
            int end = start + forReplace.length();
            if (start != -1) {
                result.append(input.substring(0, start) + replaceWith);
                input = input.substring(end);
            } else {
                hasMore = false;
                result.append(input);
            }
        }
        if (result.toString().equals("")) {
            return input;
        }
        return result.toString();
    }}

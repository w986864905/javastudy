package sql;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.sql.SqlUtil;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.SQLParser;
import com.alibaba.druid.sql.parser.SQLSelectParser;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;


import java.util.*;

/**
 * @ClassName SqlTest
 * @Description
 * @Author 王品峰
 * @Date 2021/6/16 14:24
 * @Version 1.0
 */
public class SqlTest {
    public static void main(String[] args) throws Exception {
        String sql = "SELECT name,addr as address,sum(num) as total_num FROM theme.table as t where t.user_id = '123' and pk='234' or ps='345' and wk='456' order by uid asc";
//        CCJSqlParser sqlParser = new CCJSqlParser(sql);
//        Select select = sqlParser.Select();
//        System.out.println(SqlParserUtils.select_where(sql));
        Map<String,String> reqMap = new LinkedHashMap<String,String>(){{
            put("${uid}","1234");
            put("${ps}","1234");
        }};
        System.out.println(buildSql("SELECT name,addr as address,sum(num) as total_num FROM theme.table as t where t.user_id = ${uid} and pk=${pk} or ps=${ps} and wk=${wk} order by uid asc",reqMap));
        //System.out.println(SQLUtils.format("SELECT name,addr as address,sum(num) as total_num FROM theme.table t where t.user_id = ${uid} and (pk=${pk} or ps = ${ps})","mysql"));
        //System.out.println(StrUtil.replace(SqlUtil.formatSql(SQLUtils.format("SELECT name,addr as address,sum(num) as total_num FROM theme.table as t where t.user_id = ${uid} and (pk=${pk} or ps = ${ps})","mysql")), "\n"," ").replaceAll(" +"," "));
    }

    private static String buildSql(String sql,Map<String,String> reqMap){
        //格式化sql
        sql = StrUtil.replace(SqlUtil.formatSql(SQLUtils.format(sql,"mysql")), "\n"," ").replaceAll(" +"," ");
        if (!sql.contains("WHERE")){
            return sql;
        }
        String whereSql = getWhereSql(sql);
        String newWhereSql = processWhereSql(whereSql,reqMap);
        if ("".equals(newWhereSql)){
            return sql.replace("Where "+whereSql,"");
        }
        return sql.replace(whereSql,newWhereSql);
    }

    private static String getWhereSql(String sql){
        int whereStartIndex = sql.indexOf("WHERE");
        if (whereStartIndex == -1){
            return sql;
        }
        //拆分 where 后语句
        int whereEndIndex = sql.lastIndexOf('}');
        if (whereEndIndex < sql.length()-2){
            if (sql.charAt(whereEndIndex+2) == ')'){
                whereEndIndex = whereEndIndex + 2;
            }
        }
        return sql.substring(whereStartIndex+6,whereEndIndex+1);
    }

    private static String processWhereSql(String whereSql,Map<String,String> reqMap){
        int startIndex = whereSql.indexOf('$');
        int lastIndex = whereSql.lastIndexOf('$');
        StringBuilder sbb = new StringBuilder();
        int index = 0;
        int end;
        //寻找入参
        while(index < whereSql.length()){
            if (whereSql.charAt(index) == '$'){
                end = index;
                while (end < whereSql.length()){
                    sbb.append(whereSql.charAt(end));
                    if (whereSql.charAt(end) == '}'){
                        break;
                    }else {
                        end++;
                    }
                }
                //匹配到非入参
                if (!reqMap.containsKey(sbb.toString())){
                    if (judgeFirst(index,startIndex,lastIndex,whereSql)){
                        //第一个入参 去除后面的连接符例如t.user_id = ${uid} and等
                        //如果只有一个入参
                        if (index == lastIndex){
                            return "";
                        }
                        int cut = findFrontSpace(index,whereSql,3);
                        //如果单纯的第一个入参
                        whereSql = removeStringSpecify(whereSql,cut,findBehindSpace(index,whereSql,2));
                        index = cut-1;
                    }else {
                        //在中间参数以及最后一个入参 去除后面的连接符例如 and t.user_id = ${uid}等
                        int cut = findFrontSpace(index,whereSql,4);
                        whereSql = removeStringSpecify(whereSql,findFrontSpace(index,whereSql,4),findBehindSpace(index,whereSql,1));
                        index = cut-1;
                    }
                    startIndex = whereSql.indexOf('$');
                    lastIndex = whereSql.lastIndexOf('$');
                }
                sbb = new StringBuilder();
            }
            index++;
        }
        return whereSql;
    }

    private static int findFrontSpace(int index, String str,int stop){
        //当count == 3时暂停或为0
        int count = 0;
        while (count != stop && index >= 0){
            if (str.charAt(index) == ' '){
                count++;
            }
            index--;
        }
        return index+1;
    }

    private static int findBehindSpace(int index, String str,int stop){
        //当count == stop时暂停
        int count = 0;
        while (count != stop && index < str.length()){
            if (str.charAt(index) == ' '){
                count++;
            }
            index++;
        }
        return index;
    }

    private static String removeStringSpecify(String str,int start,int end){
        return (start==0?"":str.substring(0,start+1))+str.substring(end);
    }

    private static Boolean judgeFirst(int index,int firstIndex, int lastIndex, String str){
        if (index == firstIndex){
            return true;
        }else if (index == lastIndex){
            return false;
        }
        index--;
        while (index > 0){
            if (str.charAt(index) == '$'){
                return false;
            }else if (str.charAt(index) == '('){
                return true;
            }
            index--;
        }
        return true;
    }
}

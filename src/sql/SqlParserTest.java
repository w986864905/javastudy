package sql;

import cn.hutool.core.lang.Tuple;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.druid.sql.visitor.*;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName SqlParserTest
 * @Description
 * @Author 王品峰
 * @Date 2021/6/21 11:28
 * @Version 1.0
 */
public class SqlParserTest {
    public static void main(String[] args) throws Exception {
        String sql = "SELECT uid,aid from table t where (pid > ${aid} and pid < ${wid}) and (gid = ${gid} and cid = ${cid}) order by t.`uid` asc,t.`aid` desc";

        jsql(sql);

    }
    private static void jsql(String sql) throws Exception{

        String dbType = JdbcConstants.MYSQL;
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql,dbType);
        if (!Token.SELECT.equals(parser.getExprParser().getLexer().token())) {
            throw new RuntimeException("不支持 " + parser.getExprParser().getLexer().token() + " 语法,仅支持 SELECT 语法");
        }
        SQLSelectStatement selectStatement = (SQLSelectStatement)parser.parseSelect();
        SQLSelectQueryBlock selectQuery = selectStatement.getSelect().getQueryBlock();
        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(dbType);
        selectStatement.accept(statVisitor);

        //获取表名
        Set<String> tables = statVisitor.getTables().keySet().stream().map(t->trim(removeDb(t.getName()))).collect(Collectors.toSet());

        //1.排序字段
        List<TableStat.Column> orderBy = statVisitor.getOrderByColumns();
        List<Triple<Integer,String,String>> orderParams = Lists.newLinkedList();
        int orderIndex = 1;
        for (TableStat.Column i:orderBy){
            //左 排序顺序 中排序字段名 右 排序方式（正排倒排）
            Triple<Integer,String,String> triples = Triple.of(orderIndex++,trim(i.getName()),String.valueOf(i.getAttributes().get("orderBy.type")).toLowerCase());
            orderParams.add(triples);
        }
        //2.出参
        List<Triple<String,String,String>> outParams = Lists.newArrayList();
        List<SQLSelectItem> selectItems =  selectQuery.getSelectList();
        selectItems.forEach(i->{
            SQLExpr expr = i.getExpr();
            if (expr instanceof SQLPropertyExpr || expr instanceof SQLIdentifierExpr){
                String columnName;
                String tableName;
                if (expr instanceof SQLPropertyExpr){
                    columnName = trim(((SQLPropertyExpr) expr).getName());
                    SQLExprTableSource tableSource = (SQLExprTableSource) ((SQLPropertyExpr) expr).getResolvedOwnerObject();
                    SQLPropertyExpr table = (SQLPropertyExpr)tableSource.getExpr();
                    tableName = removeDb(table.getName());
                }else {
                    columnName = trim(((SQLIdentifierExpr) expr).getName());
                    SQLExprTableSource tableSource = (SQLExprTableSource) ((SQLIdentifierExpr) expr).getResolvedOwnerObject();
                    SQLIdentifierExpr table = (SQLIdentifierExpr)tableSource.getExpr();
                    tableName = removeDb(table.getName());
                }

                String alis = Objects.nonNull(i.getAlias())?i.getAlias():columnName;
                Triple<String,String,String> triple = Triple.of(tableName,columnName,alis);
                outParams.add(triple);
            }else {
                //TODO:直接输出字段
            }
        });
        //3.入参
        List<TableStat.Condition> inConditions = statVisitor.getConditions();
        List<Tuple> inParams = Lists.newArrayList();
        inConditions.forEach(i->{
            //入参名 //字段名 //表名 //运算符
            String param = Objects.isNull(i.getValues().get(0))?"null":String.valueOf(i.getValues().get(0));
            Tuple tuple = new Tuple(param,trim(i.getColumn().getName()),removeDb(i.getColumn().getTable()),i.getOperator().toLowerCase());
            inParams.add(tuple);
        });
        System.out.println("11");




    }

    private static String removeDb(String tableName){
        String[] names = tableName.split("\\.");
        return trim(names[names.length-1]);
    }

    private static String trim(String columnName){
        return columnName.replace("`","");
    }

    private static void duridSql(String sql){
        String dbType = JdbcConstants.HIVE;
        ExportTableAliasVisitor visitor = new ExportTableAliasVisitor();

//        parser.parseSelect().accept(visitor);
//        System.out.println(visitor.aliasMap);
//        System.out.println(visitor.getColumns());
//        System.out.println(visitor.getAliasMap());
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql,dbType);
        SQLSelectStatement selectStatement = (SQLSelectStatement)parser.parseSelect();
        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(dbType);
        selectStatement.accept(statVisitor);
        //List<Column> columns = statVisitor.getColumns();


        //解析出的独立语句的个数
        System.out.println(statVisitor.getColumns());




//        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(dbType);
//        parser.parseSelect().accept(statVisitor);
//        Collection<TableStat.Column> columns = statVisitor.getColumns();
//        System.out.println(statVisitor.getOrderByColumns());
        //System.out.println(statVisitor.getTables());
        //selectQueryBlock.setWhere();
        //System.out.println("111");
        //System.out.println(SqlParserUtils.select_where(sql));

        //System.out.println(plain.getWhere().toString());
    }

    private static Map<String, String> getTableMappings(String sql){
        String dbType = JdbcConstants.HIVE;
        ExportTableAliasVisitor visitor = new ExportTableAliasVisitor();

        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql,dbType);
        SQLSelectStatement selectStatement = (SQLSelectStatement)parser.parseSelect();
        selectStatement.accept(visitor);
        return visitor.getAliasMap();
    }

    public static class ExportTableAliasVisitor extends SQLASTVisitorAdapter {
        private final Map<String, String> aliasMap = new HashMap<>();

        @Override
        public boolean visit(SQLExprTableSource table) {
            String alias = table.getAlias();
            aliasMap.put(table.getName().getSimpleName().replace("'", ""), alias);
            return true;
        }

        public Map<String, String> getAliasMap() {
            return aliasMap;
        }

    }
}

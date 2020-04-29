package com.oliwen.plugin;

import com.oliwen.entity.Page;
import com.oliwen.util.ReflectHelper;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import javax.xml.bind.PropertyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author olw
 * @ClassName: PagePlugin
 * @Description:
 * @date 2018年3月21日 下午5:58:40
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {

    /**数据库方言*/
    private static String dialect = "";
    /**mapper.xml中需要拦截的ID(正则匹配)*/
    private static String pageSqlId = "";
    /**是否记录sql*/
    private static String logSQL = "";

    @Override
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
            logSQL(delegate.getBoundSql(), mappedStatement.getStatementLog());
            //拦截需要分页的SQL
            if (mappedStatement.getId().toUpperCase().matches(pageSqlId.toUpperCase())) {
                BoundSql boundSql = delegate.getBoundSql();
                String sql = boundSql.getSql();
                //分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                } else {
                    Page page;
                    //参数就是Page实体
                    if (parameterObject instanceof Page) {
                        page = (Page) parameterObject;
                    } else {
                        throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                    }
                    if (page.getCheckTotal()) {
                        String countSql = "select count(0) from (" + sql + ")  tmp_count";
                        Connection connection = (Connection) ivk.getArgs()[0];
                        //利用原始sql语句的方法执行
                        PreparedStatement countStatement = connection.prepareStatement(countSql);
                        //在本例中，查询参数为空，但实际应用时，多为带有条件的分页查询，下面的这句话就是获取查询条件的参数
                        ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
                        //经过set方法，就可以正确的执行sql语句
                        parameterHandler.setParameters(countStatement);
                        ResultSet rs = countStatement.executeQuery();
                        //当结果集中有值时，表示页面数量大于等于1
                        if (rs.next()) {
                            page.setTotalResult(rs.getInt(1));
                        }
                        rs.close();
                        countStatement.close();
                    }
                    String pageSql = generatePageSql(sql, page);
                    //将分页sql语句反射回BoundSql.
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }
        return ivk.proceed();
    }

    public void logSQL(BoundSql boundSql, Log log) {
        if (logSQL != null && "true".equals(logSQL)) {
            String sql = boundSql.getSql().replaceAll("\t", " ").replaceAll("\n", " ").replaceAll("  ", " ");
            StringBuffer sb = new StringBuffer();
            sb.append("SQL: ").append(sql).append(" ");
//        sb.append("Params: ").append(JSONObject.toJSONString(boundSql.getParameterObject()));
            log.warn(sb.toString());
        }

    }

    /**
     * 根据数据库方言，生成特定的分页sql
     * @param sql
     * @param page
     * @return
     */
    private String generatePageSql(String sql, Page page) {
        if (page != null && dialect !=null && !"".equals(dialect)) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getPageResult() + "," + page.getPageSize());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
                pageSql.append(sql);
                //pageSql.append(") as tmp_tb where ROWNUM<=");
                pageSql.append(") tmp_tb where ROWNUM<=");
                pageSql.append(page.getPageResult() + page.getPageSize());
                pageSql.append(") where row_id>");
                pageSql.append(page.getPageResult());
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (dialect == null || "".equals(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if (pageSqlId == null || "".equals(pageSqlId)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }

        logSQL = p.getProperty("logSQL");
    }

}

package com.oliwen.conf;

import com.oliwen.entity.Page;
import com.oliwen.entity.PageData;
import com.oliwen.plugin.PagePlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/21 10:32 AM
 * @description:  数据连接配置
 */
@Configuration
public class DataSourceConfig {

    @Value("${mybatis.dialect}")
    private String dialect;
    @Value("${mybatis.pageSqlId}")
    private String pageSqlId;
    @Value("${mybatis.logSQL}")
    private String logSQL;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "datasource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Autowired
    private MybatisProperties mybatisProperties;

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        bean.setConfiguration(configuration);
        configuration.addInterceptor(getPagePlugin());
        registerAlias(configuration.getTypeAliasRegistry());
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 分页拦截器
     * @return
     */
    public Interceptor getPagePlugin() {
        Interceptor interceptor = new PagePlugin();
        Properties props = new Properties();
        props.setProperty("dialect", dialect);
        props.setProperty("pageSqlId", pageSqlId);
        props.setProperty("logSQL", logSQL);
        interceptor.setProperties(props);
        return interceptor;
    }

    /**
     * 为实体设置别名
     * @param typeAliasRegistry
     */
    public void registerAlias(TypeAliasRegistry typeAliasRegistry) {
        typeAliasRegistry.registerAlias("pd", PageData.class);
        typeAliasRegistry.registerAlias("Page", Page.class);
    }
}
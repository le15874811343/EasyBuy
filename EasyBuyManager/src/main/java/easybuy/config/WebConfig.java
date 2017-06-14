package easybuy.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import easybuy.interceptor.LoginInterceptor;

@Configuration
@EnableTransactionManagement
@ImportResource(value="classpath:/applicationContext.xml")
public class WebConfig {
	
	/**
	 * 数据源配置(连接池)
	 * @return
	 */
	@Bean
	public DataSource dataSource(){
		BasicDataSource basicDataSource = new BasicDataSource();
		// 设置数据库驱动
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		// 设置连接地址
		basicDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		// 设置数据库用户名
		basicDataSource.setUsername("scott");
		// 设置数据库密码
		basicDataSource.setPassword("le353480");
		// 设置连接池创建初始化连接数
		basicDataSource.setInitialSize(20);
		// 设置最大连接数
		basicDataSource.setMaxActive(50);
		// 设置最大空闲连接数
		basicDataSource.setMaxIdle(30);
		// 设置最小空闲连接数
		basicDataSource.setMinIdle(20);
		// 设置最大等待时间
		basicDataSource.setMaxWait(3000);
		return basicDataSource;
	}

	/**
	 * sql处理工厂组件
	 * @return 
	 * @throws Exception 
	 */
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory =  new SqlSessionFactoryBean();
		// 设置数据源
		sessionFactory.setDataSource(dataSource());
		// 设置mybatis配置文件
		sessionFactory.setConfigLocation(new ClassPathResource("Mybatis.cfg.xml"));
		return sessionFactory.getObject();
	}

	/**
	 * sql处理模板组件
	 * @return
	 * @throws Exception 
	 */
	@Bean
	public SqlSessionTemplate sessionTemplate() throws Exception{
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}

	/**
	 * 事务管理器组件
	 * @return
	 */
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager  transactionManager(){
		DataSourceTransactionManager transactionManager =new DataSourceTransactionManager(dataSource());
		return transactionManager;
	}

	@Bean
	public LoginInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}
	@Bean
	public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping(){
		BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = new BeanNameUrlHandlerMapping();
		beanNameUrlHandlerMapping.setInterceptors(new Object[]{loginInterceptor()});
	return beanNameUrlHandlerMapping;
	}
	
	
}

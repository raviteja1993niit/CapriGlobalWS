//package com.cgcl.config;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.transaction.PlatformTransactionManager;
//
////import com.cgcl.controller.CapriGlobalRestController;
//
//@Configuration
////@EnableTransactionManagement
////@EnableJpaRepositories("com.cgcl.repository")
//@PropertySource("classpath:application.properties")
//public class JPAConfig {
//	
//	private static Logger logger = Logger.getLogger(JPAConfig.class.getName());
//
//	@Autowired
//	private Environment env;
//
//	//@Bean(name = "entityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
//		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
//		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
//		lcemfb.setDataSource(getDataSource());
//		lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
//		lcemfb.setPackagesToScan("com.cgcl.*");
//		Properties properties = new Properties();
//		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//		properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));
//		properties.put("hibernate.hbm2ddl.auto", "update");
//		lcemfb.setJpaProperties(properties);
//		return lcemfb;
//	}
//
//	//@Bean
//	public JpaVendorAdapter getJpaVendorAdapter() {
//		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		return adapter;
//	}
////    @Primary
////	@Bean(destroyMethod = "")
////	public DataSource getDataSource() {
////		BasicDataSource dataSource = new BasicDataSource();
////		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class"));
////		dataSource.setUrl(env.getProperty("spring.datasource.url"));
////		dataSource.setUsername(env.getProperty("spring.datasource.username"));
////		dataSource.setPassword(env.getProperty("spring.datasource.password"));
////		return dataSource;
////	}
//
//    @Primary
//   	@Bean(destroyMethod = "")
//    public DataSource getDataSource() {
//		BasicDataSource dataSource=null;
//		try{
//		dataSource = new BasicDataSource();
//	//	DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class"));
//		dataSource.setUrl(env.getProperty("spring.datasource.url"));
//		dataSource.setUsername(env.getProperty("spring.datasource.username"));
//		dataSource.setPassword(env.getProperty("spring.datasource.password"));
//		dataSource.setMaxIdle(Integer.parseInt(env.getProperty("max.idle.connections")));
//		dataSource.setMinIdle(Integer.parseInt(env.getProperty("min.idle.connections")));
//	//	dataSource.setMaxOpenPreparedStatements(Integer.parseInt(env.getProperty("max.open.prepstmt")));
//    //  dataSource.setMaxActive(Integer.parseInt(env.getProperty("max.active")));
//	//	dataSource.setMaxWait(Integer.parseInt(env.getProperty("max.wait")));
//		dataSource.setMaxTotal(Integer.parseInt(env.getProperty("max.active")));
//	//	dataSource.setMaxWaitMillis(Long.parseLong(env.getProperty("max.wait.millis")));
//		dataSource.setInitialSize(Integer.parseInt(env.getProperty("initial.pool.size")));
//	//	dataSource.setMaxConnLifetimeMillis(Integer.parseInt(env.getProperty("max.conn.life.time")));
//	//	dataSource.setCacheState(true);
//		return dataSource;
//		}
//		catch(Exception e)
//		{
//			logger.error("Exception while getting DataSource... "+e.getMessage());
//			throw(e);
//		}
//	}
//	
//    
//	//@Bean(name = "transactionManager")
//	public PlatformTransactionManager txManager() {
//		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
//				getEntityManagerFactoryBean().getObject());
//		return jpaTransactionManager;
//	}
//	
//	
//
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//		return new JdbcTemplate( dataSource);
//	}
//
//	
//	/*
//	 * private Properties jpaProperties() { Properties properties = new
//	 * Properties(); properties.put("hibernate.dialect",
//	 * env.getProperty("hibernate.dialect")); properties.put("hibernate.show_sql",
//	 * env.getProperty("hibernate.show_sql"));
//	 * properties.put("hibernate.format_sql",
//	 * env.getProperty("hibernate.format_sql"));
//	 * properties.put("hibernate.id.new_generator_mappings",
//	 * env.getProperty("hibernate.id.new_generator_mappings"));
//	 * properties.put("hibernate.hbm2ddl.auto", "update"); return properties; }
//	 */
//	
//}

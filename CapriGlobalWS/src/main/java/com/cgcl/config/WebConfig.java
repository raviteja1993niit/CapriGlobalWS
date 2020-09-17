package com.cgcl.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@PropertySource("classpath:application.properties")
public class WebConfig {

	private static Logger logger = Logger.getLogger(WebConfig.class.getName());

	@Autowired
	private Environment env;
	
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource=null;
		try{
		dataSource = new BasicDataSource();
	//	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setMaxIdle(Integer.parseInt(env.getProperty("max.idle.connections")));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("min.idle.connections")));
	//	dataSource.setMaxOpenPreparedStatements(Integer.parseInt(env.getProperty("max.open.prepstmt")));
    //  dataSource.setMaxActive(Integer.parseInt(env.getProperty("max.active")));
	//	dataSource.setMaxWait(Integer.parseInt(env.getProperty("max.wait")));
		dataSource.setMaxTotal(Integer.parseInt(env.getProperty("max.active")));
	//	dataSource.setMaxWaitMillis(Long.parseLong(env.getProperty("max.wait.millis")));
		dataSource.setInitialSize(Integer.parseInt(env.getProperty("initial.pool.size")));
	//	dataSource.setMaxConnLifetimeMillis(Integer.parseInt(env.getProperty("max.conn.life.time")));
	//	dataSource.setCacheState(true);
		return dataSource;
		}
		catch(Exception e)
		{
			logger.error("Exception while getting DataSource... "+e.getMessage());
			throw(e);
		}
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate( dataSource);
	}
}

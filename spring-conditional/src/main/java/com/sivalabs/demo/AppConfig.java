/**
 * 
 */
package com.sivalabs.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Siva
 *
 */
@Configuration
@ComponentScan
public class AppConfig
{
	

	@Bean
	@Conditional(MySQLDatabaseTypeCondition.class)
	public UserDAO jdbcUserDAO(){
		return new JdbcUserDAO();
	}
	
	@Bean
	@Conditional(MongoDBDatabaseTypeCondition.class)
	public UserDAO mongoUserDAO(){
		return new MongoUserDAO();
	}

	/*
	@Bean
	@Conditional(MongoDriverNotPresentsCondition.class)
	public UserDAO jdbcUserDAO(){
		return new JdbcUserDAO();
	}
	
	@Bean
	@Conditional(MongoDriverPresentsCondition.class)
	public UserDAO mongoUserDAO(){
		return new MongoUserDAO();
	}
	*/
	
	/*
	@Bean
	@DatabaseType("MYSQL")
	public UserDAO jdbcUserDAO(){
		return new JdbcUserDAO();
	}
	
	@Bean
	@DatabaseType("MONGO")
	public UserDAO mongoUserDAO(){
		return new MongoUserDAO();
	}
	*/
}

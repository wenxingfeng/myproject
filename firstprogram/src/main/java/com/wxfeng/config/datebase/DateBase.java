package com.wxfeng.config.datebase;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@Scope()
public class DateBase {

	@Autowired Environment en;
	
	@Bean private DataSource uniorderDatesource() {
		// TODO Auto-generated method stub
		DriverManagerDataSource uniorderDatesource = new DriverManagerDataSource();
		uniorderDatesource.setDriverClassName(en.getProperty("jdbc.driverClassName"));
		uniorderDatesource.setUrl(en.getProperty("jdbc.url"));
		uniorderDatesource.setPassword(en.getProperty("jdbc.password"));
		uniorderDatesource.setUsername(en.getProperty("jdbc.username"));
		return uniorderDatesource;
	}
	
	
	
}

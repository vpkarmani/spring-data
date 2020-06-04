package com.vpkarmani.jdbc.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

  @Value("${spring.data.jdbc.driver.class.name}")
  private String driverClassName;

  @Value("${spring.data.jdbc.url}")
  private String url;

  @Value("${spring.data.jdbc.usrename}")
  private String userName;

  @Value("${spring.data.jdbc.password}")
  private String password;

  @Bean
  public JdbcTemplate jdbcTemplate() {
    /* Initialize the datasource */
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    return new JdbcTemplate(dataSource);
  }

}

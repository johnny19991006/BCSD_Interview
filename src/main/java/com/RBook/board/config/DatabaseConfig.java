package com.RBook.board.config;
/*
Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).

의존성 주입 및 application.yml에 CP 설정을 마친 뒤에 서버를 기동하면 위와 같은 에러 발생
이는 H2, HSQL db에 대한 classpath를 추가해달란 것, 이 오류가 뜨는 이유는 hikariCP와 관련된 설정들이 현재 어플리케이션에 적용이 안돼서 생기는 것
현재 어플리에이켠에 적용이 되지 않으면 디폴트 DB인 H2, HSQL로 셋팅이 되고, 실제 application.yml에는 이에 대한 설정값이 없어서 발생하는 에러

 */

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}
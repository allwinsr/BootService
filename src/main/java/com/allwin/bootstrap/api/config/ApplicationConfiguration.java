package com.allwin.bootstrap.api.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.yml")
public class ApplicationConfiguration {

    @Autowired
    private Environment environment;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        /*dataSource.setPassword(readEnvProperty("spring.datasource.password"));
        dataSource.setURL(readEnvProperty("spring.datasource.url"));
        dataSource.setUser(readEnvProperty("spring.datasource.username"));*/

        dataSource.setPassword(password);
        dataSource.setURL(url);
        dataSource.setUser(userName);
        return dataSource;
    }

    private String readEnvProperty(String key) {
        return environment.getRequiredProperty(key);
    }
}

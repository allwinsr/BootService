package com.allwin.bootstrap.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@SpringBootApplication(scanBasePackages = "com.allwin.bootstrap")
@EnableJpaRepositories(basePackages = { "com.allwin.bootstrap.dal" })
@EntityScan("com.allwin.bootstrap.dal.*")
@EnableAspectJAutoProxy
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class Application {

    public static void main(String[] args) {
        System.out.println("God is great!!!");
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
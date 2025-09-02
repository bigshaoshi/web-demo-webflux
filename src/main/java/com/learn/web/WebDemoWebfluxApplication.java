package com.learn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.learn.web")
@EnableR2dbcRepositories(basePackages = "com.learn.web.dao.repository")
public class WebDemoWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDemoWebfluxApplication.class, args);
    }

}

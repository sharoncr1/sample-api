package com.mycom.thirdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@SpringBootApplication
@PropertySources({
    @PropertySource("classpath:${env}.properties"),
    @PropertySource("classpath:base.properties")
})
public class MainClass extends SpringBootServletInitializer {

    public static void main(String[] args){
        System.out.println(System.getenv("env"));
        SpringApplication.run(MainClass.class,args);
    }
}

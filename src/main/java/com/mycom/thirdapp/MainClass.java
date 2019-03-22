package com.mycom.thirdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class MainClass extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(MainClass.class,args);
    }
}

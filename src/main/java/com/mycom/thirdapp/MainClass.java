package com.mycom.thirdapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Configuration
@SpringBootApplication
public class MainClass extends SpringBootServletInitializer {


    public static void main(String[] args){

        Logger logger= LoggerFactory.getLogger(MainClass.class);
        String url="jdbc:mysql://localhost:3306/";
        try {
            Connection con = DriverManager.getConnection( url, "root", "root" );
            Statement s=con.createStatement();
            int result=s.executeUpdate("CREATE DATABASE if not exists db_student");
            logger.info("Create Database result : "+result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        SpringApplication.run(MainClass.class,args);
    }
}

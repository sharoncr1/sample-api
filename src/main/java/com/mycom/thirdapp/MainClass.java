package com.mycom.thirdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Configuration
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class MainClass extends SpringBootServletInitializer {

    public static void main(String[] args) throws ParseException {

        Integer pastDate=2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -pastDate);
        String firstDate = simpleDateFormat.format(calendar.getTime());
        Date fdate=simpleDateFormat.parse(firstDate);
        System.out.println(fdate.toString());
        if(Date.from(Instant.now()).after(fdate)){
            System.out.println("Past Date");
        }
        else {
            System.out.println("Future Date");
        }
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 4);
        String lastDate = simpleDateFormat.format(calendar.getTime());
        Date ldate=simpleDateFormat.parse(lastDate);
        if(Date.from(Instant.now()).after(ldate)){
            System.out.println("Past Date");
        }
        else {
            System.out.println("Future Date");
        }
        System.out.println(ldate.toString());
        System.out.println(firstDate);
        System.out.println(lastDate);

        String name=null;

        if(name==null){
            System.out.println("null");
        }
        else {
            System.out.println("not null");
        }
        SpringApplication.run(MainClass.class,args);
    }

//    @Scheduled(cron="0/2 * * * * ?")
//    public static void runMeForNoUse(){
//        System.out.println("executed");
//    }
}

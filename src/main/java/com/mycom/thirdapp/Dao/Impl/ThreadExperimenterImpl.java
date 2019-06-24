package com.mycom.thirdapp.Dao.Impl;

import com.mycom.thirdapp.Dao.ThreadExperimenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class ThreadExperimenterImpl implements ThreadExperimenter {

    Logger logger= LoggerFactory.getLogger(ThreadExperimenterImpl.class);
    public static int COUNTER=0;

    @Override
    @Async
    public void printRandomly() {
        long randomInterval=0L;
        logger.info(String.valueOf(++COUNTER));
        try {
            randomInterval=(long)(Math.random()*5000);
            Thread.sleep(randomInterval);
            logger.info(String.valueOf(randomInterval));
            logger.info("Executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

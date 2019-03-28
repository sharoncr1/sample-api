package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.db.models.Attendance;
import com.mycom.thirdapp.services.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttendanceController {

    final Logger logger= LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    AttendanceService attendanceService;

    @RequestMapping(method = RequestMethod.POST,value = "/attendance/save")
    public void add(@RequestBody Attendance attendance){
        attendanceService.add(attendance);
    }
    @RequestMapping("/attendance/get/{id}")
    public Attendance get(@PathVariable String id){
        logger.info(id);
        return attendanceService.get(id);
    }
}

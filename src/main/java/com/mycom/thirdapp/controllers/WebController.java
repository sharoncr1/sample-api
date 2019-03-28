package com.mycom.thirdapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycom.thirdapp.services.StudentService;

@Controller
public class WebController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home")
    public String studentDetailsTable() {
        return "studentdetailstable";
    }

    @RequestMapping(value = "/attendance")
    public String addAttendance(){
        return "addattendance";
    }
}

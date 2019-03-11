package com.mycom.thirdapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycom.thirdapp.models.StudentDetailsService;

@Controller
public class WebController {

    @Autowired
    private StudentDetailsService studentDetailsService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/addstudentform")
    public String addstudent() {
        return "addstudent";
    }
    @RequestMapping(value = "/studentdetailstable")
    public String studentDetailsTable() {
        return "studentdetailstable";
    }
}

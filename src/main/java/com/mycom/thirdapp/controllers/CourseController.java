package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.db.models.Course;
import com.mycom.thirdapp.db.models.Teacher;
import com.mycom.thirdapp.db.models.TeachesCourse;
import com.mycom.thirdapp.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    final Logger logger= LoggerFactory.getLogger(CourseController.class);

    @RequestMapping("courses/get/{standard}")
    public List<Course> getAllByStandard(@PathVariable int standard) {
        logger.info("Getting all courses with standard="+standard);
        return courseService.getAllByStanadard(standard);
    }

    @RequestMapping("teacher/get/{courseid}")
    public TeachesCourse findTutorId(@PathVariable String courseid){
        logger.info("finding tutor of course with id "+ courseid);
        return courseService.findTutorId(courseid);
    }

    @RequestMapping("teacher/getname/{teacherid}")
    public Teacher findTeacher(@PathVariable String teacherid){
        logger.info("finding teacher with id "+ teacherid);
        return courseService.findTeacher(teacherid);
    }
}

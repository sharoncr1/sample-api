package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.db.models.Report;
import com.mycom.thirdapp.db.models.Student;
import com.mycom.thirdapp.services.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mycom.thirdapp.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    final Logger logger= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/hello")
    public String welcome(){
        return "Hello World";
    }

    @RequestMapping("/getall")
    public List<Student> getAll(){
        logger.info("Handling /getall end point");
      return studentService.findAll();
    }

    @RequestMapping("/student/standard/{standard}")
    public List<Student> getByStandard(@PathVariable int standard) {
        return studentService.getBystandard(standard);
    }

    @RequestMapping("/get/{studentID}")
    public Student get(@PathVariable String studentID){
        Student searchResult= studentService.find(studentID);
        if(searchResult!=null) {
            logger.info("Returning record with id {}", searchResult.getId());
        }
        else {
            logger.warn("No Search Results");
        }
        return searchResult;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public void add(@RequestBody Student student){
        studentService.add(student);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/update/{studentID}")
    public void update(@RequestBody Student student, @PathVariable String studentID){
        studentService.update(student,studentID);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{studentID}")
    public void delete(@PathVariable String studentID){
        studentService.delete(studentID);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/get/student/{start}/{end}")
    public List<Student> filterByStandard(@PathVariable int start, @PathVariable int end){
        List<Student> filteredResult= studentService.filterByStandard(start,end);
        logger.info("Number of records Returned to the ui :\n"+filteredResult.size());
        return filteredResult;
    }

}

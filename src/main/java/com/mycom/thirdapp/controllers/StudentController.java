package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.student.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mycom.thirdapp.models.StudentDetailsService;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentDetailsService studentDetailsService;

    @RequestMapping("/hello")
    public String welcome(){
        return "Hello World";
    }

    @RequestMapping("/studentdetails")
    public List<StudentDetails> getAllStudentDetails(){
      return studentDetailsService.getAllStudentDetails();
    }

    @RequestMapping("/studentdetails/{studentID}")
    public StudentDetails getStudentDetails(@PathVariable String studentID){
        return studentDetailsService.getStudentDetails(studentID);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addstudent")
    public void addStudentDetails(@RequestBody StudentDetails studentDetails){
        studentDetailsService.addStudentDetails(studentDetails);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updatestudent/{studentID}")
    public void updateStudentDetails(@RequestBody StudentDetails studentDetails, @PathVariable String studentID){
        studentDetailsService.updateStudentDetails(studentDetails,studentID);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/deletestudent/{studentID}")
    public void deleteStudentDetails(@PathVariable String studentID){
        studentDetailsService.deleteStudentDetails(studentID);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/filterstudentsbystandard/{starting}/{ending}")
    public List<StudentDetails> filterStudentsByStandard(@PathVariable int starting,@PathVariable int ending){
        return studentDetailsService.filterStudentsByStandard(starting,ending);
    }

}

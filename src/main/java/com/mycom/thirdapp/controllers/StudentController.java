package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.student.StudentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mycom.thirdapp.models.StudentDetailsService;

import java.util.List;

@RestController
public class StudentController {

    final Logger logger= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentDetailsService studentDetailsService;

    @RequestMapping("/hello")
    public String welcome(){
        return "Hello World";
    }

    @RequestMapping("/studentdetails")
    public List<StudentDetails> getAllStudentDetails(){
        logger.info("Handling /studentdetails end point");
      return studentDetailsService.getAllStudentDetails();
    }

    @RequestMapping("/studentdetails/{studentID}")
    public StudentDetails getStudentDetails(@PathVariable String studentID){
        StudentDetails searchResult=studentDetailsService.getStudentDetails(studentID);
        if(searchResult!=null) {
            logger.info("Returning record with id {}", searchResult.getId());
        }
        else {
            logger.warn("No Search Results");
        }
        return searchResult;
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
        List<StudentDetails> filteredResult=studentDetailsService.filterStudentsByStandard(starting,ending);
        logger.info("Number of records Returned to the ui :\n"+filteredResult.size());
        return filteredResult;
    }

}

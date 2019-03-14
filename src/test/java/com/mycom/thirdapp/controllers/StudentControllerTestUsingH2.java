package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.models.StudentDetailsService;
import com.mycom.thirdapp.student.StudentDetails;
import com.mycom.thirdapp.student.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StudentControllerTestUsingH2 {

    @Autowired
    StudentDetailsService studentDetailsService;

    @Autowired
    StudentRepository studentRepository;

    private StudentDetails getTestStudent(){
        StudentDetails studentDetails=new StudentDetails("name",8,"st001");
        return studentDetails;
    }

    @Test
    public void addStudentAndgetStudentDetailsByIDTest(){
        StudentDetails testDetails=getTestStudent();
        studentDetailsService.addStudentDetails(testDetails);
        StudentDetails getFromDB=studentDetailsService.getStudentDetails("st001");
        assert(getFromDB.getName()).equals("name");
    }
    @Test
    public void getAllstudentDetailsTest(){
        List<StudentDetails> studentDetailsList;
        StudentDetails testDetails=getTestStudent();
        studentDetailsService.addStudentDetails(testDetails);
        studentDetailsList=studentDetailsService.getAllStudentDetails();
        assertThat(studentDetailsList.size()).isEqualTo(1);
    }
    @Test
    public void deleteStudentTest(){
        StudentDetails testDetails=getTestStudent();
        studentDetailsService.addStudentDetails(testDetails);
//        deleting the record with id st001
        studentDetailsService.deleteStudentDetails("st001");
//        tyring to fetch record with id st001
        StudentDetails getFromDB=studentDetailsService.getStudentDetails("st001");
        assertThat(getFromDB).isNull();
    }
    @Test
    public void updateStudentTest(){
        StudentDetails testDetails=getTestStudent();
        studentDetailsService.addStudentDetails(testDetails);
//        updating name in the record with id st001
        studentDetailsService.updateStudentDetails(new StudentDetails("newname",8,"st001"),"st001");
//        fetching the name in the record with id st001 and making sure it's newname

        StudentDetails resultDetails=studentDetailsService.getStudentDetails("st001");
        assertThat(resultDetails.getName()).isEqualTo("newname");
    }

}

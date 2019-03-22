package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.db.models.Student;
import com.mycom.thirdapp.services.StudentService;
import com.mycom.thirdapp.db.repositories.StudentRepository;
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
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    private Student getTestStudent(){
        Student student =new Student("name",8,"st001");
        return student;
    }

    @Test
    public void addAndgetTest(){
        Student testDetails=getTestStudent();
        studentService.add(testDetails);
        Student getFromDB= studentService.find("st001");
        assert(getFromDB.getName()).equals("name");
    }
    @Test
    public void getAllTest(){
        List<Student> studentList;
        Student testDetails=getTestStudent();
        studentService.add(testDetails);
        studentList = studentService.findAll();
        assertThat(studentList.size()).isEqualTo(1);
    }
    @Test
    public void deleteTest(){
        Student testDetails=getTestStudent();
        studentService.add(testDetails);
//        deleting the record with id st001
        studentService.delete("st001");
//        tyring to fetch record with id st001
        Student getFromDB= studentService.find("st001");
        assertThat(getFromDB).isNull();
    }
    @Test
    public void updateTest(){
        Student testDetails=getTestStudent();
        studentService.add(testDetails);
//        updating name in the record with id st001
        studentService.update(new Student("newname",8,"st001"),"st001");
//        fetching the name in the record with id st001 and making sure it's newname

        Student resultDetails= studentService.find("st001");
        assertThat(resultDetails.getName()).isEqualTo("newname");
    }

}

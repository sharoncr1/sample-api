package com.mycom.thirdapp.models;

import com.mycom.thirdapp.controllers.StudentController;
import com.mycom.thirdapp.student.StudentDetails;
import com.mycom.thirdapp.student.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDetailsService {

    @Autowired
    private StudentRepository studentRepository;
    final Logger logger= LoggerFactory.getLogger(StudentDetailsService.class);

    public List<StudentDetails> getAllStudentDetails(){
        List<StudentDetails> studentDetails = new ArrayList<>();
        studentRepository.findAll().forEach(studentDetails::add);
        return studentDetails;
    }

    public StudentDetails getStudentDetails(String studentID){
        return studentRepository.findOne(studentID);
    }

    public void addStudentDetails(StudentDetails sd){
        studentRepository.save(sd);
    }

    public void updateStudentDetails(StudentDetails sd, String studentID) {
//      studentID argument not needed here
        studentRepository.save(sd);
    }

    public void deleteStudentDetails(String studentID) {
        studentRepository.delete(studentID);
    }

    public List<StudentDetails> filterStudentsByStandard(int starting, int ending) {
        List<StudentDetails> studentDetails = new ArrayList<>();
        studentDetails=studentRepository.filterStudentsByStandard(starting,ending);
        return studentDetails;
    }
}

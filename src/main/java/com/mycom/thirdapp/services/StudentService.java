package com.mycom.thirdapp.services;

import com.mycom.thirdapp.db.models.Student;
import com.mycom.thirdapp.db.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    final Logger logger= LoggerFactory.getLogger(StudentService.class);

    public List<Student> findAll(){
        List<Student> studentDetails = new ArrayList<>();
        studentRepository.findAll().forEach(studentDetails::add);
        return studentDetails;
    }

    public Student find(String studentID){
        return studentRepository.findOne(studentID);
    }

    public void add(Student sd){
        studentRepository.save(sd);
    }

    public void update(Student sd, String studentID) {
//      studentID argument not needed here
        studentRepository.save(sd);
    }

    public void delete(String studentID) {
        studentRepository.delete(studentID);
    }

    public List<Student> filterByStandard(int starting, int ending) {
        List<Student> studentDetails = new ArrayList<>();
        studentDetails=studentRepository.filterByStandard(starting,ending);
        return studentDetails;
    }

    public List<Student> getBystandard(int standard) {
        List<Student> studentDetails = new ArrayList<>();
        studentDetails=studentRepository.getByStandard(standard);
        return studentDetails;
    }
}

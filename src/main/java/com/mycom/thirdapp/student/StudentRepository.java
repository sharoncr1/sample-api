package com.mycom.thirdapp.student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentDetails,String> {


    @Query(
            value = "SELECT * FROM student_details WHERE student_standard BETWEEN ?1 AND ?2 ORDER BY  student_standard ASC",
            nativeQuery = true)
    List<StudentDetails> filterStudentsByStandard(int starting, int ending);
}

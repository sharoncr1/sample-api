package com.mycom.thirdapp.db.repositories;

import com.mycom.thirdapp.db.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StudentRepository extends CrudRepository<Student,String> {


    @Query(
            value = "SELECT * FROM student WHERE standard BETWEEN ?1 AND ?2 ORDER BY standard ASC",
            nativeQuery = true)
    List<Student> filterByStandard(int starting, int ending);
}

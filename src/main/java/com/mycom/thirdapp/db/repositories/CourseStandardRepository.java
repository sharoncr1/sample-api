package com.mycom.thirdapp.db.repositories;

import com.mycom.thirdapp.db.models.CourseStandard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CourseStandardRepository extends CrudRepository <CourseStandard,Integer>{

    @Query(value = "SELECT * FROM coursestandard WHERE standard=?1",
            nativeQuery = true)
    List<CourseStandard> findCoursesOfStandard(int standard);
}

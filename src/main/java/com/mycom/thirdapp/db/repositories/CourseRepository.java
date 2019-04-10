package com.mycom.thirdapp.db.repositories;

import com.mycom.thirdapp.db.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,String>{
}

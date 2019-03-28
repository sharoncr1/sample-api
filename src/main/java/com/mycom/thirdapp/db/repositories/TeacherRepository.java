package com.mycom.thirdapp.db.repositories;

import com.mycom.thirdapp.db.models.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,String> {
}

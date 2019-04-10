package com.mycom.thirdapp.db.repositories;

import com.mycom.thirdapp.db.models.Attendance;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance,String> {
}

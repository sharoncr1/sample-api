package com.mycom.thirdapp.services;

import com.mycom.thirdapp.db.models.Attendance;
import com.mycom.thirdapp.db.models.Course;
import com.mycom.thirdapp.db.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    public static final int REQUIRED_AVERAGE_ATTENDACE=75;

    @Autowired
    AttendanceRepository attendanceRepository;

    public void add(Attendance attendance) {
        float average=(attendance.getCourse1()+attendance.getCourse2()+attendance.getCourse3())/3;
        String eligibility;
        if(average<REQUIRED_AVERAGE_ATTENDACE){
            eligibility="Not Eligible";
        }
        else{
            eligibility="Eligible";
        }
        attendance.setAverage(average);
        attendance.setEligibility(eligibility);
        attendanceRepository.save(attendance);
    }

    public Attendance get(String id) {
        return attendanceRepository.findOne(id);
    }
}

package com.mycom.thirdapp.services;

import com.mycom.thirdapp.db.models.Attendance;
import com.mycom.thirdapp.db.models.Report;
import com.mycom.thirdapp.db.repositories.AttendanceRepository;
import com.mycom.thirdapp.db.repositories.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    public static final int REQUIRED_AVERAGE_ATTENDANCE =75;

    final Logger logger= LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    ReportRepository reportRepository;

    @CachePut(value = "attendance", key = "#attendance.studentid", unless = "#result.average < "+ REQUIRED_AVERAGE_ATTENDANCE)
    public Attendance add(Attendance attendance) {
        float average=(float)((attendance.getCourse1()+attendance.getCourse2()+attendance.getCourse3())/3);
        String eligibility;
        if(average< REQUIRED_AVERAGE_ATTENDANCE){
            eligibility="Not Eligible";
        }
        else{
            eligibility="Eligible";
        }
        attendance.setAverage(average);
        attendance.setEligibility(eligibility);
        logger.info("id being saved ="+attendance.getStudentid());
        attendanceRepository.save(attendance);
        return attendance;
    }

    @Cacheable(value = "attendance1" , key="#id")
    public Attendance get(String id) {
        logger.info("Without using cache");
        return attendanceRepository.findOne(id);
    }

    @Caching(
            evict= {
                    @CacheEvict(value= "attendance", key= "#studentid", condition = "!#isDelete")
            })
    public List<Report> generateReport(String studentid, boolean isDelete){
        List<Report> reportList=new ArrayList<>();
        reportList.add(reportRepository.generateCourse1Report(studentid));
        reportList.add(reportRepository.generateCourse2Report(studentid));
        reportList.add(reportRepository.generateCourse3Report(studentid));
        return reportList;
    }
    public boolean checkOddOrEven( Integer num){
        if(num%2==0) return true;
        else return false;
    }


}

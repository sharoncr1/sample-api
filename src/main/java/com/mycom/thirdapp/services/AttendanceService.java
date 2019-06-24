package com.mycom.thirdapp.services;

import com.mycom.thirdapp.Dao.ThreadExperimenter;
import com.mycom.thirdapp.db.models.Attendance;
import com.mycom.thirdapp.db.models.Report;
import com.mycom.thirdapp.db.repositories.AttendanceRepository;
import com.mycom.thirdapp.db.repositories.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    public void add(Attendance attendance) {
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
    }

    public Attendance get(String id) {
        return attendanceRepository.findOne(id);
    }
    public List<Report> generateReport(String studentid){
        List<Report> reportList=new ArrayList<>();
        reportList.add(reportRepository.generateCourse1Report(studentid));
        reportList.add(reportRepository.generateCourse2Report(studentid));
        reportList.add(reportRepository.generateCourse3Report(studentid));
        return reportList;
    }


    @Autowired
    public ThreadExperimenter threadExperimenter;

    @Scheduled(fixedDelay = 1000)
    public void runMe() throws InterruptedException {
        threadExperimenter.printRandomly();
    }
}

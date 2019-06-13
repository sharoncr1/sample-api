package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.db.models.Attendance;
import com.mycom.thirdapp.db.repositories.AttendanceRepository;
import com.mycom.thirdapp.services.AttendanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AttendanceControllerTest {


    @Autowired
    AttendanceService attendanceService;

    @Autowired
    AttendanceRepository attendanceRepository;

    public final String TST_OBJ_studentid="testid";
    public final float TST_OBJ_course1=73;
    public final float TST_OBJ_course2=78;
    public final float TST_OBJ_course3=70;
    public final float TST_OBJ_average=(TST_OBJ_course1+TST_OBJ_course2+TST_OBJ_course3)/3;

    public Attendance createTestAttendanceRecord(){
        Attendance testObject=new Attendance(TST_OBJ_studentid,TST_OBJ_course1,TST_OBJ_course2,TST_OBJ_course3,0,"");
        testObject.setStudentid("testid");
        testObject.setCourse1(73);

        return testObject;
    }

    @Test
    public void addTest() {
        Attendance testObj=createTestAttendanceRecord();
        attendanceService.add(testObj);
        Attendance fromDB=attendanceService.get("testid");
        assert(fromDB.getStudentid().equals("testid"));
    }

//    testing the average value being calculated by AttendanceService.add
    @Test
    public void averageAttendanceValueTest(){
        attendanceService.add(createTestAttendanceRecord());
        Attendance fromDB=attendanceService.get("testid");
        assertEquals(fromDB.getAverage(),TST_OBJ_average,0);
    }

//    testing the eligibility value
    @Test
    public void eligibilityValueTest(){
        attendanceService.add(createTestAttendanceRecord());
        Attendance fromDB=attendanceService.get("testid");
        if(TST_OBJ_average<AttendanceService.REQUIRED_AVERAGE_ATTENDANCE) {
            assert (fromDB.getEligibility().equals("Not Eligible"));
        }
        else{
            assert (fromDB.getEligibility().equals("Eligible"));
        }
    }
}
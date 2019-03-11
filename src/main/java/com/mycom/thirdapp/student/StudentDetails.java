package com.mycom.thirdapp.student;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentDetails {
    private String studentName;
    private int studentStandard;
    @Id
    private String studentID;


    public StudentDetails(String studentName, int studentStandard, String studentID) {
        this.studentName = studentName;
        this.studentStandard = studentStandard;
        this.studentID = studentID;
    }

    public StudentDetails() {

    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentStandard() {
        return studentStandard;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentStandard(int studentStandard) {
        this.studentStandard = studentStandard;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}

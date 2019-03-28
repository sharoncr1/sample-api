package com.mycom.thirdapp.db.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Attendance")
public class Attendance {

    @Id
    private String studentid;

    @Column(precision=5, scale=2)
    private int course1;

    @Column(precision=5, scale=2)
    private int course2;

    @Column(precision=5, scale=2)
    private int course3;

    @Column(precision=5, scale=2)
    private float average;

    @Column(name="eligibility")
    @Size(max = 15)
    private String eligibility;

    public Attendance() {
    }

    public Attendance(String studentid, int course1, int course2, int course3, int average, String eligibility) {
        this.studentid = studentid;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.average = average;
        this.eligibility = eligibility;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public int getCourse1() {
        return course1;
    }

    public void setCourse1(int course1) {
        this.course1 = course1;
    }

    public int getCourse2() {
        return course2;
    }

    public void setCourse2(int course2) {
        this.course2 = course2;
    }

    public int getCourse3() {
        return course3;
    }

    public void setCourse3(int course3) {
        this.course3 = course3;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }
}

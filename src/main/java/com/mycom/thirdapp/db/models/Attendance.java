package com.mycom.thirdapp.db.models;


//import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="Attendance")
//@RedisHash("Attendance")
public class Attendance implements Serializable {

    @Id
    private String studentid;

    @Column(precision=5, scale=2)
    private float course1;

    @Column(precision=5, scale=2)
    private float course2;

    @Column(precision=5, scale=2)
    private float course3;

    @Column(precision=5, scale=2)
    private float average;

    @Column(name="eligibility")
    @Size(max = 15)
    private String eligibility;

    public Attendance() {
    }

    public Attendance(String studentid, float course1, float course2, float course3, float average, String eligibility) {
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

    public float getCourse1() {
        return course1;
    }

    public void setCourse1(float course1) {
        this.course1 = course1;
    }

    public float getCourse2() {
        return course2;
    }

    public void setCourse2(float course2) {
        this.course2 = course2;
    }

    public float getCourse3() {
        return course3;
    }

    public void setCourse3(float course3) {
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

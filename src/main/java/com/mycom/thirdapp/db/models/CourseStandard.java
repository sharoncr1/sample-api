package com.mycom.thirdapp.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "coursestandard")
public class CourseStandard {

    @Id
    @Column(name = "standard")
    int standard;

    @Column(name="course1")
    @Size(max = 3)
    private String course1;

    @Column(name="course2")
    @Size(max = 3)
    private String course2;

    @Column(name="course3")
    @Size(max = 3)
    private String course3;

    public CourseStandard() {
    }

    public CourseStandard(int standard, String course1, String course2, String course3) {
        this.standard = standard;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public String getCourse3() {
        return course3;
    }

    public void setCourse3(String course3) {
        this.course3 = course3;
    }
}

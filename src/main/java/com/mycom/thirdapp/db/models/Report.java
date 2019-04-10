package com.mycom.thirdapp.db.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {
    @Id
    String courseid;
    String coursetitle;
    String tutorname;
    float attendance;

    public Report() {
    }

    public Report(String courseid, String coursetitle, String tutorname, float attendance) {
        this.courseid = courseid;
        this.coursetitle = coursetitle;
        this.tutorname = tutorname;
        this.attendance = attendance;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }

    public String getTutorname() {
        return tutorname;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public float getAttendance() {
        return attendance;
    }

    public void setAttendance(float attendance) {
        this.attendance = attendance;
    }
}

package com.mycom.thirdapp.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="teachescourse")
public class TeachesCourse {

    @Id
    @NotNull
    @Column(name="courseid")
    @Size(max = 3)
    private String courseid;

    @NotNull
    @Column(name="teacherid")
    @Size(max = 3)
    private String teacherid;


    public TeachesCourse(String courseid, String teacherid) {
        this.courseid = courseid;
        this.teacherid = teacherid;
    }

    public TeachesCourse(){
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }
}

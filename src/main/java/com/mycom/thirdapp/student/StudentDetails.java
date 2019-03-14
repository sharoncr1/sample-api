package com.mycom.thirdapp.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_details")
public class StudentDetails {
    @Column(name="name")
    private String name;

    @Column(name="standard")
    private int standard;

    @Column(name="id")
    @Id
    private String id;


    public StudentDetails(String name, int standard, String id) {
        this.name = name;
        this.standard = standard;
        this.id = id;
    }

    public StudentDetails() {

    }

    public String getName() {
        return name;
    }

    public int getStandard() {
        return standard;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public void setId(String id) {
        this.id = id;
    }
}

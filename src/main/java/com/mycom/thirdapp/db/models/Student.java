package com.mycom.thirdapp.db.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="student")
public class Student {

    @Size(max = 50)
    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="standard")
    @NotNull
    private int standard;

    @Id
    @Column(name="id")
    @Size(max = 5)
    private String id;


    public Student(String name, int standard, String id) {
        this.name = name;
        this.standard = standard;
        this.id = id;
    }

    public Student() {

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
